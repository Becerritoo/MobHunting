package one.lindegaard.MobHunting.compatibility;

import com.Zrips.CMI.Modules.Holograms.CMIHologram;

import one.lindegaard.CustomItemsLib.compatibility.CMICompat;
import one.lindegaard.MobHunting.leaderboard.HologramLeaderboard;

public class CMIHologramsHelper {

	public static void createHologram(HologramLeaderboard board) {
		CMIHologram hologram = new CMIHologram(board.getHologramName(), board.getLocation());
		CMICompat.getHologramManager().addHologram(hologram, true);
	}

	public static void deleteHologram(CMIHologram hologram) {
		CMICompat.getHologramManager().hideHoloForAllPlayers(hologram);
		CMICompat.getHologramManager().removeChunkRecords(hologram);
		hologram.remove();
	}

	public static void editTextLine(CMIHologram hologram, String text, int lineIndex) {
		if (hologram.getLines().size() > lineIndex) {
			hologram.setLine(lineIndex, text);
		} else {
			hologram.addLine(text);
		}
	}

}
