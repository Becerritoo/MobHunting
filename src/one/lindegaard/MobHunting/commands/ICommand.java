package one.lindegaard.MobHunting.commands;

import java.util.List;

import org.bukkit.command.CommandSender;

public interface ICommand {

	String getName();

	String[] getAliases();

	String getPermission();

	String[] getUsageString(String label, CommandSender sender);

	String getDescription();

	boolean canBeConsole();

	boolean canBeCommandBlock();

	boolean onCommand(CommandSender sender, String label, String[] args);

	List<String> onTabComplete(CommandSender sender, String label, String[] args);
}
