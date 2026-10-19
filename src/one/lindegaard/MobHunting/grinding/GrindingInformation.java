package one.lindegaard.MobHunting.grinding;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import one.lindegaard.CustomItemsLib.mobs.MobType;
import one.lindegaard.MobHunting.MobHunting;

public final class GrindingInformation {

	private final int entityId;
	private final UUID killer;
	private final UUID worldId;
	private final EntityType entityType;
	private final MobType mobType;
	private final double x;
	private final double y;
	private final double z;
	private final long timeOfDeath;
	private final double cDampnerRange;

	GrindingInformation(UUID killer, LivingEntity killed) {
		this(killer, killed, killed.getLocation(),
				System.currentTimeMillis(), MobHunting.getInstance().getConfigManager().grindingDetectionRange);
	}

	private GrindingInformation(UUID killer, LivingEntity killed, Location location, long timeOfDeath,
			double cDampnerRange) {
		this(killer, killed.getEntityId(), killed.getWorld().getUID(), killed.getType(), MobType.getMobType(killed),
				location.getX(), location.getY(), location.getZ(), timeOfDeath, cDampnerRange);
	}

	GrindingInformation(UUID killer, int entityId, UUID worldId, EntityType entityType, MobType mobType,
			double x, double y, double z, long timeOfDeath, double cDampnerRange) {
		this.killer = killer;
		this.entityId = entityId;
		this.worldId = worldId;
		this.entityType = entityType;
		this.mobType = mobType;
		this.x = x;
		this.y = y;
		this.z = z;
		this.timeOfDeath = timeOfDeath;
		this.cDampnerRange = cDampnerRange;
	}

	public int getEntityId() {
		return entityId;
	}

	public UUID getKillerUUID() {
		return killer;
	}

	public UUID getWorldId() {
		return worldId;
	}

	public EntityType getEntityType() {
		return entityType;
	}

	public MobType getMobType() {
		return mobType;
	}

	public long getTimeOfDeath() {
		return timeOfDeath;
	}

	public double getcDampnerRange() {
		return cDampnerRange;
	}

	public boolean isInWorld(UUID candidateWorldId) {
		return worldId != null && worldId.equals(candidateWorldId);
	}

	public boolean isWithin(Location location, double range) {
		return location != null && location.getWorld() != null && isInWorld(location.getWorld().getUID())
				&& distanceSquared(location.getX(), location.getY(), location.getZ()) < range * range;
	}

	double distanceSquared(double candidateX, double candidateY, double candidateZ) {
		double deltaX = x - candidateX;
		double deltaY = y - candidateY;
		double deltaZ = z - candidateZ;
		return deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ;
	}
}
