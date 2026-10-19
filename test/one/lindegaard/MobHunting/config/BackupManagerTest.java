package one.lindegaard.MobHunting.config;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BackupManagerTest {

	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();

	@Test
	public void rotatesBackupsAndKeepsConfiguredLimit() throws Exception {
		Path root = temporaryFolder.getRoot().toPath();
		Path source = root.resolve("config.yml");
		Path backups = root.resolve("backup");

		write(source, "first");
		BackupManager.backup(source, backups, 2);
		write(source, "second");
		BackupManager.backup(source, backups, 2);
		write(source, "third");
		BackupManager.backup(source, backups, 2);

		assertEquals("third", read(backups.resolve("config.yml.bak1")));
		assertEquals("second", read(backups.resolve("config.yml.bak2")));
		assertFalse(Files.exists(backups.resolve("config.yml.bak3")));
		assertTrue(Files.isRegularFile(backups.resolve("config.yml.bak1")));
	}

	@Test
	public void clampsBackupCountToSafeBounds() {
		assertEquals(1, BackupManager.normalizeBackupCount(0));
		assertEquals(10, BackupManager.normalizeBackupCount(10));
		assertEquals(100, BackupManager.normalizeBackupCount(1000));
	}

	@Test
	public void replacesEmptyDirectoryCreatedByLegacyBackupCode() throws Exception {
		Path root = temporaryFolder.getRoot().toPath();
		Path source = root.resolve("config.yml");
		Path backups = Files.createDirectories(root.resolve("backup"));
		Files.createDirectory(backups.resolve("config.yml.bak1"));
		write(source, "valid");

		BackupManager.backup(source, backups, 3);

		assertEquals("valid", read(backups.resolve("config.yml.bak1")));
		assertTrue(Files.isRegularFile(backups.resolve("config.yml.bak1")));
	}

	private static void write(Path path, String value) throws Exception {
		Files.write(path, value.getBytes(StandardCharsets.UTF_8));
	}

	private static String read(Path path) throws Exception {
		return new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
	}
}
