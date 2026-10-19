package one.lindegaard.MobHunting;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MobHuntingTest {

	@Test
	public void metricsRequireBothSettings() {
		assertTrue(MobHunting.shouldStartMetrics(true, true, false));
		assertFalse(MobHunting.shouldStartMetrics(false, true, false));
		assertFalse(MobHunting.shouldStartMetrics(true, false, false));
	}

	@Test
	public void metricsRemainDisabledOnGlowstone() {
		assertFalse(MobHunting.shouldStartMetrics(true, true, true));
	}
}
