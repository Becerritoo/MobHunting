package one.lindegaard.MobHunting.mobs;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MobPluginTest {

	@Test
	public void resolvesPersistedPluginIds() {
		for (MobPlugin plugin : MobPlugin.values())
			assertEquals(plugin, MobPlugin.fromId(plugin.getId()));

		assertEquals(MobPlugin.CustomMobs, MobPlugin.fromId(4));
		assertEquals(MobPlugin.SmartGiants, MobPlugin.fromId(6));
		assertEquals(MobPlugin.Herobrine, MobPlugin.fromId(8));
	}

	@Test(expected = IllegalArgumentException.class)
	public void rejectsUnknownPluginIds() {
		MobPlugin.fromId(999);
	}
}
