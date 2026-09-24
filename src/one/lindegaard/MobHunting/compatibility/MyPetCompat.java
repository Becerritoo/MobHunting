package one.lindegaard.MobHunting.compatibility;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import de.Keyle.MyPet.MyPetPlugin;
import de.Keyle.MyPet.api.entity.Pet;
import de.Keyle.MyPet.api.event.PetInventoryActionEvent;
import de.Keyle.MyPet.api.event.PetInventoryActionEvent.Action;
import de.Keyle.MyPet.api.event.PetPickupItemEvent;
import one.lindegaard.CustomItemsLib.Core;
import one.lindegaard.CustomItemsLib.compatibility.CompatPlugin;
import one.lindegaard.CustomItemsLib.rewards.Reward;
import one.lindegaard.MobHunting.MobHunting;

public class MyPetCompat implements Listener {
	private static boolean supported = false;
	private static MyPetPlugin mPlugin;

	public MyPetCompat() {
		if (!MobHunting.getInstance().getConfigManager().enableIntegrationMyPet) {
			Bukkit.getConsoleSender()
					.sendMessage(MobHunting.PREFIX_WARNING + "Compatibility with MyPet is disabled in config.yml");
		} else {
			mPlugin = (MyPetPlugin) Bukkit.getPluginManager().getPlugin(CompatPlugin.MyPet.getName());
			Bukkit.getPluginManager().registerEvents(this, MobHunting.getInstance());
			Bukkit.getConsoleSender().sendMessage(MobHunting.PREFIX + "Enabling compatibility with MyPet ("
					+ getMyPetPlugin().getDescription().getVersion() + ")");
			supported = true;
		}
	}

	// **************************************************************************
	// OTHER FUNCTIONS
	// **************************************************************************

	public static boolean isSupported() {
		return supported;
	}

	public static MyPetPlugin getMyPetPlugin() {
		return mPlugin;
	}

	public static boolean isMyPet(Entity entity) {
		if (isSupported() && entity != null)
			return getMyPetPlugin().getPetManager().getPetFromEntity(entity) != null;
		return false;
	}

	public static boolean isEnabledInConfig() {
		return MobHunting.getInstance().getConfigManager().enableIntegrationMyPet;
	}

	public static boolean isKilledByMyPet(Entity entity) {
		if (isSupported() && (entity.getLastDamageCause() instanceof EntityDamageByEntityEvent)) {
			EntityDamageByEntityEvent dmg = (EntityDamageByEntityEvent) entity.getLastDamageCause();
			if (dmg != null && isMyPet(dmg.getDamager()))
				return true;
		}
		return false;
	}

	public static Pet getMyPet(Entity entity) {
		if (isMyPet(entity))
			return getMyPetPlugin().getPetManager().getPetFromEntity(entity);

		if (!(entity.getLastDamageCause() instanceof EntityDamageByEntityEvent))
			return null;

		EntityDamageByEntityEvent dmg = (EntityDamageByEntityEvent) entity.getLastDamageCause();

		if (dmg == null)
			return null;

		return getMyPetPlugin().getPetManager().getPetFromEntity(dmg.getDamager());
	}

	public static Player getMyPetOwner(Entity entity) {
		Pet directPet = getMyPetPlugin().getPetManager().getPetFromEntity(entity);
		if (directPet != null && directPet.getOwner() != null)
			return directPet.getOwner().getPlayer();

		if (!(entity.getLastDamageCause() instanceof EntityDamageByEntityEvent))
			return null;

		EntityDamageByEntityEvent dmg = (EntityDamageByEntityEvent) entity.getLastDamageCause();

		if (dmg == null)
			return null;

		Pet killer = getMyPetPlugin().getPetManager().getPetFromEntity(dmg.getDamager());

		if (killer == null || killer.getOwner() == null)
			return null;

		return killer.getOwner().getPlayer();
	}

	// **************************************************************************
	// EVENTS
	// **************************************************************************
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	private void onMyPetKillMob(EntityDeathEvent event) {
		// MobHunting is not started initialized yet...
		if (MobHunting.getInstance().getMobHuntingManager() == null)
			return;

		if (!MobHunting.getInstance().getMobHuntingManager().isHuntEnabledInWorld(event.getEntity().getWorld())
				|| !(event.getEntity().getLastDamageCause() instanceof EntityDamageByEntityEvent))
			return;

		EntityDamageByEntityEvent dmg = (EntityDamageByEntityEvent) event.getEntity().getLastDamageCause();
		if (dmg == null || !isMyPet(dmg.getDamager()))
			return;

		Pet killer = getMyPetPlugin().getPetManager().getPetFromEntity(dmg.getDamager());
		if (killer.getOwner() != null) {
			Player owner = killer.getOwner().getPlayer();
			if (owner != null && MobHunting.getInstance().getMobHuntingManager().isHuntEnabled(owner))
				MobHunting.getInstance().getAchievementManager().awardAchievementProgress("fangmaster", owner,
						MobHunting.getInstance().getExtendedMobManager().getExtendedMobFromEntity(event.getEntity()),
						1);
		}
	}

	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = false)
	private void onMyPetInventoryActionEvent(PetInventoryActionEvent event) {
		if (event.getAction() == Action.PICKUP)
			MobHunting.getInstance().getMessages().debug("MyPetInventoryActionEvent=%s", event.getAction().name());
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = false)
	private void onMyPetPickupItem(PetPickupItemEvent event) {
		if (event.isCancelled())
			return;

		Item item = event.getItem();
		Player player = event.getOwner().getPlayer();
		Pet pet = event.getPet();

		if (Reward.isReward(item)) {
			Reward reward = Reward.getReward(item);
			MobHunting.getInstance().getMessages().playerActionBarMessageQueue(player,
					MobHunting.getInstance().getMessages().getString("mobhunting.reward.mypet_pickup", "rewardname",
							ChatColor.valueOf(Core.getConfigManager().rewardTextColor) + reward.getDisplayName(),
							"petname", pet.getPetName(), "money",
							MobHunting.getInstance().getEconomyManager().format(reward.getMoney())));
			MobHunting.getInstance().getMessages().debug("%s owned by %s picked up %s %s.", pet.getPetName(),
					player.getName(), MobHunting.getInstance().getEconomyManager().format(reward.getMoney()),
					reward.getDisplayName());
			if (reward.isBagOfGoldReward() || reward.isItemReward()) {
				if (!BagOfGoldCompat.isSupported()
						&& !MobHunting.getInstance().getConfigManager().dropMoneyOnGroundUseItemAsCurrency) {
					event.setCancelled(true);
					item.remove();
					MobHunting.getInstance().getRewardManager().depositPlayer(player, reward.getMoney());
				}
			}
		}
	}

}
