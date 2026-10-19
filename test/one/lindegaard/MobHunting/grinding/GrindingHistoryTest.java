package one.lindegaard.MobHunting.grinding;

import org.bukkit.entity.EntityType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GrindingHistoryTest {

	@Test
	public void purgesExpiredEntriesAndKeepsRecentSnapshots() {
		GrindingHistory history = new GrindingHistory(10);
		history.add(record(1, 1_000L), 1_000L, 10_000L);
		history.add(record(2, 9_000L), 9_000L, 10_000L);

		List<GrindingInformation> snapshot = history.snapshot(12_000L, 5_000L);

		assertEquals(1, snapshot.size());
		assertEquals(2, snapshot.get(0).getEntityId());
	}

	@Test
	public void boundsHistoryToConfiguredSize() {
		GrindingHistory history = new GrindingHistory(3);
		for (int id = 1; id <= 5; id++)
			history.add(record(id, id), id, 60_000L);

		List<GrindingInformation> snapshot = history.snapshot(5L, 60_000L);

		assertEquals(3, snapshot.size());
		assertEquals(3, snapshot.get(0).getEntityId());
		assertEquals(5, snapshot.get(2).getEntityId());
	}

	@Test
	public void acceptsConcurrentRegistrationWithoutConcurrentModification() throws Exception {
		GrindingHistory history = new GrindingHistory(100);
		ExecutorService executor = Executors.newFixedThreadPool(4);
		CountDownLatch start = new CountDownLatch(1);
		List<Future<?>> registrations = new ArrayList<>();
		for (int id = 0; id < 400; id++) {
			final int entityId = id;
			registrations.add(executor.submit(() -> {
				start.await();
				history.add(record(entityId, entityId), entityId, 60_000L);
				history.snapshot(entityId, 60_000L);
				return null;
			}));
		}
		start.countDown();
		executor.shutdown();

		assertTrue(executor.awaitTermination(10, TimeUnit.SECONDS));
		for (Future<?> registration : registrations)
			registration.get();
		assertTrue(history.size() <= 100);
	}

	@Test
	public void normalizesRetentionAndHistoryLimits() {
		assertEquals(120_000L, GrindingManager.calculateRetentionMillis(30, 120, 15));
		assertEquals(1_000L, GrindingManager.calculateRetentionMillis(Double.NaN, -10));
		assertEquals(100, GrindingManager.normalizeHistoryLimit(1));
		assertEquals(100_000, GrindingManager.normalizeHistoryLimit(Integer.MAX_VALUE));
	}

	@Test
	public void snapshotUsesCoordinatesWithoutRetainingAnEntity() {
		GrindingInformation information = record(1, 1L);
		assertEquals(25.0, information.distanceSquared(3.0, 4.0, 0.0), 0.0);
	}

	private static GrindingInformation record(int entityId, long timeOfDeath) {
		return new GrindingInformation(UUID.randomUUID(), entityId, UUID.randomUUID(), EntityType.ZOMBIE, null,
				0.0, 0.0, 0.0, timeOfDeath, 15.0);
	}
}
