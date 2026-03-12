package one.lindegaard.MobHunting.compatibility;

import org.bukkit.Bukkit;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;

import one.lindegaard.CustomItemsLib.server.Servers;
import one.lindegaard.CustomItemsLib.compatibility.CompatPlugin;
import one.lindegaard.MobHunting.MobHunting;

public class WorldEditCompat {
	private static WorldEditPlugin mPlugin;
	private static boolean supported = false;

	public WorldEditCompat() {
		if (!isEnabledInConfig()) {
			Bukkit.getConsoleSender()
					.sendMessage(MobHunting.PREFIX + "Compatibility with WorldEdit is disabled in config.yml");
		} else {
			mPlugin = (WorldEditPlugin) Bukkit.getPluginManager().getPlugin(CompatPlugin.WorldEdit.getName());
			if (Servers.isMC113OrNewer()) {
				if (mPlugin.getDescription().getVersion().compareTo("7.0.0") >= 0
					|| mPlugin.getDescription().getVersion().startsWith("2.")) {
					Bukkit.getConsoleSender().sendMessage(MobHunting.PREFIX + "Enabling compatibility with WorldEdit ("
							+ mPlugin.getDescription().getVersion() + ")");
					supported = true;
				} else if (mPlugin.getDescription().getVersion().compareTo("1.16") >= 0) {
					Bukkit.getConsoleSender()
							.sendMessage(MobHunting.PREFIX + "Enabling compatibility with FastAsyncWorldEdit ("
									+ mPlugin.getDescription().getVersion() + ")");
					supported = true;
				} else {
					Bukkit.getConsoleSender().sendMessage(MobHunting.PREFIX_WARNING
							+ "Your current version of WorldEdit (" + mPlugin.getDescription().getVersion()
							+ ") is not supported by MobHunting. Mobhunting 6.x does only support 7.0.0 and newer.");
				}
			} else {
				if (mPlugin.getDescription().getVersion().compareTo("6.1.0") >= 0) {
					Bukkit.getConsoleSender().sendMessage(MobHunting.PREFIX + "Enabling compatibility with WorldEdit ("
							+ mPlugin.getDescription().getVersion() + ")");
					supported = true;
				} else {
					Bukkit.getConsoleSender().sendMessage(MobHunting.PREFIX_WARNING
							+ "Your current version of WorldEdit (" + mPlugin.getDescription().getVersion()
							+ ") is not supported by MobHunting. Mobhunting does only support 6.1.0 and newer.");
				}
			}
		}
	}

	public static WorldEditPlugin getWorldEdit() {
		return mPlugin;
	}

	public static boolean isEnabledInConfig() {
		return MobHunting.getInstance().getConfigManager().enableIntegrationWorldEdit;
	}

	public static boolean isSupported() {
		return supported;
	}
}
