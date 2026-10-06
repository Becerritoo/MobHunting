package one.lindegaard.MobHunting.compatibility;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class HerobrineCompatTest {

	@Test
	public void acceptsOnlyTheLegacyApiMainClass() {
		assertTrue(HerobrineApiIdentity.supports("net.theprogrammersworld.herobrine.Herobrine"));
		assertFalse(HerobrineApiIdentity.supports("com.sausaliens.herobrine.HerobrinePlugin"));
		assertFalse(HerobrineApiIdentity.supports(null));
	}
}
