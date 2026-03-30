package one.lindegaard.MobHunting.compatibility;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

import one.lindegaard.CustomItemsLib.compatibility.CompatPlugin;
import one.lindegaard.CustomItemsLib.mobs.MobType;
import one.lindegaard.MobHunting.MobHunting;

public class InfernalMobsCompat implements Listener {

	// https://www.spigotmc.org/resources/infernal-mobs.2156/

	private static boolean supported = false;
	private static Plugin mPlugin;
	private static HashMap<String, Double> mMobRewardData = new HashMap<String, Double>();
	private static Method idSearchMethod;
	private static Method findMobAbilitiesMethod;
	public static final String MH_INFERNALMOBS = "MH:INFERNALMOBS";

	public InfernalMobsCompat() {
		if (!isEnabledInConfig()) {
			Bukkit.getConsoleSender().sendMessage(
					MobHunting.PREFIX_WARNING + "Compatibility with InfernalMobs is disabled in config.yml");
		} else {
			mPlugin = Bukkit.getPluginManager().getPlugin(CompatPlugin.InfernalMobs.getName());
			if (mPlugin == null || !mPlugin.isEnabled()) {
				Bukkit.getConsoleSender().sendMessage(
						MobHunting.PREFIX_WARNING + "InfernalMobs plugin not found/enabled. Skipping compatibility.");
				return;
			}

			if (!resolveInfernalApi()) {
				Bukkit.getConsoleSender().sendMessage(
						MobHunting.PREFIX_WARNING
								+ "InfernalMobs API mismatch. Required methods idSearch(UUID) and findMobAbilities(UUID) not found.");
				return;
			}

			Bukkit.getPluginManager().registerEvents(this, MobHunting.getInstance());

			Bukkit.getConsoleSender().sendMessage(MobHunting.PREFIX + "Enabling Compatibility with InfernalMobs ("
					+ getInfernalMobs().getDescription().getVersion() + ")");

			loadInfernalMobsData();

			MobHunting.getInstance().getStoreManager().insertInfernalMobs();

			supported = true;
		}
	}

	// **************************************************************************
	// OTHER FUNCTIONS
	// **************************************************************************
	public static Plugin getInfernalMobs() {
		return mPlugin;
	}

	public static boolean isSupported() {
		return supported;
	}

	public static boolean isInfernalMob(Entity entity) {
		if (isSupported()) {
			int id = invokeIdSearch(entity.getUniqueId());
			return entity.hasMetadata(MH_INFERNALMOBS) || id != -1;
		}
		return false;
	}

	public static HashMap<String, Double> getMobRewardData() {
		return mMobRewardData;
	}

	public static boolean isEnabledInConfig() {
		return MobHunting.getInstance().getConfigManager().enableIntegrationInfernalMobs;
	}

	private static boolean resolveInfernalApi() {
		try {
			idSearchMethod = mPlugin.getClass().getMethod("idSearch", UUID.class);
			findMobAbilitiesMethod = mPlugin.getClass().getMethod("findMobAbilities", UUID.class);
			return true;
		} catch (NoSuchMethodException ex) {
			idSearchMethod = null;
			findMobAbilitiesMethod = null;
			return false;
		}
	}

	private static int invokeIdSearch(UUID uuid) {
		if (idSearchMethod == null || mPlugin == null)
			return -1;
		try {
			Object value = idSearchMethod.invoke(mPlugin, uuid);
			if (value instanceof Number)
				return ((Number) value).intValue();
		} catch (Exception ignored) {
		}
		return -1;
	}

	@SuppressWarnings("unchecked")
	private static List<String> invokeFindMobAbilities(UUID uuid) {
		if (findMobAbilitiesMethod == null || mPlugin == null)
			return null;
		try {
			Object value = findMobAbilitiesMethod.invoke(mPlugin, uuid);
			if (value instanceof List<?>)
				return (List<String>) value;
		} catch (Exception ignored) {
		}
		return null;
	}

	// **************************************************************************
	// LOAD & SAVE
	// **************************************************************************
	public static void loadInfernalMobsData() {
		for (MobType mob : MobType.values()) {
			String key = mob.getMobType();
			mMobRewardData.put(key, 1.0);
		}
		MobHunting.getInstance().getMessages().debug("Loaded %s InfernalMobs", mMobRewardData.size());
	}

	// **************************************************************************
	// EVENTS
	// **************************************************************************
	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	private void onInfernalMobDeathEvent(EntityDeathEvent event) {
		Entity entity = event.getEntity();
		if (isInfernalMob(entity)) {
			List<String> abilities = invokeFindMobAbilities(entity.getUniqueId());
			if (abilities != null)
				entity.setMetadata(MH_INFERNALMOBS,
						new FixedMetadataValue(MobHunting.getInstance(), abilities));
		}
	}

	public static int getProgressAchievementLevel1(String mobtype) {
		MobType mob = MobType.valueOf(mobtype);
		if (mob != null)
			return MobHunting.getInstance().getConfigManager().getProgressAchievementLevel1(mob);
		else
			return 100;
	}

}
