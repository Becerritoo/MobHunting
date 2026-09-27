package one.lindegaard.MobHunting.compatibility;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.Server;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.plugin.Plugin;
import one.lindegaard.MobHunting.MobHunting;

/** Tracks the downing hit, never the later synthetic damage or finishing blow. */
public class BetterReviveCompat implements Listener {
    private final MobHunting plugin;
    private final Server server;
    private final Map<UUID, EntityDamageEvent> currentDamage = new HashMap<>();
    private final Map<UUID, Downing> downings = new HashMap<>();
    private Plugin betterRevive;

    public static class Downing {
        public final UUID attacker;
        public final EntityDamageEvent.DamageCause cause;
        public final EntityDamageEvent damage;
        private boolean processed;

        Downing(UUID attacker, EntityDamageEvent damage) {
            this.attacker = attacker;
            this.damage = damage;
            this.cause = damage == null ? null : damage.getCause();
        }

        public boolean claim() {
            if (processed) return false;
            processed = true;
            return true;
        }
    }

    public BetterReviveCompat(MobHunting plugin) {
        this(plugin, plugin.getServer());
    }

    BetterReviveCompat(MobHunting plugin, Server server) {
        this.plugin = plugin;
        this.server = server;
    }

    public void enable() {
        if (!plugin.getConfigManager().enableIntegrationBetterRevive) return;
        betterRevive = server.getPluginManager().getPlugin("BetterRevive");
        if (betterRevive == null || !betterRevive.isEnabled()) return;
        try {
            ClassLoader loader = betterRevive.getClass().getClassLoader();
            Class<? extends Event> bleed = Class.forName(
                    "com.alonsoaliaga.betterrevive.api.events.PlayerBleedEvent", true, loader).asSubclass(Event.class);
            Class<? extends Event> revive = Class.forName(
                    "com.alonsoaliaga.betterrevive.api.events.PlayerReviveEvent", true, loader).asSubclass(Event.class);
            Method reason = bleed.getMethod("getReason");
            server.getPluginManager().registerEvent(bleed, this, EventPriority.MONITOR,
                    (listener, event) -> {
                        try {
                            onDowned(((PlayerEvent) event).getPlayer(), "DAMAGE".equals(String.valueOf(reason.invoke(event))));
                        } catch (ReflectiveOperationException exception) {
                            throw new org.bukkit.event.EventException(exception);
                        }
                    }, plugin, true);
            server.getPluginManager().registerEvent(revive, this, EventPriority.MONITOR,
                    (listener, event) -> clear(((PlayerEvent) event).getPlayer().getUniqueId()), plugin, true);
            server.getPluginManager().registerEvents(this, plugin);
            plugin.getLogger().info("Enabling compatibility with BetterRevive: final death, original downing attacker.");
        } catch (ReflectiveOperationException | LinkageError exception) {
            close();
            plugin.getLogger().warning("BetterRevive integration unavailable: " + exception);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void captureDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player)
            currentDamage.put(event.getEntity().getUniqueId(), event);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void finishDamage(EntityDamageEvent event) {
        currentDamage.remove(event.getEntity().getUniqueId(), event);
    }

    void onDowned(Player victim, boolean fromDamage) {
        UUID id = victim.getUniqueId();
        if (downings.containsKey(id)) return;
        EntityDamageEvent damage = fromDamage ? currentDamage.get(id) : null;
        UUID attacker = null;
        if (damage != null && !damage.isCancelled() && damage instanceof EntityDamageByEntityEvent) {
            Entity source = ((EntityDamageByEntityEvent) damage).getDamager();
            if (source instanceof Projectile) {
                Object shooter = ((Projectile) source).getShooter();
                source = shooter instanceof Entity ? (Entity) shooter : null;
            }
            if (source instanceof Player && !source.getUniqueId().equals(id)) attacker = source.getUniqueId();
        }
        downings.put(id, new Downing(attacker, damage));
    }

    public Downing getDowning(UUID victim) {
        return downings.get(victim);
    }

    void clear(UUID victim) {
        downings.remove(victim);
        currentDamage.remove(victim);
    }

    @EventHandler public void onRespawn(PlayerRespawnEvent event) {
        clear(event.getPlayer().getUniqueId());
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onQuit(PlayerQuitEvent event) {
        // BetterRevive can issue the final death from inside PlayerQuitEvent.
        UUID id = event.getPlayer().getUniqueId();
        server.getScheduler().runTask(plugin, () -> clear(id));
    }

    @EventHandler public void onPluginDisable(PluginDisableEvent event) {
        if (event.getPlugin() == betterRevive) close();
    }

    public void close() {
        HandlerList.unregisterAll(this);
        downings.clear();
        currentDamage.clear();
    }
}
