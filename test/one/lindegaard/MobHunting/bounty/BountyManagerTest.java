package one.lindegaard.MobHunting.bounty;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.LinkedHashSet;
import java.util.Set;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.junit.Before;
import org.junit.Test;
import one.lindegaard.MobHunting.MobHunting;
import one.lindegaard.MobHunting.config.ConfigManager;

public class BountyManagerTest {
    private MobHunting plugin;
    private BountyManager manager;
    private OfflinePlayer owner;
    private Player target;
    private Player hunter;

    @Before public void setup() {
        plugin = mock(MobHunting.class, RETURNS_DEEP_STUBS);
        ConfigManager config = mock(ConfigManager.class);
        config.enableRandomBounty = false;
        config.bountyReturnPct = 50;
        when(plugin.getConfigManager()).thenReturn(config);
        manager = new BountyManager(plugin);
        owner = mock(OfflinePlayer.class);
        target = mock(Player.class);
        hunter = mock(Player.class);
    }

    private Bounty bounty(OfflinePlayer sponsor, String world, double prize) {
        Bounty b = new Bounty(plugin);
        b.setBountyOwner(sponsor);
        b.setWantedPlayer(target);
        b.setWorldGroup(world);
        b.setPrize(prize);
        b.setStatus(BountyStatus.open);
        manager.getAllBounties().add(b);
        return b;
    }

    @Test public void searchesPastOtherSponsorsAndWorlds() {
        bounty(mock(OfflinePlayer.class), "survival", 10);
        bounty(owner, "other", 10);
        Bounty correct = bounty(owner, "survival", 100);
        assertSame(correct, manager.getOpenBounty("survival", target, owner));
        assertSame(correct, manager.getBounty("survival", target, owner));
        assertTrue(manager.hasOpenBounty("survival", target, owner));
        assertNull(manager.getOpenBounty("missing", target, owner));
    }

    @Test public void sortedBountiesAreRemovedAfterPayment() {
        when(owner.getName()).thenReturn("Owner");
        when(target.getName()).thenReturn("Target");
        Bounty b = bounty(owner, "survival", 100);
        manager.sort();
        when(plugin.getRewardManager().depositPlayer(hunter, 100)).thenReturn(true);
        assertTrue(manager.payBounties(hunter, manager.getOpenBounties("survival", target)));
        assertTrue(manager.getAllBounties().isEmpty());
        assertEquals(100, b.getPrize(), 0);
    }

    @Test public void invalidPrizeDoesNotReachEconomy() {
        bounty(owner, "survival", Double.NaN);
        assertFalse(manager.payBounties(hunter, manager.getOpenBounties("survival", target)));
        verify(plugin.getRewardManager(), never()).depositPlayer(any(), anyDouble());
    }

    @Test public void distinguishesRandomBounties() {
        bounty(owner, "survival", 100);
        Bounty random = bounty(null, "survival", 20);
        assertSame(random, manager.getOpenBounty("survival", target, null));
    }

    @Test public void failedRefundKeepsBounty() {
        Bounty b = bounty(owner, "survival", 100);
        assertFalse(manager.refund(b));
        assertTrue(b.isOpen());
        assertEquals(100, b.getPrize(), 0);
        verify(plugin.getDataStoreManager(), never()).updateBounty(any());
    }

    @Test public void successfulRefundPaysHalfAndRemovesBounty() {
        Bounty b = bounty(owner, "survival", 100);
        when(plugin.getRewardManager().depositPlayer(owner, 50)).thenReturn(true);
        assertTrue(manager.refund(b));
        assertEquals(BountyStatus.canceled, b.getStatus());
        assertTrue(manager.getAllBounties().isEmpty());
        assertFalse(manager.refund(b));
        verify(plugin.getRewardManager(), times(1)).depositPlayer(owner, 50);
    }

    @Test public void failedPaymentKeepsAllBounties() {
        Bounty b = bounty(owner, "survival", 100);
        Set<Bounty> selected = manager.getOpenBounties("survival", target);
        assertFalse(manager.payBounties(hunter, selected));
        assertTrue(b.isOpen());
        assertEquals(100, b.getPrize(), 0);
        verify(plugin.getDataStoreManager(), never()).updateBounty(any());
    }

    @Test public void successfulPaymentPreservesPrizeAndCannotRepeat() {
        Bounty a = bounty(owner, "survival", 100);
        Bounty b = bounty(null, "survival", 20);
        Set<Bounty> selected = new LinkedHashSet<>(manager.getAllBounties());
        when(plugin.getRewardManager().depositPlayer(hunter, 120)).thenReturn(true);
        assertTrue(manager.payBounties(hunter, selected));
        assertEquals(BountyStatus.completed, a.getStatus());
        assertEquals(BountyStatus.completed, b.getStatus());
        assertEquals(100, a.getPrize(), 0);
        assertTrue(manager.getAllBounties().isEmpty());
        assertFalse(manager.payBounties(hunter, selected));
        verify(plugin.getRewardManager(), times(1)).depositPlayer(hunter, 120);
    }

    @Test public void paymentExceptionDoesNotCloseBounty() {
        Bounty b = bounty(owner, "survival", 100);
        when(plugin.getRewardManager().depositPlayer(hunter, 100)).thenThrow(new IllegalStateException("unavailable"));
        assertThrows(IllegalStateException.class, () -> manager.payBounties(hunter, manager.getOpenBounties("survival", target)));
        assertTrue(b.isOpen());
    }
}
