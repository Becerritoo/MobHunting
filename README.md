![alt text](https://www.spigotmc.org/attachments/mobhunting-header-png.194617/)

MobHunting
=====================
*MobHunting adds a new level of fun to hunting monsters, animals or opponents. Now you can get money or even dead players skull from kills, get bonuses for skilled and creative kills, and get special achievements!*

## JL fork: Paper 1.21.11

Active branch: [`release/paper-1.21.11`](https://github.com/Becerritoo/MobHunting/tree/release/paper-1.21.11).
Current release: [`8.5.7-rc2-JL.6`](https://github.com/Becerritoo/MobHunting/releases/tag/v8.5.7-rc2-JL.6).
The server operator confirmed successful in-game validation on Minecraft 1.21.11 on 2026-09-27.
The previous `release/paper-1.21.4` branch is retained as historical development.

See [changelog.txt](changelog.txt) for JL changes and upstream history, and
[release notes](releases/v8.5.7-rc2-JL.6.md) for artifact provenance and validation.

### Build and compatibility

The installed JL.6 build uses CustomItemsLib `1.1.0-JL.11` and MyPet `4.0.4`.
The inherited Spigot API dependency is still `1.21.4-R0.1-SNAPSHOT`; runtime validation targets **1.21.11**.
`api-version: 1.21` is plugin metadata, not a claim of validation on every Minecraft release.

Build with the existing JL Maven dependency environment using `mvn -o -B -ntp package`.
The parent `one.lindegaard:Main:0.1.6-SNAPSHOT` and legacy/custom dependencies must be available.
MyPet currently uses a local system dependency; on another machine supply
`-Dmypet.jar=/absolute/path/to/MyPet-4.0.4.jar`.
A clean checkout alone does not provision these dependencies.

BetterRevive integration pays through the normal reward flow only at final death and requires
the original downing attacker to remain online. Revival clears attribution; attribution does
not survive restart. These limitations remain part of the validated behavior.

Websites - more info
-------------------------
- [SpigotMC](https://www.spigotmc.org/resources/mobhunting.3582/)

You should also look at following recommended plugins:
- [Vault](https://www.spigotmc.org/resources/vault.34315/)
- [BagOfGold](https://www.spigotmc.org/resources/bagofgold.49332/) 
- [Protocollib](https://www.spigotmc.org/resources/protocollib.1997/)
- [PerWorldInventory](https://www.spigotmc.org/resources/per-world-inventory.4482/)

## Features
* Reward Money from killing monsters.
* Reward / Punish Money from killing animals. (disabled by default)
* Steal money from other players pockets (can be disabled) 
* Put a bounty on your enemies and get them killed.
* [Run a console command as a reward or punishment or Start a Skull Collection / Give permissions to something.](http://dev.bukkit.org/bukkit-plugins/mobhunting/pages/run-a-console-command-as-a-reward/)
* Modifiers to increase your income for creative kills
* Many Achievements to collect
* MasterMobHunters (Citizens2) and Signs
* Leaderboards
* Mob grinder detection
* JL release validated on Minecraft 1.21.11; other versions are not validated by this release.
* Heavily customizable
* Language support
* Tested on SpigotMC, CraftBukkit, PaperSpigot/PaperClip
* NEW Learning mode so players understand why they didn't get a reward when killing a mod.
