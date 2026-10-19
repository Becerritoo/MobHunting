package one.lindegaard.MobHunting;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MessagesTest {

	@Test
	public void uninitializedTranslationTableDoesNotThrow() {
		assertNull(Messages.findTranslation(null, "mobhunting.config.fail"));
	}

	@Test
	public void translationIsTrimmedWhenAvailable() {
		assertEquals("Loaded", Messages.findTranslation(
				Collections.singletonMap("mobhunting.config.loaded", "  Loaded  "),
				"mobhunting.config.loaded"));
	}
}
