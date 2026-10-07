# Compatibility status

MobHunting is developed for Paper 1.21.11. The status below reflects the
plugins available in the maintainer's reference test environment. An
integration marked as **not verified** is not necessarily incompatible; it has
not been tested because the corresponding plugin is not installed.

## Available for 8.7.0 validation

These integrations are present in the reference environment and form the
supported test scope for the 8.7.0 development branch:

| Integration | Reference plugin version | 8.7.0 status |
| --- | --- | --- |
| CustomItemsLib | 1.2.0 | Pending 8.7.0 regression test |
| BagOfGold | 4.6.2-SNAPSHOT | Pending 8.7.0 regression test |
| BetterRevive | 0.9.1 custom build | Pending 8.7.0 regression test |
| CMILib | 1.6.0.1 | Pending 8.7.0 regression test |
| Citizens | 2.0.41-b4134 | Pending 8.7.0 regression test |
| EssentialsX | 2.22.0 development build | Pending 8.7.0 regression test |
| InfernalMobs | Version not exposed by the jar name | Pending 8.7.0 regression test |
| Multiverse-Core | 4.3.12 | Pending 8.7.0 regression test |
| MyPet | 4.0.4 | Pending 8.7.0 regression test |
| PlaceholderAPI | 2.11.5 | Pending 8.7.0 regression test |
| ProtocolLib | Version not exposed by the jar name | Pending 8.7.0 regression test |
| Towny | 0.102.0.0 | Pending 8.7.0 regression test |
| Vault-compatible economy bridge | 2.17.0 | Pending 8.7.0 regression test |
| WorldEdit | 7.2.17 | Pending 8.7.0 regression test |
| WorldGuard | 7.0.9 | Pending 8.7.0 regression test |

## Not verified in the reference environment

The following active integrations are retained in the source but are not part
of the maintainer's current test environment:

- BattleArena, Minigames, MinigamesLib, MobArena and PVPArena
- Boss, EliteMobs, LevelledMobs, MythicMobs and TARDISWeepingAngels
- CMI, Factions, Gringotts, Residence and PreciousStones
- CrackShot, ExtraHardMode, McMMO, McMMOHorses and WeaponMechanics
- LibsDisguises and VanishNoPacket
- MobStacker and StackMob
- MysteriousHalloween and Reserve

Compatibility claims for these integrations require reports or tests from
servers that actually use them.

## Retired in 8.7.0

Support for BossShop, ConquestiaMobs, CustomMobs, DisguiseCraft, Herobrine,
HolographicDisplays, iDisguise, LorinthsRpgMobs, SainttX Holograms and
SmartGiants was removed because their adapters target obsolete or unavailable
APIs. Historical database identifiers for CustomMobs, SmartGiants and
Herobrine remain reserved so existing statistics are not reinterpreted.

Plugins that reuse one of these names but expose a different API are not
treated as compatible implementations of the retired integration.
