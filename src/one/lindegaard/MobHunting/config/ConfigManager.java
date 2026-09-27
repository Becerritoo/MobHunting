package one.lindegaard.MobHunting.config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import one.lindegaard.CustomItemsLib.Tools;
import one.lindegaard.CustomItemsLib.config.AutoConfig;
import one.lindegaard.CustomItemsLib.config.ConfigField;
import one.lindegaard.CustomItemsLib.mobs.MobType;
import one.lindegaard.MobHunting.MobHunting;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigManager extends AutoConfig {

	private MobHunting plugin;

	public ConfigManager(MobHunting plugin, File file) {

		super(file);
		this.plugin = plugin;

		setCategoryComment("general", "########################################################################"
				+ "\nMobHunting Configuration File"
				+ "\n########################################################################\n"
				+ "\nOBS! Please note that the plugins/BagOfGold folder contains shared"
				+ "\nsettings, which is used by both MobHunting and BagOfGold." + "\n\n"
				+ "\n########################################################################" + "\nGeneral settings"
				+ "\n########################################################################");

		setCategoryComment("dropmoneyonground",
				"########################################################################"
						+ "\nDropMoneyOnGround for servers WITHOUT the BagOfGold plugin installed"
						+ "\n########################################################################");

		setCategoryComment("database",
				"########################################################################" + "\nDatabase Settings."
						+ "\n########################################################################");

		setCategoryComment("updates", "########################################################################"
				+ "\nUpdate settings" + "\n########################################################################");

		setCategoryComment("example", "########################################################################"
				+ "\nExample of a mob configuration of rewards for killing a mob."
				+ "\n########################################################################"
				+ "\nHere is where you set the base prize in $ for killing a mob of each type"
				+ "\nYou can either set a decimal number ex 1.23 or a range 1.23:2.23"
				+ "\n\nFor each kill you can run a console command to give the player a reward."
				+ "\nYou can use the following variables:"
				+ "\n{killer},{killed},{player},{killed_player},{prize},{world},"
				+ "\n{killerpos},{killedpos}. Killerpos and Killedpos will have the "
				+ "\nformat <x> <y> <z>. Which could be used to /summon items. "
				+ "\nAn example could be /summon apple {killedpos} 2. to summon two apples where"
				+ "\nwhere the mob was killed or /summon apple {killerpos} 1. to summon an"
				+ "\nan apple where the player is." + "\nAnother example could be to give the player permission to fly"
				+ "\nfor 1 hour or use give command to the player items."
				+ "\n\nYou can also specify the message send to the player."
				+ "\nThe text can be color coded with these codes:"
				+ "\nhttp://minecraft.gamepedia.com/Formatting_codes"
				+ "\n\nYou can run many console commands on each line, each command" + "\nmust be separated by |"
				+ "\nThe player will have the cmd run in {mob_cmd_run_chance} times in average. If mob_cmd_run_chance=0 it"
				+ "\nwill never run. If f.ex. mob_cmd_run_chance=0.50 and it will run run every second time in average."
				+ "\n\nThe mobname_head_prize is only used if you want the dropped heads after killing a mob to have a value."
				+ "\nPlease also check the \"dropmoneyonground\" section in this file.");

		setCategoryComment("mobs",
				"########################################################################"
						+ "\nRewards for killing Hostile Mobs."
						+ "\n########################################################################"
						+ "\nHere is where you set the rewards for killing agressive mobs.");

		setCategoryComment("mobs.default", "### Default/Global settings ###"
				+ "\nPossible message_type values: Chat, ActionBar, BossBar, Title, Subtitle, None");
		setCategoryComment("mobs.blaze", "### Blaze settings ###");
		setCategoryComment("mobs.cave_spider", "### Cave Spider settings ###");
		setCategoryComment("mobs.creeper", "### Creeper settings ###");
		setCategoryComment("mobs.drowned", "### Drowned settings ###");
		setCategoryComment("mobs.elder_guardian", "### Elder Guardian settings ###");
		setCategoryComment("mobs.enderman", "### Enderman settings ###");
		setCategoryComment("mobs.endermite", "### Endermite settings ###");
		setCategoryComment("mobs.evoker", "### Evoker settings ###");
		setCategoryComment("mobs.ghast", "### Ghast settings ###");
		setCategoryComment("mobs.giant", "### Giant settings ###");
		setCategoryComment("mobs.iron_golem", "### Iron Golem settings ###");
		setCategoryComment("mobs.guardian", "### Guardian settings ###");
		setCategoryComment("mobs.hoglin", "### Hoglin settings ###");
		setCategoryComment("mobs.husk", "### Husk settings ###");
		setCategoryComment("mobs.killer_rabbit", "### Killer Rabbit settings ###");
		setCategoryComment("mobs.magma_cube", "### Magma Cube settings ###");
		setCategoryComment("mobs.phantom", "### Phantom settings ###");
		setCategoryComment("mobs.piglin", "### Piglin settings ###");
		setCategoryComment("mobs.pillager", "### Pillager settings ###");
		setCategoryComment("mobs.polar_bear", "### Polar Bear settings ###");
		setCategoryComment("mobs.ravager", "### Ravager settings ###");
		setCategoryComment("mobs.slime", "### Slime settings ###");
		setCategoryComment("mobs.shulker", "### Shulker settings ###");
		setCategoryComment("mobs.silverfish", "### Silverfish settings ###");
		setCategoryComment("mobs.skeleton", "### Skeleton settings ###");
		setCategoryComment("mobs.spider", "### Spider settings ###");
		setCategoryComment("mobs.strider", "### Strider settings ###");
		setCategoryComment("mobs.stray", "### Stray settings ###");
		setCategoryComment("mobs.zoglin", "### Zoglin settings ###");
		setCategoryComment("mobs.zombie", "### Zombie settings ###");
		setCategoryComment("mobs.zombie_pigman", "### Zombie Pigman settings ###");
		setCategoryComment("mobs.vex", "### Vex settings ###");
		setCategoryComment("mobs.vindicator", "### Vindicator settings ###");
		setCategoryComment("mobs.warden", "### Warden settings ###");
		setCategoryComment("mobs.witch", "### Witch settings ###");
		setCategoryComment("mobs.wither_skeleton", "### Wither Skeleton settings ###");

		setCategoryComment("boss",
				"########################################################################"
						+ "\nRewards for killing bosses"
						+ "\n########################################################################"
						+ "\nHere is where you set the base prize in $ for killing the bosses");

		setCategoryComment("boss.wither", "### Wither settings ###");
		setCategoryComment("boss.ender_dragon", "### Ender Dragon settings ###");

		setCategoryComment("villager",
				"########################################################################"
						+ "\nRewards for killing villagers"
						+ "\n########################################################################"
						+ "\nHere is where you set the base prize in $ for killing the villagers"
						+ "\nMobHunting only handle Villagers on profession level, all careers is "
						+ "\nhandles as their profession. Info anbout Profession and Caarer:"
						+ "\nhttp://minecraft.gamepedia.com/Villager#Professions_and_careers");

		setCategoryComment("villager.armorer", "### Armorer settings ###");
		setCategoryComment("villager.butcher", "### Butcher settings ###");
		setCategoryComment("villager.cartographer", "### Cartographer settings ###");
		setCategoryComment("villager.cleric", "### Cleric settings ###");
		setCategoryComment("villager.farmer", "### Farmer settings ###");
		setCategoryComment("villager.fisherman", "### Fisherman settings ###");
		setCategoryComment("villager.fletcher", "### Fletcher settings ###");
		setCategoryComment("villager.leatherworker", "### Leatherworker settings ###");
		setCategoryComment("villager.librarian", "### Librarian settings ###");
		setCategoryComment("villager.mason", "### Mason settings ###");
		setCategoryComment("villager.nitwit", "### Nitwit settings ###");
		setCategoryComment("villager.villager", "### Villager settings (No profession) ###");
		setCategoryComment("villager.shepherd", "### Shepherd settings ###");
		setCategoryComment("villager.toolsmith", "### Toolsmith settings ###");
		setCategoryComment("villager.wanderingtrader", "### Wandering Trader settings ###");
		setCategoryComment("villager.weaponsmith", "### Weaponsmith settings ###");

		// This is passive monster - not a villaer
		setCategoryComment("villager.zombie_villager",
				"### Zombie Villager settings - This is a passive monster, not a villager ###");

		// deprecated in mc 1.14
		setCategoryComment("villager.blacksmith", "### Blacksmith settings - deprecated in mc 1.14 ###");
		setCategoryComment("villager.priest", "### Priest settings - deprecated in mc 1.14 ###");

		// Unused mobs are found in the source code and can be spawned using /summon,
		// but are unavailable in normal gameplay.
		setCategoryComment("villager.illusioner", "### Illusioner settings ###");

		setCategoryComment("passive",
				"########################################################################"
						+ "\nRewards for killing passive mobs"
						+ "\n########################################################################"
						+ "\nHere is where you set the base prize in $ for killing passive/friendly mobs."
						+ "\nBy default the player does not get a reward for killing friendly mobs."
						+ "\nIf you make the number negative, the reward will be a fine for killing a passive animal.");

		setCategoryComment("passive.allay", "### Allay settings ###");
		setCategoryComment("passive.axolotl", "### Axolotl settings ###");
		setCategoryComment("passive.bat", "### Bat settings ###");
		setCategoryComment("passive.bee", "### Bee settings ###");
		setCategoryComment("passive.camel", "### Camel settings ###");
		setCategoryComment("passive.cat", "### Cat settings ###");
		setCategoryComment("passive.chicken", "### Chicken settings ###");
		setCategoryComment("passive.cow", "### Cow settings ###");
		setCategoryComment("passive.dolphin", "### Dolphin settings ###");
		setCategoryComment("passive.donkey", "### Donkey settings ###");
		setCategoryComment("passive.fox", "### Fox settings ###");
		setCategoryComment("passive.frog", "### Frog settings ###");
		setCategoryComment("passive.goat", "### Goat settings ###");
		setCategoryComment("passive.horse", "### Horse settings ###");
		setCategoryComment("passive.llama", "### Llama settings ###");
		setCategoryComment("passive.mule", "### Mule settings ###");
		setCategoryComment("passive.mushroom_cow", "### Mushroom Cow settings ###");
		setCategoryComment("passive.ocelot", "### Ocelot settings ###");
		setCategoryComment("passive.panda", "### Panda settings ###");
		setCategoryComment("passive.parrot", "### Parrot settings ###");
		setCategoryComment("passive.pig", "### Pig settings ###");
		setCategoryComment("passive.rabbit", "### Rabbit settings ###");
		setCategoryComment("passive.sheep", "### Sheep settings ###");
		setCategoryComment("passive.skeleton_horse", "### Skeleton Horse settings ###");
		setCategoryComment("passive.sniffer", "### Sniffer settings ###");
		setCategoryComment("passive.snowman", "### Snowman settings ###");
		setCategoryComment("passive.squid", "### Squid settings ###");
		setCategoryComment("passive.tadpole", "### Tadpole settings ###");
		setCategoryComment("passive.traderllama", "### Trader Llama settings ###");
		setCategoryComment("passive.turtle", "### Turtle settings ###");
		setCategoryComment("passive.wolf", "### Wolf settings ###");
		setCategoryComment("passive.zombie_horse", "### Zombie Horse settings ###");

		setCategoryComment("fishing",
				"########################################################################" + "\nRewards for fishing"
						+ "\n########################################################################"
						+ "\nHere is where you set the base prize in $ for catching a fish");

		setCategoryComment("fishing.cod", "### Raw Cod settings ###");
		setCategoryComment("fishing.salmon", "### Raw Salmon settings ###");
		setCategoryComment("fishing.tropical_fish", "### Tropical Fish settings ###");
		setCategoryComment("fishing.pufferfish", "### Pufferfish settings ###");

		setCategoryComment("pvp", "########################################################################"
				+ "\nPvp rewards" + "\n########################################################################"
				+ "\nPvp configuration. Set pvp_allowed = true if you want give the players a reward when they kill eachother."
				+ "\nYou can alsp run a console command when this happens to give the player a reward or punish him."
				+ "\nYou can you the following variables {player},{world},{killed_player}."
				+ "\nAn example could be to give the player permission to fly "
				+ "\nfor 1 hour or use give command to the player items."
				+ "\nYou can also specify the message send to the player."
				+ "\nYou can run many console commands on each line, each command" + "\nmust be separated by |");

		setCategoryComment("pvp.player.money",
				"The kill prize can be a number to steal x dollars from the killed player,"
						+ "\nor it can be a cut in percent of his balance. Rob from victiom is about where the money comes from."
						+ "\nIf FALSE the money comes from from the server, if TRUE the money comes from the dead player."
						+ "\nIf you dont want the player to get any money for PVP kills, you MUST set pvp_kill_prize: 0");

		setCategoryComment("pvp.player.head", "Drop a head of the killed player");

		setCategoryComment("achievements",
				"########################################################################"
						+ "\nSpecial / Achievements rewards"
						+ "\n########################################################################"
						+ "\nHere is where you set the prize in $ for achieving a special kill. "
						+ "\nFor each achievment you can run a console command to give the player a reward. "
						+ "\nYou can use the following variables {player},{world}, {killerpos},"
						+ "\n{monstertype} and more can be added on request."
						+ "\nmonstertype is the monstername. A valid list can be found in your "
						+ "\nlang file. Ex. if it is mobs.skeleton.name, monstertype will return skeleton"
						+ "\nAn example command could be to give the player permission to fly "
						+ "\nfor 1 hour or use give command to the player items."
						+ "\nYou can also specify the message send to the player."
						+ "\nYou can run many console commands on each line, each command" + "\nmust be separated by |"
						+ "\nAchievements will not be shown in the GUI if there is a reward for killing the mob,"
						+ "\nunless you set show_achievements_without_reward=true.");

		setCategoryComment("achievements.specials",
				"########################################################################" + "\n### Specials ###"
						+ "\n########################################################################");

		setCategoryComment("achievements.hunter",
				"########################################################################" + "\n### Hunter Levels ###"
						+ "\n########################################################################");

		setCategoryComment("achievements.hunter.mob_level", "Achievement Hunter Levels - First Mob level"
				+ "\nHere is where you set how many mobs to kill to reach next level per mob."
				+ "\nYou can only set the number of mobs to kill to reach level 1. the next"
				+ "\nlevels is automatically calculated this way." + "\nLevel 1: 100   (100 kills)"
				+ "\nLevel 2: x 2.5 (250 kills)" + "\nLevel 3: x 5   (500 kills)" + "\nLevel 4: x 10  (1000 kills)"
				+ "\nLevel 5: x 25  (2500 kills)" + "\nLevel 6: x 50  (5000 kills)" + "\nLevel 7: x 100 (10000 kills)"
				+ "\nLevel Achievements can be disabled by setting the number to 0");

		setCategoryComment("assists",
				"########################################################################"
						+ "\nRewards for assisting killings"
						+ "\n########################################################################"
						+ "\nThey players can get an extra reward if they help each other killing mobs.");

		setCategoryComment("grinding",
				"########################################################################"
						+ "\nGrinding detection settings"
						+ "\n########################################################################"
						+ "\nHere you can change the behavior of the grinding detection.");

		setCategoryComment("grinding.area", "Area grinding detection."
				+ "\nEnabling this prevents a player from earning too much money from using a mob grinder."
				+ "\nSet 'enable_grinding_detection: false' to disable the grinding detection."
				+ "\nOBS: You can whitelist an area to allow grinding using '/mobhunt whitelistarea <add|remove>'"
				+ "\nif the area is detected as a grinding area. See also '/mobhunt checkgrinding'"
				+ "\nFor each kill MobHunting check the number of kills within the range"
				+ "\nIf number of kills exceeds 10, the reward will decrese with 10% until the 'number of deaths'"
				+ "\nis reached, whereafter the reward will be zero.");

		setCategoryComment("grinding.speed_grinding", "Speed grinding detection."
				+ "\nLimit the number of mobs which can be killed with in a given timeframe");

		setCategoryComment("grinding.farms", "Detect Grinding Farms."
				+ "\nWhen this is true, the plugin will try to detect if the players has build a Mob Grinding Farm."
				+ "\nFarm detection can be completly disabled or you can whitelist an area using the whitelist"
				+ "\ncommand if you want the players to harvest mobs from a farm.");

		setCategoryComment("grinding.farms.nether_gold_farms", "Nether Gold Farm detection."
				+ "\nWhen this is true, the plugin will try to detect if the players has build a Nether Gold Farm."
				+ "\nThere is no guarantie that the plugin can detect all types of Nether Gold farms, but it has"
				+ "\nbeen testet on this one: https://www.youtube.com/watch?v=jQWG9Q7HoUA"
				+ "\nWhen searching for grinding the plugin measures how many mobs dies per timeframe within a range."
				+ "\nBe careful if you chance this number there is a risk for false positives.");

		setCategoryComment("grinding.farms.endermanfarms", "Enderman Farm detection."
				+ "\nWhen this is true, the plugin will try to detect if the players has build an enderman Farm."
				+ "\nThere is no guarantie that the plugin can detect all types of Enderman Farms. When searching"
				+ "\nfor grinding the plugin measures how many mobs dies in the VOID per timeframe within a range."
				+ "\nBe careful if you chance this number there is a risk for false positives.");

		setCategoryComment("grinding.farms.otherfarms",
				"Other Farm detection."
						+ "\nWhen this is true, the plugin will try to detect if the players has build other Farms"
						+ "\nwhere different mobs is falling into death. The plugin is still counting mobs which"
						+ "\ndies from falling, with in a range and a time frame.");

		setCategoryComment("grinding.spawners",
				"########################################################################" + "\nMobspawner settings"
						+ "\n########################################################################");

		setCategoryComment("multiplier",
				"########################################################################" + "\nMultiplier Section"
						+ "\n########################################################################" + "\n");

		setCategoryComment("multiplier.penalty",
				"########################################################################" + "\nPenalty multipliers"
						+ "\n########################################################################"
						+ "\nThese are penalty multipliers that can modify the base prize. "
						+ "\nREMEMBER: These are not in $ but they are a multiplier. "
						+ "\nSetting to 1 will disable them.");

		setCategoryComment("multiplier.killstreak",
				"########################################################################"
						+ "\nReward for kills in a row"
						+ "\n########################################################################"
						+ "\nSet the multiplier when the player kills 1,2,3,4 mob in a row without getting damage."
						+ "\nKillstreak will be disabled if you set the multiplier: 1.0");

		setCategoryComment("multiplier.rank", "########################################################################"
				+ "\nRank multipliers" + "\n########################################################################"
				+ "\nYou can add multipliers for players with different ranks/groups. To do this\"\n"
				+ "\nyou must set give the user/group permissions with a format like this:"
				+ "\nmobhunting.multiplier.guest" + "\nmobhunting.multiplier.guardian" + "\nmobhunting.multiplier.staff"
				+ "\nmobhunting.multiplier.hasVoted" + "\nmobhunting.multiplier.donator"
				+ "\nmobhunting.multiplier.op <____ Notice 'op' is reserved for OP'ed players!"
				+ "\nOP'ed players will only get the OP multiplier"
				+ "\nyou can make your own permission nodes. You just need to keep the format"
				+ "\nmobhunting.multiplier.name 'value' in your permissions file and the "
				+ "\nformat below in this file.");

		setCategoryComment("multiplier.difficulty",
				"########################################################################" + "\nPenalty multipliers"
						+ "\n########################################################################"
						+ "\nYou can chance the multiplier for different world difficulties."
						+ "\nA player which play in a HARD world should get more that a player "
						+ "\na player who is player in a peaceful world.The difficulty multipliers"
						+ "\nwith the mobs basic reward."
						+ "\nREMEMBER: These are not money, but a multiplier. Setting to 1 will disable them.");

		setCategoryComment("multiplier.world",
				"########################################################################" + "\nPenalty multipliers"
						+ "\n########################################################################"
						+ "\nYou can change the multiplier for different worlds."
						+ "\nIf the world does not exist in this list the multiplier"
						+ "\nwill be set to 1, which is neutral/no bonus. Add you"
						+ "\nown world names and a multiplier.");

		setCategoryComment("multiplier.bonus",
				"########################################################################" + "\n Bonus multipliers"
						+ "\n########################################################################"
						+ "\nThese are bonus multipliers that can modify the base prize. "
						+ "\nREMEMBER: These are not in $ but they are a multiplier. "
						+ "\nSetting to 1 will disable them.");

		setCategoryComment("bounties",
				"########################################################################" + "\nBounty settings"
						+ "\n########################################################################"
						+ "\nHere you can set the behavior of the Bounty Command or you can disable"
						+ "\nthe command completely.");

		setCategoryComment("happyhour",
				"########################################################################" + "\nHappy Hour settings"
						+ "\n########################################################################"
						+ "\nHere you can set the behavior of the Happy Hour event.");

		setCategoryComment("plugins",
				"########################################################################"
						+ "\nIntegration to other plugins."
						+ "\n########################################################################");

		setCategoryComment("plugins.disguises",
				"########################################################################" + "\nDisguises rewards"
						+ "\n########################################################################"
						+ "\nHere is where can define the actions when a player is under disguise (attacker)"
						+ "\n or when the attacked (victim)");

		setCategoryComment("plugins.citizens",
				"########################################################################"
						+ "\nCitizens / MasterMobHunter settings."
						+ "\n########################################################################");

		setCategoryComment("plugins.stackedmobs",
				"########################################################################" + "\nStacked mobs settings"
						+ "\n########################################################################"
						+ "\nHere you can chance the behavior of stacked mobs integration, or you can disable"
						+ "\nintegration completely.");

		setCategoryComment("plugins.custommobs",
				"########################################################################" + "\nCustomMob settings"
						+ "\n########################################################################"
						+ "\nHere you can chance the behavior of CustomMobs Integration, or you can disable"
						+ "\nintegration completely." + "\nhttps://www.spigotmc.org/resources/custommobs.7339/");

		setCategoryComment("plugins.infernalmobs",
				"########################################################################" + "\nInfernalMobs settings"
						+ "\n########################################################################"
						+ "\nHere you can chance the behavior of InfernalMobs Integration, or you can disable"
						+ "\nintegration completely." + "\nhttps://www.spigotmc.org/resources/infernal_mobs.2156/");

		setCategoryComment("plugins.elitemobs",
				"########################################################################" + "\nInfernalMobs settings"
						+ "\n########################################################################"
						+ "\nHere you can chance the behavior of EliteMobs Integration, or you can disable"
						+ "\nintegration completely."
						+ "\nhttps://www.spigotmc.org/resources/%E2%9A%94elitemobs%E2%9A%94.40090/");

		setCategoryComment("plugins.levelmobs",
				"########################################################################"
						+ "\nLevel Mob Settings (Conquestian / LorinthsRPGMobs / LevelledMobs"
						+ "\n########################################################################");

		setCategoryComment("plugins.levelmobs.conquestiamobs",
				"########################################################################"
						+ "\nConquestia Mobs settings"
						+ "\n########################################################################"
						+ "\nHere you can chance the behavior of ConquestiaMobs Integration, or you can disable"
						+ "\nintegration completely." + "\nhttps://www.spigotmc.org/resources/conquesita_mobs.21307/");

		setCategoryComment("plugins.levelmobs.lorinthsrpgmobs",
				"########################################################################" + "\nLorinthsRPGMobs"
						+ "\n########################################################################"
						+ "\nDisable integration with LorinthsRpgMobs"
						+ "\nhttps://dev.bukkit.org/projects/lorinthsrpgmobs");

		setCategoryComment("plugins.levelmobs.levelledmobs",
				"########################################################################" + "\nLevelledMobs"
						+ "\n########################################################################"
						+ "\nDisable integration with LevelledMobs"
						+ "\nhttps://www.spigotmc.org/resources/levelledmobs-for-1-14-x-1-17-x.74304/");

		setCategoryComment("plugins.factions",
				"########################################################################"
						+ "\nFactions / FactionsUUID settings"
						+ "\n########################################################################"
						+ "\nHere you can chance the behavior of the Factions / FactionsUUID integration, or you can disable"
						+ "\nintegration completely." + "\nhttps://www.spigotmc.org/resources/factions.1900/"
						+ "\nhttps://www.spigotmc.org/resources/factionsuuid.1035/");

		setCategoryComment("plugins.towny",
				"########################################################################" + "\nTowny settings"
						+ "\n########################################################################"
						+ "\nHere you can chance the behavior of the Towny integration, or you can disable"
						+ "\nintegration completely." + "\nhttp://towny.palmergames.com/");

		setCategoryComment("plugins.residence",
				"########################################################################" + "\nResidence settings"
						+ "\n########################################################################"
						+ "\nHere you can chance the behavior of the Residence integration, or you can disable"
						+ "\nintegration completely."
						+ "\nhttps://www.spigotmc.org/resources/residence_1_7_10_up_to_1_11.11480/");

		setCategoryComment("plugins.mcmmo",
				"########################################################################" + "\nIntegration to McMMO"
						+ "\n########################################################################"
						+ "\nThis section only relevant if you use McMMO."
						+ "\nHere you configure if the player will get McMMO Levels for MobHunting kills and"
						+ "\nand the chance to get the xp.");

		setCategoryComment("plugins.crackshot",
				"########################################################################"
						+ "\nIntegration to CrackShot"
						+ "\n########################################################################"
						+ "\nThis section only relevant if you use CrackShot."
						+ "\nHere you configure if the player will get a multiplier for using a CrackShot weapon");

		setCategoryComment("plugins.weaponmechanics",
				"########################################################################"
						+ "\nIntegration to WeaponMechanics"
						+ "\n########################################################################"
						+ "\nThis section only relevant if you use WeaponMechanics."
						+ "\nHere you configure if the player will get a multiplier for using a WeaponMechanics weapon");

		setCategoryComment("plugins.mobarena",
				"########################################################################" + "\nMobArena"
						+ "\n########################################################################");

		setCategoryComment("plugins.pvparena",
				"########################################################################" + "\nPVPArena"
						+ "\n########################################################################"
						+ "\nHere is where can configure how mobhunting acts when killing players while playing PvpArena");

		setCategoryComment("plugins.mythicmobs",
				"########################################################################" + "\nMythicMobs"
						+ "\n########################################################################");

		setCategoryComment("plugins.mypet", "########################################################################"
				+ "\nMyPet" + "\n########################################################################");

		setCategoryComment("plugins.mcmmohorses",
				"########################################################################" + "\nMcMMOHorses"
						+ "\n########################################################################");

		setCategoryComment("plugins.minigames",
				"########################################################################" + "\nMinigames"
						+ "\n########################################################################");

		setCategoryComment("plugins.minigameslib",
				"########################################################################" + "\nMinigamesLib"
						+ "\n########################################################################");

		setCategoryComment("plugins.worldguard",
				"########################################################################" + "\nWorldguard"
						+ "\n########################################################################");

		setCategoryComment("plugins.worldedit",
				"########################################################################" + "\nWorldedit"
						+ "\n########################################################################");

		setCategoryComment("plugins.essentials",
				"########################################################################" + "\nEssentials"
						+ "\n########################################################################");

		setCategoryComment("plugins.battlearena",
				"########################################################################" + "\nBattleArena"
						+ "\n########################################################################");

		setCategoryComment("plugins.vanishnopacket",
				"########################################################################" + "\nVanishNoPackets"
						+ "\n########################################################################");

		setCategoryComment("plugins.gringotts",
				"########################################################################" + "\nGringotts"
						+ "\n########################################################################");

		setCategoryComment("plugins.tardis_weepingangles",
				"########################################################################" + "\nTARDIS Weeping Angels"
						+ "\n########################################################################");

		setCategoryComment("plugins.mysterious_halloween",
				"########################################################################" + "\nMysterousHalloween"
						+ "\n########################################################################");

		setCategoryComment("plugins.smartgiants",
				"########################################################################" + "\nSmartGiants"
						+ "\n########################################################################");

		setCategoryComment("plugins.placeholderapi",
				"########################################################################" + "\nPlaceholderApi"
						+ "\n########################################################################");

		setCategoryComment("plugins.bossshop",
				"########################################################################" + "\nBossShop"
						+ "\n########################################################################");

		setCategoryComment("plugins.extra_hard_mode",
				"########################################################################" + "\nExtraHardMode"
						+ "\n########################################################################");

		setCategoryComment("plugins.herobrine",
				"########################################################################" + "\nHerobrine"
						+ "\n########################################################################");

		setCategoryComment("plugins.boss", "########################################################################"
				+ "\nBoss" + "\n########################################################################");

		setCategoryComment("plugins.holograms",
				"########################################################################" + "\nHolograms"
						+ "\n########################################################################");

		setCategoryComment("plugins.holographic_displays",
				"########################################################################" + "\nHolograpic Displays"
						+ "\n########################################################################");

		setCategoryComment("plugins.precious_stones",
				"########################################################################" + "\nPrecious Stones"
						+ "\n########################################################################");

	}

	// #####################################################################################
	// Hostile Mobs
	// #####################################################################################
	@ConfigField(name = "enabled", category = "example.mobname", comment = "Enable MobHunting rewards for this mob (true or false)")
	public boolean exampleEnabled = true;
	@ConfigField(name = "message", category = "example.mobname", comment = "The message you want when this mob is killed")
	public String exampleMessage = "The Mobname dropped {prize} BagOfGold.";
	@ConfigField(name = "amount", category = "example.mobname.money", comment = "The amount of money you want to be dropped / paid")
	public String exampleMoney = "10.0";
	@ConfigField(name = "chance", category = "example.mobname.money", comment = "The chance to drop/pay the amount of money (0-1)")
	public double exampleMoneyChance = 1;
	@ConfigField(name = "commands", category = "example.mobname", comment = "You can use any command you want, each command has some options."
			+ "\n 'cmd:' and 'chance:' is mandatory fields, 'message:' 'message_type:' and 'permission:' is optional"
			+ "\nIf you add a permission, the command will only be run if the player has this permission."
			+ "\nmessage_type can be: Chat, ActionBar, BossBar, Title, Subtitle or None. Default/Fallback is Chat. The words"
			+ " \nare case sensitive and you you will need a supporting plugin. Ex. TitleManager,ActionBar,BossBar")
	public List<HashMap<String, String>> exampleCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "pex user {player} add any.permission {world}");
		values1.put("chance", "0.5");
		values1.put("message", "You got permission to do ..... ");
		exampleCommands.add(values1);

		HashMap<String, String> values2 = new HashMap<String, String>();
		values2.put("cmd", "give {player} iron_ingot_ingot 1");
		values2.put("chance", "0.2");
		values2.put("message", "You got an iron ingot!");
		values2.put("message_type", "ActionBar");
		exampleCommands.add(values2);

		HashMap<String, String> values3 = new HashMap<String, String>();
		values3.put("cmd", "say {player} killed an {killed}");
		values3.put("chance", "1");
		values3.put("message", "You killed an §1{killed}");
		exampleCommands.add(values3);

		HashMap<String, String> values4 = new HashMap<String, String>();
		values4.put("cmd", "Say {player} killed an {killed}");
		values4.put("chance", "1");
		values4.put("message", "You shout to all other players.");
		values4.put("permission", "any.permission");
		values4.put("message_type", "Title");
		exampleCommands.add(values4);

	}

	@ConfigField(name = "drophead", category = "example.mobname.head", comment = "Set to true or false if you want a head to be dropped as a reward")
	public boolean exampleHeadDropHead = true;
	@ConfigField(name = "value", category = "example.mobname.head", comment = "The value you want the head to have when dropped")
	public String exampleHeadValue = "5";
	@ConfigField(name = "chance", category = "example.mobname.head", comment = "The chance to drop a head (a number between 0 and 1")
	public double exampleHeadChance = 0.5;
	@ConfigField(name = "message", category = "example.mobname.head", comment = "The message you want when a head is dropped")
	public String exampleHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Default============================================
	@ConfigField(name = "default.message_type", category = "mobs")
	public String defaultMessageType = "ActionBar";
	@ConfigField(name = "default.money.message_type", category = "mobs")
	public String defaultMoneyMessageType = "ActionBar";
	@ConfigField(name = "default.head.message_type", category = "mobs")
	public String defaultHeadMessageType = "ActionBar";

	// =====Blaze============================================
	@ConfigField(name = "blaze.enabled", category = "mobs")
	public boolean blazeEnabled = true;
	@ConfigField(name = "blaze.message", category = "mobs")
	public String blazeMessage = "You killed a §1{killed}";
	@ConfigField(name = "blaze.money.amount", category = "mobs")
	public String blazeMoney = "10.0";
	@ConfigField(name = "blaze.money.chance", category = "mobs")
	public double blazeMoneyChance = 1;
	@ConfigField(name = "blaze.commands", category = "mobs")
	public List<HashMap<String, String>> blazeCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.10");
		blazeCommands.add(values1);
	}
	@ConfigField(name = "blaze.head.drophead", category = "mobs")
	public boolean blazeHeadDropHead = true;
	@ConfigField(name = "blaze.head.value", category = "mobs")
	public String blazeHeadPrize = "0";
	@ConfigField(name = "blaze.head.chance", category = "mobs")
	public double blazeHeadDropChance = 0.10;
	@ConfigField(name = "blaze.head.message", category = "mobs")
	public String blazeHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Cave Spider============================================
	@ConfigField(name = "cave_spider.enabled", category = "mobs")
	public boolean caveSpiderEnabled = true;
	@ConfigField(name = "cave_spider.message", category = "mobs")
	public String caveSpiderMessage = "You killed a §1{killed}";
	@ConfigField(name = "cave_spider.money.amount", category = "mobs")
	public String caveSpiderMoney = "10:20";
	@ConfigField(name = "cave_spider.money.chance", category = "mobs")
	public double caveSpiderMoneyChance = 1;
	@ConfigField(name = "cave_spider.commands", category = "mobs")
	public List<HashMap<String, String>> caveSpiderCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} torch 5");
		values1.put("chance", "0.10");
		caveSpiderCommands.add(values1);
	}
	@ConfigField(name = "cave_spider.head.drophead", category = "mobs")
	public boolean caveSpiderHeadDropHead = true;
	@ConfigField(name = "cave_spider.head.value", category = "mobs")
	public String caveSpiderHeadPrize = "0";
	@ConfigField(name = "cave_spider.head.chance", category = "mobs")
	public double caveSpiderHeadDropChance = 0.1;
	@ConfigField(name = "cave_spider.head.message", category = "mobs")
	public String caveSpiderHeadMessage = "§aThe §1{killed} §adropped a skull on the ground.";

	// =====Creeper============================================
	@ConfigField(name = "creeper.enabled", category = "mobs")
	public boolean creeperEnabled = true;
	@ConfigField(name = "creeper.message", category = "mobs")
	public String creeperMessage = "You killed a §1{killed}";
	@ConfigField(name = "creeper.money.amount", category = "mobs")
	public String creeperMoney = "10.0";
	@ConfigField(name = "creeper.money.chance", category = "mobs")
	public double creeperMoneyChance = 1;
	@ConfigField(name = "creeper.commands", category = "mobs")
	public List<HashMap<String, String>> creeperCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} emerald 1");
		values1.put("chance", "0.05");
		creeperCommands.add(values1);
	}
	@ConfigField(name = "creeper.head.drophead", category = "mobs")
	public boolean creeperHeadDropHead = true;
	@ConfigField(name = "creeper.head.value", category = "mobs")
	public String creeperHeadPrize = "0";
	@ConfigField(name = "creeper.head.chance", category = "mobs")
	public double creeperHeadDropChance = 0.05;
	@ConfigField(name = "creeper.head.message", category = "mobs")
	public String creeperHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Dolphin============================================
	@ConfigField(name = "dolphin.enabled", category = "mobs")
	public boolean dolphinEnabled = true;
	@ConfigField(name = "dolphin.message", category = "mobs")
	public String dolphinMessage = "You killed a §1{killed}";
	@ConfigField(name = "dolphin.money.amount", category = "mobs")
	public String dolphinMoney = "3:10";
	@ConfigField(name = "dolphin.money.chance", category = "mobs")
	public double dolphinMoneyChance = 1;
	@ConfigField(name = "dolphin.commands", category = "mobs")
	public List<HashMap<String, String>> dolphinCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} prismarine_shard 1");
		values1.put("chance", "0.33");
		dolphinCommands.add(values1);
	}
	@ConfigField(name = "dolphin.head.drophead", category = "mobs")
	public boolean dolphinHeadDropHead = true;
	@ConfigField(name = "dolphin.head.value", category = "mobs")
	public String dolphinHeadPrize = "0";
	@ConfigField(name = "dolphin.head.chance", category = "mobs")
	public double dolphinHeadDropChance = 0.33;
	@ConfigField(name = "dolphin.head.message", category = "mobs")
	public String dolphinHeadMessage = "§aThe §1{killed} §adropped a head in the water";

	// =====Drowned============================================
	@ConfigField(name = "drowned.enabled", category = "mobs")
	public boolean drownedEnabled = true;
	@ConfigField(name = "drowned.message", category = "mobs")
	public String drownedMessage = "You killed a §1{killed}";
	@ConfigField(name = "drowned.money.amount", category = "mobs")
	public String drownedMoney = "5:15";
	@ConfigField(name = "drowned.money.chance", category = "mobs")
	public double drownedMoneyChance = 1;
	@ConfigField(name = "drowned.commands", category = "mobs")
	public List<HashMap<String, String>> drownedCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} prismarine_crystals 1");
		values1.put("chance", "0.33");
		drownedCommands.add(values1);
	}
	@ConfigField(name = "drowned.head.drophead", category = "mobs")
	public boolean drownedHeadDropHead = true;
	@ConfigField(name = "drowned.head.value", category = "mobs")
	public String drownedHeadPrize = "0";
	@ConfigField(name = "drowned.head.chance", category = "mobs")
	public double drownedHeadDropChance = 0.33;
	@ConfigField(name = "drowned.head.message", category = "mobs")
	public String drownedHeadMessage = "§aThe §1{killed} §adropped a skull in the water";

	// =====Elder Guardian============================================
	@ConfigField(name = "elder_guardian.enabled", category = "mobs")
	public boolean elderGuardianEnabled = true;
	@ConfigField(name = "elder_guardian.message", category = "mobs")
	public String elderGuardianMessage = "You killed a §1{killed}";
	@ConfigField(name = "elder_guardian.money.amount", category = "mobs")
	public String elderGuardianMoney = "40:80";
	@ConfigField(name = "elder_guardian.money.chance", category = "mobs")
	public double elderGuardianMoneyChance = 1;
	@ConfigField(name = "elder_guardian.commands", category = "mobs")
	public List<HashMap<String, String>> elderGuardianCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.33");
		elderGuardianCommands.add(values1);
	}
	@ConfigField(name = "elder_guardian.head.drophead", category = "mobs")
	public boolean elderGuardianHeadDropHead = true;
	@ConfigField(name = "elder_guardian.head.value", category = "mobs")
	public String elderGuardianHeadPrize = "0";
	@ConfigField(name = "elder_guardian.head.chance", category = "mobs")
	public double elderGuardianHeadDropChance = 0.33;
	@ConfigField(name = "elder_guardian.head.message", category = "mobs")
	public String elderGuardianHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Enderman============================================
	@ConfigField(name = "enderman.enabled", category = "mobs")
	public boolean endermanEnabled = true;
	@ConfigField(name = "enderman.message", category = "mobs")
	public String endermanMessage = "You killed a §1{killed}";
	@ConfigField(name = "enderman.money.amount", category = "mobs")
	public String endermanMoney = "20:40";
	@ConfigField(name = "enderman.money.chance", category = "mobs")
	public double endermanMoneyChance = 1;
	@ConfigField(name = "enderman.commands", category = "mobs")
	public List<HashMap<String, String>> endermanCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.33");
		endermanCommands.add(values1);
	}
	@ConfigField(name = "enderman.head.drophead", category = "mobs")
	public boolean endermanHeadDropHead = true;
	@ConfigField(name = "enderman.head.value", category = "mobs")
	public String endermanHeadPrize = "0";
	@ConfigField(name = "enderman.head.chance", category = "mobs")
	public double endermanHeadDropChance = 0.33;
	@ConfigField(name = "enderman.head.message", category = "mobs")
	public String endermanHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Endermite============================================
	@ConfigField(name = "endermite.enabled", category = "mobs")
	public boolean endermiteEnabled = true;
	@ConfigField(name = "enderman.message", category = "mobs")
	public String endermiteMessage = "You killed a §1{killed}";
	@ConfigField(name = "endermite.money.amount", category = "mobs")
	public String endermiteMoney = "10";
	@ConfigField(name = "endermite.money.chance", category = "mobs")
	public double endermiteMoneyChance = 1;
	@ConfigField(name = "endermite.commands", category = "mobs")
	public List<HashMap<String, String>> endermiteCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.10");
		endermiteCommands.add(values1);
	}
	@ConfigField(name = "endermite.head.drophead", category = "mobs")
	public boolean endermiteHeadDropHead = true;
	@ConfigField(name = "endermite.head.value", category = "mobs")
	public String endermiteHeadPrize = "1";
	@ConfigField(name = "endermite.head.chance", category = "mobs")
	public double endermiteHeadDropChance = 0.10;
	@ConfigField(name = "endermite.head.message", category = "mobs")
	public String endermiteHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Ghast============================================
	@ConfigField(name = "ghast.enabled", category = "mobs")
	public boolean ghastEnabled = true;
	@ConfigField(name = "ghast.message", category = "mobs")
	public String ghastMessage = "You killed a §1{killed}";
	@ConfigField(name = "ghast.money.amount", category = "mobs")
	public String ghastMoney = "40:80";
	@ConfigField(name = "ghast.money.chance", category = "mobs")
	public double ghastMoneyChance = 1;
	@ConfigField(name = "ghast.commands", category = "mobs")
	public List<HashMap<String, String>> ghastCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.10");
		ghastCommands.add(values1);
	}
	@ConfigField(name = "ghast.head.drophead", category = "mobs")
	public boolean ghastHeadDropHead = true;
	@ConfigField(name = "ghast.head.value", category = "mobs")
	public String ghastHeadPrize = "0";
	@ConfigField(name = "ghast.head.chance", category = "mobs")
	public double ghastHeadDropChance = 0.10;
	@ConfigField(name = "ghast.head.message", category = "mobs")
	public String ghastHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Giant============================================
	@ConfigField(name = "giant.enabled", category = "mobs")
	public boolean giantEnabled = true;
	@ConfigField(name = "giant.message", category = "mobs")
	public String giantMessage = "You killed a §1{killed}";
	@ConfigField(name = "giant.money.amount", category = "mobs")
	public String giantMoney = "5.0";
	@ConfigField(name = "giant.money.chance", category = "mobs")
	public double giantMoneyChance = 1;
	@ConfigField(name = "giant.commands", category = "mobs")
	public List<HashMap<String, String>> giantCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.05");
		giantCommands.add(values1);
	}
	@ConfigField(name = "giant.head.drophead", category = "mobs")
	public boolean giantHeadDropHead = true;
	@ConfigField(name = "giant.head.value", category = "mobs")
	public String giantHeadPrize = "0";
	@ConfigField(name = "giant.head.chance", category = "mobs")
	public double giantHeadDropChance = 0.10;
	@ConfigField(name = "giant.head.message", category = "mobs")
	public String giantHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Iron Golem============================================
	@ConfigField(name = "iron_golem.enabled", category = "mobs")
	public boolean ironGolemEnabled = true;
	@ConfigField(name = "iron_golem.message", category = "mobs")
	public String ironGolemMessage = "You killed a §1{killed}";
	@ConfigField(name = "iron_golem.money.amount", category = "mobs")
	public String ironGolemMoney = "20:40";
	@ConfigField(name = "iron_golem.money.chance", category = "mobs")
	public double ironGolemMoneyChance = 1;
	@ConfigField(name = "iron_golem.commands", category = "mobs")
	public List<HashMap<String, String>> ironGolemCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 5");
		values1.put("chance", "0.10");
		ironGolemCommands.add(values1);
		HashMap<String, String> values2 = new HashMap<String, String>();
		values2.put("cmd", "give {player} redstone 5");
		values2.put("chance", "0.10");
		ironGolemCommands.add(values2);
	}
	@ConfigField(name = "iron_golem.head.drophead", category = "mobs")
	public boolean ironGolemHeadDropHead = true;
	@ConfigField(name = "iron_golem.head.value", category = "mobs")
	public String ironGolemHeadPrize = "0";
	@ConfigField(name = "iron_golem.head.chance", category = "mobs")
	public double ironGolemHeadDropChance = 0.10;
	@ConfigField(name = "iron_golem.head.message", category = "mobs")
	public String ironGolemHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Guardian============================================
	@ConfigField(name = "guardian.enabled", category = "mobs")
	public boolean guardianEnabled = true;
	@ConfigField(name = "guardian.message", category = "mobs")
	public String guardianMessge = "You killed a §1{killed}";
	@ConfigField(name = "guardian.money.amount", category = "mobs")
	public String guardianMoney = "20:40";
	@ConfigField(name = "guardian.money.chance", category = "mobs")
	public double guardianMoneyChance = 1;
	@ConfigField(name = "guardian.commands", category = "mobs")
	public List<HashMap<String, String>> guardianCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.1");
		guardianCommands.add(values1);
	}
	@ConfigField(name = "guardian.head.drophead", category = "mobs")
	public boolean guardianHeadDropHead = true;
	@ConfigField(name = "guardian.head.value", category = "mobs")
	public String guardianHeadPrize = "0";
	@ConfigField(name = "guardian.head.chance", category = "mobs")
	public double guardianHeadDropChance = 0.10;
	@ConfigField(name = "guardian.head.message", category = "mobs")
	public String guardianHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Hoglin============================================
	@ConfigField(name = "hoglin.enabled", category = "mobs")
	public boolean hoglinEnabled = true;
	@ConfigField(name = "hoglin.message", category = "mobs")
	public String hoglinMessage = "You killed a §1{killed}";
	@ConfigField(name = "hoglin.money.amount", category = "mobs")
	public String hoglinMoney = "9:13";
	@ConfigField(name = "hoglin.money.chance", category = "mobs")
	public double hoglinMoneyChance = 1;
	@ConfigField(name = "hoglin.commands", category = "mobs")
	public List<HashMap<String, String>> hoglinCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.20");
		hoglinCommands.add(values1);
	}
	@ConfigField(name = "hoglin.head.drophead", category = "mobs")
	public boolean hoglinHeadDropHead = true;
	@ConfigField(name = "hoglin.head.value", category = "mobs")
	public String hoglinHeadPrize = "0";
	@ConfigField(name = "hoglin.head.chance", category = "mobs")
	public double hoglinHeadDropChance = 0.20;
	@ConfigField(name = "hoglin.head.message", category = "mobs")
	public String hoglinHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Husk============================================
	@ConfigField(name = "husk.enabled", category = "mobs")
	public boolean huskEnabled = true;
	@ConfigField(name = "husk.message", category = "mobs")
	public String huskMessage = "You killed a §1{killed}";
	@ConfigField(name = "husk.money.amount", category = "mobs")
	public String huskMoney = "9:13";
	@ConfigField(name = "husk.money.chance", category = "mobs")
	public double huskMoneyChance = 1;
	@ConfigField(name = "husk.commands", category = "mobs")
	public List<HashMap<String, String>> huskCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.20");
		huskCommands.add(values1);
	}
	@ConfigField(name = "husk.head.drophead", category = "mobs")
	public boolean huskHeadDropHead = true;
	@ConfigField(name = "husk.head.value", category = "mobs")
	public String huskHeadPrize = "0";
	@ConfigField(name = "husk.head.chance", category = "mobs")
	public double huskHeadDropChance = 0.20;
	@ConfigField(name = "husk.head.message", category = "mobs")
	public String huskHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Killer Rabbit============================================
	@ConfigField(name = "killer_rabbit.enabled", category = "mobs")
	public boolean killerRabbitEnabled = true;
	@ConfigField(name = "killer_rabbit.message", category = "mobs")
	public String killerRabbitMessage = "You killed a §1{killed}";
	@ConfigField(name = "killer_rabbit.money.amount", category = "mobs")
	public String killerRabbitMoney = "200";
	@ConfigField(name = "killer_rabbit.money.chance", category = "mobs")
	public double killerRabbitMoneyChance = 1;
	@ConfigField(name = "killer_rabbit.commands", category = "mobs")
	public List<HashMap<String, String>> killerRabbitCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.25");
		values1.put("message", "");
		killerRabbitCommands.add(values1);
	}
	@ConfigField(name = "killer_rabbit.head.drophead", category = "mobs")
	public boolean killerRabbitHeadDropHead = true;
	@ConfigField(name = "killer_rabbit.head.value", category = "mobs")
	public String killerRabbitHeadPrize = "10";
	@ConfigField(name = "killer_rabbit.head.chance", category = "mobs")
	public double killerRabbitHeadDropChance = 0.25;
	@ConfigField(name = "killer_rabbit.head.message", category = "mobs")
	public String killerRabbitHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Magma Cube============================================
	@ConfigField(name = "magma_cube.enabled", category = "mobs")
	public boolean magmaCubeEnabled = true;
	@ConfigField(name = "magma_cube.message", category = "mobs")
	public String magmaCubeMessage = "You killed a §1{killed}";
	@ConfigField(name = "magma_cube.money.amount", category = "mobs")
	public String magmaCubeMoney = "10:20";
	@ConfigField(name = "magma_cube.money.chance", category = "mobs")
	public double magmaCubeMoneyChance = 1;
	@ConfigField(name = "magma_cube.commands", category = "mobs")
	public List<HashMap<String, String>> magmaCubeCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.10");
		magmaCubeCommands.add(values1);
	}
	@ConfigField(name = "magma_cube.head.drophead", category = "mobs")
	public boolean magmaCubeHeadDropHead = true;
	@ConfigField(name = "magma_cube.head.value", category = "mobs")
	public String magmaCubeHeadPrize = "0";
	@ConfigField(name = "magma_cube.head.chance", category = "mobs")
	public double magmaCubeHeadDropChance = 0.10;
	@ConfigField(name = "magma_cube.head.message", category = "mobs")
	public String magmaCubeHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Phantom============================================
	@ConfigField(name = "phantom.enabled", category = "mobs")
	public boolean phantomEnabled = true;
	@ConfigField(name = "phantom.message", category = "mobs")
	public String phantomMessage = "You killed a §1{killed}";
	@ConfigField(name = "phantom.money.amount", category = "mobs")
	public String phantomMoney = "20:40";
	@ConfigField(name = "phantom.money.chance", category = "mobs")
	public double phantomMoneyChance = 1;
	@ConfigField(name = "phantom.commands", category = "mobs")
	public List<HashMap<String, String>> phantomCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.33");
		phantomCommands.add(values1);
	}
	@ConfigField(name = "phantom.head.drophead", category = "mobs")
	public boolean phantomHeadDropHead = true;
	@ConfigField(name = "phantom.head.value", category = "mobs")
	public String phantomHeadPrize = "0";
	@ConfigField(name = "phantom.head.chance", category = "mobs")
	public double phantomHeadDropChance = 0.33;
	@ConfigField(name = "phantom.head.message", category = "mobs")
	public String phantomHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Piglin============================================
	@ConfigField(name = "piglin.enabled", category = "mobs")
	public boolean piglinEnabled = true;
	@ConfigField(name = "piglin.message", category = "mobs")
	public String piglinMessage = "You killed a §1{killed}";
	@ConfigField(name = "piglin.money.amount", category = "mobs")
	public String piglinMoney = "5:10";
	@ConfigField(name = "piglin.money.chance", category = "mobs")
	public double piglinMoneyChance = 1;
	@ConfigField(name = "piglin.commands", category = "mobs")
	public List<HashMap<String, String>> piglinCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.1");
		piglinCommands.add(values1);
	}
	@ConfigField(name = "piglin.head.drophead", category = "mobs")
	public boolean piglinHeadDropHead = true;
	@ConfigField(name = "piglin.head.value", category = "mobs")
	public String piglinHeadPrize = "0";
	@ConfigField(name = "piglin.head.chance", category = "mobs")
	public double piglinHeadDropChance = 0.33;
	@ConfigField(name = "piglin.head.message", category = "mobs")
	public String piglinHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Piglin Brute============================================
	@ConfigField(name = "piglin_brute.enabled", category = "mobs")
	public boolean piglinBruteEnabled = true;
	@ConfigField(name = "piglin_brute.message", category = "mobs")
	public String piglinBruteMessage = "You killed a §1{killed}";
	@ConfigField(name = "piglin_brute.money.amount", category = "mobs")
	public String piglinBruteMoney = "5:10";
	@ConfigField(name = "piglin_brute.money.chance", category = "mobs")
	public double piglinBruteMoneyChance = 1;
	@ConfigField(name = "piglin_brute.commands", category = "mobs")
	public List<HashMap<String, String>> piglinBruteCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.1");
		piglinBruteCommands.add(values1);
	}
	@ConfigField(name = "piglin_brute.head.drophead", category = "mobs")
	public boolean piglinBruteHeadDropHead = true;
	@ConfigField(name = "piglin_brute.head.value", category = "mobs")
	public String piglinBruteHeadPrize = "0";
	@ConfigField(name = "piglin_brute.head.chance", category = "mobs")
	public double piglinBruteHeadDropChance = 0.33;
	@ConfigField(name = "piglin_brute.head.message", category = "mobs")
	public String piglinBruteHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Pillager============================================
	@ConfigField(name = "pillager.enabled", category = "mobs")
	public boolean pillagerEnabled = true;
	@ConfigField(name = "pillager.message", category = "mobs")
	public String pillagerMessage = "You killed a §1{killed}";
	@ConfigField(name = "pillager.money.amount", category = "mobs")
	public String pillagerMoney = "5:10";
	@ConfigField(name = "pillager.money.chance", category = "mobs")
	public double pillagerMoneyChance = 1;
	@ConfigField(name = "pillager.commands", category = "mobs")
	public List<HashMap<String, String>> pillagerCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.33");
		pillagerCommands.add(values1);
	}
	@ConfigField(name = "pillager.head.drophead", category = "mobs")
	public boolean pillagerHeadDropHead = true;
	@ConfigField(name = "pillager.head.value", category = "mobs")
	public String pillagerHeadPrize = "0";
	@ConfigField(name = "pillager.head.chance", category = "mobs")
	public double pillagerHeadDropChance = 0.33;
	@ConfigField(name = "pillager.head.message", category = "mobs")
	public String pillagerHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Polar Bear============================================
	@ConfigField(name = "polar_bear.enabled", category = "mobs")
	public boolean polarBearEnabled = true;
	@ConfigField(name = "polar_bear.message", category = "mobs")
	public String polarBearMessage = "You killed a §1{killed}";
	@ConfigField(name = "polar_bear.money.amount", category = "mobs")
	public String polarBearMoney = "25";
	@ConfigField(name = "polar_bear.money.chance", category = "mobs")
	public double polarBearMoneyChance = 1;
	@ConfigField(name = "polar_bear.commands", category = "mobs")
	public List<HashMap<String, String>> polarBearCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.25");
		values1.put("message", "");
		polarBearCommands.add(values1);
	}
	@ConfigField(name = "polar_bear.head.drophead", category = "mobs")
	public boolean polarBearHeadDropHead = true;
	@ConfigField(name = "polar_bear.head.value", category = "mobs")
	public String polarBearHeadPrize = "0";
	@ConfigField(name = "polar_bear.head.chance", category = "mobs")
	public double polarBearHeadDropChance = 0.25;
	@ConfigField(name = "polar_bear.head.message", category = "mobs")
	public String polarBearHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Ravager============================================
	@ConfigField(name = "ravager.enabled", category = "mobs")
	public boolean ravagerEnabled = true;
	@ConfigField(name = "ravager.message", category = "mobs")
	public String ravagerMessage = "You killed a §1{killed}";
	@ConfigField(name = "ravager.money.amount", category = "mobs")
	public String ravagerMoney = "25";
	@ConfigField(name = "ravager.money.chance", category = "mobs")
	public double ravagerMoneyChance = 1;
	@ConfigField(name = "ravager.commands", category = "mobs")
	public List<HashMap<String, String>> ravagerCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.05");
		ravagerCommands.add(values1);
	}
	@ConfigField(name = "ravager.head.drophead", category = "mobs")
	public boolean ravagerHeadDropHead = true;
	@ConfigField(name = "ravager.head.value", category = "mobs")
	public String ravagerHeadPrize = "0";
	@ConfigField(name = "ravager.head.chance", category = "mobs")
	public double ravagerHeadDropChance = 0.05;
	@ConfigField(name = "ravager.head.message", category = "mobs")
	public String ravagerHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Slime============================================
	@ConfigField(name = "slime.enabled", category = "mobs")
	public boolean slimeEnabled = true;
	@ConfigField(name = "slime.message", category = "mobs")
	public String slimeMessage = "You killed a §1{killed}";
	@ConfigField(name = "slime.money.amount", category = "mobs")
	public String slimeMoney = "25";
	@ConfigField(name = "slime.money.chance", category = "mobs")
	public double slimeMoneyChance = 1;
	@ConfigField(name = "slime.commands", category = "mobs")
	public List<HashMap<String, String>> slimeCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.05");
		slimeCommands.add(values1);
	}
	@ConfigField(name = "slime.head.drophead", category = "mobs")
	public boolean slimeHeadDropHead = true;
	@ConfigField(name = "slime.head.value", category = "mobs")
	public String slimeHeadPrize = "0";
	@ConfigField(name = "slime.head.chance", category = "mobs")
	public double slimeHeadDropChance = 0.05;
	@ConfigField(name = "slime.head.message", category = "mobs")
	public String slimeHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Shulker============================================
	@ConfigField(name = "shulker.enabled", category = "mobs")
	public boolean shulkerEnabled = true;
	@ConfigField(name = "shulker.message", category = "mobs")
	public String shulkerMessage = "You killed a §1{killed}";
	@ConfigField(name = "shulker.money.amount", category = "mobs")
	public String shulkerMoney = "25";
	@ConfigField(name = "shulker.money.chance", category = "mobs")
	public double shulkerMoneyChance = 1;
	@ConfigField(name = "shulker.commands", category = "mobs")
	public List<HashMap<String, String>> shulkerCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.50");
		values1.put("message", "");
		shulkerCommands.add(values1);
	}
	@ConfigField(name = "shulker.head.drophead", category = "mobs")
	public boolean shulkerHeadDropHead = true;
	@ConfigField(name = "shulker.head.value", category = "mobs")
	public String shulkerHeadPrize = "0";
	@ConfigField(name = "shulker.head.chance", category = "mobs")
	public double shulkerHeadDropChance = 0.50;
	@ConfigField(name = "shulker.head.message", category = "mobs")
	public String shulkerHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Silverfish============================================
	@ConfigField(name = "silverfish.enabled", category = "mobs")
	public boolean silverfishEnabled = true;
	@ConfigField(name = "silverfish.message", category = "mobs")
	public String silverfishMessage = "You killed a §1{killed}";
	@ConfigField(name = "silverfish.money.amount", category = "mobs")
	public String silverfishMoney = "10";
	@ConfigField(name = "silverfish.money.chance", category = "mobs")
	public double silverfishMoneyChance = 1;
	@ConfigField(name = "silverfish.commands", category = "mobs")
	public List<HashMap<String, String>> silverfishCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.20");
		silverfishCommands.add(values1);
	}
	@ConfigField(name = "silverfish.head.drophead", category = "mobs")
	public boolean silverfishHeadDropHead = true;
	@ConfigField(name = "silverfish.head.value", category = "mobs")
	public String silverfishHeadPrize = "0";
	@ConfigField(name = "silverfish.head.chance", category = "mobs")
	public double silverfishHeadDropChance = 0.20;
	@ConfigField(name = "silverfish.head.message", category = "mobs")
	public String silverfishHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Skeleton============================================
	@ConfigField(name = "skeleton.enabled", category = "mobs")
	public boolean skeletonEnabled = true;
	@ConfigField(name = "skeleton.message", category = "mobs")
	public String skeletonMessage = "You killed a §1{killed}";
	@ConfigField(name = "skeleton.money.amount", category = "mobs")
	public String skeletonMoney = "10:30";
	@ConfigField(name = "skeleton.money.chance", category = "mobs")
	public double skeletonMoneyChance = 1;
	@ConfigField(name = "skeleton.commands", category = "mobs")
	public List<HashMap<String, String>> skeletonCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.05");
		skeletonCommands.add(values1);
	}
	@ConfigField(name = "skeleton.head.drophead", category = "mobs")
	public boolean skeletonHeadDropHead = true;
	@ConfigField(name = "skeleton.head.value", category = "mobs")
	public String skeletonHeadPrize = "0";
	@ConfigField(name = "skeleton.head.chance", category = "mobs")
	public double skeletonHeadDropChance = 0.05;
	@ConfigField(name = "skeleton.head.message", category = "mobs")
	public String skeletonHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Spider============================================
	@ConfigField(name = "spider.enabled", category = "mobs")
	public boolean spiderEnabled = true;
	@ConfigField(name = "spider.message", category = "mobs")
	public String spiderMessage = "You killed a §1{killed}";
	@ConfigField(name = "spider.money.amount", category = "mobs")
	public String spiderMoney = "5.5:10.5";
	@ConfigField(name = "spider.money.chance", category = "mobs")
	public double spiderMoneyChance = 1;
	@ConfigField(name = "spider.commands", category = "mobs")
	public List<HashMap<String, String>> spiderCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.05");
		spiderCommands.add(values1);
	}
	@ConfigField(name = "spider.head.drophead", category = "mobs")
	public boolean spiderHeadDropHead = true;
	@ConfigField(name = "spider.head.value", category = "mobs")
	public String spiderHeadPrize = "0";
	@ConfigField(name = "spider.head.chance", category = "mobs")
	public double spiderHeadDropChance = 0.05;
	@ConfigField(name = "spider.head.message", category = "mobs")
	public String spiderHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Stray============================================
	@ConfigField(name = "stray.enabled", category = "mobs")
	public boolean strayEnabled = true;
	@ConfigField(name = "stray.message", category = "mobs")
	public String strayMessage = "You killed a §1{killed}";
	@ConfigField(name = "stray.money.amount", category = "mobs")
	public String strayMoney = "15:35";
	@ConfigField(name = "stray.money.chance", category = "mobs")
	public double strayMoneyChance = 1;
	@ConfigField(name = "stray.commands", category = "mobs")
	public List<HashMap<String, String>> strayCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.20");
		strayCommands.add(values1);
	}
	@ConfigField(name = "stray.head.drophead", category = "mobs")
	public boolean strayHeadDropHead = true;
	@ConfigField(name = "stray.head.value", category = "mobs")
	public String strayHeadPrize = "0";
	@ConfigField(name = "stray.head.chance", category = "mobs")
	public double strayHeadDropChance = 0.20;
	@ConfigField(name = "stray.head.message", category = "mobs")
	public String strayHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Strider============================================
	@ConfigField(name = "strider.enabled", category = "mobs")
	public boolean striderEnabled = true;
	@ConfigField(name = "strider.message", category = "mobs")
	public String striderMessage = "You killed a §1{killed}";
	@ConfigField(name = "strider.money.amount", category = "mobs")
	public String striderMoney = "15:35";
	@ConfigField(name = "strider.money.chance", category = "mobs")
	public double striderMoneyChance = 1;
	@ConfigField(name = "strider.commands", category = "mobs")
	public List<HashMap<String, String>> striderCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.20");
		striderCommands.add(values1);
	}
	@ConfigField(name = "strider.head.drophead", category = "mobs")
	public boolean striderHeadDropHead = true;
	@ConfigField(name = "strider.head.value", category = "mobs")
	public String striderHeadPrize = "0";
	@ConfigField(name = "strider.head.chance", category = "mobs")
	public double striderHeadDropChance = 0.20;
	@ConfigField(name = "strider.head.message", category = "mobs")
	public String striderHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Turtle============================================
	@ConfigField(name = "turtle.enabled", category = "mobs")
	public boolean turtleEnabled = true;
	@ConfigField(name = "turtle.message", category = "mobs")
	public String turtleMessage = "You killed a §1{killed}";
	@ConfigField(name = "turtle.money.amount", category = "mobs")
	public String turtleMoney = "1:3";
	@ConfigField(name = "turtle.money.chance", category = "mobs")
	public double turtleMoneyChance = 1;
	@ConfigField(name = "turtle.commands", category = "mobs")
	public List<HashMap<String, String>> turtleCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.33");
		turtleCommands.add(values1);
	}
	@ConfigField(name = "turtle.head.drophead", category = "mobs")
	public boolean turtleHeadDropHead = true;
	@ConfigField(name = "turtle.head.value", category = "mobs")
	public String turtleHeadPrize = "0";
	@ConfigField(name = "turtle.head.chance", category = "mobs")
	public double turtleHeadDropChance = 0.33;
	@ConfigField(name = "turtle.head.message", category = "mobs")
	public String turtleHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Zoglin============================================
	@ConfigField(name = "zoglin.enabled", category = "mobs")
	public boolean zoglinEnabled = true;
	@ConfigField(name = "zoglin.message", category = "mobs")
	public String zoglinMessage = "You killed a §1{killed}";
	@ConfigField(name = "zoglin.money.amount", category = "mobs")
	public String zoglinMoney = "7:11";
	@ConfigField(name = "zoglin.money.chance", category = "mobs")
	public double zoglinMoneyChance = 1;
	@ConfigField(name = "zoglin.commands", category = "mobs")
	public List<HashMap<String, String>> zoglinCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.005");
		zoglinCommands.add(values1);
	}
	@ConfigField(name = "zoglin.head.drophead", category = "mobs")
	public boolean zoglinHeadDropHead = true;
	@ConfigField(name = "zoglin.head.value", category = "mobs")
	public String zoglinHeadPrize = "0";
	@ConfigField(name = "zoglin.head.chance", category = "mobs")
	public double zoglinHeadDropChance = 0.005;
	@ConfigField(name = "zoglin.head.message", category = "mobs")
	public String zoglinHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Zombie============================================
	@ConfigField(name = "zombie.enabled", category = "mobs")
	public boolean zombieEnabled = true;
	@ConfigField(name = "zombie.message", category = "mobs")
	public String zombieMessage = "You killed a §1{killed}";
	@ConfigField(name = "zombie.money.amount", category = "mobs")
	public String zombieMoney = "7:11";
	@ConfigField(name = "zombie.money.chance", category = "mobs")
	public double zombieMoneyChance = 1;
	@ConfigField(name = "zombie.commands", category = "mobs")
	public List<HashMap<String, String>> zombieCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.005");
		zombieCommands.add(values1);
	}
	@ConfigField(name = "zombie.head.drophead", category = "mobs")
	public boolean zombieHeadDropHead = true;
	@ConfigField(name = "zombie.head.value", category = "mobs")
	public String zombieHeadPrize = "0";
	@ConfigField(name = "zombie.head.chance", category = "mobs")
	public double zombieHeadDropChance = 0.005;
	@ConfigField(name = "zombie.head.message", category = "mobs")
	public String zombieHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Zombie Pigman============================================
	@ConfigField(name = "zombie_pigman.enabled", category = "mobs")
	public boolean zombiePigmanEnabled = true;
	@ConfigField(name = "zombie_pigman.message", category = "mobs")
	public String zombiePigmanMessage = "You killed a §1{killed}";
	@ConfigField(name = "zombie_pigman.money.amount", category = "mobs")
	public String zombiePigmanMoney = "4:8";
	@ConfigField(name = "zombie_pigman.money.chance", category = "mobs")
	public double zombiePigmanMoneyChance = 1;
	@ConfigField(name = "zombie_pigman.commands", category = "mobs")
	public List<HashMap<String, String>> zombiePigmanCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.10");
		zombiePigmanCommands.add(values1);
	}
	@ConfigField(name = "zombie_pigman.head.drophead", category = "mobs")
	public boolean zombiePigmanHeadDropHead = true;
	@ConfigField(name = "zombie_pigman.head.value", category = "mobs")
	public String zombiePigmanHeadPrize = "0";
	@ConfigField(name = "zombie_pigman.head.chance", category = "mobs")
	public double zombiePigmanHeadDropChance = 0.10;
	@ConfigField(name = "zombie_pigman.head.message", category = "mobs")
	public String zombiePigmanHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Vex============================================
	@ConfigField(name = "vex.enabled", category = "mobs")
	public boolean vexEnabled = true;
	@ConfigField(name = "vex.message", category = "mobs")
	public String vexMessage = "You killed a §1{killed}";
	@ConfigField(name = "vex.money.amount", category = "mobs")
	public String vexMoney = "10:15";
	@ConfigField(name = "vex.money.chance", category = "mobs")
	public double vexMoneyChance = 1;
	@ConfigField(name = "vex.commands", category = "mobs")
	public List<HashMap<String, String>> vexCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.50");
		vexCommands.add(values1);
	}
	@ConfigField(name = "vex.head.drophead", category = "mobs")
	public boolean vexHeadDropHead = true;
	@ConfigField(name = "vex.head.value", category = "mobs")
	public String vexHeadPrize = "0";
	@ConfigField(name = "vex.head.chance", category = "mobs")
	public double vexHeadDropChance = 0.50;
	@ConfigField(name = "vex.head.message", category = "mobs")
	public String vexHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Warden============================================
	@ConfigField(name = "warden.enabled", category = "mobs")
	public boolean wardenEnabled = true;
	@ConfigField(name = "warden.message", category = "mobs")
	public String wardenMessage = "You killed a §1{killed}";
	@ConfigField(name = "witch.money.amount", category = "mobs")
	public String wardenMoney = "10:15";
	@ConfigField(name = "warden.money.chance", category = "mobs")
	public double wardenMoneyChance = 1;
	@ConfigField(name = "warden.commands", category = "mobs")
	public List<HashMap<String, String>> wardenCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} emerald 1");
		values1.put("chance", "0.05");
		wardenCommands.add(values1);
	}
	@ConfigField(name = "warden.head.drophead", category = "mobs")
	public boolean wardenHeadDropHead = true;
	@ConfigField(name = "warden.head.value", category = "mobs")
	public String wardenHeadPrize = "0";
	@ConfigField(name = "warden.head.chance", category = "mobs")
	public double wardenHeadDropChance = 0.05;
	@ConfigField(name = "warden.head.message", category = "mobs")
	public String wardenHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Witch============================================
	@ConfigField(name = "witch.enabled", category = "mobs")
	public boolean witchEnabled = true;
	@ConfigField(name = "witch.message", category = "mobs")
	public String witchMessage = "You killed a §1{killed}";
	@ConfigField(name = "witch.money.amount", category = "mobs")
	public String witchMoney = "10:15";
	@ConfigField(name = "witch.money.chance", category = "mobs")
	public double witchMoneyChance = 1;
	@ConfigField(name = "witch.commands", category = "mobs")
	public List<HashMap<String, String>> witchCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} emerald 1");
		values1.put("chance", "0.05");
		witchCommands.add(values1);
	}
	@ConfigField(name = "witch.head.drophead", category = "mobs")
	public boolean witchHeadDropHead = true;
	@ConfigField(name = "witch.head.value", category = "mobs")
	public String witchHeadPrize = "0";
	@ConfigField(name = "witch.head.chance", category = "mobs")
	public double witchHeadDropChance = 0.05;
	@ConfigField(name = "witch.head.message", category = "mobs")
	public String witchHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Wither Skeleton============================================
	@ConfigField(name = "wither_skeleton.enabled", category = "mobs")
	public boolean witherSkeletonEnabled = true;
	@ConfigField(name = "wither_skeleton.message", category = "mobs")
	public String witherSkeletonMessage = "You killed a §1{killed}";
	@ConfigField(name = "wither_skeleton.money.amount", category = "mobs")
	public String witherSkeletonMoney = "30:50";
	@ConfigField(name = "wither_skeleton.money.chance", category = "mobs")
	public double witherSkeletonMoneyChance = 1;
	@ConfigField(name = "wither_skeleton.commands", category = "mobs")
	public List<HashMap<String, String>> witherSkeletonCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} fire_charge 1");
		values1.put("chance", "0.10");
		witherSkeletonCommands.add(values1);
	}
	@ConfigField(name = "wither_skeleton.head.drophead", category = "mobs")
	public boolean witherSkeletonHeadDropHead = true;
	@ConfigField(name = "wither_skeleton.head.value", category = "mobs")
	public String witherSkeletonHeadPrize = "0";
	@ConfigField(name = "wither_skeleton.head.chance", category = "mobs")
	public double witherSkeletonHeadDropChance = 0.10;
	@ConfigField(name = "wither_skeleton.head.message", category = "mobs")
	public String witherSkeletonHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// #####################################################################################
	// Bosses
	// #####################################################################################
	// =====Wither============================================
	@ConfigField(name = "wither.enabled", category = "boss")
	public boolean witherEnabled = true;
	@ConfigField(name = "wither.message", category = "boss")
	public String witherMessage = "You killed a §1{killed}";
	@ConfigField(name = "wither.money.amount", category = "boss")
	public String witherMoney = "1000:2000";
	@ConfigField(name = "wither.money.chance", category = "boss")
	public double witherMoneyChance = 1;
	@ConfigField(name = "wither.commands", category = "boss")
	public List<HashMap<String, String>> witherCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} gold_ingot 1");
		values1.put("chance", "0.50");
		values1.put("message", "You got an Iron Ingot.");
		witherCommands.add(values1);

		HashMap<String, String> values2 = new HashMap<String, String>();
		values2.put("cmd", "give {player} diamond 10");
		values2.put("chance", "0.75");
		values2.put("message", "You got ten Diamonds.");
		witherCommands.add(values2);
	}
	@ConfigField(name = "wither.head.drophead", category = "boss")
	public boolean witherHeadDropHead = true;
	@ConfigField(name = "wither.head.value", category = "boss")
	public String witherHeadPrize = "500";
	@ConfigField(name = "wither.head.chance", category = "boss")
	public double witherHeadDropChance = 0.50;
	@ConfigField(name = "wither.head.message", category = "boss")
	public String witherHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Ender Dragon============================================
	@ConfigField(name = "ender_dragon.enabled", category = "boss")
	public boolean enderDragonEnabled = true;
	@ConfigField(name = "ender_dragon.message", category = "boss")
	public String enderDragonMessage = "You killed a §1{killed}";
	@ConfigField(name = "ender_dragon.money.amount", category = "boss")
	public String enderDragonMoney = "2000.0:5000.0";
	@ConfigField(name = "ender_dragon.money.chance", category = "boss")
	public double enderDragonMoneyChance = 0.10;
	@ConfigField(name = "ender_dragon.commands", category = "boss")
	public List<HashMap<String, String>> enderDragonCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.5");
		values1.put("message", "You got an Iron Ingot.");
		enderDragonCommands.add(values1);
		HashMap<String, String> values2 = new HashMap<String, String>();
		values2.put("cmd", "give {player} diamond 10");
		values2.put("chance", "0.75");
		values2.put("message", "You got ten Diamonds.");
		enderDragonCommands.add(values2);
	}
	@ConfigField(name = "ender_dragon.head.drophead", category = "boss")
	public boolean enderDragonHeadDropHead = true;
	@ConfigField(name = "ender_dragon.head.value", category = "boss")
	public String enderDragonHeadPrize = "1000";
	@ConfigField(name = "ender_dragon.head.chance", category = "boss")
	public double enderDragonHeadDropChance = 0.5;
	@ConfigField(name = "ender_dragon.head.message", category = "boss")
	public String enderDragonHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// Usage: /summon <EntityName> [x] [y] [z] [dataTag]
	// Try this!!!! /summon Minecart ~ ~ ~20 {Riding:{id:EnderDragon}}
	// Then enter to the minecart
	// WITH THAT YOU CAN RIDE AN ENDERDRAGON!!!

	// /summon Minecart ~ ~ ~ {Riding:{Creeper,Riding:{id:Ozelot}}}
	// ...Yes..Ocelot need to be spelled Ozelot..

	// /summon Skeleton ~ ~ ~
	// {Riding:{id:Spider},Equipment:[{id:57},{id:310},{id:310},{id:310},{id:310}]}

	// #####################################################################################
	// Villagers
	// #####################################################################################

	// =====Armorer============================================
	@ConfigField(name = "armorer.enabled", category = "villager")
	public boolean armorerEnabled = true;
	@ConfigField(name = "armorer.message", category = "villager")
	public String armorerMessage = "You killed a §1{killed}";
	@ConfigField(name = "armorer.money.amount", category = "villager")
	public String armorerMoney = "1:2";
	@ConfigField(name = "armorer.money.chance", category = "villager")
	public double armorerMoneyChance = 1;
	@ConfigField(name = "armorer.commands", category = "villager")
	public List<HashMap<String, String>> armorerCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} arrow 10");
		values1.put("chance", "0.10");
		armorerCommands.add(values1);
	}
	@ConfigField(name = "armorer.head.drophead", category = "villager")
	public boolean armorerHeadDropHead = true;
	@ConfigField(name = "armorer.head.value", category = "villager")
	public String armorerHeadPrize = "0";
	@ConfigField(name = "armorer.head.chance", category = "villager")
	public double armorerHeadDropChance = 0.10;
	@ConfigField(name = "armorer.head.message", category = "villager")
	public String armorerHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Butcher============================================
	@ConfigField(name = "butcher.enabled", category = "villager")
	public boolean butcherEnabled = true;
	@ConfigField(name = "butcher.message", category = "villager")
	public String butcherMessage = "You killed a §1{killed}";
	@ConfigField(name = "butcher.money.amount", category = "villager")
	public String butcherMoney = "1:2";
	@ConfigField(name = "butcher.money.chance", category = "villager")
	public double butcherMoneyChance = 1;
	@ConfigField(name = "butcher.commands", category = "villager")
	public List<HashMap<String, String>> butcherCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_sword 1");
		values1.put("chance", "0.10");
		butcherCommands.add(values1);
	}
	@ConfigField(name = "butcher.head.drophead", category = "villager")
	public boolean butcherHeadDropHead = true;
	@ConfigField(name = "butcher.head.value", category = "villager")
	public String butcherHeadPrize = "0";
	@ConfigField(name = "butcher.head.chance", category = "villager")
	public double butcherHeadDropChance = 0.10;
	@ConfigField(name = "butcher.head.message", category = "villager")
	public String butcherHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Cartographer============================================
	@ConfigField(name = "cartographer.enabled", category = "villager")
	public boolean cartographerEnabled = true;
	@ConfigField(name = "cartographer.message", category = "villager")
	public String cartographerMessage = "You killed a §1{killed}";
	@ConfigField(name = "cartographer.money.amount", category = "villager")
	public String cartographerMoney = "1:2";
	@ConfigField(name = "cartographer.money.chance", category = "villager")
	public double cartographerMoneyChance = 1;
	@ConfigField(name = "cartographer.commands", category = "villager")
	public List<HashMap<String, String>> cartographerCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} map 1");
		values1.put("chance", "0.10");
		cartographerCommands.add(values1);
	}
	@ConfigField(name = "cartographer.head.drophead", category = "villager")
	public boolean cartographerHeadDropHead = true;
	@ConfigField(name = "cartographer.head.value", category = "villager")
	public String cartographerHeadPrize = "0";
	@ConfigField(name = "cartographer.head.chance", category = "villager")
	public double cartographerHeadDropChance = 0.10;
	@ConfigField(name = "cartographer.head.message", category = "villager")
	public String cartographerHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Cleric============================================
	@ConfigField(name = "cleric.enabled", category = "villager")
	public boolean clericEnabled = true;
	@ConfigField(name = "cleric.message", category = "villager")
	public String clericMessage = "You killed a §1{killed}";
	@ConfigField(name = "cleric.money.amount", category = "villager")
	public String clericMoney = "1:2";
	@ConfigField(name = "cleric.money.chance", category = "villager")
	public double clericMoneyChance = 1;
	@ConfigField(name = "cleric.commands", category = "villager")
	public List<HashMap<String, String>> clericCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} bowl 1");
		values1.put("chance", "0.10");
		clericCommands.add(values1);
	}
	@ConfigField(name = "cleric.head.drophead", category = "villager")
	public boolean clericHeadDropHead = true;
	@ConfigField(name = "cleric.head.value", category = "villager")
	public String clericHeadPrize = "0";
	@ConfigField(name = "cleric.head.chance", category = "villager")
	public double clericHeadDropChance = 0.10;
	@ConfigField(name = "cleric.head.message", category = "villager")
	public String clericHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Farmer============================================
	@ConfigField(name = "farmer.enabled", category = "villager")
	public boolean farmerEnabled = true;
	@ConfigField(name = "farmer.message", category = "villager")
	public String farmerMessage = "You killed a §1{killed}";
	@ConfigField(name = "farmer.money.amount", category = "villager")
	public String farmerMoney = "1:2";
	@ConfigField(name = "farmer.money.chance", category = "villager")
	public double farmerMoneyChance = 1;
	@ConfigField(name = "farmer.commands", category = "villager")
	public List<HashMap<String, String>> farmerCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_hoe 1");
		values1.put("chance", "0.1");
		farmerCommands.add(values1);
	}
	@ConfigField(name = "farmer.head.drophead", category = "villager")
	public boolean farmerHeadDropHead = true;
	@ConfigField(name = "farmer.head.value", category = "villager")
	public String farmerHeadPrize = "0";
	@ConfigField(name = "farmer.head.chance", category = "villager")
	public double farmerHeadDropChance = 0.10;
	@ConfigField(name = "farmer.head.message", category = "villager")
	public String farmerHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Fisherman============================================
	@ConfigField(name = "fisherman.enabled", category = "villager")
	public boolean fishermanEnabled = true;
	@ConfigField(name = "fisherman.message", category = "villager")
	public String fishermanMessage = "You killed a §1{killed}";
	@ConfigField(name = "fisherman.money.amount", category = "villager")
	public String fishermanMoney = "1:2";
	@ConfigField(name = "fisherman.money.chance", category = "villager")
	public double fishermanMoneyChance = 1;
	@ConfigField(name = "fisherman.commands", category = "villager")
	public List<HashMap<String, String>> fishermanCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} fishing_rod 1");
		values1.put("chance", "0.1");
		fishermanCommands.add(values1);
	}
	@ConfigField(name = "fisherman.head.drophead", category = "villager")
	public boolean fishermanHeadDropHead = true;
	@ConfigField(name = "fisherman.head.value", category = "villager")
	public String fishermanHeadPrize = "0";
	@ConfigField(name = "fisherman.head.chance", category = "villager")
	public double fishermanHeadDropChance = 0.10;
	@ConfigField(name = "fisherman.head.message", category = "villager")
	public String fishermanHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Fletcher============================================
	@ConfigField(name = "fletcher.enabled", category = "villager")
	public boolean fletcherEnabled = true;
	@ConfigField(name = "fletcher.message", category = "villager")
	public String fletcherMessage = "You killed a §1{killed}";
	@ConfigField(name = "fletcher.money.amount", category = "villager")
	public String fletcherMoney = "1:2";
	@ConfigField(name = "fletcher.money.chance", category = "villager")
	public double fletcherMoneyChance = 1;
	@ConfigField(name = "fletcher.commands", category = "villager")
	public List<HashMap<String, String>> fletcherCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.1");
		fletcherCommands.add(values1);
	}
	@ConfigField(name = "fletcher.head.drophead", category = "villager")
	public boolean fletcherHeadDropHead = true;
	@ConfigField(name = "fletcher.head.value", category = "villager")
	public String fletcherHeadPrize = "0";
	@ConfigField(name = "fletcher.head.chance", category = "villager")
	public double fletcherHeadDropChance = 0.10;
	@ConfigField(name = "fletcher.head.message", category = "villager")
	public String fletcherHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Leatherworker============================================
	@ConfigField(name = "leatherworker.enabled", category = "villager")
	public boolean leatherworkerEnabled = true;
	@ConfigField(name = "leatherworker.message", category = "villager")
	public String leatherworkerMessage = "You killed a §1{killed}";
	@ConfigField(name = "leatherworker.money.amount", category = "villager")
	public String leatherworkerMoney = "1:2";
	@ConfigField(name = "leatherworker.money.chance", category = "villager")
	public double leatherworkerMoneyChance = 1;
	@ConfigField(name = "leatherworker.commands", category = "villager")
	public List<HashMap<String, String>> leatherworkerCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.1");
		leatherworkerCommands.add(values1);
	}
	@ConfigField(name = "leatherworker.head.drophead", category = "villager")
	public boolean leatherworkerHeadDropHead = true;
	@ConfigField(name = "leatherworker.head.value", category = "villager")
	public String leatherworkerHeadPrize = "0";
	@ConfigField(name = "leatherworker.head.chance", category = "villager")
	public double leatherworkerHeadDropChance = 0.10;
	@ConfigField(name = "leatherworker.head.message", category = "villager")
	public String leatherworkerHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Librarian============================================
	@ConfigField(name = "librarian.enabled", category = "villager")
	public boolean librarianEnabled = true;
	@ConfigField(name = "librarian.message", category = "villager")
	public String librarianMessage = "You killed a §1{killed}";
	@ConfigField(name = "librarian.money.amount", category = "villager")
	public String librarianMoney = "1:2";
	@ConfigField(name = "librarian.money.chance", category = "villager")
	public double librarianMoneyChance = 1;
	@ConfigField(name = "librarian.commands", category = "villager")
	public List<HashMap<String, String>> librarianCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} writable_book 1");
		values1.put("chance", "0.5");
		librarianCommands.add(values1);
	}
	@ConfigField(name = "librarian.head.drophead", category = "villager")
	public boolean librarianHeadDropHead = true;
	@ConfigField(name = "librarian.head.value", category = "villager")
	public String librarianHeadPrize = "0";
	@ConfigField(name = "librarian.head.chance", category = "villager")
	public double librarianHeadDropChance = 0.50;
	@ConfigField(name = "librarian.head.message", category = "villager")
	public String librarianHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Mason============================================
	@ConfigField(name = "mason.enabled", category = "villager")
	public boolean masonEnabled = true;
	@ConfigField(name = "mason.message", category = "villager")
	public String masonMessage = "You killed a §1{killed}";
	@ConfigField(name = "mason.money.amount", category = "villager")
	public String masonMoney = "1:2";
	@ConfigField(name = "mason.money.chance", category = "villager")
	public double masonMoneyChance = 1;
	@ConfigField(name = "mason.commands", category = "villager")
	public List<HashMap<String, String>> masonCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.5");
		masonCommands.add(values1);
	}
	@ConfigField(name = "mason.head.drophead", category = "villager")
	public boolean masonHeadDropHead = true;
	@ConfigField(name = "mason.head.value", category = "villager")
	public String masonHeadPrize = "0";
	@ConfigField(name = "mason.head.chance", category = "villager")
	public double masonHeadDropChance = 0.50;
	@ConfigField(name = "mason.head.message", category = "villager")
	public String masonHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Nitwit============================================
	@ConfigField(name = "nitwit.enabled", category = "villager")
	public boolean nitwitEnabled = true;
	@ConfigField(name = "nitwit.message", category = "villager")
	public String nitwitMessage = "You killed a §1{killed}";
	@ConfigField(name = "nitwit.money.amount", category = "villager")
	public String nitwitMoney = "1:2";
	@ConfigField(name = "nitwit.money.chance", category = "villager")
	public double nitwitMoneyChance = 1;
	@ConfigField(name = "nitwit.commands", category = "villager")
	public List<HashMap<String, String>> nitwitCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.5");
		nitwitCommands.add(values1);
	}
	@ConfigField(name = "nitwit.head.drophead", category = "villager")
	public boolean nitwitHeadDropHead = true;
	@ConfigField(name = "nitwit.head.value", category = "villager")
	public String nitwitHeadPrize = "0";
	@ConfigField(name = "nitwit.head.chance", category = "villager")
	public double nitwitHeadDropChance = 0.5;
	@ConfigField(name = "nitwit.head.message", category = "villager")
	public String nitwitHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Villager============================================
	@ConfigField(name = "villager.enabled", category = "villager")
	public boolean villagerEnabled = true;
	@ConfigField(name = "villager.message", category = "villager")
	public String villagerMessage = "You killed a §1{killed}";
	@ConfigField(name = "villager.money.amount", category = "villager")
	public String villagerMoney = "1";
	@ConfigField(name = "villager.money.chance", category = "villager")
	public double villagerMoneyChance = 1;
	@ConfigField(name = "villager.commands", category = "villager")
	public List<HashMap<String, String>> villagerCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} melon 1");
		values1.put("chance", "0.3");
		villagerCommands.add(values1);
	}
	@ConfigField(name = "villager.head.drophead", category = "villager")
	public boolean villagerHeadDropHead = true;
	@ConfigField(name = "villager.head.value", category = "villager")
	public String villagerHeadPrize = "0";
	@ConfigField(name = "villager.head.chance", category = "villager")
	public double villagerHeadDropChance = 0.30;
	@ConfigField(name = "villager.head.message", category = "villager")
	public String villagerHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Shepherd============================================
	@ConfigField(name = "shepherd.enabled", category = "villager")
	public boolean shepherdEnabled = true;
	@ConfigField(name = "shepherd.message", category = "villager")
	public String shepherdMessage = "You killed a §1{killed}";
	@ConfigField(name = "shepherd.money.amount", category = "villager")
	public String shepherdMoney = "1";
	@ConfigField(name = "shepherd.money.chance", category = "villager")
	public double shepherdMoneyChance = 1;
	@ConfigField(name = "shepherd.commands", category = "villager")
	public List<HashMap<String, String>> shepherdCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} shears 1");
		values1.put("chance", "0.3");
		shepherdCommands.add(values1);
	}
	@ConfigField(name = "shepherd.head.drophead", category = "villager")
	public boolean shepherdHeadDropHead = true;
	@ConfigField(name = "shepherd.head.value", category = "villager")
	public String shepherdHeadPrize = "0";
	@ConfigField(name = "shepherd.head.chance", category = "villager")
	public double shepherdHeadDropChance = 0.30;
	@ConfigField(name = "shepherd.head.message", category = "villager")
	public String shepherdHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Toolsmith============================================
	@ConfigField(name = "toolsmith.enabled", category = "villager")
	public boolean toolsmithEnabled = true;
	@ConfigField(name = "toolsmith.message", category = "villager")
	public String toolsmithMessage = "You killed a §1{killed}";
	@ConfigField(name = "toolsmith.money.amount", category = "villager")
	public String toolsmithMoney = "1";
	@ConfigField(name = "toolsmith.money.chance", category = "villager")
	public double toolsmithMoneyChance = 1;
	@ConfigField(name = "toolsmith.commands", category = "villager")
	public List<HashMap<String, String>> toolsmithCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} golden_shovel 1");
		values1.put("chance", "0.3");
		toolsmithCommands.add(values1);
	}
	@ConfigField(name = "toolsmith.head.drophead", category = "villager")
	public boolean toolsmithHeadDropHead = true;
	@ConfigField(name = "toolsmith.head.value", category = "villager")
	public String toolsmithHeadPrize = "0";
	@ConfigField(name = "toolsmith.head.chance", category = "villager")
	public double toolsmithHeadDropChance = 0.30;
	@ConfigField(name = "toolsmith.head.message", category = "villager")
	public String toolsmithHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Shepherd============================================
	@ConfigField(name = "weaponsmith.enabled", category = "villager")
	public boolean weaponsmithEnabled = true;
	@ConfigField(name = "weaponsmith.message", category = "villager")
	public String weaponsmithMessage = "You killed a §1{killed}";
	@ConfigField(name = "weaponsmith.money.amount", category = "villager")
	public String weaponsmithMoney = "1";
	@ConfigField(name = "weaponsmith.money.chance", category = "villager")
	public double weaponsmithMoneyChance = 1;
	@ConfigField(name = "weaponsmith.commands", category = "villager")
	public List<HashMap<String, String>> weaponsmithCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} golden_sword 1");
		values1.put("chance", "0.3");
		weaponsmithCommands.add(values1);
	}
	@ConfigField(name = "weaponsmith.head.drophead", category = "villager")
	public boolean weaponsmithHeadDropHead = true;
	@ConfigField(name = "weaponsmith.head.value", category = "villager")
	public String weaponsmithHeadPrize = "0";
	@ConfigField(name = "weaponsmith.head.chance", category = "villager")
	public double weaponsmithHeadDropChance = 0.30;
	@ConfigField(name = "weaponsmith.head.message", category = "villager")
	public String weaponsmithHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// #####################################################################################
	// Deprecated Villagers (Villages which has been removed in Minecraft 1.14)
	// #####################################################################################

	// =====Blacksmith============================================
	@ConfigField(name = "blacksmith.enabled", category = "villager")
	public boolean blacksmithEnabled = true;
	@ConfigField(name = "blacksmith.message", category = "villager")
	public String blacksmithMessage = "You killed a §1{killed}";
	@ConfigField(name = "blacksmith.money.amount", category = "villager")
	public String blacksmithMoney = "1:2";
	@ConfigField(name = "blacksmith.money.chance", category = "villager")
	public double blacksmithMoneyChance = 1;
	@ConfigField(name = "blacksmith.commands", category = "villager")
	public List<HashMap<String, String>> blacksmithCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.10");
		blacksmithCommands.add(values1);
	}
	@ConfigField(name = "blacksmith.head.drophead", category = "villager")
	public boolean blacksmithHeadDropHead = true;
	@ConfigField(name = "blacksmith.head.value", category = "villager")
	public String blacksmithHeadPrize = "0";
	@ConfigField(name = "blacksmith.head.chance", category = "villager")
	public double blacksmithHeadDropChance = 0.10;
	@ConfigField(name = "blacksmith.head.message", category = "villager")
	public String blacksmithHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Priest============================================
	@ConfigField(name = "priest.enabled", category = "villager")
	public boolean priestEnabled = true;
	@ConfigField(name = "priest.message", category = "villager")
	public String priestMessage = "You killed a §1{killed}";
	@ConfigField(name = "priest.money.amount", category = "villager")
	public String priestMoney = "1:2";
	@ConfigField(name = "priest.money.chance", category = "villager")
	public double priestMoneyChance = 1;
	@ConfigField(name = "priest.commands", category = "villager")
	public List<HashMap<String, String>> priestCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.5");
		priestCommands.add(values1);
	}
	@ConfigField(name = "priest.head.drophead", category = "villager")
	public boolean priestHeadDropHead = true;
	@ConfigField(name = "priest.head.value", category = "villager")
	public String priestHeadPrize = "0";
	@ConfigField(name = "priest.head.chance", category = "villager")
	public double priestHeadDropChance = 0.50;
	@ConfigField(name = "priest.head.message", category = "villager")
	public String priestHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Evoker============================================
	@ConfigField(name = "evoker.enabled", category = "villager")
	public boolean evokerEnabled = true;
	@ConfigField(name = "evoker.message", category = "villager")
	public String evokerMessage = "You killed a §1{killed}";
	@ConfigField(name = "evoker.money.amount", category = "villager")
	public String evokerMoney = "10";
	@ConfigField(name = "evoker.money.chance", category = "villager")
	public double evokerMoneyChance = 0.50;
	@ConfigField(name = "evoker.commands", category = "villager")
	public List<HashMap<String, String>> evokerCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.1");
		evokerCommands.add(values1);
	}
	@ConfigField(name = "evoker.head.drophead", category = "villager")
	public boolean evokerHeadDropHead = true;
	@ConfigField(name = "evoker.head.value", category = "villager")
	public String evokerHeadPrize = "0";
	@ConfigField(name = "evoker.head.chance", category = "villager")
	public double evokerHeadDropChance = 0.50;
	@ConfigField(name = "evoker.head.message", category = "villager")
	public String evokerHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Illusioner============================================
	@ConfigField(name = "illusioner.enabled", category = "villager")
	public boolean illusionerEnabled = true;
	@ConfigField(name = "illusioner.message", category = "villager")
	public String illusionerMessage = "You killed a §1{killed}";
	@ConfigField(name = "illusioner.money.amount", category = "villager")
	public String illusionerMoney = "30:50";
	@ConfigField(name = "illusioner.money.chance", category = "villager")
	public double illusionerMoneyChance = 0.10;
	@ConfigField(name = "illusioner.commands", category = "villager")
	public List<HashMap<String, String>> illusionerCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.10");
		illusionerCommands.add(values1);
	}
	@ConfigField(name = "illusioner.head.drophead", category = "villager")
	public boolean illusionerHeadDropHead = true;
	@ConfigField(name = "illusioner.head.value", category = "villager")
	public String illusionerHeadPrize = "0";
	@ConfigField(name = "illusioner.head.chance", category = "villager")
	public double illusionerHeadDropChance = 0.10;
	@ConfigField(name = "illusioner.head.message", category = "villager")
	public String illusionerHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Vindicator============================================
	@ConfigField(name = "vindicator.enabled", category = "villager")
	public boolean vindicatorEnabled = true;
	@ConfigField(name = "vindicator.message", category = "villager")
	public String vindicatorMessage = "You killed a §1{killed}";
	@ConfigField(name = "vindicator.money.amount", category = "villager")
	public String vindicatorMoney = "10:15";
	@ConfigField(name = "vindicator.money.chance", category = "villager")
	public double vindicatorMoneyChance = 1;
	@ConfigField(name = "vindicator.commands", category = "villager")
	public List<HashMap<String, String>> vindicatorCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.05");
		vindicatorCommands.add(values1);
	}
	@ConfigField(name = "vindicator.head.drophead", category = "villager")
	public boolean vindicatorHeadDropHead = true;
	@ConfigField(name = "vindicator.head.value", category = "villager")
	public String vindicatorHeadPrize = "0";
	@ConfigField(name = "vindicator.head.chance", category = "villager")
	public double vindicatorHeadDropChance = 0.05;
	@ConfigField(name = "vindicator.head.message", category = "villager")
	public String vindicatorHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// #####################################################################################
	// Passive Mobs
	// #####################################################################################
	// =====Allay============================================
	@ConfigField(name = "allay.enabled", category = "mobs")
	public boolean allayEnabled = true;
	@ConfigField(name = "allay.message", category = "mobs")
	public String allayMessage = "You killed a §1{killed}";
	@ConfigField(name = "allay.money.amount", category = "mobs")
	public String allayMoney = "0.0";
	@ConfigField(name = "allay.money.chance", category = "mobs")
	public double allayMoneyChance = 0.05;
	@ConfigField(name = "allay.commands", category = "mobs")
	public List<HashMap<String, String>> allayCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.10");
		allayCommands.add(values1);
	}
	@ConfigField(name = "allay.head.drophead", category = "mobs")
	public boolean allayHeadDropHead = true;
	@ConfigField(name = "allay.head.value", category = "mobs")
	public String allayHeadPrize = "0";
	@ConfigField(name = "allay.head.chance", category = "mobs")
	public double allayHeadDropChance = 0.05;
	@ConfigField(name = "allay.head.message", category = "mobs")
	public String allayHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Axolotl============================================
	@ConfigField(name = "axolotl.enabled", category = "mobs")
	public boolean axolotlEnabled = true;
	@ConfigField(name = "axolotl.message", category = "mobs")
	public String axolotlMessage = "You killed a §1{killed}";
	@ConfigField(name = "axolotl.money.amount", category = "mobs")
	public String axolotlMoney = "0.0";
	@ConfigField(name = "axolotl.money.chance", category = "mobs")
	public double axolotlMoneyChance = 0.05;
	@ConfigField(name = "axolotl.commands", category = "mobs")
	public List<HashMap<String, String>> axolotlCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.10");
		axolotlCommands.add(values1);
	}
	@ConfigField(name = "axolotl.head.drophead", category = "mobs")
	public boolean axolotlHeadDropHead = true;
	@ConfigField(name = "axolotl.head.value", category = "mobs")
	public String axolotlHeadPrize = "0";
	@ConfigField(name = "axolotl.head.chance", category = "mobs")
	public double axolotlHeadDropChance = 0.05;
	@ConfigField(name = "axolotl.head.message", category = "mobs")
	public String axolotlHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Bat============================================
	@ConfigField(name = "bat.enabled", category = "passive")
	public boolean batEnabled = true;
	@ConfigField(name = "bat.message", category = "passive")
	public String batMessage = "You killed a §1{killed}";
	@ConfigField(name = "bat.money.amount", category = "passive")
	public String batMoney = "0";
	@ConfigField(name = "bat.money.chance", category = "passive")
	public double batCmdRunChance = 0.05;
	@ConfigField(name = "bat.commands", category = "passive")
	public List<HashMap<String, String>> batCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} coal 3");
		values1.put("chance", "0.05");
		batCommands.add(values1);
	}
	@ConfigField(name = "bat.head.drophead", category = "passive")
	public boolean batHeadDropHead = true;
	@ConfigField(name = "bat.head.value", category = "passive")
	public String batHeadPrize = "0";
	@ConfigField(name = "bat.head.chance", category = "passive")
	public double batHeadDropChance = 0.05;
	@ConfigField(name = "bat.head.message", category = "passive")
	public String batHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Bee============================================
	@ConfigField(name = "bee.enabled", category = "passive")
	public boolean beeEnabled = true;
	@ConfigField(name = "bee.message", category = "passive")
	public String beeMessage = "You killed a §1{killed}";
	@ConfigField(name = "bee.money.amount", category = "passive")
	public String beeMoney = "0";
	@ConfigField(name = "bee.money.chance", category = "passive")
	public double beeMoneyChance = 0.05;
	@ConfigField(name = "bee.commands", category = "passive")
	public List<HashMap<String, String>> beeCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} golden_carrot 1");
		values1.put("chance", "0.05");
		beeCommands.add(values1);
	}
	@ConfigField(name = "bee.head.drophead", category = "passive")
	public boolean beeHeadDropHead = true;
	@ConfigField(name = "bee.head.value", category = "passive")
	public String beeHeadPrize = "0";
	@ConfigField(name = "bee.head.chance", category = "passive")
	public double beeHeadDropChance = 0.05;
	@ConfigField(name = "bee.head.message", category = "passive")
	public String beeHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Camel============================================
	@ConfigField(name = "cat.enabled", category = "passive")
	public boolean camelEnabled = true;
	@ConfigField(name = "cat.message", category = "passive")
	public String camelMessage = "You killed a §1{killed}";
	@ConfigField(name = "camel.money.amount", category = "passive")
	public String camelMoney = "0";
	@ConfigField(name = "camel.money.chance", category = "passive")
	public double camelMoneyChance = 0.1;
	@ConfigField(name = "camel.commands", category = "passive")
	public List<HashMap<String, String>> camelCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} water_bucket 1");
		values1.put("chance", "0.05");
		camelCommands.add(values1);
	}
	@ConfigField(name = "camel.head.drophead", category = "passive")
	public boolean camelHeadDropHead = true;
	@ConfigField(name = "camel.head.value", category = "passive")
	public String camelHeadPrize = "0";
	@ConfigField(name = "camel.head.chance", category = "passive")
	public double camelHeadDropChance = 0.05;
	@ConfigField(name = "camel.head.message", category = "passive")
	public String camelHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Cat============================================
	@ConfigField(name = "cat.enabled", category = "passive")
	public boolean catEnabled = true;
	@ConfigField(name = "cat.message", category = "passive")
	public String catMessage = "You killed a §1{killed}";
	@ConfigField(name = "cat.money.amount", category = "passive")
	public String catMoney = "0";
	@ConfigField(name = "cat.money.chance", category = "passive")
	public double catMoneyChance = 0.05;
	@ConfigField(name = "cat.commands", category = "passive")
	public List<HashMap<String, String>> catCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.05");
		catCommands.add(values1);
	}
	@ConfigField(name = "cat.head.drophead", category = "passive")
	public boolean catHeadDropHead = true;
	@ConfigField(name = "cat.head.value", category = "passive")
	public String catHeadPrize = "0";
	@ConfigField(name = "cat.head.chance", category = "passive")
	public double catHeadDropChance = 0.05;
	@ConfigField(name = "cat.head.message", category = "passive")
	public String catHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Chicken============================================
	@ConfigField(name = "chicken.enabled", category = "passive")
	public boolean chickenEnabled = true;
	@ConfigField(name = "chicken.message", category = "passive")
	public String chickenMessage = "You killed a §1{killed}";
	@ConfigField(name = "chicken.money.amount", category = "passive")
	public String chickenMoney = "0";
	@ConfigField(name = "chicken.money.chance", category = "passive")
	public double chickenCmdRunChance = 1;
	@ConfigField(name = "chicken.commands", category = "passive")
	public List<HashMap<String, String>> chickenCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} wheat_seeds 3");
		values1.put("chance", "0.05");
		chickenCommands.add(values1);
	}
	@ConfigField(name = "chicken.head.drophead", category = "passive")
	public boolean chickenHeadDropHead = true;
	@ConfigField(name = "chicken.head.value", category = "passive")
	public String chickenHeadPrize = "0";
	@ConfigField(name = "chicken.head.chance", category = "passive")
	public double chickenHeadDropChance = 0.05;
	@ConfigField(name = "chicken.head.message", category = "passive")
	public String chickenHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Cow============================================
	@ConfigField(name = "cow.enabled", category = "passive")
	public boolean cowEnabled = true;
	@ConfigField(name = "cow.message", category = "passive")
	public String cowCmdDesc = "You killed a §1{killed}";
	@ConfigField(name = "cow.money.amount", category = "passive")
	public String cowPrize = "0";
	@ConfigField(name = "cow.money.chance", category = "passive")
	public double cowCmdRunChance = 1;
	@ConfigField(name = "cow.commands", category = "passive")
	public List<HashMap<String, String>> cowCmdNew = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} leather_helmet 1");
		values1.put("chance", "0.05");
		values1.put("message", "");
		cowCmdNew.add(values1);
	}
	@ConfigField(name = "cow.head.drophead", category = "passive")
	public boolean cowHeadDropHead = true;
	@ConfigField(name = "cow.head.value", category = "passive")
	public String cowHeadPrize = "0";
	@ConfigField(name = "cow.head.chance", category = "passive")
	public double cowHeadDropChance = 0.05;
	@ConfigField(name = "cow.head.message", category = "passive")
	public String cowHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Donkey============================================
	@ConfigField(name = "donkey.enabled", category = "passive")
	public boolean donkeyEnabled = true;
	@ConfigField(name = "donkey.message", category = "passive")
	public String donkeyMessage = "You killed a §1{killed}";
	@ConfigField(name = "donkey.money.amount", category = "passive")
	public String donkeyMoney = "5";
	@ConfigField(name = "donkey.money.chance", category = "passive")
	public double donkeyMoneyChance = 1;
	@ConfigField(name = "donkey.commands", category = "passive")
	public List<HashMap<String, String>> donkeyCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.05");
		donkeyCommands.add(values1);
	}
	@ConfigField(name = "donkey.head.drophead", category = "passive")
	public boolean donkeyHeadDropHead = true;
	@ConfigField(name = "donkey.head.value", category = "passive")
	public String donkeyHeadPrize = "0";
	@ConfigField(name = "donkey.head.chance", category = "passive")
	public double donkeyHeadDropChance = 0.05;
	@ConfigField(name = "donkey.head.message", category = "passive")
	public String donkeyHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Fox============================================
	@ConfigField(name = "fox.enabled", category = "passive")
	public boolean foxEnabled = true;
	@ConfigField(name = "fox.message", category = "passive")
	public String foxMessage = "You killed a §1{killed}";
	@ConfigField(name = "fox.money.amount", category = "passive")
	public String foxMoney = "2";
	@ConfigField(name = "fox.money.chance", category = "passive")
	public double foxMoneyChance = 0.05;
	@ConfigField(name = "fox.commands", category = "passive")
	public List<HashMap<String, String>> foxCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} ghast_tear 1");
		values1.put("chance", "0.1");
		foxCommands.add(values1);
	}
	@ConfigField(name = "fox.drophead", category = "passive")
	public boolean foxHeadDropHead = true;
	@ConfigField(name = "fox.head.value", category = "passive")
	public String foxHeadPrize = "0";
	@ConfigField(name = "fox.head.chance", category = "passive")
	public double foxHeadDropChance = 0.05;
	@ConfigField(name = "fox.head.message", category = "passive")
	public String foxHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Frog============================================
	@ConfigField(name = "frog.enabled", category = "passive")
	public boolean frogEnabled = true;
	@ConfigField(name = "frog.message", category = "passive")
	public String frogMessage = "You killed a §1{killed}";
	@ConfigField(name = "frog.money.amount", category = "passive")
	public String frogMoney = "2";
	@ConfigField(name = "frog.money.chance", category = "passive")
	public double frogMoneyChance = 0.05;
	@ConfigField(name = "frog.commands", category = "passive")
	public List<HashMap<String, String>> frogCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} ghast_tear 1");
		values1.put("chance", "0.1");
		frogCommands.add(values1);
	}
	@ConfigField(name = "frog.drophead", category = "passive")
	public boolean frogHeadDropHead = true;
	@ConfigField(name = "frog.head.value", category = "passive")
	public String frogHeadPrize = "0";
	@ConfigField(name = "frog.head.chance", category = "passive")
	public double frogHeadDropChance = 0.05;
	@ConfigField(name = "frog.head.message", category = "passive")
	public String frogHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Goat============================================
	@ConfigField(name = "goat.enabled", category = "mobs")
	public boolean goatEnabled = true;
	@ConfigField(name = "goat.message", category = "mobs")
	public String goatMessage = "You killed a §1{killed}";
	@ConfigField(name = "goat.money.amount", category = "mobs")
	public String goatMoney = "0.0";
	@ConfigField(name = "goat.money.chance", category = "mobs")
	public double goatMoneyChance = 0.05;
	@ConfigField(name = "goat.commands", category = "mobs")
	public List<HashMap<String, String>> goatCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.10");
		goatCommands.add(values1);
	}
	@ConfigField(name = "goat.head.drophead", category = "mobs")
	public boolean goatHeadDropHead = true;
	@ConfigField(name = "goat.head.value", category = "mobs")
	public String goatHeadPrize = "1";
	@ConfigField(name = "goat.head.chance", category = "mobs")
	public double goatHeadDropChance = 0.05;
	@ConfigField(name = "goat.head.message", category = "mobs")
	public String goatHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Glow Squid============================================
	@ConfigField(name = "glowsquid.enabled", category = "mobs")
	public boolean glowsquidEnabled = true;
	@ConfigField(name = "glowsquid.message", category = "mobs")
	public String glowsquidMessage = "You killed a §1{killed}";
	@ConfigField(name = "glowsquid.money.amount", category = "mobs")
	public String glowsquidMoney = "2.0";
	@ConfigField(name = "glowsquid.money.chance", category = "mobs")
	public double glowsquidMoneyChance = 0.5;
	@ConfigField(name = "glowsquid.commands", category = "mobs")
	public List<HashMap<String, String>> glowsquidCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.10");
		glowsquidCommands.add(values1);
	}
	@ConfigField(name = "glowsquid.head.drophead", category = "mobs")
	public boolean glowsquidHeadDropHead = true;
	@ConfigField(name = "glowsquid.head.value", category = "mobs")
	public String glowsquidHeadPrize = "0";
	@ConfigField(name = "glowsquid.head.chance", category = "mobs")
	public double glowsquidHeadDropChance = 0.05;
	@ConfigField(name = "glowsquid.head.message", category = "mobs")
	public String glowsquidHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Horse============================================
	@ConfigField(name = "horse.enabled", category = "passive")
	public boolean horseEnabled = true;
	@ConfigField(name = "horse.message", category = "passive")
	public String horseMessage = "You killed a §1{killed}";
	@ConfigField(name = "horse.money.amount", category = "passive")
	public String horseMoney = "0";
	@ConfigField(name = "horse.money.chance", category = "passive")
	public double horseCmdRunChance = 0.05;
	@ConfigField(name = "horse.commands", category = "passive")
	public List<HashMap<String, String>> horseCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.1");
		horseCommands.add(values1);
	}
	@ConfigField(name = "horse.head.drophead", category = "passive")
	public boolean horseHeadDropHead = true;
	@ConfigField(name = "horse.head.value", category = "passive")
	public String horseHeadPrize = "0";
	@ConfigField(name = "horse.head.chance", category = "passive")
	public double horseHeadDropChance = 0.05;
	@ConfigField(name = "horse.head.message", category = "passive")
	public String horseHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Llama============================================
	@ConfigField(name = "llama.enabled", category = "passive")
	public boolean llamaEnabled = true;
	@ConfigField(name = "llama.message", category = "passive")
	public String llamaMessage = "You killed a §1{killed}";
	@ConfigField(name = "llama.money.amount", category = "passive")
	public String llamaMoney = "0";
	@ConfigField(name = "llama.money.chance", category = "passive")
	public double llamaMoneyChance = 1;
	@ConfigField(name = "llama.commands", category = "passive")
	public List<HashMap<String, String>> llamaCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.05");
		llamaCommands.add(values1);
	}
	@ConfigField(name = "llama.head.drophead", category = "passive")
	public boolean llamaHeadDropHead = true;
	@ConfigField(name = "llama.head.value", category = "passive")
	public String llamaHeadPrize = "0";
	@ConfigField(name = "llama.head.chance", category = "passive")
	public double llamaHeadDropChance = 0.05;
	@ConfigField(name = "llama.head.message", category = "passive")
	public String llamaHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Mule============================================
	@ConfigField(name = "mule.enabled", category = "passive")
	public boolean muleEnabled = true;
	@ConfigField(name = "mule.message", category = "passive")
	public String muleMessage = "You killed a §1{killed}";
	@ConfigField(name = "mule.money.amount", category = "passive")
	public String muleMoney = "0";
	@ConfigField(name = "mule.money.chance", category = "passive")
	public double muleMoneyChance = 1;
	@ConfigField(name = "mule.commands", category = "passive")
	public List<HashMap<String, String>> muleCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} beetroot 2");
		values1.put("chance", "0.05");
		muleCommands.add(values1);
	}
	@ConfigField(name = "mule.head.drophead", category = "passive")
	public boolean muleHeadDropHead = true;
	@ConfigField(name = "mule.head.value", category = "passive")
	public String muleHeadPrize = "0";
	@ConfigField(name = "mule.head.chance", category = "passive")
	public double muleHeadDropChance = 0.05;
	@ConfigField(name = "mule.head.message", category = "passive")
	public String muleHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Mushroom Cow============================================
	@ConfigField(name = "mushroom_cow.enabled", category = "passive")
	public boolean mushroomCowEnabled = true;
	@ConfigField(name = "mushroom_cow.message", category = "passive")
	public String mushroomCowMessage = "You killed a §1{killed}";
	@ConfigField(name = "mushroom_cow.money.amount", category = "passive")
	public String mushroomCowMoney = "0";
	@ConfigField(name = "mushroom_cow.money.chance", category = "passive")
	public double mushroomCowCmdRunChance = 1;
	@ConfigField(name = "mushroom_cow.commands", category = "passive")
	public List<HashMap<String, String>> mushroomCowCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} clay_ball 1");
		values1.put("chance", "0.05");
		mushroomCowCommands.add(values1);
	}
	@ConfigField(name = "mushroom_cow.head.drophead", category = "passive")
	public boolean mushroomCowHeadDropHead = true;
	@ConfigField(name = "mushroom_cow.head.value", category = "passive")
	public String mushroomCowHeadPrize = "0";
	@ConfigField(name = "mushroom_cow.head.chance", category = "passive")
	public double mushroomCowHeadDropChance = 0.05;
	@ConfigField(name = "mushroom_cow.head.message", category = "passive")
	public String mushroomCowHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Ocelot============================================
	@ConfigField(name = "ocelot.enabled", category = "passive")
	public boolean ocelotEnabled = true;
	@ConfigField(name = "ocelot.message", category = "passive")
	public String ocelotMessage = "You killed a §1{killed}";
	@ConfigField(name = "ocelot.money.amount", category = "passive")
	public String ocelotMoney = "0";
	@ConfigField(name = "ocelot.money.chance", category = "passive")
	public double ocelotCmdRunChance = 1;
	@ConfigField(name = "ocelot.commands", category = "passive")
	public List<HashMap<String, String>> ocelotCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} milk_bucket 1");
		values1.put("chance", "0.05");
		ocelotCommands.add(values1);
	}
	@ConfigField(name = "ocelot.head.drophead", category = "passive")
	public boolean ocelotHeadDropHead = true;
	@ConfigField(name = "ocelot.head.value", category = "passive")
	public String ocelotHeadPrize = "0";
	@ConfigField(name = "ocelot.head.chance", category = "passive")
	public double ocelotHeadDropChance = 0.05;
	@ConfigField(name = "ocelot.head.message", category = "passive")
	public String ocelotHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Panda============================================
	@ConfigField(name = "panda.enabled", category = "passive")
	public boolean pandaEnabled = true;
	@ConfigField(name = "panda.message", category = "passive")
	public String pandaMessage = "You killed a §1{killed}";
	@ConfigField(name = "panda.money.amount", category = "passive")
	public String pandaMoney = "2";
	@ConfigField(name = "panda.money.chance", category = "passive")
	public double pandaMoneyChance = 1;
	@ConfigField(name = "panda.commands", category = "passive")
	public List<HashMap<String, String>> pandaCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} reeds 3");
		values1.put("chance", "0.1");
		pandaCommands.add(values1);
	}
	@ConfigField(name = "panda.head.drophead", category = "passive")
	public boolean pandaHeadDropHead = true;
	@ConfigField(name = "panda.head.value", category = "passive")
	public String pandaHeadPrize = "0";
	@ConfigField(name = "panda.head.chance", category = "passive")
	public double pandaHeadDropChance = 0.10;
	@ConfigField(name = "panda.head.message", category = "passive")
	public String pandaHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Parrot============================================
	@ConfigField(name = "parrot.enabled", category = "passive")
	public boolean parrotEnabled = true;
	@ConfigField(name = "parrot.message", category = "passive")
	public String parrotMessage = "You killed a §1{killed}";
	@ConfigField(name = "parrot.money.amount", category = "passive")
	public String parrotMoney = "2";
	@ConfigField(name = "parrot.money.chance", category = "passive")
	public double parrotMoneyChance = 1;
	@ConfigField(name = "parrot.commands", category = "passive")
	public List<HashMap<String, String>> parrotCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} feather 1");
		values1.put("chance", "0.1");
		parrotCommands.add(values1);
	}
	@ConfigField(name = "parrot.head.drophead", category = "passive")
	public boolean parrotHeadDropHead = true;
	@ConfigField(name = "parrot.head.value", category = "passive")
	public String parrotHeadPrize = "0";
	@ConfigField(name = "parrot.head.chance", category = "passive")
	public double parrotHeadDropChance = 0.10;
	@ConfigField(name = "parrot.head.message", category = "passive")
	public String parrotHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Pig============================================
	@ConfigField(name = "pig.enabled", category = "passive")
	public boolean pigEnabled = true;
	@ConfigField(name = "pig.message", category = "passive")
	public String pigMessage = "You killed a §1{killed}";
	@ConfigField(name = "pig.money.amount", category = "passive")
	public String pigMoney = "0";
	@ConfigField(name = "pig.money.chance", category = "passive")
	public double pigCmdRunChance = 1;
	@ConfigField(name = "pig.commands", category = "passive")
	public List<HashMap<String, String>> pigCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} carrot 2");
		values1.put("chance", "0.05");
		pigCommands.add(values1);
	}
	@ConfigField(name = "pig.head.drophead", category = "passive")
	public boolean pigHeadDropHead = true;
	@ConfigField(name = "pig.head.value", category = "passive")
	public String pigHeadPrize = "0";
	@ConfigField(name = "pig.head.chance", category = "passive")
	public double pigHeadDropChance = 0.05;
	@ConfigField(name = "pig.head.message", category = "passive")
	public String pigHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Rabbit============================================
	@ConfigField(name = "rabbit.enabled", category = "passive")
	public boolean rabbitEnabled = true;
	@ConfigField(name = "rabbit.message", category = "passive")
	public String rabbitMessage = "You killed a §1{killed}";
	@ConfigField(name = "rabbit.money.amount", category = "passive")
	public String rabbitMoney = "0";
	@ConfigField(name = "rabbit.money.chance", category = "passive")
	public double rabbitCmdRunChance = 1;
	@ConfigField(name = "rabbit.commands", category = "passive")
	public List<HashMap<String, String>> rabbitCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} carrot 5");
		values1.put("chance", "0.05");
		rabbitCommands.add(values1);
	}
	@ConfigField(name = "rabbit.head.drophead", category = "passive")
	public boolean rabbitHeadDropHead = true;
	@ConfigField(name = "rabbit.head.value", category = "passive")
	public String rabbitHeadPrize = "0";
	@ConfigField(name = "rabbit.head.chance", category = "passive")
	public double rabbitHeadDropChance = 0.05;
	@ConfigField(name = "rabbit.head.message", category = "passive")
	public String rabbitHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Sheep============================================
	@ConfigField(name = "sheep.enabled", category = "passive")
	public boolean sheepEnabled = true;
	@ConfigField(name = "sheep.message", category = "passive")
	public String sheepMessage = "You killed a §1{killed}";
	@ConfigField(name = "sheep.money.amount", category = "passive")
	public String sheepMoney = "0";
	@ConfigField(name = "sheep.money.chance", category = "passive")
	public double sheepCmdRunChance = 1;
	@ConfigField(name = "sheep.commands", category = "passive")
	public List<HashMap<String, String>> sheepCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} sugar 5");
		values1.put("chance", "0.05");
		sheepCommands.add(values1);
	}
	@ConfigField(name = "sheep.head.drophead", category = "passive")
	public boolean sheepHeadDropHead = true;
	@ConfigField(name = "sheep.head.value", category = "passive")
	public String sheepHeadPrize = "0";
	@ConfigField(name = "sheep.head.chance", category = "passive")
	public double sheepHeadDropChance = 0.05;
	@ConfigField(name = "sheep.head.message", category = "passive")
	public String sheepHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Skeleton Horse============================================
	@ConfigField(name = "skeleton_horse.enabled", category = "passive")
	public boolean skeletonHorseEnabled = true;
	@ConfigField(name = "skeleton_horse.message", category = "passive")
	public String skeletonHorseMessage = "You killed a §1{killed}";
	@ConfigField(name = "skeleton_horse.money.amount", category = "passive")
	public String skeletonHorseMoney = "10";
	@ConfigField(name = "skeleton_horse.money.chance", category = "passive")
	public double skeletonHorseMoneyChance = 1;
	@ConfigField(name = "skeleton_horse.commands", category = "passive")
	public List<HashMap<String, String>> skeletonHorseCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} bone 5");
		values1.put("chance", "0.05");
		skeletonHorseCommands.add(values1);
	}
	@ConfigField(name = "skeleton_horse.head.drophead", category = "passive")
	public boolean skeletonHorseHeadDropHead = true;
	@ConfigField(name = "skeleton_horse.head.value", category = "passive")
	public String skeletonHorseHeadPrize = "0";
	@ConfigField(name = "skeleton_horse.head.chance", category = "passive")
	public double skeletonHorseHeadDropChance = 0.05;
	@ConfigField(name = "skeleton_horse.head.message", category = "passive")
	public String skeletonHorseHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Sniffer============================================
	@ConfigField(name = "sniffer.enabled", category = "passive")
	public boolean snifferEnabled = true;
	@ConfigField(name = "sniffer.message", category = "passive")
	public String snifferMessage = "You killed a §1{killed}";
	@ConfigField(name = "sniffer.money.amount", category = "passive")
	public String snifferMoney = "0";
	@ConfigField(name = "sniffer.money.chance", category = "passive")
	public double snifferCmdRunChance = 0;
	@ConfigField(name = "sniffer.commands", category = "passive")
	public List<HashMap<String, String>> snifferCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} suspicious_sand 1");
		values1.put("chance", "0.25");
		snifferCommands.add(values1);
	}
	@ConfigField(name = "sniffer.head.drophead", category = "passive")
	public boolean snifferHeadDropHead = true;
	@ConfigField(name = "sniffer.head.value", category = "passive")
	public String snifferHeadPrize = "0";
	@ConfigField(name = "sniffer.head.chance", category = "passive")
	public double snifferHeadDropChance = 0.05;
	@ConfigField(name = "sniffer.head.message", category = "passive")
	public String snifferHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Snowman============================================
	@ConfigField(name = "snowman.enabled", category = "passive")
	public boolean snowmanEnabled = true;
	@ConfigField(name = "snowman.message", category = "passive")
	public String snowmanMessage = "You killed a §1{killed}";
	@ConfigField(name = "snowman.money.amount", category = "passive")
	public String snowmanMoney = "0";
	@ConfigField(name = "snowman.money.chance", category = "passive")
	public double snowmanCmdRunChance = 0;
	@ConfigField(name = "snowman.commands", category = "passive")
	public List<HashMap<String, String>> snowmanCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} snowball 10");
		values1.put("chance", "0.1");
		snowmanCommands.add(values1);
	}
	@ConfigField(name = "snowman.head.drophead", category = "passive")
	public boolean snowmanHeadDropHead = true;
	@ConfigField(name = "snowman.head.value", category = "passive")
	public String snowmanHeadPrize = "0";
	@ConfigField(name = "snowman.head.chance", category = "passive")
	public double snowmanHeadDropChance = 0.05;
	@ConfigField(name = "snowman.head.message", category = "passive")
	public String snowmanHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Squid============================================
	@ConfigField(name = "squid.enabled", category = "passive")
	public boolean squidEnabled = true;
	@ConfigField(name = "squid.message", category = "passive")
	public String squidMessage = "You killed a §1{killed}";
	@ConfigField(name = "squid.money.amount", category = "passive")
	public String squidMoney = "0";
	@ConfigField(name = "squid.money.chance", category = "passive")
	public double squidCmdRunChance = 1;
	@ConfigField(name = "squid.commands", category = "passive")
	public List<HashMap<String, String>> squidCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} slimeball 5");
		values1.put("chance", "0.05");
		squidCommands.add(values1);
	}
	@ConfigField(name = "squid.head.drophead", category = "passive")
	public boolean squidHeadDropHead = true;
	@ConfigField(name = "squid.head.value", category = "passive")
	public String squidHeadPrize = "0";
	@ConfigField(name = "squid.head.chance", category = "passive")
	public double squidHeadDropChance = 0.05;
	@ConfigField(name = "squid.head.message", category = "passive")
	public String squidHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Tadpole============================================
	@ConfigField(name = "tadpole.enabled", category = "passive")
	public boolean tadpoleEnabled = true;
	@ConfigField(name = "tadpole.message", category = "passive")
	public String tadpoleMessage = "You killed a §1{killed}";
	@ConfigField(name = "tadpole.money.amount", category = "passive")
	public String tadpoleMoney = "-10";
	@ConfigField(name = "tadpole.money.chance", category = "passive")
	public double tadpoleMoneyChance = 1;

	@ConfigField(name = "tadpole.commands", category = "passive")
	public List<HashMap<String, String>> tadpoleCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} chest 1");
		values1.put("chance", "0.05");
		tadpoleCommands.add(values1);
	}
	@ConfigField(name = "tadpole.head.drophead", category = "passive")
	public boolean tadpoleHeadDropHead = true;
	@ConfigField(name = "tadpole.head.value", category = "passive")
	public String tadpoleHeadPrize = "0";
	@ConfigField(name = "tadpole.head.chance", category = "passive")
	public double tadpoleHeadDropChance = 0.05;
	@ConfigField(name = "tadpole.head.message", category = "passive")
	public String tadpoleHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Trader Llama============================================
	@ConfigField(name = "traderllama.enabled", category = "passive")
	public boolean traderLlamaEnabled = true;
	@ConfigField(name = "traderllama.message", category = "passive")
	public String traderLlamaMessage = "You killed a §1{killed}";
	@ConfigField(name = "traderllama.money.amount", category = "passive")
	public String traderLlamaMoney = "-10";
	@ConfigField(name = "traderllama.money.chance", category = "passive")
	public double traderLlamaCmdRunChance = 1;
	@ConfigField(name = "traderllama.commands", category = "passive")
	public List<HashMap<String, String>> traderllamaCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} chest 1");
		values1.put("chance", "0.05");
		traderllamaCommands.add(values1);
	}
	@ConfigField(name = "traderllama.head.drophead", category = "passive")
	public boolean traderLlamaHeadDropHead = true;
	@ConfigField(name = "traderllama.head.value", category = "passive")
	public String traderLlamaHeadPrize = "0";
	@ConfigField(name = "traderllama.head.chance", category = "passive")
	public double traderLlamaHeadDropChance = 0.05;
	@ConfigField(name = "traderllama.head.message", category = "passive")
	public String traderLlamaHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Wandering Trader============================================
	@ConfigField(name = "wanderingtrader.enabled", category = "villager")
	public boolean wanderingTraderEnabled = true;
	@ConfigField(name = "wanderingtrader.message", category = "villager")
	public String wanderingTraderMessage = "You killed a §1{killed}";
	@ConfigField(name = "wanderingtrader.money.amount", category = "villager")
	public String wanderingTraderMoney = "-5";
	@ConfigField(name = "wanderingtrader.money.chance", category = "villager")
	public double wanderingTraderCmdRunChance = 1;
	@ConfigField(name = "wanderingtrader.commands", category = "villager")
	public List<HashMap<String, String>> wanderingTraderCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} torch 10");
		values1.put("chance", "0.5");
		wanderingTraderCommands.add(values1);
	}
	@ConfigField(name = "wanderingtrader.head.drophead", category = "villager")
	public boolean wanderingTraderHeadDropHead = true;
	@ConfigField(name = "wanderingtrader.head.value", category = "villager")
	public String wanderingTraderHeadPrize = "0";
	@ConfigField(name = "wanderingtrader.head.chance", category = "villager")
	public double wanderingTraderHeadDropChance = 0.05;
	@ConfigField(name = "wanderingtrader.head.message", category = "villager")
	public String wanderingTraderHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Wolf============================================
	@ConfigField(name = "wolf.enabled", category = "passive")
	public boolean wolfEnabled = true;
	@ConfigField(name = "wolf.message", category = "passive")
	public String wolfMessage = "You killed a §1{killed}";
	@ConfigField(name = "wolf.money.amount", category = "passive")
	public String wolfMoney = "-10";
	@ConfigField(name = "wolf.money.chance", category = "passive")
	public double wolfCmdRunChance = 1;
	@ConfigField(name = "wolf.commands", category = "passive")
	public List<HashMap<String, String>> wolfCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.05");
		wolfCommands.add(values1);
	}
	@ConfigField(name = "wolf.head.drophead", category = "passive")
	public boolean wolfHeadDropHead = true;
	@ConfigField(name = "wolf.head.value", category = "passive")
	public String wolfHeadPrize = "0";
	@ConfigField(name = "wolf.head.chance", category = "passive")
	public double wolfHeadDropChance = 0.05;
	@ConfigField(name = "wolf.head.message", category = "passive")
	public String wolfHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// =====Zombie Horse============================================
	@ConfigField(name = "zombie_horse.enabled", category = "passive")
	public boolean zombieHorseEnabled = true;
	@ConfigField(name = "zombie_horse.message", category = "passive")
	public String zombieHorseMessage = "You killed a §1{killed}";
	@ConfigField(name = "zombie_horse.money.amount", category = "passive")
	public String zombieHorseMoney = "-10";
	@ConfigField(name = "zombie_horse.money.chance", category = "passive")
	public double zombieHorseMoneyChance = 1;
	@ConfigField(name = "zombie_horse.commands", category = "passive")
	public List<HashMap<String, String>> zombieHorseCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} quartz 3");
		values1.put("chance", "0.25");
		zombieHorseCommands.add(values1);
	}
	@ConfigField(name = "zombie_horse.head.drophead", category = "passive")
	public boolean zombieHorseHeadDropHead = true;
	@ConfigField(name = "zombie_horse.head.value", category = "passive")
	public String zombieHorseHeadPrize = "0";
	@ConfigField(name = "zombie_horse.head.chance", category = "passive")
	public double zombieHorseHeadDropChance = 0.25;
	@ConfigField(name = "zombie_horse.head.message", category = "passive")
	public String zombieHorseHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// #####################################################################################
	// Passive Mobs - Monsters
	// #####################################################################################

	// =====Zombie Villager============================================
	@ConfigField(name = "zombie_villager.enabled", category = "passive_monsters")
	public boolean zombieVillagerEnabled = true;
	@ConfigField(name = "zombie_villager.message", category = "passive_monsters")
	public String zombieVillagerMessage = "You killed a §1{killed}";
	@ConfigField(name = "zombie_villager.money.amount", category = "passive_monsters")
	public String zombieVillagerMoney = "1:2";
	@ConfigField(name = "zombie_villager.money.chance", category = "passive_monsters")
	public double zombieVillagerMoneyChance = 1;
	@ConfigField(name = "zombie_villager.commands", category = "passive_monsters")
	public List<HashMap<String, String>> zombieVillagerCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} bowl 1");
		values1.put("chance", "0.05");
		zombieVillagerCommands.add(values1);
	}
	@ConfigField(name = "zombie_villager.head.drophead", category = "passive_monsters")
	public boolean zombieVillagerHeadDropHead = true;
	@ConfigField(name = "zombie_villager.head.value", category = "passive_monsters")
	public String zombieVillagerHeadPrize = "0";
	@ConfigField(name = "zombie_villager.head.chance", category = "passive_monsters")
	public double zombieVillagerHeadDropChance = 0.05;
	@ConfigField(name = "zombie_villager.head.message", category = "passive_monsters")
	public String zombieVillagerHeadMessage = "§aThe §1{killed} §adropped a skull on the ground";

	// #####################################################################################
	// Fish / Fishing
	// #####################################################################################

	@ConfigField(name = "enable_fishing_rewards", category = "fishing", comment = "Set this to false if you want to disable all fishing rewards / features.")
	public boolean enableFishingRewards = true;

	// =====Raw Fish============================================
	@ConfigField(name = "cod.enabled", category = "fishing")
	public boolean codEnabled = true;
	@ConfigField(name = "cod.message", category = "fishing")
	public String codMessage = "You caught a §1{killed}";
	@ConfigField(name = "cod.money.amount", category = "fishing")
	public String codMoney = "1:3";
	@ConfigField(name = "cod.money.chance", category = "fishing")
	public double codCmdRunChance = 1;
	@ConfigField(name = "cod.commands", category = "fishing")
	public List<HashMap<String, String>> codCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.05");
		codCommands.add(values1);
	}
	@ConfigField(name = "cod.head.drophead", category = "fishing")
	public boolean codHeadDropHead = true;
	@ConfigField(name = "cod.head.value", category = "fishing")
	public String codHeadPrize = "0";
	@ConfigField(name = "cod.head.chance", category = "fishing")
	public double codHeadDropChance = 0.05;
	@ConfigField(name = "cod.head.message", category = "fishing")
	public String codHeadMessage = "§aThe §1{killed} §adropped a Cod head in the water";

	// =====Raw Salmon============================================
	@ConfigField(name = "salmon.enabled", category = "fishing")
	public boolean salmonEnabled = true;
	@ConfigField(name = "salmon.message", category = "fishing")
	public String salmonMessage = "You caught a §1{killed}";
	@ConfigField(name = "salmon.money.amount", category = "fishing")
	public String salmonMoney = "2:8";
	@ConfigField(name = "salmon.money.chance", category = "fishing")
	public double salmonCmdRunChance = 1;
	@ConfigField(name = "salmon.commands", category = "fishing")
	public List<HashMap<String, String>> salmonCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} bowl 1");
		values1.put("chance", "0.1");
		salmonCommands.add(values1);
	}
	@ConfigField(name = "salmons.head.drophead", category = "fishing")
	public boolean salmonHeadDropHead = true;
	@ConfigField(name = "salmon.head.value", category = "fishing")
	public String salmonHeadPrize = "0";
	@ConfigField(name = "salmon.head.chance", category = "fishing")
	public double salmonHeadDropChance = 0.10;
	@ConfigField(name = "salmon.head.message", category = "fishing")
	public String salmonHeadMessage = "§aThe §1{killed} §adropped a Salmon head in the water";

	// =====Tropical Fish============================================
	@ConfigField(name = "tropical_fish.enabled", category = "fishing")
	public boolean tropicalFishEnabled = true;
	@ConfigField(name = "tropical_fish.message", category = "fishing")
	public String tropicalFishMessage = "You caught a §1{killed}";
	@ConfigField(name = "tropical_fish.money.amount", category = "fishing")
	public String tropicalFishMoney = "20:40";
	@ConfigField(name = "tropical_fish.money.chance", category = "fishing")
	public double tropicalFishCmdRunChance = 1;
	@ConfigField(name = "tropical_fish.commands", category = "fishing")
	public List<HashMap<String, String>> tropicalFishCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.5");
		tropicalFishCommands.add(values1);
	}
	@ConfigField(name = "tropical_fish.head.drophead", category = "fishing")
	public boolean tropicalFishHeadDropHead = true;
	@ConfigField(name = "tropical_fish.head.value", category = "fishing")
	public String tropicalFishHeadPrize = "0";
	@ConfigField(name = "tropical_fish.head.chance", category = "fishing")
	public double tropicalFishHeadDropChance = 0.5;
	@ConfigField(name = "tropical_fish.head.message", category = "fishing")
	public String tropicalFishHeadMessage = "§aThe §1{killed} §adropped a Aquarium in the water";

	// =====Pufferfish============================================
	@ConfigField(name = "pufferfish.enabled", category = "fishing")
	public boolean pufferfishEnabled = true;
	@ConfigField(name = "pufferfish.message", category = "fishing")
	public String pufferfishMessage = "You caught a §1{killed}";
	@ConfigField(name = "pufferfish.money.amount", category = "fishing")
	public String pufferfishMoney = "5:15";
	@ConfigField(name = "pufferfish.money.chance", category = "fishing")
	public double pufferfishCmdRunChance = 1;
	@ConfigField(name = "pufferfish.commands", category = "fishing")
	public List<HashMap<String, String>> pufferfishCommands = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "give {player} iron_ingot 1");
		values1.put("chance", "0.4");
		pufferfishCommands.add(values1);
	}
	@ConfigField(name = "pufferfish.head.drophead", category = "fishing")
	public boolean pufferfishHeadDropHead = true;
	@ConfigField(name = "pufferfish.head.value", category = "fishing")
	public String pufferfishHeadPrize = "0";
	@ConfigField(name = "pufferfish.head.chance", category = "fishing")
	public double pufferfishHeadDropChance = 0.40;
	@ConfigField(name = "pufferfish.head.message", category = "fishing")
	public String pufferfishHeadMessage = "§aThe §1{killed} §adropped a fish head in the water";

	// #####################################################################################
	// PVP
	// #####################################################################################
	@ConfigField(name = "pvp_allowed", category = "pvp.player", comment = "Set pvpAllowed=false to disable rewards on killing other players.")
	public boolean pvpAllowed = true;
	@ConfigField(name = "rob_from_victim", category = "pvp.player", comment = "Set rob_from_victim=true to steal from the victim or "
			+ "\nrob_from_victim=false to get the reward money from the server.")
	public boolean robFromVictim = true;
	@ConfigField(name = "message", category = "pvp.player")
	public String pvpCmdDesc = "";
	@ConfigField(name = "amount", category = "pvp.player.money")
	public String pvpKillMoney = "1.0%";
	@ConfigField(name = "chance", category = "pvp.player.money.")
	public double pvpCmdRunChance = 1;
	@ConfigField(name = "message", category = "pvp.player", comment = "Write the message to the killer, describing the reward / console commands")
	public String pvpKillMessage = "You got {killed_player}\'s skull";
	@ConfigField(name = "commands", category = "pvp.player", comment = "One or more console commands to be run when a player kills another player.")
	public List<HashMap<String, String>> pvpCmdNew = new ArrayList<HashMap<String, String>>();
	{
		HashMap<String, String> values1 = new HashMap<String, String>();
		values1.put("cmd", "");
		values1.put("chance", "0.5");
		pvpCmdNew.add(values1);
	}
	@ConfigField(name = "drophead", category = "pvp.player.head")
	public boolean pvpHeadDropHead = true;
	@ConfigField(name = "value", category = "pvp.player.head", comment = "The Head price if you want playerheads to have a value like the bag of gold.")
	public String pvpHeadPrize = "10";
	@ConfigField(name = "chance", category = "pvp.player.head")
	public double pvpHeadDropChance = 1;
	@ConfigField(name = "message", category = "pvp.player.head")
	public String pvpHeadMessage = "You got {killed}'s skull";

	// #####################################################################################
	// Specials / Achievements
	// #####################################################################################
	@ConfigField(name = "disable_achievements_in_worlds", category = "achievements", comment = "Put the names of the worlds here where you want to disable achievements."
			+ "\nPlayers will still get rewards for killings.")
	public String[] disableAchievementsInWorlds = { "worldname", "worldname2" };
	@ConfigField(name = "show_achievements_without_reward", category = "achievements", comment = "Set this to true if you want to see achievements when you use /mobhunt achievements"
			+ "\nallthough there is no reward for this.")
	public boolean showAchievementsWithoutAReward = false;

	@ConfigField(name = "money", category = "achievements.specials.charged_kill")
	public double specialCharged = 1000;
	@ConfigField(name = "commands", category = "achievements.specials.charged_kill")
	public String specialChargedCmd = "give {player} gold_ingot 1";
	@ConfigField(name = "message", category = "achievements.specials.charged_kill")
	public String specialChargedCmdDesc = "";

	@ConfigField(name = "money", category = "achievements.specials.creeper_punch")
	public double specialCreeperPunch = 1000;
	@ConfigField(name = "commands", category = "achievements.specials.creeper_punch")
	public String specialCreeperPunchCmd = "give {player} gold_ingot 1";
	@ConfigField(name = "message", category = "achievements.specials.creeper_punch")
	public String specialCreeperPunchCmdDesc = "";

	@ConfigField(name = "money", category = "achievements.specials.axe_murderer")
	public double specialAxeMurderer = 1000;
	@ConfigField(name = "commands", category = "achievements.specials.axe_murderer")
	public String specialAxeMurdererCmd = "give {player} gold_ingot 1";
	@ConfigField(name = "message", category = "achievements.specials.axe_murderer")
	public String specialAxeMurdererCmdDesc = "";

	@ConfigField(name = "money", category = "achievements.specials.david_and_goliath")
	public double davidAndGoliat = 1000;
	@ConfigField(name = "commands", category = "achievements.specials.david_and_goliath")
	public String davidAndGoliatCmd = "give {player} diamond_helmet 1";
	@ConfigField(name = "message", category = "achievements.specials.david_and_goliath")
	public String davidAndGoliatCmdDesc = "You got 1000 and a Diamond Helmet for the kill";

	@ConfigField(name = "money", category = "achievements.specials.recordhungry")
	public double specialRecordHungry = 1000;
	@ConfigField(name = "commands", category = "achievements.specials.recordhungry")
	public String specialRecordHungryCmd = "give {player} gold_ingot 1";
	@ConfigField(name = "message", category = "achievements.specials.recordhungry")
	public String specialRecordHungryCmdDesc = "";

	@ConfigField(name = "money", category = "achievements.specials.infighting")
	public double specialInfighting = 2000;
	@ConfigField(name = "commands", category = "achievements.specials.infighting")
	public String specialInfightingCmd = "give {player} gold_ingot 1";
	@ConfigField(name = "messages", category = "achievements.specials.infighting")
	public String specialInfightingCmdDesc = "";

	@ConfigField(name = "money", category = "achievements.specials.by_the_book")
	public double specialByTheBook = 1000;
	@ConfigField(name = "commands", category = "achievements.specials.by_the_book")
	public String specialByTheBookCmd = "give {player} gold_ingot 1";
	@ConfigField(name = "message", category = "achievements.specials.by_the_book")
	public String specialByTheBookCmdDesc = "";

	@ConfigField(name = "money", category = "achievements.specials.creepercide")
	public double specialCreepercide = 1000;
	@ConfigField(name = "commands", category = "achievements.specials.creepercide")
	public String specialCreepercideCmd = "give {player} gold_ingot 1";
	@ConfigField(name = "message", category = "achievements.specials.creepercide")
	public String specialCreepercideCmdDesc = "";

	@ConfigField(name = "money", category = "achievements.specials.hunt_begins")
	public double specialHuntBegins = 500;
	@ConfigField(name = "commands", category = "achievements.specials.hunt_begins")
	public String specialHuntBeginsCmd = "";
	@ConfigField(name = "message", category = "achievements.specials.hunt_begins")
	public String specialHuntBeginsCmdDesc = "";

	@ConfigField(name = "money", category = "achievements.specials.itsmagic")
	public double specialItsMagic = 2000;
	@ConfigField(name = "commands", category = "achievements.specials.itsmagic")
	public String specialItsMagicCmd = "give {player} gold_ingot 1";
	@ConfigField(name = "message", category = "achievements.specials.itsmagic")
	public String specialItsMagicCmdDesc = "Enjoy you Gold ingot";

	@ConfigField(name = "money", category = "achievements.specials.fancypants")
	public double specialFancyPants = 1000;
	@ConfigField(name = "commands", category = "achievements.specials.fancypants")
	public String specialFancyPantsCmd = "give {player} gold_ingot 1";
	@ConfigField(name = "message", category = "achievements.specials.fancypants")
	public String specialFancyPantsCmdDesc = "Enjoy you Gold ingot";

	@ConfigField(name = "money", category = "achievements.specials.master_sniper")
	public double specialMasterSniper = 2000;
	@ConfigField(name = "commands", category = "achievements.specials.master_sniper")
	public String specialMasterSniperCmd = "give {player} gold_ingot 1";
	@ConfigField(name = "message", category = "achievements.specials.master_sniper")
	public String specialMasterSniperCmdDesc = "Enjoy you Gold ingot";

	@ConfigField(name = "money", category = "achievements.specials.neptune")
	public double specialNeptune = 2000;
	@ConfigField(name = "commands", category = "achievements.specials.neptune")
	public String specialNeptuneCmd = "give {player} gold_ingot 1";
	@ConfigField(name = "message", category = "achievements.specials.neptune")
	public String specialNeptuneCmdDesc = "Enjoy you Gold ingot";

	@ConfigField(name = "money", category = "achievements.specials.justintime")
	public double specialJustInTime = 1000;
	@ConfigField(name = "commands", category = "achievements.specials.justintime")
	public String specialJustInTimeCmd = "give {player} gold_ingot 1";
	@ConfigField(name = "message", category = "achievements.specials.justintime")
	public String specialJustInTimeCmdDesc = "Enjoy you Gold ingot";

	@ConfigField(name = "money", category = "achievements.specials.fangmaster")
	public double specialFangMaster = 1000;
	@ConfigField(name = "commands", category = "achievements.specials.fangmaster")
	public String specialFangMasterCmd = "give {player} gold_ingot 1";
	@ConfigField(name = "message", category = "achievements.specials.fangmaster")
	public String specialFangMasterCmdDesc = "Enjoy your Gold ingot";

	@ConfigField(name = "money", category = "achievements.hunter.level1")
	public double specialHunter1 = 1000;
	@ConfigField(name = "commands", category = "achievements.hunter.level1")
	public String specialHunter1Cmd = "give {player} gold_ingot 5";
	@ConfigField(name = "message", category = "achievements.hunter.level1")
	public String specialHunter1CmdDesc = "Enjoy your 5 Gold ingots";

	@ConfigField(name = "money", category = "achievements.hunter.level2")
	public double specialHunter2 = 2500;
	@ConfigField(name = "commands", category = "achievements.hunter.level2")
	public String specialHunter2Cmd = "give {player} gold_ingot 10";
	@ConfigField(name = "message", category = "achievements.hunter.level2")
	public String specialHunter2CmdDesc = "Enjoy your 10 Gold ingots";

	@ConfigField(name = "money", category = "achievements.hunter.level3")
	public double specialHunter3 = 5000;
	@ConfigField(name = "commands", category = "achievements.hunter.level3")
	public String specialHunter3Cmd = "give {player} gold_ingot 20";
	@ConfigField(name = "message", category = "achievements.hunter.level3")
	public String specialHunter3CmdDesc = "Enjoy your 20 Gold ingots";

	@ConfigField(name = "money", category = "achievements.hunter.level4")
	public double specialHunter4 = 10000;
	@ConfigField(name = "commands", category = "achievements.hunter.level4")
	public String specialHunter4Cmd = "give {player} gold_ingot 25";
	@ConfigField(name = "message", category = "achievements.hunter.level4")
	public String specialHunter4CmdDesc = "Enjoy your 25 Gold ingots";

	@ConfigField(name = "money", category = "achievements.hunter.level5")
	public double specialHunter5 = 20000;
	@ConfigField(name = "commands", category = "achievements.hunter.level5")
	public String specialHunter5Cmd = "give {player} gold_ingot 40";
	@ConfigField(name = "message", category = "achievements.hunter.level5")
	public String specialHunter5CmdDesc = "Enjoy your 40 Gold ingots";

	@ConfigField(name = "money", category = "achievements.hunter.level6")
	public double specialHunter6 = 40000;
	@ConfigField(name = "commands", category = "achievements.hunter.level6")
	public String specialHunter6Cmd = "give {player} gold_ingot 50";
	@ConfigField(name = "message", category = "achievements.hunter.level6")
	public String specialHunter6CmdDesc = "Enjoy your 50 Gold ingots";

	@ConfigField(name = "money", category = "achievements.hunter.level7")
	public double specialHunter7 = 80000;
	@ConfigField(name = "commands", category = "achievements.hunter.level7")
	public String specialHunter7Cmd = "give {player} gold_ingot 60";
	@ConfigField(name = "message", category = "achievements.hunter.level7")
	public String specialHunter7CmdDesc = "Enjoy your 60 Gold ingots";

	@ConfigField(name = "money", category = "achievements.hunter.level8")
	public double specialHunter8 = 160000;
	@ConfigField(name = "commands", category = "achievements.hunter.level8")
	public String specialHunter8Cmd = "give {player} gold_ingot 120";
	@ConfigField(name = "message", category = "achievements.hunter.level8")
	public String specialHunter8CmdDesc = "Enjoy your 120 Gold ingots";

	// #####################################################################################
	// Achievement Hunter Levels
	// #####################################################################################
	@ConfigField(name = "allay_level1", category = "achievements.hunter.mob_level")
	public int allayLevel1 = 100;

	@ConfigField(name = "armorer_level1", category = "achievements.hunter.mob_level")
	public int armorerLevel1 = 100;

	@ConfigField(name = "axolotl_level1", category = "achievements.hunter.mob_level")
	public int axolotlLevel1 = 100;

	@ConfigField(name = "bat_level1", category = "achievements.hunter.mob_level")
	public int batLevel1 = 100;

	@ConfigField(name = "bee_level1", category = "achievements.hunter.mob_level")
	public int beeLevel1 = 100;

	@ConfigField(name = "blaze_level1", category = "achievements.hunter.mob_level")
	public int blazeLevel1 = 80;

	@ConfigField(name = "blacksmith_level1", category = "achievements.hunter.mob_level")
	public int blacksmithLevel1 = 100;

	@ConfigField(name = "bonusmob_level1", category = "achievements.hunter.mob_level")
	public int bonusMobLevel1 = 20;

	@ConfigField(name = "butcher_level1", category = "achievements.hunter.mob_level")
	public int butcherLevel1 = 100;

	@ConfigField(name = "cat_level1", category = "achievements.hunter.mob_level")
	public int catLevel1 = 100;

	@ConfigField(name = "cartographer_level1", category = "achievements.hunter.mob_level")
	public int cartographerLevel1 = 100;

	@ConfigField(name = "cave_spider_level1", category = "achievements.hunter.mob_level")
	public int caveSpiderLevel1 = 100;

	@ConfigField(name = "chicken_level1", category = "achievements.hunter.mob_level")
	public int chickenLevel1 = 100;

	@ConfigField(name = "cleric_level1", category = "achievements.hunter.mob_level")
	public int clericLevel1 = 100;

	@ConfigField(name = "clownfish_level1", category = "achievements.hunter.mob_level")
	public int clownfishLevel1 = 100;

	@ConfigField(name = "cow_level1", category = "achievements.hunter.mob_level")
	public int cowLevel1 = 100;

	@ConfigField(name = "creeper_level1", category = "achievements.hunter.mob_level")
	public int creeperLevel1 = 100;

	@ConfigField(name = "donkey_level1", category = "achievements.hunter.mob_level")
	public int donkeyLevel1 = 100;

	@ConfigField(name = "dolphin_level1", category = "achievements.hunter.mob_level")
	public int dolphinLevel1 = 100;

	@ConfigField(name = "drowned_level1", category = "achievements.hunter.mob_level")
	public int drownedLevel1 = 100;

	@ConfigField(name = "elder_guardian_level1", category = "achievements.hunter.mob_level")
	public int elderGuardianLevel1 = 50;

	@ConfigField(name = "enderdragon_level1", category = "achievements.hunter.mob_level")
	public int enderdragonLevel1 = 20;

	@ConfigField(name = "enderman_level1", category = "achievements.hunter.mob_level")
	public int endermanLevel1 = 100;

	@ConfigField(name = "endermite_level1", category = "achievements.hunter.mob_level")
	public int endermiteLevel1 = 100;

	@ConfigField(name = "evoker_level1", category = "achievements.hunter.mob_level")
	public int evokerLevel1 = 50;

	@ConfigField(name = "farmer_level1", category = "achievements.hunter.mob_level")
	public int farmerLevel1 = 100;

	@ConfigField(name = "fish_level1", category = "achievements.hunter.mob_level")
	public int fishLevel1 = 100;

	@ConfigField(name = "fisherman_level1", category = "achievements.hunter.mob_level")
	public int fishermanLevel1 = 100;

	@ConfigField(name = "fletcher_level1", category = "achievements.hunter.mob_level")
	public int fletcherLevel1 = 100;

	@ConfigField(name = "fox_level1", category = "achievements.hunter.mob_level")
	public int foxLevel1 = 100;

	@ConfigField(name = "frog_level1", category = "achievements.hunter.mob_level")
	public int frogLevel1 = 100;

	@ConfigField(name = "ghast_level1", category = "achievements.hunter.mob_level")
	public int ghastLevel1 = 80;

	@ConfigField(name = "giant_level1", category = "achievements.hunter.mob_level")
	public int giantLevel1 = 100;

	@ConfigField(name = "goat_level1", category = "achievements.hunter.mob_level")
	public int goatLevel1 = 100;

	@ConfigField(name = "glow_squid_level1", category = "achievements.hunter.mob_level")
	public int glowSquidLevel1 = 100;

	@ConfigField(name = "guardian_level1", category = "achievements.hunter.mob_level")
	public int guardianLevel1 = 100;

	@ConfigField(name = "horse_level1", category = "achievements.hunter.mob_level")
	public int horseLevel1 = 100;

	@ConfigField(name = "hoglin_level1", category = "achievements.hunter.mob_level")
	public int hoglinLevel1 = 100;

	@ConfigField(name = "husk_level1", category = "achievements.hunter.mob_level")
	public int huskLevel1 = 100;

	@ConfigField(name = "illusioner_level1", category = "achievements.hunter.mob_level")
	public int illusionerLevel1 = 100;

	@ConfigField(name = "iron_golem_level1", category = "achievements.hunter.mob_level")
	public int ironGolemLevel1 = 100;

	@ConfigField(name = "killerrabbit_level1", category = "achievements.hunter.mob_level")
	public int killerRabbitLevel1 = 100;

	@ConfigField(name = "leatherworker_level1", category = "achievements.hunter.mob_level")
	public int leatherworkerLevel1 = 100;

	@ConfigField(name = "librarian_level1", category = "achievements.hunter.mob_level")
	public int librarianLevel1 = 100;

	@ConfigField(name = "llama_level1", category = "achievements.hunter.mob_level")
	public int llamaLevel1 = 100;

	@ConfigField(name = "magma_cube_level1", category = "achievements.hunter.mob_level")
	public int magmaCubeLevel1 = 100;

	@ConfigField(name = "mason_level1", category = "achievements.hunter.mob_level")
	public int masonLevel1 = 100;

	@ConfigField(name = "mule_level1", category = "achievements.hunter.mob_level")
	public int muleLevel1 = 100;

	@ConfigField(name = "mushroom_cow_level1", category = "achievements.hunter.mob_level")
	public int mushroomCowLevel1 = 100;

	@ConfigField(name = "nitwit_level1", category = "achievements.hunter.mob_level")
	public int nitwitLevel1 = 100;

	@ConfigField(name = "ocelot_level1", category = "achievements.hunter.mob_level")
	public int ocelotLevel1 = 100;

	@ConfigField(name = "panda_level1", category = "achievements.hunter.mob_level")
	public int pandaLevel1 = 100;

	@ConfigField(name = "parrot_level1", category = "achievements.hunter.mob_level")
	public int parrotLevel1 = 100;

	@ConfigField(name = "phantom_level1", category = "achievements.hunter.mob_level")
	public int phantomLevel1 = 100;

	@ConfigField(name = "pillager_level1", category = "achievements.hunter.mob_level")
	public int pillagerLevel1 = 100;

	@ConfigField(name = "piglin_level1", category = "achievements.hunter.mob_level")
	public int piglinLevel1 = 100;

	@ConfigField(name = "piglin_brute_level1", category = "achievements.hunter.mob_level")
	public int piglinBruteLevel1 = 100;

	@ConfigField(name = "pig_level1", category = "achievements.hunter.mob_level")
	public int pigLevel1 = 100;

	@ConfigField(name = "polar_bear_level1", category = "achievements.hunter.mob_level")
	public int polarBearLevel1 = 100;

	@ConfigField(name = "priest_level1", category = "achievements.hunter.mob_level")
	public int priestLevel1 = 100;

	@ConfigField(name = "pvpplayer_level1", category = "achievements.hunter.mob_level")
	public int pvpPlayerLevel1 = 100;

	@ConfigField(name = "pufferfish_level1", category = "achievements.hunter.mob_level")
	public int pufferfishLevel1 = 100;

	@ConfigField(name = "rabbit_level1", category = "achievements.hunter.mob_level")
	public int rabbitLevel1 = 100;

	@ConfigField(name = "ravager_level1", category = "achievements.hunter.mob_level")
	public int ravagerLevel1 = 100;

	@ConfigField(name = "rawfish_level1", category = "achievements.hunter.mob_level")
	public int rawfishLevel1 = 100;

	@ConfigField(name = "rawsalmon_level1", category = "achievements.hunter.mob_level")
	public int rawsalmonLevel1 = 100;

	@ConfigField(name = "sheep_level1", category = "achievements.hunter.mob_level")
	public int sheepLevel1 = 100;

	@ConfigField(name = "shepherd_level1", category = "achievements.hunter.mob_level")
	public int shepherdLevel1 = 100;

	@ConfigField(name = "shulker_level1", category = "achievements.hunter.mob_level")
	public int shulkerLevel1 = 100;

	@ConfigField(name = "silverfish_level1", category = "achievements.hunter.mob_level")
	public int silverfishLevel1 = 100;

	@ConfigField(name = "skeleton_level1", category = "achievements.hunter.mob_level")
	public int skeletonLevel1 = 100;

	@ConfigField(name = "skeletonhorse_level1", category = "achievements.hunter.mob_level")
	public int skeletonHorseLevel1 = 100;

	@ConfigField(name = "slime_base_level1", category = "achievements.hunter.mob_level")
	public int slimeLevel1 = 100;

	@ConfigField(name = "snowman_level1", category = "achievements.hunter.mob_level")
	public int snowmanLevel1 = 100;

	@ConfigField(name = "spider_level1", category = "achievements.hunter.mob_level")
	public int spiderLevel1 = 100;

	@ConfigField(name = "squid_level1", category = "achievements.hunter.mob_level")
	public int squidLevel1 = 100;

	@ConfigField(name = "strider_level1", category = "achievements.hunter.mob_level")
	public int striderLevel1 = 100;

	@ConfigField(name = "traderllama_level1", category = "achievements.hunter.mob_level")
	public int traderllamaLevel1 = 100;

	@ConfigField(name = "stray_level1", category = "achievements.hunter.mob_level")
	public int strayLevel1 = 100;

	@ConfigField(name = "tadpole_level1", category = "achievements.hunter.mob_level")
	public int tadpoleLevel1 = 100;

	@ConfigField(name = "turtle_level1", category = "achievements.hunter.mob_level")
	public int turtleLevel1 = 100;

	@ConfigField(name = "toolsmith_level1", category = "achievements.hunter.mob_level")
	public int toolsmithLevel1 = 100;

	@ConfigField(name = "vex_level1", category = "achievements.hunter.mob_level")
	public int vexLevel1 = 100;

	@ConfigField(name = "villager_level1", category = "achievements.hunter.mob_level")
	public int villagerLevel1 = 100;

	@ConfigField(name = "vindicator_level1", category = "achievements.hunter.mob_level")
	public int vindicatorLevel1 = 100;

	@ConfigField(name = "wandering_trader_level1", category = "achievements.hunter.mob_level")
	public int wanderingTraderLevel1 = 80;

	@ConfigField(name = "warden_level1", category = "achievements.hunter.mob_level")
	public int wardenLevel1 = 80;

	@ConfigField(name = "weaponsmith_level1", category = "achievements.hunter.mob_level")
	public int weaponsmithLevel1 = 80;

	@ConfigField(name = "witch_level1", category = "achievements.hunter.mob_level")
	public int witchLevel1 = 80;

	@ConfigField(name = "wither_level1", category = "achievements.hunter.mob_level")
	public int witherLevel1 = 20;

	@ConfigField(name = "wither_skeleton_level1", category = "achievements.hunter.mob_level")
	public int witherSkeletonLevel1 = 80;

	@ConfigField(name = "wolf_level1", category = "achievements.hunter.mob_level")
	public int wolfLevel1 = 100;

	@ConfigField(name = "zoglin_level1", category = "achievements.hunter.mob_level")
	public int zoglinLevel1 = 100;

	@ConfigField(name = "zombie_level1", category = "achievements.hunter.mob_level")
	public int zombieLevel1 = 100;

	@ConfigField(name = "zombiehorse_level1", category = "achievements.hunter.mob_level")
	public int zombieHorseLevel1 = 100;

	@ConfigField(name = "zombie_pigman_level1", category = "achievements.hunter.mob_level")
	public int zombiePigmanLevel1 = 100;

	@ConfigField(name = "zombie_villager_level1", category = "achievements.hunter.mob_level")
	public int zombieVillagerLevel1 = 100;

	// #####################################################################################
	// Assists
	// #####################################################################################
	@ConfigField(name = "enable", category = "assists", comment = "Enabling assist allows the second last player to attack a mob to get some money from it")
	public boolean enableAssists = true;
	@ConfigField(name = "multiplier", category = "assists", comment = "This should be a value that is multiplied against the mobs base kill value."
			+ "\nThis is used to determine how much money an assister gets.")
	public double assistMultiplier = 0.25;
	@ConfigField(name = "allow_killstreak", category = "assists", comment = "Should killstreak be applied to assists")
	public boolean assistAllowKillstreak = false;
	@ConfigField(name = "timeout", category = "assists", comment = "Time in seconds after attacking a mob that can be counted as an assist")
	public int assistTimeout = 4;

	// #####################################################################################
	// Grinding detection
	// #####################################################################################
	@ConfigField(name = "enable_grinding_detection", category = "grinding")
	public boolean grindingDetectionEnabled = true;
	@ConfigField(name = "disable_grinding_detection_in_worlds", category = "grinding", comment = "Put the names of the worlds here where you want to disable grinding detection"
			+ "\nYou would typically do this in creative worlds.")
	public String[] disableGrindingDetectionInWorlds = { "worldname", "worldname2" };

	@ConfigField(name = "grinding_stacked_mobs_allowed", category = "grinding", comment = "Killing stacked mobs (created by a mob stacking plugin) "
			+ "\nis by nature detected as grinding and by default allowed. If you want to the the grinding detection to detect"
			+ "\nkillings of stacked to be detected as gring, you must set grinding_stacked_mobs_allowed to false.")
	public boolean isGrindingStackedMobsAllowed = true;

	@ConfigField(name = "disable_natural_item_drops_on_player_grinding", category = "grinding", comment = "Disable natural drops here")
	public boolean disableNaturalItemDropsOnPlayerGrinding = false;
	@ConfigField(name = "disable_natural_xp_drops_on_player_grinding", category = "grinding")
	public boolean disableNaturalXPDropsOnPlayerGrinding = false;

	// Area grinding
	@ConfigField(name = "detect_grinding_areas", category = "grinding.area")
	public boolean areaGrindingDetectionEnabled = true;
	@ConfigField(name = "grinding_detection_range", category = "grinding.area")
	public int grindingDetectionRange = 15;
	@ConfigField(name = "grinding_detection_number_of_death", category = "grinding.area")
	public int grindingDetectionNumberOfDeath = 20;
	@ConfigField(name = "blacklist_player_grinding_spots_as_server_worldwide_spots", category = "grinding.area")
	public boolean blacklistPlayerGrindingSpotsServerWorldWide = false;

	// Time Grinding
	@ConfigField(name = "detect_speed_grinding", category = "grinding.speed_grinding")
	public boolean speedGrindingDetectionEnabled = true;
	@ConfigField(name = "time_frame", category = "grinding.speed_grinding")
	public double speedGrindingTimeFrame = 120;
	@ConfigField(name = "no_of_mobs", category = "grinding.speed_grinding")
	public int speedGrindingNoOfMobs = 10;

	// Farm detection
	@ConfigField(name = "detect_farms", category = "grinding.farms")
	public boolean detectFarms = true;

	// NetherGoldXPFarm
	@ConfigField(name = "detect_nether_gold_farms", category = "grinding.farms.nether_gold_farms")
	public boolean detectNetherGoldFarms = true;
	@ConfigField(name = "seconds_to_search_for_grinding", category = "grinding.farms.nether_gold_farms")
	public int secondsToSearchForGrinding = 30;
	@ConfigField(name = "range_to_search_for_grinding", category = "grinding.farms.nether_gold_farms")
	public double rangeToSearchForGrinding = 4;
	@ConfigField(name = "number_of_deaths_when_searching_for_grinding", category = "grinding.farms.nether_gold_farms")
	public int numberOfDeathsWhenSearchingForGringding = 5;
	@ConfigField(name = "disable_natural_item_drops", category = "grinding.farms.nether_gold_farms")
	public boolean disableNaturalItemDropsOnNetherGoldFarms = false;
	@ConfigField(name = "disable_natural_xp_drops", category = "grinding.farms.nether_gold_farms")
	public boolean disableNaturalXPDropsOnNetherGoldFarms = false;

	// Enderman farms
	@ConfigField(name = "detect_enderman_farms", category = "grinding.farms.endermanfarms")
	public boolean detectEndermanFarms = true;
	@ConfigField(name = "seconds_to_search_for_grinding", category = "grinding.farms.endermanfarms")
	public int secondsToSearchForGrindingOnEndermanFarms = 30;
	@ConfigField(name = "range_to_search_for_grinding", category = "grinding.farms.endermanfarms")
	public double rangeToSearchForGrindingOnEndermanFarms = 5;
	@ConfigField(name = "number_of_deaths_when_searching_for_grinding", category = "grinding.farms.endermanfarms")
	public int numberOfDeathsWhenSearchingForGringdingOnEndermanFarms = 5;
	@ConfigField(name = "disable_natural_item_drops", category = "grinding.farms.endermanfarms")
	public boolean disableNaturalItemDropsOnEndermanFarms = false;
	@ConfigField(name = "disable_natural_xp_drops", category = "grinding.farms.endermanfarms")
	public boolean disableNaturalXPDropsOnEndermanFarms = false;

	// other farms
	@ConfigField(name = "detect_other_farms", category = "grinding.farms.otherfarms")
	public boolean detectOtherFarms = true;
	@ConfigField(name = "seconds_to_search_for_grinding", category = "grinding.farms.otherfarms")
	public int secondsToSearchForGrindingOnOtherFarms = 30;
	@ConfigField(name = "range_to_search_for_grinding", category = "grinding.farms.otherfarms")
	public double rangeToSearchForGrindingOnOtherFarms = 4;
	@ConfigField(name = "number_of_deaths_when_searching_for_grinding", category = "grinding.farms.otherfarms")
	public int numberOfDeathsWhenSearchingForGringdingOnOtherFarms = 10;
	@ConfigField(name = "disable_natural_item_drops", category = "grinding.farms.otherfarms")
	public boolean disableNaturalItemDropsOnOtherFarms = false;
	@ConfigField(name = "disable_natural_xp_drops", category = "grinding.farms.otherfarms")
	public boolean disableNaturalXPDropsOnOtherFarms = false;

	@ConfigField(name = "disable_money_rewards_from_mobspawners_and_eggs", category = "grinding.spawners", comment = "Can the players earn money on mobs spawned from mobspawners, eggs and from eggs from Dispensers?"
			+ "\nIf you disable this you are still able to get rewards from specific Spawners, if you white list the area "
			+ "\nusing '/mh whitelistarea'.")
	public boolean disableMoneyRewardsFromMobSpawnersEggsAndDispensers = true;

	@ConfigField(name = "enable_rewards_from_cave_spiders", category = "grinding.spawners", comment = "This allow players to get rewards when killing Cave Spiders from SPAWNERS. Cave Spiders does not only "
			+ "\nnaturally and only from SPAWNERS.")
	public boolean enableRewardsFromCaveSpiders = true;

	@ConfigField(name = "deny_slime_and_maga_cube_to_spilt", category = "grinding.spawners", comment = "Slimes and Maga Cubes can be used for grinding money because that only the"
			+ "\nmother is marked as from a SPAWNER. Spilitting is therefor denied if the slime/MC is"
			+ "+\nfrom a SPAWNER. If you want Vanilla behavior set this to false.")
	public boolean denySlimesToSpiltIfFromSpawer = true;

	@ConfigField(name = "disable_naturally_dropped_items_from_mobspawners_and_eggs", category = "grinding.spawners", comment = "Let the players get the naturally dropped items from mobs spawned from mobspawners, eggs and from eggs from Dispensers ?")
	public boolean disableNaturallyDroppedItemsFromMobSpawnersEggsAndDispensers = false;
	@ConfigField(name = "disable_naturally_dropped_xp_from_mobspawners_and_eggs", category = "grinding.spawners", comment = "Let the players get the naturally dropped XP from mobs spawned from mobspawners, eggs and from eggs from Dispensers ?")
	public boolean disableNaturallyDroppedXPFromMobSpawnersEggsAndDispensers = false;

	// #####################################################################################
	// Multipier Section
	// #####################################################################################
	// #####################################################################################
	// Bonuses _ multipliers
	// #####################################################################################
	@ConfigField(name = "sneaky", category = "multiplier.bonus")
	public double bonusSneaky = 2.0;
	@ConfigField(name = "return_to_sender", category = "multiplier.bonus")
	public double bonusReturnToSender = 2.0;
	@ConfigField(name = "push_off_cliff", category = "multiplier.bonus")
	public double bonusSendFalling = 2.0;
	@ConfigField(name = "no_weapon", category = "multiplier.bonus")
	public double bonusNoWeapon = 2.0;
	@ConfigField(name = "far_shot", category = "multiplier.bonus", comment = "This is the PRO_Sniper bonus. The Sniper bonus is calulated as half of PRO_Sniper bonus."
			+ "\nIf If PRO Sniper (far_shot) is 2, then Sniper will be = 1+((far_shot_1)/2)=1.5")
	public double bonusFarShot = 2.0;
	@ConfigField(name = "mounted", category = "multiplier.bonus")
	public double bonusMounted = 1.5;
	@ConfigField(name = "friendly_fire", category = "multiplier.bonus")
	public double bonusFriendlyFire = 4;
	@ConfigField(name = "bonus_mob", category = "multiplier.bonus")
	public double bonusBonusMob = 10;
	@ConfigField(name = "bonusMob_head_prize", category = "multiplier.bonus")
	public String bonusMobHeadPrize = "100";

	@ConfigField(name = "critical", category = "multiplier.bonus")
	public double bonusCritical = 2;
	@ConfigField(name = "bonus_mob_chance", category = "multiplier.bonus", comment = "This is the chance (% chance 0-100) that a bonus mob will spawn.")
	public double bonusMobChance = 0.2;
	@ConfigField(name = "babyMultiplier", category = "multiplier.bonus", comment = "Bonus for killing a Baby mob.")
	public double babyMultiplier = 1.2;

	// #####################################################################################
	// Killstreaks Multiplier
	// #####################################################################################
	@ConfigField(name = "level1", category = "multiplier.killstreak")
	public int killstreakLevel1 = 5;
	@ConfigField(name = "level1_multiplier", category = "multiplier.killstreak")
	public double killstreakLevel1Mult = 1.5;
	@ConfigField(name = "level2", category = "multiplier.killstreak")
	public int killstreakLevel2 = 10;
	@ConfigField(name = "level2_multiplier", category = "multiplier.killstreak")
	public double killstreakLevel2Mult = 2;
	@ConfigField(name = "level3", category = "multiplier.killstreak")
	public int killstreakLevel3 = 20;
	@ConfigField(name = "level3_multiplier", category = "multiplier.killstreak")
	public double killstreakLevel3Mult = 3;
	@ConfigField(name = "level4", category = "multiplier.killstreak")
	public int killstreakLevel4 = 40;
	@ConfigField(name = "level4_multiplier", category = "multiplier.killstreak")
	public double killstreakLevel4Mult = 4;

	// #####################################################################################
	// Multiplier by rank / permission
	// #####################################################################################
	@ConfigField(name = "rank_multiplier", category = "multiplier.rank", comment = "Ranks")
	public HashMap<String, String> rankMultiplier = new HashMap<String, String>();
	{
		rankMultiplier.put("mobhunting.multiplier.guest", "0.9");
		rankMultiplier.put("mobhunting.multiplier.guardian", "1.02");
		rankMultiplier.put("mobhunting.multiplier.staff", "1.05");
		rankMultiplier.put("mobhunting.multiplier.hasVoted", "2");
		rankMultiplier.put("mobhunting.multiplier.donator", "3");
	}

	// #####################################################################################
	// Multiplier pr World Difficulty
	// #####################################################################################
	@ConfigField(name = "world_difficulty_multiplier", category = "multiplier.difficulty", comment = "This is the reward multiplier for the WorldDifficulty. Note that extrahard is "
			+ "\nused for worlds where the plugin ExtraHardMode is enabled.")
	public HashMap<String, String> difficultyMultiplier = new HashMap<String, String>();
	{
		difficultyMultiplier.put("peaceful", "0.5");
		difficultyMultiplier.put("easy", "0.75");
		difficultyMultiplier.put("normal", "1");
		difficultyMultiplier.put("hard", "2");
		difficultyMultiplier.put("extrahard", "2.5");
	}

	// #####################################################################################
	// Multiplier pr World
	// #####################################################################################
	@ConfigField(name = "world_multiplier", category = "multiplier.world", comment = "This is the reward multiplier for the different Worlds")
	public HashMap<String, String> worldMultiplier = new HashMap<String, String>();
	{
		worldMultiplier.put("world", "1.0");
		worldMultiplier.put("world_nether", "1.0");
		worldMultiplier.put("world_the_end", "1.0");
		worldMultiplier.put("worldname", "1.5");
	}

	// #####################################################################################
	// Flying Penalty Multiplier
	// #####################################################################################
	@ConfigField(name = "flyingPenalty", category = "multiplier.penalty", comment = "If a player flies at any point in a fight, this penalty will be applied")
	public double penaltyFlying = 0.2;

	@ConfigField(name = "mob_rob_from_player", category = "multiplier.penalty", comment = "This is the penalty if the player gets killed by a mob."
			+ "\nSet mob_rob_from_player=10 to let the mob steal 10 dollars"
			+ "\n or 10% to let the mob steal 10% of the players balance."
			+ "\nSet mob_rob_from_player=0 to disable this")
	public String mobKillsPlayerPenalty = "0%";

	// #####################################################################################
	// Bounty Settings
	// #####################################################################################
	@ConfigField(name = "enable_player_bounties", category = "bounties", comment = "Set to true if you want to disable players to be able to put bounties on each other.")
	public boolean enablePlayerBounties = true;
	@ConfigField(name = "bounty_return_pct", category = "bounties", comment = "Here you set how much of a bound the bounty owner get back if "
			+ "\nhe drop the bounty on another player")
	public int bountyReturnPct = 50;
	@ConfigField(name = "bounty_duration", category = "bounties", comment = "Here you set the number of days the Bounty is collectable."
			+ "\nAfter the number of days the Bounty will be removed automatically")
	public int bountyDaysToLive = 30;
	@ConfigField(name = "enable_random_bounty", category = "bounties", comment = "Set enable_random_bounty=false to disable random bounties")
	public boolean enableRandomBounty = true;
	@ConfigField(name = "time_between_random_bounties", category = "bounties", comment = "Time between Random Bounty is created in minutes")
	public int timeBetweenRandomBounties = 60;
	@ConfigField(name = "minimum_number_of_online_players", category = "bounties", comment = "Minimum number of players before the server starts to make random bounties")
	public int minimumNumberOfOnlinePlayers = 5;
	@ConfigField(name = "chance_to_create_a_random_bounty", category = "bounties", comment = "Chance that a bounty is created on a player after the minimum time. Must be a number between 0 and 1. (0 = never, 0.5 = 50% 1 = always)")
	public double chanceToCreateBounty = 0.5;
	@ConfigField(name = "random_bounty_prize", category = "bounties", comment = "Random Bounty. Can be a number 100 or a range 100:200")
	public String randomBounty = "50:100";

	// #####################################################################################
	// Bounty Settings
	// #####################################################################################
	@ConfigField(name = "delay_happyhour_annoucement", category = "happyhour", comment = "Here you can delay the HappyHour announcement announced to a "
			+ "\njoining player, if an event is ongion.")
	public int delayHappyHourAnnouncement = 3;

	// #####################################################################################
	// Integration
	// #####################################################################################
	// #####################################################################################
	// Disguises
	// #####################################################################################
	@ConfigField(name = "enable_integration_i_disguise", category = "plugins.disguises", comment = "Enable/disable integration with iDisguise")
	public boolean enableIntegrationIDisguise = true;

	@ConfigField(name = "enable_integration_disguisecraft", category = "plugins.disguises", comment = "Enable/disable integration with DisguiseCcraft")
	public boolean enableIntegrationDisguiseCraft = true;

	@ConfigField(name = "enable_integration_libsdisguises", category = "plugins.disguises", comment = "Enable/disable integration with LibsDisguises")
	public boolean enableIntegrationLibsDisguises = true;

	@ConfigField(name = "remove_disguise_when_attacking", category = "plugins.disguises", comment = "Set pvpAllowed=false to disable rewards on killing other players.")
	public boolean removeDisguiseWhenAttacking = true;

	@ConfigField(name = "remove_disguise_when_attacked", category = "plugins.disguises", comment = "Set pvpAllowed=false to disable rewards on killing other players.")
	public boolean removeDisguiseWhenAttacked = true;

	@ConfigField(name = "undercover_multiplier", category = "plugins.disguises", comment = "Bonus multiplier for killing while disgused."
			+ "\nCan be both positive an negative = reward or penalty"
			+ "\nand over and under 1 = raise or lower the reward. ")
	public double undercoverMultiplier = 0.95;
	@ConfigField(name = "cover_blown_multiplier", category = "plugins.disguises", comment = "Bonus multiplier for killing a disgused player."
			+ "\nCan be both positive an negative = reward or penalty"
			+ "\nand over and under 1 = raise or lower the reward. ")
	public double coverBlownMultiplier = 1.2;

	// #####################################################################################
	// Citizens / MasterMobHunter Settings
	// #####################################################################################
	@ConfigField(name = "enable_integration_citizens", category = "plugins.citizens", comment = "Enable/disable integration with Citizens2")
	public boolean enableIntegrationCitizens = true;
	@ConfigField(name = "masterMobHunter_check_every", category = "plugins.citizens", comment = "Set the number of seconds between each check. Recommended setting is"
			+ "\nmasterMobHunter_check_every: 300 ~ to update all MasterMobHunters every 5th minute."
			+ "\nBe careful not to lower this number too much. It can cause lag and server crashes "
			+ "\nbecause of database lockings.")
	public int masterMobHuntercheckEvery = 300;

	// #####################################################################################
	// Stacked Mobs Settings
	// #####################################################################################
	@ConfigField(name = "mobstacker.enable_integration_mobstacker", category = "plugins.stackedmobs", comment = "Enable/disable integration with MobStacker."
			+ "\nhttps://www.spigotmc.org/resources/mobstacker.15596/")
	public boolean enableIntegrationMobStacker = true;

	@ConfigField(name = "stackmob.enable_integration_stackmob", category = "plugins.stackedmobs", comment = "Enable/disable integration with StackMob."
			+ "\nhttps://www.spigotmc.org/resources/stackmob.29999/")
	public boolean enableIntegrationStackMob = true;

	@ConfigField(name = "get_reward_from_stacked_mobs", category = "plugins.stackedmobs", comment = "Set to true if you want stacked mobs to pay a reward.")
	public boolean getRewardFromStackedMobs = true;

	// #####################################################################################
	// CustomMobs Settings
	// #####################################################################################
	@ConfigField(name = "enable_integration_custommobs", category = "plugins.custommobs", comment = "Enable/disable integration with CustomMobs"
			+ "\nhttps://dev.bukkit.org/bukkit_plugins/custom_mobs/")
	public boolean enableIntegrationCustomMobs = true;

	@ConfigField(name = "allow_custom_mobspawners_and_eggs", category = "plugins.custommobs", comment = "Can the players earn money on mobs spawned from CustomMobs Spawners and eggs?")
	public boolean allowCustomMobsSpawners = false;

	// #####################################################################################
	// InfernalMobs Settings
	// #####################################################################################
	@ConfigField(name = "enable_integration_infernalmobs", category = "plugins.infernalmobs", comment = "Enable/disable integration with InfernalMobs")
	public boolean enableIntegrationInfernalMobs = true;

	@ConfigField(name = "multiplier_per_level", category = "plugins.infernalmobs", comment = "For InfernalMobs mob prize is calculated by the minecraft reward x multiplier_per_level^Infernal_Level"
			+ "\nEx.If multiplier=1.2 and level is 3 normal reward will be multiplied with 1.2*1.2*1.2=1,728")
	public double multiplierPerInfernalLevel = 1.25;

	// #####################################################################################
	// Elitemobs Settings
	// #####################################################################################
	@ConfigField(name = "enable_integration_elitemobs", category = "plugins.elitemobs", comment = "Enable/Disable integration with EliteMobs")
	public boolean enableIntegrationEliteMobs = true;

	@ConfigField(name = "maximum_multiplier", category = "plugins.elitemobs", comment = "The reward for EliteMobs from level 50-400 will be multiplier linearly")
	public double elitemobMultiplier = 2;

	// #####################################################################################
	// ConquestiaMobs / LorinthsRpgMobs / LevelledMobs Settings
	// #####################################################################################
	@ConfigField(name = "enable_integration_conquestiamobs", category = "plugins.levelmobs.conquestia", comment = "Enable/disable integration with ConquestiaMobs"
			+ "\nhttps://www.spigotmc.org/resources/conquesita_mobs.21307/")
	public boolean enableIntegrationConquestiaMobs = true;

	@ConfigField(name = "enable_integration_lorinthsrpgmobs", category = "plugins.levelmobs.lorinthsrpgmobs")
	public boolean enableIntegrationLorinthsRpgMobs = true;

	@ConfigField(name = "enable_integration_levelledmobs", category = "plugins.levelmobs.levelledmobs")
	public boolean enableIntegrationLevelledMobs = true;

	@ConfigField(name = "multiplier_per_level", category = "plugins.levelmobs", comment = "This is the multiplier per level mutiplied with the basic reward."
			+ "\nBecareful not to ruin the server economy by making the multiplier to big."
			+ "\nExample: If the reward is 10 and the multiplier is 1.05, the calculated" + "\nreward is:"
			+ "\nLevel 1: reward=10" + "\nLevel 2: reward=10*1.05=10.5" + "\nLevel 3: reward=10*1.05*1.05=11.03"
			+ "\nLevel 4: reward=10*1.05*1.05*1.05=11.58" + "\nLevel 5: reward=10*1.05*1.05*1.05*1.05=12.16"
			+ "\nLevel 6: reward=10*1.05*1.05*1.05*1.05*1.05=12.76"
			+ "\nLevel 7: reward=10*1.05*1.05*1.05*1.05*1.05*1.05=13.40"
			+ "\nLevel 8: reward=10*1.05*1.05*1.05*1.05*1.05*1.05*1.05=14.07"
			+ "\nLevel 9: reward=10*1.05*1.05*1.05*1.05*1.05*1.05*1.05*1.05=14.77"
			+ "\nLevel 10: reward=10*1.05*1.05*1.05*.....=15.51" + "\nLevel 20: reward=10*1.05*1.05*1.05*.....=25..27"
			+ "\nLevel 30: reward=10*1.05*1.05*1.05*.....=41.61" + "\nLevel 40: reward=10*1.05*1.05*1.05*.....=67.05"
			+ "\nLevel 50: reward=10*1.05*1.05*1.05*.....=109.21"
			+ "\nLevel 100: reward=10*1.05*1.05*1.05*.....=1252.39")
	public double mulitiplierPerLevel = 1.05;

	// #####################################################################################
	// Factions / FactionsUUID Settings
	// #####################################################################################
	@ConfigField(name = "enable_integration_factions", category = "plugins.factions", comment = "Enable/disable integration with Factions."
			+ "\nhttps://www.massivecraft.com/" + "\nhttps://www.spigotmc.org/resources/factions.1900/"
			+ "\nhttps://www.spigotmc.org/resources/factionsuuid.1035/")
	public boolean enableIntegrationFactions = true;

	@ConfigField(name = "factions_warzone_multiplier", category = "plugins.factions", comment = "This is the bonus when a player kills a mob or a player in a Factions WarZone.")
	public double factionWarZoneBonusMultiplier = 1.1;

	// #####################################################################################
	// Towny Settings
	// #####################################################################################
	@ConfigField(name = "enable_integration_towny", category = "plugins.towny", comment = "Enable/disable integration with Towny."
			+ "\nhttp://towny.palmergames.com/")
	public boolean enableIntegrationTowny = true;

	@ConfigField(name = "enable_integration_betterrevive", category = "plugins.betterrevive", comment = "Attribute final deaths to the player who caused the BetterRevive knockdown."
			+ "\nNo payout on knockdown. Reviving clears attribution; finishing blows do not replace it."
			+ "\nThe attacker must be online at final death. Restart required when changing this option.")
	public boolean enableIntegrationBetterRevive = true;

	@ConfigField(name = "disable_rewards_in_home_town", category = "plugins.towny", comment = "Disable rewards when the player is in his hometown."
			+ "\nhttp://towny.palmergames.com/")
	public boolean disableRewardsInHomeTown = true;

	@ConfigField(name = "disable_rewards_in_any_town", category = "plugins.towny", comment = "Disable rewards when the player is in any town."
			+ "\nhttp://towny.palmergames.com/")
	public boolean disableRewardsInAnyTown = false;

	@ConfigField(name = "disable_naturally_drops_and_xp_in_home_town", category = "plugins.towny", comment = "Disable naturally drops and xp drops when the player kill mobs in his home town.")
	public boolean disableNaturallyRewardsInHomeTown = false;

	// #####################################################################################
	// Residence Settings
	// #####################################################################################
	@ConfigField(name = "enable_integration_residence", category = "plugins.residence", comment = "Enable/disable integration with Residence."
			+ "\nhttp://towny.palmergames.com/")
	public boolean enableIntegrationResidence = true;

	@ConfigField(name = "disable_rewards_in_home_town", category = "plugins.residence", comment = "Disable rewards when the player is protected against damage."
			+ "\nhttp://towny.palmergames.com/")
	public boolean disableRewardsInHomeResidence = true;

	@ConfigField(name = "disable_naturally_drops_and_xp_in_protected_residence", category = "plugins.residence", comment = "Disable naturally drops and xp drops when the player kill mobs in his home town.")
	public boolean disableNaturallyRewardsInProtectedResidence = false;

	// #####################################################################################
	// McMMO integration
	// #####################################################################################
	@ConfigField(name = "enable_integration_mcmmo", category = "plugins.mcmmo", comment = "Enable/disable the integration with McMMO."
			+ "\nhttps://www.spigotmc.org/resources/mcmmo.2445/")
	public boolean enableIntegrationMcMMO = true;

	@ConfigField(name = "enable_mcmmo_level_rewards", category = "plugins.mcmmo", comment = "Set 'enable_mcmmo_level_rewards: true' to let the players get Level as a MobHunting reward.")
	public boolean enableMcMMOLevelRewards = true;

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.allay")
	public String allayMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.allay")
	public double allayMcMMOSkillRewardChance = 0.025;

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.axolotl")
	public String axolotlMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.axolotl")
	public double axolotlMcMMOSkillRewardChance = 0.025;

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.bat")
	public String batMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.bat")
	public double batMcMMOSkillRewardChance = 0.025;

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.bee")
	public String beeMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.bee")
	public double beeMcMMOSkillRewardChance = 0.025;

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.cat")
	public String catMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.cat")
	public double catMcMMOSkillRewardChance = 0.025;

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.blacksmith")
	public String blacksmithMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.blacksmith")
	public double blacksmithMcMMOSkillRewardChance = 0.025;
	// Passive mob, risk free

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.blaze")
	public String blazeMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.blaze")
	public double blazeMcMMOSkillRewardChance = 0.05;
	// Hostile, normal challenge

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.bonusmob")
	public String bonusMobMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.bonusmob")
	public double bonusMobMcMMOSkillRewardChance = 0.05;
	// No opinion yet, I'm not quite sure what a bonus mob is

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.armorer")
	public String armorerMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.armorer")
	public double armorerMcMMOSkillRewardChance = 0.025;
	// Passive mob, risk free

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.butcher")
	public String butcherMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.butcher")
	public double butcherMcMMOSkillRewardChance = 0.025;
	// Passive mob, risk free

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.cartographer")
	public String cartographerMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.cartographer")
	public double cartographerMcMMOSkillRewardChance = 0.025;
	// Passive mob, risk free

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.cave_spider")
	public String caveSpiderMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.cave_spider")
	public double caveSpiderMcMMOSkillRewardChance = 0.04;
	// Hostile mob, easy

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.chicken")
	public String chickenMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.chicken")
	public double chickenMcMMOSkillRewardChance = 0.025;
	// Passive mob, risk free

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.cleric")
	public String clericMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.cleric")
	public double clericMcMMOSkillRewardChance = 0.025;
	// Passive mob, risk free

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.clownfish")
	public String clownfishMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.clownfish")
	public double clownfishMcMMOSkillRewardChance = 0.075;
	// Fishing Hard

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.cow")
	public String cowMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.cow")
	public double cowMcMMOSkillRewardChance = 0.025;
	// Passive mob, risk free

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.creeper")
	public String creeperMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.creeper")
	public double creeperMcMMOSkillRewardChance = 0.04;
	// Hostile mob, easy

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.dolphin")
	public String dolphinMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.dolphin")
	public double dolphinMcMMOSkillRewardChance = 0.025;
	// Passive mob, ???

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.drowned")
	public String drownedMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.drowned")
	public double drownedMcMMOSkillRewardChance = 0.025;
	// Passive mob, ???

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.donkey")
	public String donkeyMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.donkey")
	public double donkeyMcMMOSkillRewardChance = 0.025;
	// Passive mob, risk free

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.elder_guardian")
	public String elderGuardianMcMMOSkillRewardAmount = "1:2";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.elder_guardian")
	public double elderGuardianMcMMOSkillRewardChance = 0.1;
	// Hostile mob, challenging

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.enderdragon")
	public String enderdragonMcMMOSkillRewardAmount = "5";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.enderdragon")
	public double enderdragonMcMMOSkillRewardChance = 0.33;
	// Hostile mob, hard

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.enderman")
	public String endermanMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.enderman")
	public double endermanMcMMOSkillRewardChance = 0.04;
	// Hostile mob, easy

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.endermite")
	public String endermiteMcMMOSkillRewardAmount = "1:2";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.endermite")
	public double endermiteMcMMOSkillRewardChance = 0.2;
	// Hostile mob, easy (but rare)

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.evoker")
	public String evokerMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.evoker")
	public double evokerMcMMOSkillRewardChance = 0.05;
	// Hostile mob, normal

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.farmer")
	public String farmerMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.farmer")
	public double farmerMcMMOSkillRewardChance = 0.025;
	// Passive mob, risk free

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.fish")
	public String fishMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.fish")
	public double fishMcMMOSkillRewardChance = 0.025;
	// Passive mob, risk free

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.fisherman")
	public String fishermanMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.fisherman")
	public double fishermanMcMMOSkillRewardChance = 0.025;
	// Passive mob, risk free

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.fletcher")
	public String fletcherMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.fletcher")
	public double fletcherMcMMOSkillRewardChance = 0.025;
	// Passive mob, risk free

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.fox")
	public String foxMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.fox")
	public double foxMcMMOSkillRewardChance = 0.025;
	// Passive mob, risk free

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.frog")
	public String frogMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.frog")
	public double frogMcMMOSkillRewardChance = 0.025;
	// Passive mob, risk free

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.ghast")
	public String ghastMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.ghast")
	public double ghastMcMMOSkillRewardChance = 0.05;
	// Hostile mob, normal

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.giant")
	public String giantMcMMOSkillRewardAmount = "1:2";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.giant")
	public double giantMcMMOSkillRewardChance = 0.1;

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.goat")
	public String goatMcMMOSkillRewardAmount = "1:2";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.goat")
	public double goatMcMMOSkillRewardChance = 0.1;

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.glowsquid")
	public String glowsquidMcMMOSkillRewardAmount = "1:2";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.glowsquid")
	public double glowsquidMcMMOSkillRewardChance = 0.1;

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.guardian")
	public String guardianMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.guardian")
	public double guardianMcMMOSkillRewardChance = 0.05;
	// Hostile mob, normal (because of the terrain and beam attack)

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.hoglin")
	public String hoglinMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.hoglin")
	public double hoglinMcMMOSkillRewardChance = 0.025;
	// Passive mob, risk free

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.horse")
	public String horseMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.horse")
	public double horseMcMMOSkillRewardChance = 0.025;
	// Passive mob, risk free

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.husk")
	public String huskMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.husk")
	public double huskMcMMOSkillRewardChance = 0.04;
	// Passive mob, easy

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.illusioner")
	public String illusionerMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.illusioner")
	public double illusionerMcMMOSkillRewardChance = 0.05;
	// Hostile mob, looks normal on Youtube videos (haven't tried 1.12)

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.iron_golem")
	public String ironGolemMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmov.iron_golem")
	public double ironGolemMcMMOSkillRewardChance = 0.05;
	// Hostile mob, normal

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.killer_rabbit")
	public String killerRabbitMcMMOSkillRewardAmount = "5";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.killer_rabbit")
	public double killerRabbitMcMMOSkillRewardChance = 1.0;
	// Hostile mob, easy (but extremely rare)

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.leatherworker")
	public String leatherworkerMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.leatherworker")
	public double leatherworkerMcMMOSkillRewardChance = 0.025;
	// Passive mob, risk free

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.llama")
	public String llamaMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.llama")
	public double llamaMcMMOSkillRewardChance = 0.025;
	// Passive mob, risk free

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.librarian")
	public String librarianMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.librarian")
	public double librarianMcMMOSkillRewardChance = 0.025;
	// Passive mob, risk free

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.magma_cube")
	public String magmaCubeMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.magma_cube")
	public double magmaCubeMcMMOSkillRewardChance = 0.04;
	// Hostile mob, easy

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.mason")
	public String masonMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.mason")
	public double masonMcMMOSkillRewardChance = 0.025;
	// Passive mob, risk free

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.mule")
	public String muleMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.mule")
	public double muleMcMMOSkillRewardChance = 0.025;
	// Passive mob, risk free

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.mushroom_cow")
	public String mushroomCowMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.mushroom_cow.")
	public double mushroomCowMcMMOSkillRewardChance = 0.025;
	// Passive mob, risk free

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.nitwit")
	public String nitwitMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.nitwit")
	public double nitwitMcMMOSkillRewardChance = 0.025;
	// Passive mob, risk free

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.ocelot")
	public String ocelotMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.ocelot")
	public double ocelotMcMMOSkillRewardChance = 0.025;
	// Passive mob, risk free

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.parrot")
	public String parrotMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.parrot")
	public double parrotMcMMOSkillRewardChance = 0.025;
	// Passive mob, risk free

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.panda")
	public String pandaMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.panda")
	public double pandaMcMMOSkillRewardChance = 0.025;
	// Passive mob, risk free

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.phantom")
	public String phantomMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.phantom")
	public double phantomMcMMOSkillRewardChance = 0.025;
	// Passive mob, risk free

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.piglin")
	public String piglinMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.piglin")
	public double piglinMcMMOSkillRewardChance = 0.050;
	// Hostile mob, low risc

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.piglin_brute")
	public String piglinBruteMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.piglin_brute")
	public double piglinBruteMcMMOSkillRewardChance = 0.050;
	// Hostile mob, low risc

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.pig")
	public String pigMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.pig")
	public double pigMcMMOSkillRewardChance = 0.025;
	// Passive mob, risk free

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.polar_bear")
	public String polarBearMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.polar_bear")
	public double polarBearMcMMOSkillRewardChance = 0.05;
	// Hostile mob, normal

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.pillager")
	public String pillagerMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.pillager")
	public double pillagerMcMMOSkillRewardChance = 0.05;
	// Hostile mob, normal

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.priest")
	public String priestMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.priest")
	public double priestMcMMOSkillRewardChance = 0.025;
	// Passive mob, risk free

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.pufferfish")
	public String pufferfishMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.pufferfish")
	public double pufferfishMcMMOSkillRewardChance = 0.06;
	// Fishing Hard

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.pvpplayer")
	public String pvpPlayerMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.pvpplayer")
	public double pvpPlayerMcMMOSkillRewardChance = 0.025;
	// Easy to abuse

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.rabbit")
	public String rabbitMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.rabbit")
	public double rabbitMcMMOSkillRewardChance = 0.025;
	// Passive mob, risk free

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.ravager")
	public String ravagerMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.ravager")
	public double ravagerMcMMOSkillRewardChance = 0.025;
	// Passive mob, risk free

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.raw_fish")
	public String rawfishMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.raw_fish")
	public double rawfishMcMMOSkillRewardChance = 0.05;
	// Fishing Easy

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.raw_salmon")
	public String rawsalmonMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.raw_salmon")
	public double rawsalmonMcMMOSkillRewardChance = 0.06;
	// Fishing normal

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.sheep")
	public String sheepMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.sheep")
	public double sheepMcMMOSkillRewardChance = 0.025;
	// Passive mob, risk free

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.shepherd")
	public String shepherdMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.shepherd")
	public double shepherdMcMMOSkillRewardChance = 0.05;
	// Hostile mob, normal

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.shulker")
	public String shulkerMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.shulker")
	public double shulkerMcMMOSkillRewardChance = 0.05;
	// Hostile mob, normal

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.silverfish")
	public String silverfishMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.silverfish")
	public double silverfishMcMMOSkillRewardChance = 0.04;
	// Hostile mob, easy

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.skeleton")
	public String skeletonMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.skeleton")
	public double skeletonMcMMOSkillRewardChance = 0.04;
	// Hostile mob, easy

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.skeletonhorse")
	public String skeletonHorseMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.skeletonhorse")
	public double skeletonHorseMcMMOSkillRewardChance = 0.025;
	// Passive mob, risk free

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.slime_base")
	public String slimeMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.slime_base")
	public double slimeMcMMOSkillRewardChance = 0.04;
	// Hostile mob, easy

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.snowman")
	public String snowmanMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.snowman")
	public double snowmanMcMMOSkillRewardChance = 0.025;
	// Passive mob, risk free

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.spider")
	public String spiderMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.spider")
	public double spiderMcMMOSkillRewardChance = 0.04;
	// Hostile mob, easy

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.squid")
	public String squidMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.squid")
	public double squidMcMMOSkillRewardChance = 0.025;
	// Passive mob, risk free

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.stray")
	public String strayMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.stray")
	public double strayMcMMOSkillRewardChance = 0.04;
	// Hostile mob, easy

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.strider")
	public String striderMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.strider")
	public double striderMcMMOSkillRewardChance = 0.025;
	// Hostile mob, normal

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.turtle")
	public String turtleMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.turtle")
	public double turtleMcMMOSkillRewardChance = 0.04;
	// Passive mob, easy

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.toolsmith")
	public String toolsmithMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.toolsmith")
	public double toolsmithMcMMOSkillRewardChance = 0.04;
	// Passive mob, easy

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.tadpole")
	public String tadpoleMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.tadpole")
	public double tadpoleMcMMOSkillRewardChance = 0.04;
	// Passive mob, easy

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.traderllama")
	public String traderLlamaMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.traderllama")
	public double traderLlamaMcMMOSkillRewardChance = 0.04;
	// Passive mob, easy

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.vex")
	public String vexMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.vex")
	public double vexMcMMOSkillRewardChance = 0.04;
	// Hostile mob, easy

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.villager")
	public String villagerMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.villager")
	public double villagerMcMMOSkillRewardChance = 0.025;
	// Passive mob, risk free

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.vindicator")
	public String vindicatorMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.vindicator")
	public double vindicatorMcMMOSkillRewardChance = 0.05;
	// Hostile mob, normal

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.wanderingtrader")
	public String wanderingTraderMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.wanderingtrader")
	public double wanderingTraderMcMMOSkillRewardChance = 0.05;
	// Hostile mob, normal

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.warden")
	public String wardenMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.warden")
	public double wardenMcMMOSkillRewardChance = 0.05;
	// Hostile mob, normal

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.weaponsmith")
	public String weaponsmithMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.weaponsmith")
	public double weaponsmithMcMMOSkillRewardChance = 0.05;
	// Hostile mob, normal

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.witch")
	public String witchMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.witch")
	public double witchMcMMOSkillRewardChance = 0.05;
	// Hostile mob, normal

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.wither")
	public String witherMcMMOSkillRewardAmount = "5";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.wither")
	public double witherMcMMOSkillRewardChance = 0.33;
	// Hostile mob, hard (and rare considering the summoning requirements)

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.wither_skeleton")
	public String witherSkeletonMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.wither_skeleton")
	public double witherSkeletonMcMMOSkillRewardChance = 0.05;
	// Hostile mob, normal

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.wolf")
	public String wolfMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.wolf")
	public double wolfMcMMOSkillRewardChance = 0.04;
	// Hostile mob (kind of, needs to be hit first), easy

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.zoglin")
	public String zoglinMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.zoglin")
	public double zoglinMcMMOSkillRewardChance = 0.025;
	// Hostile mob, normal

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.zombie")
	public String zombieMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.zombie")
	public double zombieMcMMOSkillRewardChance = 0.4;
	// Hostile mob, easy

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.zombiehorse")
	public String zombieHorseMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.zombiehorse")
	public double zombieHorseMcMMOSkillRewardChance = 0.025;
	// Passive mob, risk free

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.zombie_pigman")
	public String zombiePigManMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.zombie_pigman")
	public double zombiePigManMcMMOSkillRewardChance = 0.05;
	// Hostile mob, normal

	@ConfigField(name = "skillreward_amount", category = "plugins.mcmmo.mobs.zombie_villager")
	public String zombieVillagerMcMMOSkillRewardAmount = "1";
	@ConfigField(name = "skillreward_chance", category = "plugins.mcmmo.mobs.zombie_villager")
	public double zombieVillagerMcMMOSkillRewardChance = 0.04;

	// #####################################################################################
	// CrackShot integration
	// #####################################################################################
	@ConfigField(name = "crackshot.enable_integration_crackshot", category = "plugins", comment = "Enable/disable integration with CrackShot."
			+ "\nhttps://dev.bukkit.org/projects/crackshot")
	public boolean enableIntegrationCrackShot = true;

	@ConfigField(name = "crackshot.crackshot_multiplier", category = "plugins", comment = "Multiplier used when a Crackshot weapon was used to kill a mob or a player")
	public double crackShot = 0.7;

	// #####################################################################################
	// WeaponMechanics integration
	// #####################################################################################
	@ConfigField(name = "weaponmechanics.enable_integration_weaponmechanics", category = "plugins", comment = "Enable/disable integration with Weapon Mechanics."
			+ "\nhttps://www.spigotmc.org/resources/weaponmechanics-1-9-4-1-19-2.99913/")
	public boolean enableIntegrationWeaponMechanics = true;

	@ConfigField(name = "weaponmechanics.weaponmechanics_multiplier", category = "plugins", comment = "Multiplier used when a WeaponMechanics weapon was used to kill a mob or a player")
	public double weaponMechanicsShot = 0.5;

	// #####################################################################################
	// MobArena integration
	// #####################################################################################
	@ConfigField(name = "mobarena.enable_integration_mobarena", category = "plugins", comment = "Enable/Disable integration with MobArena")
	public boolean enableIntegrationMobArena = true;

	@ConfigField(name = "mobarena.mobarena_get_rewards", category = "plugins", comment = "Set to true if you want the players to get rewards while playing MobArena.")
	public boolean mobarenaGetRewards = false;

	// #####################################################################################
	// Pvparena integration
	// #####################################################################################
	@ConfigField(name = "pvparena.enable_integration_pvparena", category = "plugins", comment = "Enable/Disable integration with PvpArena")
	public boolean enableIntegrationPvpArena = true;

	@ConfigField(name = "pvparena.pvparena_get_rewards", category = "plugins", comment = "Set to true if you want the players to get rewards while playing pvpArena.")
	public boolean pvparenaGetRewards = false;

	// #####################################################################################
	// Plugin integration
	// #####################################################################################
	@ConfigField(name = "mythicmobs.enable_integration_mythicmobs", category = "plugins", comment = "Enable/Disable integration with MythicMobs")
	public boolean enableIntegrationMythicmobs = true;

	@ConfigField(name = "mypet.enable_integration_mypet", category = "plugins", comment = "Enable/Disable integration with MyPet")
	public boolean enableIntegrationMyPet = true;

	@ConfigField(name = "mcmmohorses.enable_integration_mcmmohorses", category = "plugins", comment = "Enable/Disable integration with McMMOHorses."
			+ "\nhttps://www.spigotmc.org/resources/mcmmohorses.46301/")
	public boolean enableIntegrationMcMMOHorses = true;

	@ConfigField(name = "minigames.enable_integration_minigames", category = "plugins", comment = "Enable/Disable integration with MiniGames")
	public boolean enableIntegrationMinigames = true;

	@ConfigField(name = "minigameslib.enable_integration_minigameslib", category = "plugins", comment = "Enable/Disable integration with MiniGamesLib"
			+ "\nhttps://www.spigotmc.org/resources/minigameslib.23844/")
	public boolean enableIntegrationMinigamesLib = true;

	@ConfigField(name = "worldguard.enable_integration_worldguard", category = "plugins", comment = "Enable/Disable integration with WorldGuard")
	public boolean enableIntegrationWorldGuard = true;

	@ConfigField(name = "worldedit.enable_integration_worldedit", category = "plugins", comment = "Enable/Disable integration with WorldEdit")
	public boolean enableIntegrationWorldEdit = true;

	@ConfigField(name = "essentials.enable_integration_essentials", category = "plugins", comment = "Enable/Disable integration with Essentials"
			+ "\nhttp://dev.bukkit.org/bukkit_plugins/essentialsx/")
	public boolean enableIntegrationEssentials = true;

	@ConfigField(name = "battlearena.enable_integration_battlearena", category = "plugins", comment = "Enable/Disable integration with BattleArena")
	public boolean enableIntegrationBattleArena = true;

	@ConfigField(name = "vanishnopacket.enable_integration_vanishnopacket", category = "plugins", comment = "Enable/Disable integration with VanishNoPacket")
	public boolean enableIntegrationVanishNoPacket = true;

	@ConfigField(name = "gringotts.enable_integration_gringotts", category = "plugins", comment = "Enable/Disable integration with Gringotts Economy."
			+ "\nhttp://dev.bukkit.org/bukkit_plugins/gringotts/")
	public boolean enableIntegrationGringotts = true;

	@ConfigField(name = "tardis_weepingangles.enable_integration_tardis_weeping_angels", category = "plugins", comment = "Enable/Disable integration with TARDIS Weeping Angels."
			+ "\nhttp://dev.bukkit.org/bukkit_plugins/tardisweepingangels/")
	public boolean enableIntegrationTARDISWeepingAngels = true;

	@ConfigField(name = "protocollib.show_grinding_area_using_protocollib_temp", category = "plugins", comment = "Enable/Disable. When a Grinding is detected and the reward is denied, the Grinding Area can be shown while a circle of flames."
			+ "\nhttps://www.spigotmc.org/wiki/mobhunting-grinding-detection/")
	public boolean showGrindingAreaUsingProtocolLib = false;

	@ConfigField(name = "mysterious_halloween.enable_integration_mysterious_halloween", category = "plugins", comment = "Enable/Disable integration with MysteriousHalloween."
			+ "\nhttps://www.spigotmc.org/resources/mysterioushalloween.13059/")
	public boolean enableIntegrationMysteriousHalloween = true;

	@ConfigField(name = "smartgiants.enable_integration_smartgiants", category = "plugins", comment = "Enable/Disable integration with SmartGiants."
			+ "\nhttps://www.spigotmc.org/threads/smartgiants.55208/")
	public boolean enableIntegrationSmartGiants = true;

	@ConfigField(name = "placeholderapi.enable_integration_placeholderapi", category = "plugins", comment = "Enable/Disable integration with PlaceholderAPI."
			+ "\nhttps://www.spigotmc.org/resources/placeholderapi.6245/")
	public boolean enableIntegrationPlaceholderAPI = true;

	@ConfigField(name = "bossshop.enable_integration_bossshop", category = "plugins", comment = "Enable/Disable integration with BossShop."
			+ "\nhttps://www.spigotmc.org/resources/bossshop_powerful_and_playerfriendly_chest_gui_shop_menu_plugin.222/")
	public boolean enableIntegrationBossShop = true;

	@ConfigField(name = "extra_hard_mode.enable_integration_extra_hard_mode", category = "plugins", comment = "Enable/Disable integration with ExtraHardmode."
			+ "\nhttps://www.spigotmc.org/resources/extra_hard_mode.19673/")
	public boolean enableIntegrationExtraHardMode = true;

	@ConfigField(name = "herobrine.enable_integration_herobrine", category = "plugins", comment = "Enable/Disable integration with Herobrine."
			+ "\nhttps://www.theprogrammersworld.net/Herobrine/")
	public boolean enableIntegrationHerobrine = true;

	@ConfigField(name = "boss.enable_integration_boss", category = "plugins", comment = "Enable/Disable integration with Boss."
			+ "\nhttps://")
	public boolean enableIntegrationBoss = true;

	@ConfigField(name = "holograms.enable_integration_holograms", category = "plugins", comment = "Enable/Disable integration with Holograms."
			+ "\nhttps://www.spigotmc.org/resources/holograms.4924/")
	public boolean enableIntegrationHolograms = true;

	@ConfigField(name = "holographic_displays.enable_integration_holographic_displays", category = "plugins", comment = "Enable/Disable integration with Holograms."
			+ "\nhttps://dev.bukkit.org/projects/holographic_displays")
	public boolean enableIntegrationHolographicDisplays = true;

	@ConfigField(name = "precious_stones.enable_integration_preciousstones", category = "plugins", comment = "Enable/Disable integration with PreciousStones."
			+ "\nhttps://www.spigotmc.org/resources/preciousstones.5270/")
	public boolean enableIntegrationPreciousStones = true;

	// #####################################################################################
	// DropMoneyOnGround settings - for servers without the BagOfGold plugin
	// #####################################################################################
	@ConfigField(name = "drop_money_on_ground", category = "dropmoneyonground", comment = "When a player get a money reward for a kill, the money will go directly"
			+ "\ninto his pocket. If you set dropMoneyOnGround=true the reward will "
			+ "\ndropped on ground to be picked up by the player."
			+ "\nNegative rewards will always be taken from the player. "
			+ "\n\nOBS Notice if you have the BagOfGold plugin installed these settings will be "
			+ "\noverruled by the settings in BagOfGold config.yml !!!")
	public boolean dropMoneyOnGroup = true;

	@ConfigField(name = "drop_money_use_item_as_currency", category = "dropmoneyonground", comment = "If you dont want to use BagOfGold (https://dev.bukkit.org/projects/bagofgold) "
			+ "\nas you economy plugin, but still want to bags to be dropped on the ground, "
			+ "\nyou can set this to true and then the player will be able to pick up the bags "
			+ "\nand use MobHunting signs to sell the Bags and get the money this way.")
	public boolean dropMoneyOnGroundUseItemAsCurrency = true;

	// #####################################################################################
	// Database
	// #####################################################################################
	@ConfigField(name = "type", category = "database", comment = "Type of database to use. Valid values are: sqlite, mysql")
	public String databaseType = "sqlite";

	@ConfigField(name = "database_name", category = "database")
	public String databaseName = "mobhunting";

	@ConfigField(name = "username", category = "database.mysql")
	public String databaseUsername = "user";

	@ConfigField(name = "password", category = "database.mysql")
	public String databasePassword = "password";

	@ConfigField(name = "host", category = "database.mysql")
	public String databaseHost = "localhost:3306";

	@ConfigField(name = "useSSL", category = "database.mysql")
	public String databaseUseSSL = "false";

	@ConfigField(name = "database_version", category = "database", comment = "This is the database layout version. Mostly for internal use and you should not need"
			+ "\nto chance this value. In case you decide to delete your database and let it recreate"
			+ "\nor if you chance database type sqlite/mysql you should set this value to 0 again.")
	public int databaseVersion = 0;

	// #####################################################################################
	// Update Settings
	// #####################################################################################
	@ConfigField(name = "update_check", category = "updates", comment = "Check if there is a new version of the plugin available.")
	public boolean updateCheck = true;

	@ConfigField(name = "check_every", category = "updates", comment = "Set the number of seconds between each check. Recommended setting is"
			+ "\ncheck_every: 7200 ~ to check every second hour.")
	public int checkEvery = 7200;

	@ConfigField(name = "autoupdate", category = "updates", comment = "Set 'autoupdate: true' if you want new updates downloaded and installed."
			+ "\nYou will still have to reboot the server manually.")
	public boolean autoupdate = false;

	// #####################################################################################
	// Generel settings
	// #####################################################################################
	@ConfigField(name = "disabled_in_worlds", category = "general", comment = "Put the names of the worlds here that you do not wish for mobhunting to be enabled in.")
	public String[] disabledInWorlds = { "worldname", "worldname2" };

	@ConfigField(name = "language", category = "general", comment = "The language (file) to use. You can put the name of the language file as the language code "
			+ "\n(eg. en_US, de_DE, fr_FR, ru_RU, pl_PL ect.) or you can specify the name of a custom file without the .lang\nPlease check the lang/ folder for a list of all available translations.")
	public String language = "en_US";

	@ConfigField(name = "disable_mobhunting_advancements", category = "general", comment = "As of V 5.0.0 MobHunting utilizises the Advancement system (L key) to to show which"
			+ "\nAchievements the players has made. This is still BETA feature and it is only Supported"
			+ "\non Spigot Servers and if you have any problems, you can set 'disable_mobhunting_advancements: true "
			+ "\nand the reload the plugin.")
	public boolean disableMobHuntingAdvancements = false;

	@ConfigField(name = "disable_redstone_powered_signs", category = "general", comment = "It seems that redstone powered signs can cause lag on many servers.")
	public boolean disableRedstonePoweredSigns = true;

	@ConfigField(name = "use_actionbar_for_broadcasts", category = "general", comment = "Broadcast messages will be send in the ActionBar if MobHunting finds a supported ActionBar plugin.")
	public boolean useActionBarforBroadcasts = true;

	@ConfigField(name = "broadcast_achievement", category = "general", comment = "Should achievements be broadcasted?")
	public boolean broadcastAchievement = true;

	@ConfigField(name = "broadcast_first_achievement", category = "general", comment = "Should the hunt begins achievement be broadcasted?")
	public boolean broadcastFirstAchievement = true;

	@ConfigField(name = "leaderboard_update_period", category = "general", comment = "Time between leaderboard updates in ticks (20 ticks ~ 1 sec) This number must be higher that 1200 ticks = 2 minutes,"
			+ "\nbut I recommend to update leaderboards max every 5 min = 6000 ticks")
	public int leaderboardUpdatePeriod = 6000;

	@ConfigField(name = "kill_timeout", category = "general", comment = "Time in seconds after attacking a mob that can be counted as a kill")
	public int killTimeout = 4;

	@ConfigField(name = "debug", category = "general", comment = "If kills are not being registered in mob hunting. Enable this to see why they arent")
	public boolean killDebug = false;

	@ConfigField(name = "backup", category = "general", comment = "Backup config on each server start / reload")
	public boolean backup = true;

	@ConfigField(name = "newplayer_learning_mode", category = "general", comment = "When a new playerjoins the server he will by default start"
			+ "\nin 'LEARNING MODE' and get extra information about when he get rewards and not,"
			+ "\nwhen killing Mobs. The player can disable this InGame by using the command '/mobhunt learn'")
	public boolean learningMode = true;

	@ConfigField(name = "use_gui_for_achievements", category = "general", comment = "When use_gui_for_achivements=true the status of players achievements will"
			+ "\nbe showed in a Inventory GUI.")
	public boolean useGuiForAchievements = true;

	@ConfigField(name = "use_gui_for_bounties", category = "general", comment = "When use_gui_for_bounties=true the open bounties and most wanted players will"
			+ "\nbe showed in a Inventory GUI.")
	public boolean useGuiForBounties = true;

	@ConfigField(name = "disable_natural_item_drops", category = "general", comment = "Disable natural drops when a mob is killed "
			+ "\n(because player is grinding or protected by Worldguard or in God mode or similar)"
			+ "\nIf you want the mobs to drops normal rewards set " + "\n\"disable_natural_item_drops\"=false")
	public boolean disableNaturalItemDrops = true;

	@ConfigField(name = "disable_natural_xp_drops", category = "general", comment = "Disable natural xp drops when a mob is killed"
			+ "\n(because player is grinding or protected by Worldguard or in God mode or similar)"
			+ "\nIf you want the mobs to drop normal XP set " + "\n\"disable_natural_xp_drops\"=false")
	public boolean disableNatualXPDrops = true;

	@ConfigField(name = "try_to_cancel_natural_drops_when_in_creative", category = "general", comment = "Try to cancel natural drops when a mob is killed the player is in creative mode."
			+ "\nIf you want the mobs to drops normal rewards set "
			+ "\n\"try_to_cancel_natural_drops_when_in_creative\"=false")
	public boolean tryToCancelNaturalDropsWhenInCreative = true;

	@ConfigField(name = "try_to_cancel_xp_drops_when_in_creative", category = "general", comment = "Try to cancel XP drops when a mob is killed while the player is in creative mode."
			+ "\nIf you want the mobs to drop normal XP set " + "\n\"try_to_cancel_xp_drops_when_in_creative\"=false")
	public boolean tryToCancelXPDropsWhenInCreative = true;

	@ConfigField(name = "config_version", category = "general", comment = "Do not chance this value unless you know what you are doing. It's meant for internal use.")
	public int configVersion = 1;

	@Override
	protected void onPostLoad() throws InvalidConfigurationException {
		plugin.getMessages().setLanguage(language + ".lang");
	}

	public static List<HashMap<String, String>> convertCommands(String str, double chance) {
		List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		if (!str.equals("")) {
			String[] commands = str.split("\\|");
			for (int n = commands.length; n > 0; n--) {
				if (!commands[n - 1].contains("head give")) {
					HashMap<String, String> cmd = new HashMap<String, String>();
					cmd.put("cmd", commands[n - 1]);
					cmd.put("chance", String.valueOf(chance));
					cmd.put("message", "");
					result.add(cmd);
				}
			}
		}
		return result;
	}

	public void backupConfig(File mFile) {
		File backupFile = new File(mFile.toString());
		int count = 0;
		while (backupFile.exists() && count++ < 1000) {
			backupFile = new File("plugins/MobHunting/backup/" + mFile.getName() + ".bak" + count);
		}
		if (mFile.exists())
			try {
				if (!backupFile.exists())
					backupFile.mkdirs();
				Files.copy(mFile.toPath(), backupFile.toPath(), StandardCopyOption.COPY_ATTRIBUTES,
						StandardCopyOption.REPLACE_EXISTING);
				Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "[MobHunting]" + ChatColor.RESET
						+ " Config.yml was backed up to " + backupFile.getPath());
			} catch (IOException e1) {
				Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "[MobHunting]" + ChatColor.RED
						+ "[ERROR] - Could not backup config.yml file to plugins/MobHunting/config.yml. Delete some old backups");
				e1.printStackTrace();
			}
	}

	public static int getConfigVersion(File file) {
		if (!file.exists())
			return -1;

		YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
		int res = config.getInt("general.config_version", config.contains("general.kill-debug") == true ? 0 : -2);
		if (res == -2)
			file.delete();
		return res;
	}

	public int getProgressAchievementLevel1(MobType mob) {
		switch (mob) {

		case Allay:
			return allayLevel1;
		case Axolotl:
			return axolotlLevel1;
		case Bat:
			return batLevel1;
		case BonusMob:
			return bonusMobLevel1;
		case Blacksmith:
			return blacksmithLevel1;
		case Blaze:
			return blazeLevel1;
		case Butcher:
			return butcherLevel1;
		case TropicalFish:
			return clownfishLevel1;
		case CaveSpider:
			return caveSpiderLevel1;
		case Chicken:
			return chickenLevel1;
		case Cow:
			return cowLevel1;
		case Creeper:
			return creeperLevel1;
		case Donkey:
			return donkeyLevel1;
		case ElderGuardian:
			return elderGuardianLevel1;
		case EnderDragon:
			return enderdragonLevel1;
		case Enderman:
			return endermanLevel1;
		case Endermite:
			return endermiteLevel1;
		case Evoker:
			return evokerLevel1;
		case Farmer:
			return farmerLevel1;
		case Ghast:
			return ghastLevel1;
		case Giant:
			return giantLevel1;
		case Goat:
			return goatLevel1;
		case GlowSquid:
			return glowSquidLevel1;
		case Guardian:
			return guardianLevel1;
		case Horse:
			return horseLevel1;
		case Husk:
			return huskLevel1;
		case Illusioner:
			return illusionerLevel1;
		case IronGolem:
			return ironGolemLevel1;
		case KillerRabbit:
			return killerRabbitLevel1;
		case Librarian:
			return librarianLevel1;
		case Llama:
			return llamaLevel1;
		case Mule:
			return muleLevel1;
		case MagmaCube:
			return magmaCubeLevel1;
		case MushroomCow:
			return mushroomCowLevel1;
		case Nitwit:
			return nitwitLevel1;
		case Ocelot:
			return ocelotLevel1;
		case Parrot:
			return parrotLevel1;
		case PassiveRabbit:
			return rabbitLevel1;
		case Pig:
			return pigLevel1;
		case PolarBear:
			return polarBearLevel1;
		case Priest:
			return priestLevel1;
		case Pufferfish:
			return pufferfishLevel1;
		case PvpPlayer:
			return pvpPlayerLevel1;
		case Cod:
			return rawfishLevel1;
		case Salmon:
			return rawsalmonLevel1;
		case Sheep:
			return sheepLevel1;
		case Shulker:
			return shulkerLevel1;
		case Silverfish:
			return silverfishLevel1;
		case Skeleton:
			return skeletonLevel1;
		case SkeletonHorse:
			return skeletonHorseLevel1;
		case Slime:
			return slimeLevel1;
		case Snowman:
			return snowmanLevel1;
		case Spider:
			return spiderLevel1;
		case Squid:
			return squidLevel1;
		case Stray:
			return strayLevel1;
		case Tadpole:
			return tadpoleLevel1;
		case TraderLlama:
			return traderllamaLevel1;
		case Vex:
			return vexLevel1;
		case Villager:
			return villagerLevel1;
		case Vindicator:
			return vindicatorLevel1;
		case WanderingTrader:
			return wanderingTraderLevel1;
		case Warden:
			return wardenLevel1;
		case Witch:
			return witchLevel1;
		case Wither:
			return witherLevel1;
		case WitherSkeleton:
			return witherSkeletonLevel1;
		case Wolf:
			return wolfLevel1;
		case Zombie:
			return zombieLevel1;
		case ZombieHorse:
			return zombieHorseLevel1;
		case ZombiePigman:
			return zombiePigmanLevel1;
		case ZombieVillager:
			return zombieVillagerLevel1;
		case Dolphin:
			return dolphinLevel1;
		case Drowned:
			return drownedLevel1;
		case Phantom:
			return phantomLevel1;
		case Turtle:
			return turtleLevel1;
		case Cat:
			return catLevel1;
		case Fox:
			return foxLevel1;
		case Frog:
			return frogLevel1;
		case Panda:
			return pandaLevel1;
		case Pillager:
			return pillagerLevel1;
		case Ravager:
			return ravagerLevel1;
		case Armorer:
			return armorerLevel1;
		case Cartographer:
			return cartographerLevel1;
		case Cleric:
			return clericLevel1;
		case Fisherman:
			return fishermanLevel1;
		case Fletcher:
			return fletcherLevel1;
		case Leatherworker:
			return leatherworkerLevel1;
		case Mason:
			return masonLevel1;
		case Shepherd:
			return shepherdLevel1;
		case Toolsmith:
			return toolsmithLevel1;
		case Unemployed:
			return villagerLevel1;
		case Weaponsmith:
			return weaponsmithLevel1;
		case Bee:
			return beeLevel1;
		case Hoglin:
			return hoglinLevel1;
		case Piglin:
			return piglinLevel1;
		case PiglinBrute:
			return piglinBruteLevel1;
		case Strider:
			return striderLevel1;
		case Zoglin:
			return zoglinLevel1;
		}
		return 100;
	}

	/**
	 * Return the reward money for a given mob
	 * 
	 * @return value
	 */
	public double getHeadPrize(MobType mob) {
		switch (mob) {
		case Allay:
			return getPrice(mob, allayHeadPrize);
		case Bat:
			return getPrice(mob, batHeadPrize);
		case Blacksmith:
			return getPrice(mob, blacksmithHeadPrize);
		case Blaze:
			return getPrice(mob, blazeHeadPrize);
		case BonusMob:
			return getPrice(mob, bonusMobHeadPrize);
		case Butcher:
			return getPrice(mob, butcherHeadPrize);
		case Cartographer:
			return getPrice(mob, cartographerHeadPrize);
		case CaveSpider:
			return getPrice(mob, caveSpiderHeadPrize);
		case Chicken:
			return getPrice(mob, chickenHeadPrize);
		case TropicalFish:
			return getPrice(mob, tropicalFishHeadPrize);
		case Cow:
			return getPrice(mob, cowHeadPrize);
		case Creeper:
			return getPrice(mob, creeperHeadPrize);
		case Donkey:
			return getPrice(mob, donkeyHeadPrize);
		case ElderGuardian:
			return getPrice(mob, elderGuardianHeadPrize);
		case EnderDragon:
			return getPrice(mob, enderDragonHeadPrize);
		case Enderman:
			return getPrice(mob, endermanHeadPrize);
		case Endermite:
			return getPrice(mob, endermiteHeadPrize);
		case Evoker:
			return getPrice(mob, evokerHeadPrize);
		case Farmer:
			return getPrice(mob, farmerHeadPrize);
		case Frog:
			return getPrice(mob, frogHeadPrize);
		case Ghast:
			return getPrice(mob, ghastHeadPrize);
		case Giant:
			return getPrice(mob, giantHeadPrize);
		case Guardian:
			return getPrice(mob, guardianHeadPrize);
		case Horse:
			return getPrice(mob, horseHeadPrize);
		case Husk:
			return getPrice(mob, huskHeadPrize);
		case Illusioner:
			return getPrice(mob, illusionerHeadPrize);
		case IronGolem:
			return getPrice(mob, ironGolemHeadPrize);
		case KillerRabbit:
			return getPrice(mob, killerRabbitHeadPrize);
		case Librarian:
			return getPrice(mob, librarianHeadPrize);
		case Llama:
			return getPrice(mob, llamaHeadPrize);
		case MagmaCube:
			return getPrice(mob, magmaCubeHeadPrize);
		case Mule:
			return getPrice(mob, muleHeadPrize);
		case MushroomCow:
			return getPrice(mob, mushroomCowHeadPrize);
		case Nitwit:
			return getPrice(mob, nitwitHeadPrize);
		case Ocelot:
			return getPrice(mob, ocelotHeadPrize);
		case Parrot:
			return getPrice(mob, parrotHeadPrize);
		case PassiveRabbit:
			return getPrice(mob, rabbitHeadPrize);
		case Pig:
			return getPrice(mob, pigHeadPrize);
		case PolarBear:
			return getPrice(mob, polarBearHeadPrize);
		case Priest:
			return getPrice(mob, priestHeadPrize);
		case Pufferfish:
			return getPrice(mob, pufferfishHeadPrize);
		case PvpPlayer:
			return getPrice(mob, pvpHeadPrize);
		case Cod:
			return getPrice(mob, codHeadPrize);
		case Salmon:
			return getPrice(mob, salmonHeadPrize);
		case Sheep:
			return getPrice(mob, sheepHeadPrize);
		case Shulker:
			return getPrice(mob, shulkerHeadPrize);
		case Silverfish:
			return getPrice(mob, silverfishHeadPrize);
		case Skeleton:
			return getPrice(mob, skeletonHeadPrize);
		case SkeletonHorse:
			return getPrice(mob, skeletonHorseHeadPrize);
		case Slime:
			return getPrice(mob, slimeHeadPrize);
		case Snowman:
			return getPrice(mob, snowmanHeadPrize);
		case Spider:
			return getPrice(mob, spiderHeadPrize);
		case Squid:
			return getPrice(mob, squidHeadPrize);
		case Stray:
			return getPrice(mob, strayHeadPrize);
		case Tadpole:
			return getPrice(mob, tadpoleHeadPrize);
		case TraderLlama:
			return getPrice(mob, traderLlamaHeadPrize);
		case Vex:
			return getPrice(mob, vexHeadPrize);
		case Villager:
			return getPrice(mob, villagerHeadPrize);
		case Vindicator:
			return getPrice(mob, vindicatorHeadPrize);
		case WanderingTrader:
			return getPrice(mob, wanderingTraderHeadPrize);
		case Witch:
			return getPrice(mob, witchHeadPrize);
		case Wither:
			return getPrice(mob, witherHeadPrize);
		case WitherSkeleton:
			return getPrice(mob, witherSkeletonHeadPrize);
		case Wolf:
			return getPrice(mob, wolfHeadPrize);
		case Zombie:
			return getPrice(mob, zombieHeadPrize);
		case ZombieHorse:
			return getPrice(mob, zombieHorseHeadPrize);
		case ZombiePigman:
			return getPrice(mob, zombiePigmanHeadPrize);
		case ZombieVillager:
			return getPrice(mob, zombieVillagerHeadPrize);
		case Dolphin:
			return getPrice(mob, dolphinHeadPrize);
		case Drowned:
			return getPrice(mob, drownedHeadPrize);
		case Phantom:
			return getPrice(mob, phantomHeadPrize);
		case Turtle:
			return getPrice(mob, turtleHeadPrize);
		case Cat:
			return getPrice(mob, catHeadPrize);
		case Fox:
			return getPrice(mob, foxHeadPrize);
		case Panda:
			return getPrice(mob, pandaHeadPrize);
		case Pillager:
			return getPrice(mob, pillagerHeadPrize);
		case Ravager:
			return getPrice(mob, ravagerHeadPrize);
		case Armorer:
			return getPrice(mob, armorerHeadPrize);
		case Cleric:
			return getPrice(mob, clericHeadPrize);
		case Fisherman:
			return getPrice(mob, fishermanHeadPrize);
		case Fletcher:
			return getPrice(mob, fletcherHeadPrize);
		case Leatherworker:
			return getPrice(mob, leatherworkerHeadPrize);
		case Mason:
			return getPrice(mob, masonHeadPrize);
		case Shepherd:
			return getPrice(mob, sheepHeadPrize);
		case Toolsmith:
			return getPrice(mob, toolsmithHeadPrize);
		case Unemployed:
			return getPrice(mob, villagerHeadPrize);
		case Weaponsmith:
			return getPrice(mob, weaponsmithHeadPrize);
		case Bee:
			return getPrice(mob, beeHeadPrize);
		case Hoglin:
			return getPrice(mob, hoglinHeadPrize);
		case Piglin:
			return getPrice(mob, piglinHeadPrize);
		case PiglinBrute:
			return getPrice(mob, piglinBruteHeadPrize);
		case Strider:
			return getPrice(mob, striderHeadPrize);
		case Zoglin:
			return getPrice(mob, zoglinHeadPrize);
		case Axolotl:
			return getPrice(mob, axolotlHeadPrize);
		case GlowSquid:
			return getPrice(mob, glowsquidHeadPrize);
		case Goat:
			return getPrice(mob, goatHeadPrize);
		case Warden:
			return getPrice(mob, wardenHeadPrize);
		default:
			break;
		}
		return 0;
	}

	private double getPrice(MobType mob, String str) {
		if (str == null || str.equals("") || str.isEmpty()) {
			Bukkit.getServer().getConsoleSender()
					.sendMessage(ChatColor.RED + "[MobHunting][WARNING]" + ChatColor.RESET + " The prize for killing a "
							+ mob.getFriendlyName()
							+ " is not set in config.yml. Please set the prize to 0 or a positive or negative number.");
			return 0;
		} else if (str.startsWith(":")) {
			Bukkit.getServer().getConsoleSender()
					.sendMessage(ChatColor.RED + "[MobHunting][WARNING]" + ChatColor.RESET + " The prize for killing a "
							+ mob.getFriendlyName()
							+ " in config.yml has a wrong format. The prize can't start with \":\"");
			if (str.length() > 1)
				return getPrice(mob, str.substring(1, str.length()));
			else
				return 0;
		} else if (str.contains(":")) {
			String[] str1 = str.split(":");
			double prize = (MobHunting.getInstance().mRand.nextDouble()
					* (Double.valueOf(str1[1]) - Double.valueOf(str1[0])) + Double.valueOf(str1[0]));
			return Tools.round(prize);
		} else
			return Double.valueOf(str);
	}

	public String getFriendlyName(MobType mob) {
		return MobHunting.getInstance().getMessages().getString("mobs." + mob.name() + ".name");
	}

}
