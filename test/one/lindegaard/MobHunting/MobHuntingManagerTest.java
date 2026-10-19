package one.lindegaard.MobHunting;

import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.projectiles.ProjectileSource;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MobHuntingManagerTest {

	@Test
	public void projectileWithoutShooterIsReportedAsUnknown() {
		Projectile projectile = mock(Projectile.class);
		when(projectile.getShooter()).thenReturn(null);

		ProjectileSource shooter = MobHuntingManager.getProjectileShooter(projectile);

		assertNull(shooter);
		assertEquals("unknown", MobHuntingManager.describeProjectileShooter(shooter));
	}

	@Test
	public void projectileShooterIsPreservedForAttribution() {
		Projectile projectile = mock(Projectile.class);
		Player player = mock(Player.class);
		when(projectile.getShooter()).thenReturn(player);

		assertSame(player, MobHuntingManager.getProjectileShooter(projectile));
	}
}
