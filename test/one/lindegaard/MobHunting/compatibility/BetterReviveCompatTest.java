package one.lindegaard.MobHunting.compatibility;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.UUID;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Zombie;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.mockito.ArgumentCaptor;
import org.junit.Before;
import org.junit.Test;
import one.lindegaard.MobHunting.MobHunting;

public class BetterReviveCompatTest {
    private BetterReviveCompat compat;
    private Player victim;
    private Player attacker;
    private MobHunting plugin;
    private org.bukkit.Server server;

    private Player player() {
        Player p = mock(Player.class);
        when(p.getUniqueId()).thenReturn(UUID.randomUUID());
        return p;
    }

    @Before public void setup() {
        plugin = mock(MobHunting.class, RETURNS_DEEP_STUBS);
        server = mock(org.bukkit.Server.class, RETURNS_DEEP_STUBS);
        compat = new BetterReviveCompat(plugin, server);
        victim = player();
        attacker = player();
    }

    private EntityDamageByEntityEvent hit(org.bukkit.entity.Entity source) {
        EntityDamageByEntityEvent event = mock(EntityDamageByEntityEvent.class);
        when(event.getEntity()).thenReturn(victim);
        when(event.getDamager()).thenReturn(source);
        when(event.getCause()).thenReturn(DamageCause.ENTITY_ATTACK);
        compat.captureDamage(event);
        return event;
    }

    @Test public void storesDownerWithoutProcessingPayment() {
        EntityDamageEvent hit = hit(attacker);
        compat.onDowned(victim, true);
        compat.finishDamage(hit);
        BetterReviveCompat.Downing downing = compat.getDowning(victim.getUniqueId());
        assertEquals(attacker.getUniqueId(), downing.attacker);
        assertEquals(DamageCause.ENTITY_ATTACK, downing.cause);
        assertTrue(downing.claim());
        assertFalse(downing.claim());
    }

    @Test public void projectileUsesShooter() {
        Projectile arrow = mock(Projectile.class);
        when(arrow.getShooter()).thenReturn(attacker);
        hit(arrow);
        compat.onDowned(victim, true);
        assertEquals(attacker.getUniqueId(), compat.getDowning(victim.getUniqueId()).attacker);
    }

    @Test public void finisherDoesNotReplaceDowner() {
        hit(attacker);
        compat.onDowned(victim, true);
        hit(player());
        compat.onDowned(victim, true);
        assertEquals(attacker.getUniqueId(), compat.getDowning(victim.getUniqueId()).attacker);
    }

    @Test public void environmentDoesNotUsePreviousPlayerHit() {
        compat.finishDamage(hit(attacker));
        EntityDamageEvent fall = mock(EntityDamageEvent.class);
        when(fall.getEntity()).thenReturn(victim);
        when(fall.getCause()).thenReturn(DamageCause.FALL);
        compat.captureDamage(fall);
        compat.onDowned(victim, true);
        assertNull(compat.getDowning(victim.getUniqueId()).attacker);
        assertEquals(DamageCause.FALL, compat.getDowning(victim.getUniqueId()).cause);
    }

    @Test public void mobThenPlayerFinisherDoesNotPayPlayer() {
        hit(mock(Zombie.class));
        compat.onDowned(victim, true);
        hit(attacker);
        compat.onDowned(victim, true);
        assertNull(compat.getDowning(victim.getUniqueId()).attacker);
    }

    @Test public void reviveClearsAttributionAndNewDowningHasNewOwner() {
        hit(attacker);
        compat.onDowned(victim, true);
        compat.clear(victim.getUniqueId());
        assertNull(compat.getDowning(victim.getUniqueId()));
        Player other = player();
        hit(other);
        compat.onDowned(victim, true);
        assertEquals(other.getUniqueId(), compat.getDowning(victim.getUniqueId()).attacker);
    }

    @Test public void apiDowningDoesNotReuseDamage() {
        hit(attacker);
        compat.onDowned(victim, false);
        assertNull(compat.getDowning(victim.getUniqueId()).attacker);
    }

    @Test public void cancelledDamageCannotAttributeKill() {
        EntityDamageEvent hit = hit(attacker);
        when(hit.isCancelled()).thenReturn(true);
        compat.onDowned(victim, true);
        assertNull(compat.getDowning(victim.getUniqueId()).attacker);
    }

    @Test public void ordinaryDeathHasNoOverride() {
        compat.finishDamage(hit(attacker));
        assertNull(compat.getDowning(victim.getUniqueId()));
    }

    @Test public void selfDamageCannotClaimOwnBounty() {
        hit(victim);
        compat.onDowned(victim, true);
        assertNull(compat.getDowning(victim.getUniqueId()).attacker);
    }

    @Test public void disconnectKeepsAttributionDuringNestedDeathThenCleansUp() {
        hit(attacker);
        compat.onDowned(victim, true);
        PlayerQuitEvent quit = new PlayerQuitEvent(victim, "quit");
        compat.onQuit(quit);
        assertEquals(attacker.getUniqueId(), compat.getDowning(victim.getUniqueId()).attacker);
        ArgumentCaptor<Runnable> cleanup = ArgumentCaptor.forClass(Runnable.class);
        verify(server.getScheduler()).runTask(eq(plugin), cleanup.capture());
        cleanup.getValue().run();
        assertNull(compat.getDowning(victim.getUniqueId()));
    }

    @Test public void respawnClearsProcessedDeath() {
        hit(attacker);
        compat.onDowned(victim, true);
        assertTrue(compat.getDowning(victim.getUniqueId()).claim());
        PlayerRespawnEvent respawn = new PlayerRespawnEvent(victim, new org.bukkit.Location(null, 0, 64, 0), false);
        compat.onRespawn(respawn);
        assertNull(compat.getDowning(victim.getUniqueId()));
    }

    @Test public void disabledIntegrationDoesNotRegisterListeners() {
        plugin.getConfigManager().enableIntegrationBetterRevive = false;
        compat.enable();
        verifyNoInteractions(server);
    }
}
