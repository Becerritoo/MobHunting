package one.lindegaard.MobHunting.update;

import one.lindegaard.CustomItemsLib.update.HangarUpdateService;
import one.lindegaard.MobHunting.MobHunting;

public class HangarUpdater extends HangarUpdateService {

	private final MobHunting plugin;

	public HangarUpdater(MobHunting plugin) {
		super(plugin, "Becerritoo", "MobHunting");
		this.plugin = plugin;
	}

	@Override
	protected long getCheckIntervalSeconds() {
		return plugin.getConfigManager().checkEvery;
	}

	@Override
	protected boolean isAutoUpdateEnabled() {
		return plugin.getConfigManager().autoupdate;
	}

	@Override
	protected String getPrefix() {
		return MobHunting.PREFIX;
	}

	@Override
	protected String getCheckingMessage() {
		return plugin.getMessages().getString("mobhunting.commands.update.check");
	}

	@Override
	protected String getVersionFoundMessage(String version) {
		return plugin.getMessages().getString("mobhunting.commands.update.version-found", "newversion", version);
	}

	@Override
	protected String getNoUpdateMessage() {
		return plugin.getMessages().getString("mobhunting.commands.update.no-update");
	}

	@Override
	protected String getUpdateCompleteMessage() {
		return plugin.getMessages().getString("mobhunting.commands.update.complete");
	}

	@Override
	protected String getCouldNotUpdateMessage() {
		return plugin.getMessages().getString("mobhunting.commands.update.could-not-update");
	}

	@Override
	protected String getUpdateHelpMessage(String projectUrl) {
		return plugin.getMessages().getString("mobhunting.commands.update.help") + " " + projectUrl;
	}
}
