package one.lindegaard.MobHunting.mobs;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MobPluginTest {

	@Test
	public void resolvesPersistedPluginIds() {
		for (MobPlugin plugin : MobPlugin.values())
			assertEquals(plugin, MobPlugin.fromId(plugin.getId()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void rejectsUnknownPluginIds() {
		MobPlugin.fromId(999);
	}
}
