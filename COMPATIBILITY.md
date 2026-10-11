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
| CustomItemsLib | 1.3.0-SNAPSHOT | Startup and shutdown verified with MobHunting alone and in the complete stack |
| BagOfGold | 4.7.0-SNAPSHOT | Startup, shutdown and Vault provider selection verified |
| BetterRevive | 0.9.1 custom build | Adapter enabled; 13 attribution regression tests passed |
| CMILib | 1.6.0.1 | Detected during startup; message integration retained |
| Citizens | 2.0.41-b4134 | Adapter enabled and compatibility shutdown completed cleanly |
| EssentialsX | 2.22.0 development build | Adapter enabled and selected through Vault when BagOfGold was absent |
| InfernalMobs | 6.9.3 | Adapter loaded 99 definitions; source-prefixed achievement behavior validated in game |
| Multiverse-Core | 5.5.3 | Present during startup; MobHunting-specific world interaction not retested |
| MyPet | 4.0.4 | Adapter enabled during startup |
| PlaceholderAPI | 2.12.2 | Expansion registered during startup |
| ProtocolLib | 5.4.1 development build | Detected through CustomItemsLib during startup |
| Towny | 0.102.0.0 | Adapter enabled during startup |
| Vault-compatible economy bridge | 2.17.0 | EssentialsX and BagOfGold provider selection verified across the matrix |
| WorldEdit | 7.4.0 development build | Adapter enabled during startup |
| WorldGuard | 7.0.13 development build | Adapter enabled during startup |

These results cover plugin loading, linkage and clean shutdown. Only behavior
explicitly identified above as an in-game or regression test has gameplay
coverage in this development cycle.

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
