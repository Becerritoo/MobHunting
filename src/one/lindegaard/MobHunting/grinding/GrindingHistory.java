package one.lindegaard.MobHunting.grinding;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

final class GrindingHistory {

	private final LinkedHashMap<Integer, GrindingInformation> entries = new LinkedHashMap<>();
	private final int maximumEntries;

	GrindingHistory(int maximumEntries) {
		this.maximumEntries = Math.max(1, maximumEntries);
	}

	synchronized void add(GrindingInformation information, long now, long retentionMillis) {
		purge(now, retentionMillis);
		entries.put(information.getEntityId(), information);
		while (entries.size() > maximumEntries) {
			Iterator<Integer> iterator = entries.keySet().iterator();
			if (!iterator.hasNext())
				break;
			iterator.next();
			iterator.remove();
		}
	}

	synchronized List<GrindingInformation> snapshot(long now, long retentionMillis) {
		purge(now, retentionMillis);
		return new ArrayList<>(entries.values());
	}

	synchronized int size() {
		return entries.size();
	}

	private void purge(long now, long retentionMillis) {
		long cutoff = now - Math.max(0L, retentionMillis);
		Iterator<Map.Entry<Integer, GrindingInformation>> iterator = entries.entrySet().iterator();
		while (iterator.hasNext()) {
			GrindingInformation information = iterator.next().getValue();
			if (information == null || information.getTimeOfDeath() < cutoff)
				iterator.remove();
		}
	}
}
