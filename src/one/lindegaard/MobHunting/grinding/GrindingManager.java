package one.lindegaard.MobHunting.grinding;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldUnloadEvent;

import one.lindegaard.CustomItemsLib.Tools;
import one.lindegaard.CustomItemsLib.mobs.MobType;
import one.lindegaard.MobHunting.MobHunting;
import one.lindegaard.MobHunting.mobs.ExtendedMob;

public class GrindingManager implements Listener {

	private MobHunting plugin;

	private boolean saveWhitelist = false;
	private boolean saveBlacklist = false;

	private static HashMap<UUID, LinkedList<Area>> mBlacklistedAreas = new HashMap<>();
	private static HashMap<UUID, LinkedList<Area>> mWhitelistedAreas = new HashMap<>();
	private static LinkedHashMap<Integer, GrindingInformation> killed_mobs = new LinkedHashMap<>();

	public GrindingManager(MobHunting instance) {
		this.plugin = instance;
		if (!loadWhitelist(instance))
			throw new RuntimeException();
		if (!loadBlacklist(instance))
			throw new RuntimeException();
		Bukkit.getPluginManager().registerEvents(this, instance);
	}

	public void saveData() {
		if (saveWhitelist) {
			plugin.getMessages().debug("Saving whitelisted areas to disk.");
			saveWhitelist();
		}
		if (saveBlacklist) {
			plugin.getMessages().debug("Saving blacklisted areas to disk.");
			saveBlacklist();
		}
	}

	/**
	 * Register a kill for later inspection. Farming can be caught because most
	 * farms kills the mobs by letting them fall down from a high place.
	 * 
	 * @param killed
	 */
	public void registerDeath(LivingEntity killer, LivingEntity killed) {
		GrindingInformation grindingInformation = new GrindingInformation(killer != null ? killer.getUniqueId() : null,
				killed);
		if (!isGrindingArea(killed.getLocation()) && !isWhitelisted(killed.getLocation())) {
			killed_mobs.put(killed.getEntityId(), grindingInformation);
		}
	}

	/**
	 * Test if the same type of mobs is killed to fast. When players make a farm,
	 * they tend to kill the mobs to fast.
	 * 
	 * @param killed
	 * @return
	 */
	public boolean isPlayerSpeedGrinding(LivingEntity killer, LivingEntity killed) {
		long starttime = System.currentTimeMillis();
		Iterator<Entry<Integer, GrindingInformation>> itr = killed_mobs.entrySet().iterator();
		List<Integer> to_be_deleted = new ArrayList<Integer>();
		int n = 0;
		long timeframe = 0;
		long avg_time = 0;
		long oldestKill = starttime;
		ExtendedMob mob = plugin.getExtendedMobManager().getExtendedMobFromEntity(killed);
		while (itr.hasNext()) {
			GrindingInformation gi = itr.next().getValue();
			if (killer == null) {
				return false;
			}
			if (gi == null || gi.getKillerUUID() == null) {
				continue;
			}

			if (!killer.getUniqueId().equals(gi.getKillerUUID()))
				continue;

			if (starttime > gi.getTimeOfDeath() + (1000L * plugin.getConfigManager().speedGrindingTimeFrame)) {
				// delete after x min
				to_be_deleted.add(gi.getEntityId());
				continue;
			}

			n++; // No of killed mobs
			oldestKill = Math.min(oldestKill, gi.getTimeOfDeath());
		}
		timeframe = (starttime - oldestKill) / 1000L;
		avg_time = timeframe / (long) n; // sec.
		for (int i : to_be_deleted) {
			killed_mobs.remove(i);
		}
		plugin.getMessages().debug(
				"%s has killed %s %s in %s seconds. Avg.kill time %s must greater than %s when %s mobs is killed.",
				killer.getName(), n, mob.getMobName(), timeframe, avg_time,
				plugin.getConfigManager().speedGrindingTimeFrame / plugin.getConfigManager().speedGrindingNoOfMobs,
				plugin.getConfigManager().speedGrindingNoOfMobs);
		if (avg_time != 0 && n >= plugin.getConfigManager().speedGrindingNoOfMobs
				&& avg_time < plugin.getConfigManager().speedGrindingTimeFrame
						/ plugin.getConfigManager().speedGrindingNoOfMobs) {
			return true;
		} else
			return false;
	}

	/**
	 * Test if the killed mob is killed in a NetherGoldXPFarm
	 * 
	 * @param killed
	 * @param silent
	 * @return true if the location is detected as a NetherGoldXPFarm, or if the
	 *         area is detected as a Grinding Area
	 */
	public boolean isNetherGoldXPFarm(LivingEntity killed, boolean silent) {
		int n = 0;
		long now = System.currentTimeMillis();
		final long seconds = plugin.getConfigManager().secondsToSearchForGrinding;
		final double killRadius = plugin.getConfigManager().rangeToSearchForGrinding;
		final int numberOfDeaths = plugin.getConfigManager().numberOfDeathsWhenSearchingForGringding;
		if (MobType.getMobType(killed) == MobType.ZombiePigman) {
			if (killed.getLastDamageCause().getCause() == DamageCause.FALL) {
				Area detectedGrindingArea = getGrindingArea(killed.getLocation());
				if (detectedGrindingArea == null) {
					Iterator<Entry<Integer, GrindingInformation>> itr = killed_mobs.entrySet().iterator();
					while (itr.hasNext()) {
						GrindingInformation gi = itr.next().getValue();

						if (!killed.getWorld().equals(gi.getKilled().getWorld()))
							continue;

						// if (killed.getType() == EntityType.ZOMBIFIED_PIGLIN &&
						// gi.getKilled().getType() == killed.getType()
						// && gi.getKilled().getEntityId() != killed.getEntityId()) {
						if (MobType.getMobType(gi.getKilled()) == MobType.ZombiePigman
								&& gi.getKilled().getEntityId() != killed.getEntityId()) {
							if (n < numberOfDeaths) {
								if (now < gi.getTimeOfDeath() + seconds * 1000L) {
									if (killed.getLocation().distance(gi.getKilled().getLocation()) < killRadius) {
										n++;
										// plugin.getMessages().debug("This was
										// not a Nether
										// Gold XP Farm (%s sec.)",
										// new Date(now -
										// gi.getTimeOfDeath()).getSeconds());
									}
								} else {
									// plugin.getMessages().debug("Removing old
									// kill.
									// (Killed %s seconds ago).",
									// Math.round((now - gi.getTimeOfDeath()) /
									// 1000L));
									itr.remove();
								}
							} else {
								Area area = new Area(killed.getLocation(), killRadius, numberOfDeaths);
								plugin.getMessages().debug("New Nether Gold XP Farm detected at (%s,%s,%s,%s)",
										area.getCenter().getWorld().getName(), area.getCenter().getBlockX(),
										area.getCenter().getBlockY(), area.getCenter().getBlockZ());
								registerKnownGrindingSpot(area);
								return true;
							}
						}
					}
				} else {
					if (!silent)
						plugin.getMessages().debug("This is a known grinding area: (%s,%s,%s,%s)",
								detectedGrindingArea.getCenter().getWorld().getName(),
								detectedGrindingArea.getCenter().getBlockX(),
								detectedGrindingArea.getCenter().getBlockY(),
								detectedGrindingArea.getCenter().getBlockZ());
					return true;
				}
			}
		}
		if (!silent)
			plugin.getMessages().debug(
					"Farm detection: This was not a Nether Gold XP Farm (%s of %s mobs with last %s sec.)", n,
					numberOfDeaths, seconds);
		return false;
	}

	/**
	 * Test if the killed mob is killed in a EndermanFarm
	 * 
	 * @param killed
	 * @param silent
	 * @return true if the locatnewAreaion is detected as a EndermanFarm, or if the
	 *         area is detected as a Grinding Area
	 */
	public boolean isEndermanFarm(LivingEntity killed, boolean silent) {
		int n = 0;
		long now = System.currentTimeMillis();
		final long seconds = plugin.getConfigManager().secondsToSearchForGrindingOnEndermanFarms;
		final double killRadius = plugin.getConfigManager().rangeToSearchForGrindingOnEndermanFarms;
		final int numberOfDeaths = plugin.getConfigManager().numberOfDeathsWhenSearchingForGringdingOnEndermanFarms;
		if (MobType.getMobType(killed) == MobType.Enderman) {
			if (killed.getLastDamageCause().getCause() == DamageCause.VOID) {
				Area detectedGrindingArea = getGrindingArea(killed.getLocation());
				if (detectedGrindingArea == null) {
					Iterator<Entry<Integer, GrindingInformation>> itr = killed_mobs.entrySet().iterator();
					while (itr.hasNext()) {
						GrindingInformation gi = itr.next().getValue();

						if (!killed.getWorld().equals(gi.getKilled().getWorld()))
							continue;

						if (killed.getType() == EntityType.ENDERMAN && gi.getKilled().getType() == killed.getType()
								&& gi.getKilled().getEntityId() != killed.getEntityId()) {
							if (n < numberOfDeaths) {
								if (now < gi.getTimeOfDeath() + seconds * 1000L) {
									// if (killed.getLocation().clone().subtract(0, killed.getLocation().getY() +
									// 65, 0)
									// .distance(gi.getKilled().getLocation().clone().subtract(0,
									// gi.getKilled().getLocation().getY() + 65, 0)) < killRadius) {
									// n++;
									// }
									if (killed.getLocation().distance(gi.getKilled().getLocation()) < killRadius) {
										n++;
									}
								} else {
									// Removing old kill.
									itr.remove();
								}
							} else {
								Area area = new Area(killed.getLocation(), killRadius, numberOfDeaths);
								plugin.getMessages().debug("New Enderman Farm detected at (%s,%s,%s,%s)",
										area.getCenter().getWorld().getName(), area.getCenter().getBlockX(),
										area.getCenter().getBlockY(), area.getCenter().getBlockZ());
								registerKnownGrindingSpot(area);
								return true;
							}
						} // This was not an Enderman:
					}
				} else {
					if (!silent)
						plugin.getMessages().debug("This is a known Enderman Farm area: (%s,%s,%s,%s)",
								detectedGrindingArea.getCenter().getWorld().getName(),
								detectedGrindingArea.getCenter().getBlockX(),
								detectedGrindingArea.getCenter().getBlockY(),
								detectedGrindingArea.getCenter().getBlockZ());
					return true;
				}
			}
		}
		if (!silent)
			plugin.getMessages().debug(
					"Farm detection: This was not an Enderman Farm (%s of %s mobs with last %s sec.) at (%s,%s,%s,%s)",
					n, numberOfDeaths, seconds, killed.getWorld(), killed.getLocation().getX(),
					killed.getLocation().getY(), killed.getLocation().getZ());
		return false;
	}

	public boolean isOtherFarm(LivingEntity killed, boolean silent) {
		int n = 0;
		long now = System.currentTimeMillis();
		final long seconds = plugin.getConfigManager().secondsToSearchForGrindingOnOtherFarms;
		final double killRadius = plugin.getConfigManager().rangeToSearchForGrindingOnOtherFarms;
		final int numberOfDeaths = plugin.getConfigManager().numberOfDeathsWhenSearchingForGringdingOnOtherFarms;
		if (MobType.getMobType(killed) == MobType.ZombiePigman) {
			if (killed.getLastDamageCause().getCause() == DamageCause.FALL) {
				Area detectedGrindingArea = getGrindingArea(killed.getLocation());
				if (detectedGrindingArea == null) {
					Iterator<Entry<Integer, GrindingInformation>> itr = killed_mobs.entrySet().iterator();
					while (itr.hasNext()) {
						GrindingInformation gi = itr.next().getValue();

						if (!killed.getWorld().equals(gi.getKilled().getWorld()))
							continue;

						if (gi.getKilled().getEntityId() != killed.getEntityId()) {
							if (n < numberOfDeaths) {
								if (now < gi.getTimeOfDeath() + seconds * 1000L) {
									if (killed.getLocation().distance(gi.getKilled().getLocation()) < killRadius) {
										n++;
									}
								} else {
									// Removing old kill.
									itr.remove();
								}
							} else {
								Area area = new Area(killed.getLocation(), killRadius, numberOfDeaths);
								if (!silent)
									plugin.getMessages().debug("New Generic / Other Farm detected at (%s,%s,%s,%s)",
											area.getCenter().getWorld().getName(), area.getCenter().getBlockX(),
											area.getCenter().getBlockY(), area.getCenter().getBlockZ());
								registerKnownGrindingSpot(area);
								return true;
							}
						}
					}
				} else {
					if (!silent)
						plugin.getMessages().debug("This is a known grinding area: (%s,%s,%s,%s)",
								detectedGrindingArea.getCenter().getWorld().getName(),
								detectedGrindingArea.getCenter().getBlockX(),
								detectedGrindingArea.getCenter().getBlockY(),
								detectedGrindingArea.getCenter().getBlockZ());
					return true;
				}
			}
		}
		if (!silent)
			plugin.getMessages().debug(
					"Farm detection: This was not an genric / other Farm (%s of %s mobs with last %s sec.) at (%s,%s,%s,%s)",
					n, numberOfDeaths, seconds, killed.getWorld(), killed.getLocation().getX(),
					killed.getLocation().getY(), killed.getLocation().getZ());
		return false;
	}

	// ****************************************************************
	// Events
	// ****************************************************************
	@EventHandler
	private void onWorldLoad(WorldLoadEvent event) {
		List<Area> areas = getWhitelistedAreas(event.getWorld());
		if (areas != null) {
			for (Area area : areas)
				area.getCenter().setWorld(event.getWorld());
		}
	}

	@EventHandler
	private void onWorldUnLoad(WorldUnloadEvent event) {
		List<Area> areas = getWhitelistedAreas(event.getWorld());
		if (areas != null) {
			for (Area area : areas)
				area.getCenter().setWorld(null);
		}
	}

	// ****************************************************************
	// Blacklist
	// ****************************************************************
	public LinkedList<Area> getKnownGrindingSpots(Location loc) {
		if (mBlacklistedAreas.containsKey(loc.getWorld().getUID()))
			return mBlacklistedAreas.get(loc.getWorld().getUID());
		else
			return new LinkedList<Area>();
	}

	public void addKnownGrindingSpot(Area area) {
		LinkedList<Area> list = getKnownGrindingSpots(area.getCenter());
		list.add(area);
		mBlacklistedAreas.put(area.getCenter().getWorld().getUID(), list);
		saveBlacklist = true;
	}

	private boolean saveBlacklist() {
		YamlConfiguration blacklist = new YamlConfiguration();
		File file = new File(MobHunting.getInstance().getDataFolder(), "blacklist.yml");

		for (Entry<UUID, LinkedList<Area>> entry : mBlacklistedAreas.entrySet()) {
			ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
			for (Area area : entry.getValue()) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("Center", Tools.toMap(area.getCenter()));
				map.put("Radius", area.getRange());
				map.put("Counter", area.getCounter());
				map.put("Time", area.getTime());
				list.add(map);
			}
			blacklist.set(entry.getKey().toString(), list);
		}

		try {
			blacklist.save(file);
			saveBlacklist = false;
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	private boolean loadBlacklist(MobHunting instance) {
		YamlConfiguration blacklist = new YamlConfiguration();
		File file = new File(instance.getDataFolder(), "blacklist.yml");

		if (!file.exists())
			return true;

		try {
			blacklist.load(file);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
			return false;
		}

		mBlacklistedAreas.clear();

		for (String worldId : blacklist.getKeys(false)) {
			UUID world = UUID.fromString(worldId);
			List<Map<String, Object>> list = (List<Map<String, Object>>) blacklist.getList(worldId);
			LinkedList<Area> areas = new LinkedList<Area>();

			if (list == null)
				continue;

			for (Map<String, Object> map : list) {
				Area area = new Area(Tools.fromMap((Map<String, Object>) map.get("Center")), (Double) map.get("Radius"),
						(int) map.getOrDefault("Counter", 0),
						(long) map.getOrDefault("Time", System.currentTimeMillis()));
				areas.add(area);
			}

			for (World w : Bukkit.getWorlds()) {
				if (w.getUID().equals(world)) {
					mBlacklistedAreas.put(world, areas);
					break;
				}
			}

		}

		return true;
	}

	public void registerKnownGrindingSpot(Area newArea) {
		for (Area area : getKnownGrindingSpots(newArea.getCenter())) {
			if (newArea.getCenter().getWorld().equals(area.getCenter().getWorld())) {
				double dist = newArea.getCenter().distance(area.getCenter());

				double remaining = dist;
				remaining -= area.getRange();
				remaining -= newArea.getRange();

				if (remaining < 0) {
					if (dist > area.getRange())
						area.setRange(dist);

					area.setCounter(newArea.getCounter() + 1);

					return;
				}
			}
		}

		addKnownGrindingSpot(newArea);
	}

	public Area getGrindingArea(Location location) {
		LinkedList<Area> areas = getKnownGrindingSpots(location);
		for (Area area : areas) {
			if (area.getCenter().getWorld().equals(location.getWorld())) {
				if (area.getCenter().distance(location) < area.getRange()) {
					return area;
				}
			}
		}

		return null;
	}

	public boolean isGrindingArea(Location location) {
		if (location != null) {
			LinkedList<Area> areas = getKnownGrindingSpots(location);
			for (Area area : areas) {
				if (area.getCenter().getWorld() != null && area.getCenter().getWorld().equals(location.getWorld())) {
					if (area.getCenter().distance(location) < area.getRange()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public void clearGrindingArea(Location location) {
		Iterator<Area> it = getKnownGrindingSpots(location).iterator();
		while (it.hasNext()) {
			Area area = it.next();

			if (area.getCenter().getWorld() == null || area.getCenter().getWorld().equals(location.getWorld())) {
				if (area.getCenter().distance(location) < area.getRange()) {
					it.remove();
				}
			}
		}
	}

	public void blacklistArea(Area newArea) {
		LinkedList<Area> areas = mBlacklistedAreas.get(newArea.getCenter().getWorld().getUID());
		if (areas == null) {
			areas = new LinkedList<Area>();
			mBlacklistedAreas.put(newArea.getCenter().getWorld().getUID(), areas);
		}

		for (Area area : areas) {
			if (newArea.getCenter().getWorld().equals(area.getCenter().getWorld())) {
				double dist = newArea.getCenter().distance(area.getCenter());

				double remaining = dist;
				remaining -= area.getRange();
				remaining -= newArea.getRange();

				if (remaining < 0) {
					if (dist > area.getRange())
						area.setRange(dist);

					area.setCounter(newArea.getCounter() + 1);

					return;
				}
			}
		}
		areas.add(newArea);
		mBlacklistedAreas.put(newArea.getCenter().getWorld().getUID(), areas);
		saveBlacklist = true;
	}

	public void unBlacklistArea(Location location) {
		LinkedList<Area> areas = mBlacklistedAreas.get(location.getWorld().getUID());

		if (areas == null)
			return;

		Iterator<Area> it = areas.iterator();
		while (it.hasNext()) {
			Area area = it.next();

			if (area.getCenter().getWorld().equals(location.getWorld())) {
				if (area.getCenter().distance(location) < area.getRange()) {
					it.remove();
				}
			}
		}
		if (areas.isEmpty())
			mBlacklistedAreas.remove(location.getWorld().getUID());
		else
			mBlacklistedAreas.put(location.getWorld().getUID(), areas);
		saveBlacklist = true;
	}

	// ****************************************************************
	// Whitelisted Areas
	// ****************************************************************

	public LinkedList<Area> getWhitelistedAreas(World world) {
		if (mWhitelistedAreas.containsKey(world.getUID()))
			return mWhitelistedAreas.get(world.getUID());
		else
			return new LinkedList<Area>();
	}

	private boolean saveWhitelist() {
		YamlConfiguration whitelist = new YamlConfiguration();
		File file = new File(MobHunting.getInstance().getDataFolder(), "whitelist.yml");

		for (Entry<UUID, LinkedList<Area>> entry : mWhitelistedAreas.entrySet()) {
			ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
			for (Area area : entry.getValue()) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("Center", Tools.toMap(area.getCenter()));
				map.put("Radius", area.getRange());
				map.put("Counter", area.getCounter());
				map.put("Time", area.getTime());
				list.add(map);
			}
			whitelist.set(entry.getKey().toString(), list);
		}

		try {
			whitelist.save(file);
			saveWhitelist = false;
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	private boolean loadWhitelist(MobHunting instance) {
		YamlConfiguration whitelist = new YamlConfiguration();
		File file = new File(instance.getDataFolder(), "whitelist.yml");

		if (!file.exists())
			return true;

		try {
			whitelist.load(file);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
			return false;
		}

		mWhitelistedAreas.clear();

		for (String worldId : whitelist.getKeys(false)) {
			UUID world = UUID.fromString(worldId);
			List<Map<String, Object>> list = (List<Map<String, Object>>) whitelist.getList(worldId);
			LinkedList<Area> areas = new LinkedList<Area>();

			if (list == null)
				continue;

			for (Map<String, Object> map : list) {
				Area area = new Area(Tools.fromMap((Map<String, Object>) map.get("Center")), (Double) map.get("Radius"),
						(int) map.getOrDefault("Counter", 0),
						(long) map.getOrDefault("Time", System.currentTimeMillis()));
				areas.add(area);
			}

			for (World w : Bukkit.getWorlds()) {
				if (w.getUID().equals(world)) {
					mWhitelistedAreas.put(world, areas);
					break;
				}
			}
		}

		return true;
	}

	public boolean isWhitelisted(Location location) {
		LinkedList<Area> areas = mWhitelistedAreas.get(location.getWorld().getUID());
		if (areas == null)
			return false;
		for (Area area : areas) {
			if (area.getCenter().distance(location) < area.getRange()) {
				return true;
			}
		}
		return false;
	}

	public Area getWhitelistArea(Location location) {
		LinkedList<Area> areas = getWhitelistedAreas(location.getWorld());
		for (Area area : areas) {
			if (area.getCenter().getWorld() != null && area.getCenter().getWorld().equals(location.getWorld())) {
				if (area.getCenter().distance(location) < area.getRange()) {
					return area;
				}
			}
		}
		return null;
	}

	public void whitelistArea(Area newArea) {
		LinkedList<Area> areas = mWhitelistedAreas.get(newArea.getCenter().getWorld().getUID());
		if (areas == null) {
			areas = new LinkedList<Area>();
			mWhitelistedAreas.put(newArea.getCenter().getWorld().getUID(), areas);

		}

		for (Area area : areas) {
			if (newArea.getCenter().getWorld().equals(area.getCenter().getWorld())) {
				double dist = newArea.getCenter().distance(area.getCenter());

				double remaining = dist;
				remaining -= area.getRange();
				remaining -= newArea.getRange();

				if (remaining < 0) {
					if (dist > area.getRange())
						area.setRange(dist);

					area.setCounter(newArea.getCounter() + 1);

					return;
				}
			}
		}
		areas.add(newArea);
		mWhitelistedAreas.put(newArea.getCenter().getWorld().getUID(), areas);
		saveWhitelist = true;
	}

	public void unWhitelistArea(Location location) {
		LinkedList<Area> areas = mWhitelistedAreas.get(location.getWorld().getUID());

		if (areas == null)
			return;

		Iterator<Area> it = areas.iterator();
		while (it.hasNext()) {
			Area area = it.next();

			if (area.getCenter().getWorld().equals(location.getWorld())) {
				if (area.getCenter().distance(location) < area.getRange())
					it.remove();
			}
		}
		if (areas.isEmpty())
			mWhitelistedAreas.remove(location.getWorld().getUID());
		else
			mWhitelistedAreas.put(location.getWorld().getUID(), areas);
		saveWhitelist = true;
	}

	/**
	 * Check if Grindind detection is disabled in world
	 * 
	 * @param world
	 * @return true if Grinding is disabled.
	 */
	public boolean isGrindingDisabledInWorld(World world) {
		if (world != null)
			for (String worldName : plugin.getConfigManager().disableGrindingDetectionInWorlds) {
				if (world.getName().equalsIgnoreCase(worldName))
					return true;
			}
		return false;
	}

	public void showGrindingArea(Player player, Area grindingArea, Location killedLocation) {

		if (killedLocation != null) {
			for (int n = 0; n < 5; n++) {
				if (player != null & player.isOnline()) {
					double y = killedLocation.clone().getBlockY() + 0.2 + 0.4 * n;
					player.spawnParticle(Particle.SMOKE, killedLocation.getX(), y, killedLocation.getZ(), 5);
					if (grindingArea != null) {
						double y2 = grindingArea.getCenter().clone().getBlockY() + 0.2 + n * 0.4;
						player.spawnParticle(Particle.HEART, grindingArea.getCenter().getX(), y2,
								grindingArea.getCenter().getZ(), 1);
					}
				}
			}
		}

		// Grinding Area
		if (grindingArea != null) {
			// Show center of grinding area
			for (int n = 0; n < 5; n++) {
				double y = grindingArea.getCenter().clone().getBlockY() + 0.2 + 0.4 * n;
				player.spawnParticle(Particle.HEART, grindingArea.getCenter().getX(), y,
						grindingArea.getCenter().getZ(), 1);
			}

			// Circle around the grinding area
			for (int n = 0; n < 360; n = n + (int) (45 / plugin.getConfigManager().grindingDetectionRange)) {
				double x = grindingArea.getCenter().clone().getBlockX() + 0.5
						+ Math.cos(n) * (double) plugin.getConfigManager().grindingDetectionRange;
				double y = grindingArea.getCenter().clone().getBlockY() + 0.2;
				double z = grindingArea.getCenter().clone().getBlockZ() + 0.5
						+ Math.sin(n) * (double) plugin.getConfigManager().grindingDetectionRange;
				player.spawnParticle(Particle.HEART, x, y, z, 1);
			}
		}

	}
}
