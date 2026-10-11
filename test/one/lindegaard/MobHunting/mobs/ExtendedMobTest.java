package one.lindegaard.MobHunting.mobs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ExtendedMobTest {

	@Test
	public void matchesTypeAndPluginSource() {
		ExtendedMob vanillaZombie = new ExtendedMob(MobPlugin.Minecraft, "ZOMBIE");

		assertTrue(vanillaZombie.matches(new ExtendedMob(MobPlugin.Minecraft, "zombie")));
		assertFalse(vanillaZombie.matches(new ExtendedMob(MobPlugin.InfernalMobs, "ZOMBIE")));
	}
}
