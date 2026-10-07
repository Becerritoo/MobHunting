package one.lindegaard.MobHunting.commands;

import one.lindegaard.BagOfGold.BagOfGold;
import one.lindegaard.CustomItemsLib.Core;
import one.lindegaard.CustomItemsLib.Tools;
import one.lindegaard.CustomItemsLib.rewards.CoreCustomItems;
import one.lindegaard.CustomItemsLib.rewards.Reward;
import one.lindegaard.CustomItemsLib.rewards.RewardType;
import one.lindegaard.MobHunting.MobHunting;
import one.lindegaard.MobHunting.compatibility.BagOfGoldCompat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MoneyCommand implements ICommand {

	private MobHunting plugin;

	public MoneyCommand(MobHunting plugin) {
		this.plugin = plugin;
	}

	// Admin command
	// /mh money drop <amount> - to drop <amount money> where player look.
	// Permission needed mobhunt.money.drop

	// /mh money drop <playername> <amount> - to drop <amount money> where
	// player look.
	// Permission needed mobhunt.money.drop

	// /mh money sell - to sell all bag of gold you are holding in your hand.
	// Permission needed mobhunt.money.sell

	// /mh money sell <amount> - to sell <amount money> of the bag of gold you
	// have in your hand.
	// Permission needed mobhunt.money.sell

	@Override
	public String getName() {
		return "money";
	}

	@Override
	public String[] getAliases() {
		return new String[] { "gold", "bag", Core.getConfigManager().commandAlias };
	}

	@Override
	public String getPermission() {
		return null; // "mobhunting.money";
	}

	@Override
	public String[] getUsageString(String label, CommandSender sender) {
		return new String[] {
				ChatColor.GOLD + Core.getConfigManager().commandAlias + ChatColor.GREEN + " drop <amount>"
						+ ChatColor.WHITE + " - to drop <amount> of " + Core.getConfigManager().bagOfGoldName.trim()
						+ ", where you look.",
				ChatColor.GOLD + Core.getConfigManager().commandAlias + ChatColor.GREEN + " drop <playername> "
						+ ChatColor.YELLOW + "<amount>" + ChatColor.WHITE + " - to drop <amount> of "
						+ Core.getConfigManager().bagOfGoldName.trim() + " 3 block in front of the <player>.",
				ChatColor.GOLD + Core.getConfigManager().commandAlias + ChatColor.GREEN + " give <player>"
						+ ChatColor.YELLOW + " <amount>" + ChatColor.WHITE + " - to give the player a "
						+ Core.getConfigManager().bagOfGoldName.trim() + " in his inventory. * = all online players.",
				ChatColor.GOLD + Core.getConfigManager().commandAlias + ChatColor.GREEN + " take <player>"
						+ ChatColor.YELLOW + " <amount>" + ChatColor.WHITE + " - to take <amount> gold from the "
						+ Core.getConfigManager().bagOfGoldName.trim()
						+ " in the players inventory. * = all online players.",
				ChatColor.GOLD + Core.getConfigManager().commandAlias + ChatColor.GREEN + " sell" + ChatColor.WHITE
						+ " - to sell the " + Core.getConfigManager().bagOfGoldName.trim() + " in your hand.",
				ChatColor.GOLD + Core.getConfigManager().commandAlias + ChatColor.GREEN + " sell" + ChatColor.YELLOW
						+ " <amount>" + ChatColor.WHITE + " - to sell some of the gold in your "
						+ Core.getConfigManager().bagOfGoldName.trim() + " and get the money.",
				ChatColor.GOLD + Core.getConfigManager().commandAlias + ChatColor.GREEN + " buy" + ChatColor.YELLOW
						+ " <amount>" + ChatColor.WHITE
						+ " - to buy some more gold with your money and put it into your "
						+ Core.getConfigManager().bagOfGoldName.trim() + "." };
	}

	@Override
	public String getDescription() {
		return plugin.getMessages().getString("mobhunting.commands.money.description", "rewardname",
				Core.getConfigManager().bagOfGoldName.trim());
	}

	@Override
	public boolean canBeConsole() {
		return true;
	}

	@Override
	public boolean canBeCommandBlock() {
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, String label, String[] args) {

		if (args.length == 1) {
			// /mh money help
			// Show help
			if (args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("?"))
				return false;
		}

		if (args.length == 0
				|| (args.length >= 1 && (args[0].equalsIgnoreCase("balance") || args[0].equalsIgnoreCase("bal")))) {
			// mh money
			// mh money balance
			// mh money balance <player>
			// show the total amount of "bag of gold" in the players inventory.

			if (sender.hasPermission("mobhunting.money.balance") || sender.hasPermission("mobhunting.money.*")) {
				OfflinePlayer offlinePlayer = null;
				boolean other = false;
				if (args.length <= 1) {
					if (!(sender instanceof Player)) {
						plugin.getMessages().senderSendMessage(sender, ChatColor.RED + plugin.getMessages()
								.getString("mobhunting.commands.base.noconsole", "command", "'money balance'"));
						return true;
					} else
						offlinePlayer = (Player) sender;

				} else {
					if (sender.hasPermission("mobhunting.money.balance.other")
							|| sender.hasPermission("mobhunting.money.*")) {
						offlinePlayer = Bukkit.getServer().getOfflinePlayer(args[1]);
						other = true;
					} else {
						plugin.getMessages().senderSendMessage(sender,
								ChatColor.RED + plugin.getMessages().getString("mobhunting.commands.base.nopermission",
										Core.PH_PERMISSION, "mobhunting.money.balance.other", "command",
										"money <playername>"));
						return true;
					}
				}

				double balance = plugin.getRewardManager().getBalance(offlinePlayer);

				if (other)
					plugin.getMessages().senderSendMessage(sender,
							ChatColor.GREEN
									+ plugin.getMessages().getString("mobhunting.commands.money.balance", "playername",
											offlinePlayer.getName(), "money", plugin.getRewardManager().format(balance),
											"rewardname", ChatColor.valueOf(Core.getConfigManager().rewardTextColor)
													+ Core.getConfigManager().bagOfGoldName.trim()));
				else
					plugin.getMessages().senderSendMessage(sender,
							ChatColor.GREEN + plugin.getMessages().getString("mobhunting.commands.money.balance",
									"playername", "You", "money", plugin.getRewardManager().format(balance),
									"rewardname", ChatColor.valueOf(Core.getConfigManager().rewardTextColor)
											+ Core.getConfigManager().bagOfGoldName.trim()));
			} else {
				plugin.getMessages().senderSendMessage(sender,
						ChatColor.RED + plugin.getMessages().getString("mobhunting.commands.base.nopermission",
								Core.PH_PERMISSION, "mobhunting.money.balance", "command", "money"));
			}
			return true;

		} else if (args.length == 1 && Bukkit.getServer().getOfflinePlayer(args[0]) == null) {
			plugin.getMessages().senderSendMessage(sender, ChatColor.RED + plugin.getMessages()
					.getString("mobhunting.commands.base.unknown_playername", "playername", args[0]));
			return true;

		} else if (args.length >= 2 && args[0].equalsIgnoreCase("drop") || args[0].equalsIgnoreCase("place"))

		{
			// /mh money drop <amount>
			// /mh money drop <player> <amount>
			if (sender.hasPermission("mobhunting.money.drop") || sender.hasPermission("mobhunting.money.*")) {
				if (args.length == 2 && !(sender instanceof Player)) {
					plugin.getMessages().senderSendMessage(sender, ChatColor.RED + plugin.getMessages()
							.getString("mobhunting.commands.base.playername-missing", "player", args[1]));
				} else {
					if (args[1].matches("\\d+(\\.\\d+)?")) {
						Player player = (Player) sender;
						Location location = Tools.getTargetBlock(player, 20).getLocation();
						double money = Tools.floor(Double.valueOf(args[1]));
						if (money > Core.getConfigManager().limitPerBag * 100) {
							money = Core.getConfigManager().limitPerBag * 100;
							plugin.getMessages().senderSendMessage(sender,
									ChatColor.RED
											+ plugin.getMessages().getString("mobhunting.commands.money.to_big_number",
													"number", args[1], "maximum", money));
						}
						plugin.getMessages().debug("The BagOfGold was dropped at %s", location);
						plugin.getRewardManager().dropMoneyOnGround_RewardManager(player, null, location, money);
						plugin.getMessages().playerActionBarMessageQueue(player,
								plugin.getMessages().getString("mobhunting.moneydrop", "rewardname",
										ChatColor.valueOf(Core.getConfigManager().rewardTextColor)
												+ Core.getConfigManager().bagOfGoldName,
										"money", plugin.getRewardManager().format(money)));
					} else if (Bukkit.getServer().getOfflinePlayer(args[1]).isOnline()) {
						if (args.length > 2 && args[2].matches("\\d+(\\.\\d+)?")) {
							Player player = ((Player) Bukkit.getServer().getOfflinePlayer(args[1]));
							Location location = Tools.getTargetBlock(player, 3).getLocation();
							double money = Tools.floor(Double.valueOf(args[2]));
							if (money > Core.getConfigManager().limitPerBag * 100) {
								money = Core.getConfigManager().limitPerBag * 100;
								plugin.getMessages().senderSendMessage(sender,
										ChatColor.RED + plugin.getMessages().getString(
												"mobhunting.commands.money.to_big_number", "number", args[2], "maximum",
												money));
							}
							plugin.getMessages().debug("The BagOfGold was dropped at %s", location);
							plugin.getRewardManager().dropMoneyOnGround_RewardManager(player, null, location, money);
							plugin.getMessages().playerActionBarMessageQueue(player,
									plugin.getMessages().getString("mobhunting.moneydrop", "rewardname",
											ChatColor.valueOf(Core.getConfigManager().rewardTextColor)
													+ Core.getConfigManager().bagOfGoldName.trim(),
											"money", plugin.getEconomyManager().format(money)));
						} else {
							plugin.getMessages().senderSendMessage(sender, ChatColor.RED + plugin.getMessages()
									.getString("mobhunting.commands.base.not_a_number", "number", "{missing}"));
						}
					} else {
						plugin.getMessages().senderSendMessage(sender, ChatColor.RED + plugin.getMessages()
								.getString("mobhunting.commands.base.playername-missing", "player", args[1]));
					}
				}
			} else {
				plugin.getMessages().senderSendMessage(sender,
						ChatColor.RED + plugin.getMessages().getString("mobhunting.commands.base.nopermission",
								Core.PH_PERMISSION, "mobhunting.money.drop", "command", "money drop"));
			}
			return true;

		} else if (args.length >= 2 && args[0].equalsIgnoreCase("give")) {
			// /mh money give <player> <amount>
			// /mh money give * <amount>
			if (sender.hasPermission("mobhunting.money.give") || sender.hasPermission("mobhunting.money.*")) {
				if (args.length == 2 && !(sender instanceof Player)) {
					plugin.getMessages().senderSendMessage(sender, ChatColor.RED + plugin.getMessages()
							.getString("mobhunting.commands.base.playername-missing", "player", args[1]));
					return true;
				}

				OfflinePlayer offlinePlayer = Bukkit.getServer().getOfflinePlayer(args[1]);
				boolean allPlayers = args[1].equals("*");
				if (!allPlayers)
					if (offlinePlayer == null || !offlinePlayer.hasPlayedBefore()) {
						plugin.getMessages().senderSendMessage(sender, ChatColor.RED + plugin.getMessages()
								.getString("mobhunting.commands.base.playername-missing", "player", args[1]));
						return true;
					}

				double amount = 0;
				if (args[2].matches("\\d+(\\.\\d+)?")) {
					amount = Tools.round(Double.valueOf(args[2]));
					if (amount > Core.getConfigManager().limitPerBag * 100) {
						amount = Core.getConfigManager().limitPerBag * 100;
						plugin.getMessages().senderSendMessage(sender, ChatColor.RED + plugin.getMessages().getString(
								"mobhunting.commands.money.to_big_number", "number", args[2], "maximum", amount));
						return true;
					}
				} else {
					plugin.getMessages().senderSendMessage(sender, ChatColor.RED + plugin.getMessages()
							.getString("bagofgold.commands.base.not_a_number", "number", args[2]));
					return true;
				}

				if (BagOfGoldCompat.isSupported()) {
					if (allPlayers)
						for (Player player2 : Tools.getOnlinePlayers())
							BagOfGold.getAPI().depositPlayer(player2, amount);
					else
						BagOfGold.getAPI().depositPlayer(offlinePlayer, amount);
					plugin.getMessages().debug("BagOfGold supported, using depositPlayer");
					return true;
				}

				if (plugin.getConfigManager().dropMoneyOnGroundUseItemAsCurrency) {
					if (allPlayers) {
						for (Player player : Tools.getOnlinePlayers()) {
							givePlayerMoney(player, amount);
						}
						plugin.getMessages().senderSendMessage(sender,
								plugin.getMessages().getString("mobhunting.commands.money.give-sender", "rewardname",
										ChatColor.valueOf(Core.getConfigManager().rewardTextColor)
												+ Core.getConfigManager().bagOfGoldName.trim(),
										"money", plugin.getRewardManager().format(amount), "player",
										"all online players"));
						return true;
					}

					if (offlinePlayer.isOnline()) {
						Player player = (Player) offlinePlayer;
						givePlayerMoney(player, amount);
						plugin.getMessages().senderSendMessage(sender,
								plugin.getMessages().getString("mobhunting.commands.money.give-sender", "rewardname",
										ChatColor.valueOf(Core.getConfigManager().rewardTextColor)
												+ Core.getConfigManager().bagOfGoldName.trim(),
										"money", plugin.getRewardManager().format(amount), "player", player.getName()));

					} else {
						plugin.getMessages().senderSendMessage(sender, ChatColor.RED + plugin.getMessages()
								.getString("mobhunting.commands.base.playername-missing", "player", args[1]));
					}

				} else {
					// BagOfGOld is not a currency. So /mh money give 25
					// is the same as /eco give player 25
					if (allPlayers) {
						for (Player player : Tools.getOnlinePlayers())
							plugin.getEconomyManager().depositPlayer(player, amount);
					} else
						plugin.getEconomyManager().depositPlayer(offlinePlayer, amount);
				}

			} else {
				plugin.getMessages().senderSendMessage(sender,
						ChatColor.RED + plugin.getMessages().getString("mobhunting.commands.base.nopermission",
								Core.PH_PERMISSION, "mobhunting.money.give", "command", "money give"));
			}
			return true;
		}

		else if (args.length >= 2 && args[0].equalsIgnoreCase("take")) {
			// /mh money take <player> <amount>
			if (sender.hasPermission("mobhunting.money.take") || sender.hasPermission("mobhunting.money.*")) {
				if (args.length == 2 && !(sender instanceof Player)) {
					plugin.getMessages().senderSendMessage(sender, ChatColor.RED + plugin.getMessages()
							.getString("mobhunting.commands.base.playername-missing", "player", args[1]));
					return true;
				}
				OfflinePlayer offlinePlayer = Bukkit.getServer().getOfflinePlayer(args[1]);
				boolean allPlayers = args[1].equals("*");
				if (!allPlayers)
					if (offlinePlayer == null || !offlinePlayer.hasPlayedBefore()) {
						plugin.getMessages().senderSendMessage(sender, ChatColor.RED + plugin.getMessages()
								.getString("mobhunting.commands.base.playername-missing", "player", args[1]));
						return true;
					}

				double rest = 0;
				if (args[2].matches("\\d+(\\.\\d+)?")) {
					rest = Tools.round(Double.valueOf(args[2]));
					if (rest > Core.getConfigManager().limitPerBag * 100) {
						rest = Core.getConfigManager().limitPerBag * 100;
						plugin.getMessages().senderSendMessage(sender, ChatColor.RED + plugin.getMessages().getString(
								"mobhunting.commands.money.to_big_number", "number", args[2], "maximum", rest));
						return true;
					}
				} else {
					plugin.getMessages().senderSendMessage(sender, ChatColor.RED + plugin.getMessages()
							.getString("bagofgold.commands.base.not_a_number", "number", args[2]));
					return false;
				}

				double taken = 0;
				if (BagOfGoldCompat.isSupported()) {
					if (allPlayers)
						for (Player player : Tools.getOnlinePlayers())
							BagOfGold.getAPI().withdrawPlayer(player, rest);
					else
						BagOfGold.getAPI().withdrawPlayer(offlinePlayer, rest);
					plugin.getMessages().debug("BagOfGold supported, using withdrawPlayer");
					return true;
				}

				if (plugin.getConfigManager().dropMoneyOnGroundUseItemAsCurrency) {
					if (allPlayers) {
						for (Player player : Tools.getOnlinePlayers()) {
							taken = plugin.getRewardManager().removeBagOfGoldPlayer(player, rest);
							if (taken != 0) {
								if (offlinePlayer.isOnline())
									plugin.getMessages().playerActionBarMessageQueue((Player) offlinePlayer,
											plugin.getMessages().getString("mobhunting.commands.money.take",
													"rewardname",
													ChatColor.valueOf(Core.getConfigManager().rewardTextColor)
															+ Core.getConfigManager().bagOfGoldName,
													"money", plugin.getRewardManager().format(taken)));

							}
						}
						plugin.getMessages().senderSendMessage(sender,
								plugin.getMessages().getString("mobhunting.commands.money.take-sender", "rewardname",
										ChatColor.valueOf(Core.getConfigManager().rewardTextColor)
												+ Core.getConfigManager().bagOfGoldName.trim(),
										"money", plugin.getRewardManager().format(taken), "player",
										offlinePlayer.getName()));
						return true;
					} else {
						Player player = ((Player) Bukkit.getServer().getOfflinePlayer(args[1]));
						taken = plugin.getRewardManager().removeBagOfGoldPlayer(player, rest);
						if (taken != 0) {
							if (offlinePlayer.isOnline())
								plugin.getMessages().playerActionBarMessageQueue((Player) offlinePlayer,
										plugin.getMessages().getString("mobhunting.commands.money.take", "rewardname",
												ChatColor.valueOf(Core.getConfigManager().rewardTextColor)
														+ Core.getConfigManager().bagOfGoldName,
												"money", plugin.getRewardManager().format(taken)));
							plugin.getMessages().senderSendMessage(sender, plugin.getMessages().getString(
									"mobhunting.commands.money.take-sender", "rewardname",
									ChatColor.valueOf(Core.getConfigManager().rewardTextColor)
											+ Core.getConfigManager().bagOfGoldName.trim(),
									"money", plugin.getRewardManager().format(taken), "player",
									offlinePlayer.getName()));
						}
					}
				} else {
					// BagOfGold is not a currency. /mh money take player 25
					// will work as /eco take player 25
					if (allPlayers)
						for (Player player : Tools.getOnlinePlayers())
							plugin.getEconomyManager().withdrawPlayer(player, rest);
					else
						plugin.getEconomyManager().withdrawPlayer(offlinePlayer, rest);
				}

			} else {
				plugin.getMessages().senderSendMessage(sender,
						ChatColor.RED + plugin.getMessages().getString("mobhunting.commands.base.nopermission",
								Core.PH_PERMISSION, "mobhunting.money.take", "command", "money take"));
			}
			return true;

		} else if ((args.length == 1 && args[0].equalsIgnoreCase("sell"))
				|| (args.length == 2 && args[0].equalsIgnoreCase("sell") && (args[1].matches("\\d+(\\.\\d+)?")))) {
			// /mh money sell
			// /mh money sell <amount>
			if (sender.hasPermission("mobhunting.money.sell") || sender.hasPermission("mobhunting.money.*")) {
				if (!(sender instanceof Player)) {
					plugin.getMessages().senderSendMessage(sender, ChatColor.RED + plugin.getMessages()
							.getString("mobhunting.commands.base.noconsole", "command", "'money sell'"));
					return true;
				}

				Player player = (Player) sender;
				if (args.length == 1) {
					ItemStack is = player.getItemInHand();
					if (Reward.isReward(is)) {
						Reward reward = Reward.getReward(is);
						if (BagOfGoldCompat.isSupported() && reward.isBagOfGoldReward()) {
							plugin.getMessages().playerSendMessage(player,
									plugin.getMessages().getString("mobhunting.money.you_cant_sell_and_buy_bagofgold",
											"itemname", reward.getDisplayName()));
							return true;
						}
						plugin.getEconomyManager().depositPlayer(player, reward.getMoney());
						is.setType(Material.AIR);
						is.setAmount(0);
						is.setItemMeta(null);
						player.setItemInHand(is);
						plugin.getMessages().playerActionBarMessageQueue(player,
								plugin.getMessages().getString("mobhunting.commands.money.sell", "rewardname",
										ChatColor.valueOf(Core.getConfigManager().rewardTextColor)
												+ reward.getDisplayName(),
										"money", plugin.getRewardManager().format(reward.getMoney())));
					} else {
						plugin.getMessages().playerSendMessage(player,
								plugin.getMessages().getString("mobhunting.bagofgoldsign.hold_reward_in_hand",
										"rewardname", ChatColor.valueOf(Core.getConfigManager().rewardTextColor)
												+ Core.getConfigManager().bagOfGoldName));

					}
				} else if ((args[0].equalsIgnoreCase("sell") && (args[1].matches("\\d+(\\.\\d+)?")))) {
					double sold = 0;
					double toBeSold = Tools.floor(Double.valueOf(args[1]));
					for (int slot = 0; slot < player.getInventory().getSize(); slot++) {
						ItemStack is = player.getInventory().getItem(slot);
						if (Reward.isReward(is)) {
							Reward reward = Reward.getReward(is);
							if (reward.isBagOfGoldReward()) {
								double saldo = Tools.floor(reward.getMoney());
								if (saldo > toBeSold) {
									reward.setMoney(saldo - toBeSold);
									is = CoreCustomItems.getCustomtexture(
											new Reward(Core.getConfigManager().bagOfGoldName.trim(), saldo - toBeSold,
													reward.getRewardType(), reward.getSkinUUID()),
											Core.getConfigManager().skullTextureValue,
											Core.getConfigManager().skullTextureSignature);
									player.getInventory().setItem(slot, is);
									sold = sold + toBeSold;
									toBeSold = 0;
									break;
								} else {
									is.setItemMeta(null);
									is.setType(Material.AIR);
									is.setAmount(0);
									player.getInventory().setItem(slot, is);
									sold = sold + saldo;
									toBeSold = toBeSold - saldo;
								}
							}
						}
					}
					if (sold > 0) {
						plugin.getEconomyManager().depositPlayer(player, sold);
						// plugin.getEconomyManager().depositPlayer(player, sold);
						plugin.getMessages().playerActionBarMessageQueue(player,
								plugin.getMessages().getString("mobhunting.commands.money.sell", "rewardname",
										ChatColor.valueOf(Core.getConfigManager().rewardTextColor)
												+ Core.getConfigManager().bagOfGoldName.trim(),
										"money", plugin.getRewardManager().format(sold)));
					} else {
						plugin.getMessages().playerActionBarMessageQueue(player,
								plugin.getMessages().getString("mobhunting.commands.money.sell.nobag", "rewardname",
										ChatColor.valueOf(Core.getConfigManager().rewardTextColor)
												+ Core.getConfigManager().bagOfGoldName.trim()));
					}
				}
			} else {
				plugin.getMessages().senderSendMessage(sender,
						ChatColor.RED + plugin.getMessages().getString("mobhunting.commands.base.nopermission",
								Core.PH_PERMISSION, "mobhunting.money.sell", "command", "money sell"));
			}
			return true;
		}

		else if (args.length >= 2 && args[0].equalsIgnoreCase("buy")) {
			// /mh money buy <amount>
			Player player = (Player) sender;
			if (sender.hasPermission("mobhunting.money.buy") || sender.hasPermission("mobhunting.money.*")) {
				if (BagOfGoldCompat.isSupported()) {
					plugin.getMessages().playerSendMessage(player,
							plugin.getMessages().getString("mobhunting.money.you_cant_sell_and_buy_bagofgold",
									"itemname", Core.getConfigManager().bagOfGoldName));
					return true;
				}
				if (args.length == 2 && args[1].matches("\\d+(\\.\\d+)?")) {
					if (plugin.getEconomyManager().hasMoney(player, Tools.floor(Double.valueOf(args[1])))) {
						// if (BagOfGoldCompat.isSupported()) {
						if (plugin.getConfigManager().dropMoneyOnGroundUseItemAsCurrency) {
							plugin.getRewardManager().dropMoneyOnGround_RewardManager(player, null,
									player.getLocation(), Tools.floor(Double.valueOf(args[1])));
						} else if (player.getInventory().firstEmpty() == -1)
							plugin.getRewardManager().dropMoneyOnGround_RewardManager(player, null,
									player.getLocation(), Tools.floor(Double.valueOf(args[1])));
						else {
							ItemStack is = CoreCustomItems.getCustomtexture(
									new Reward(Core.getConfigManager().bagOfGoldName.trim(),
											Tools.floor(Double.valueOf(args[1])), RewardType.BAGOFGOLD,
											UUID.fromString(RewardType.BAGOFGOLD.getUUID())),
									Core.getConfigManager().skullTextureValue,
									Core.getConfigManager().skullTextureSignature);
							player.getInventory().addItem(is);
						}
						plugin.getRewardManager().withdrawPlayer(player, Tools.floor(Double.valueOf(args[1])));
						plugin.getMessages().playerActionBarMessageQueue(player,
								plugin.getMessages().getString("mobhunting.commands.money.buy", "rewardname",
										ChatColor.valueOf(Core.getConfigManager().rewardTextColor)
												+ Core.getConfigManager().bagOfGoldName,
										"money",
										plugin.getRewardManager().format(Tools.floor(Double.valueOf(args[1])))));
					} else {
						plugin.getMessages().senderSendMessage(sender, ChatColor.RED + plugin.getMessages()
								.getString("mobhunting.commands.money.not-enough-money", "money", args[1]));
					}
				} else {
					plugin.getMessages().senderSendMessage(sender, ChatColor.RED + plugin.getMessages()
							.getString("mobhunting.commands.base.not_a_number", "number", args[1]));
				}
			} else {
				plugin.getMessages().senderSendMessage(sender,
						ChatColor.RED + plugin.getMessages().getString("mobhunting.commands.base.nopermission",
								Core.PH_PERMISSION, "mobhunting.money.buy", "command", "money buy"));
			}
			return true;
		}
		return false;

	}

	private void givePlayerMoney(Player player, double amount) {
		double result = plugin.getRewardManager().addBagOfGoldPlayer(player, amount);
		if (Tools.round(result) != Tools.round(amount)) {
			plugin.getRewardManager().dropMoneyOnGround_RewardManager(player, null, player.getLocation(),
					Tools.floor(amount - result));
		}
		if (Tools.round(result) == Tools.round(amount)) {
			plugin.getMessages().playerActionBarMessageQueue(player,
					plugin.getMessages().getString("mobhunting.commands.money.give", "rewardname",
							ChatColor.valueOf(Core.getConfigManager().rewardTextColor)
									+ Core.getConfigManager().bagOfGoldName.trim(),
							"money", plugin.getRewardManager().format(amount)));
		}
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, String label, String[] args) {
		ArrayList<String> items = new ArrayList<String>();
		if (args.length == 1) {
			items.add("drop");
			items.add("give");
			items.add("take");
			items.add("sell");
			items.add("buy");
			items.add("shop");
		} else if (args.length == 2) {
			for (Player player : Bukkit.getOnlinePlayers())
				items.add(player.getName());
			items.add("*");
		}

		if (!args[args.length - 1].trim().isEmpty()) {
			String match = args[args.length - 1].trim().toLowerCase();

			items.removeIf(name -> !name.toLowerCase().startsWith(match));
		}
		return items;
	}
}
