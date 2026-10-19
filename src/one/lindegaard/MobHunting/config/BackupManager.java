package one.lindegaard.MobHunting.config;

import java.io.IOException;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public final class BackupManager {

	private static final int MIN_BACKUPS = 1;
	private static final int MAX_BACKUPS = 100;

	private BackupManager() {
	}

	public static Path backup(Path source, Path backupDirectory, int requestedBackupCount) throws IOException {
		if (!Files.isRegularFile(source))
			throw new IOException("Backup source is not a regular file: " + source);

		int backupCount = normalizeBackupCount(requestedBackupCount);
		Files.createDirectories(backupDirectory);
		String prefix = source.getFileName().toString() + ".bak";

		removeBackupsBeyondLimit(backupDirectory, prefix, backupCount);
		for (int index = backupCount; index >= 2; index--) {
			Path previous = backupDirectory.resolve(prefix + (index - 1));
			Path target = backupDirectory.resolve(prefix + index);
			removeLegacyDirectory(target);
			if (Files.isRegularFile(previous))
				Files.move(previous, target, StandardCopyOption.REPLACE_EXISTING);
			else
				removeLegacyDirectory(previous);
		}

		Path newest = backupDirectory.resolve(prefix + 1);
		removeLegacyDirectory(newest);
		Path temporary = Files.createTempFile(backupDirectory, source.getFileName().toString() + ".", ".tmp");
		try {
			Files.copy(source, temporary, StandardCopyOption.COPY_ATTRIBUTES, StandardCopyOption.REPLACE_EXISTING);
			try {
				Files.move(temporary, newest, StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
			} catch (AtomicMoveNotSupportedException ignored) {
				Files.move(temporary, newest, StandardCopyOption.REPLACE_EXISTING);
			}
		} finally {
			Files.deleteIfExists(temporary);
		}
		return newest;
	}

	private static void removeLegacyDirectory(Path path) throws IOException {
		if (Files.isDirectory(path))
			Files.delete(path);
	}

	static int normalizeBackupCount(int requestedBackupCount) {
		return Math.max(MIN_BACKUPS, Math.min(MAX_BACKUPS, requestedBackupCount));
	}

	private static void removeBackupsBeyondLimit(Path backupDirectory, String prefix, int backupCount)
			throws IOException {
		try (DirectoryStream<Path> backups = Files.newDirectoryStream(backupDirectory, prefix + "*")) {
			for (Path backup : backups) {
				String suffix = backup.getFileName().toString().substring(prefix.length());
				try {
					if (Integer.parseInt(suffix) > backupCount)
						Files.deleteIfExists(backup);
				} catch (NumberFormatException ignored) {
					// Ignore unrelated files that merely share the prefix.
				}
			}
		}
	}
}
