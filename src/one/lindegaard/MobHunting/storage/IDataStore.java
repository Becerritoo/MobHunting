package one.lindegaard.MobHunting.storage;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import org.bukkit.OfflinePlayer;

import one.lindegaard.CustomItemsLib.storage.DataStoreException;
import one.lindegaard.MobHunting.StatType;
import one.lindegaard.MobHunting.bounty.Bounty;
import one.lindegaard.MobHunting.mobs.ExtendedMob;

public interface IDataStore {
	/**
	 * Initialize - opening a connection to the Database and initialize the
	 * connection.
	 * 
	 * @throws DataStoreException
	 */
	void initialize() throws DataStoreException;

	/**
	 * Closing all connections to the Database
	 * 
	 * @throws DataStoreException
	 */
	void shutdown() throws DataStoreException;

	/**
	 * loadPlayerStats - Loading <count> records of Player Stats from the Database
	 * 
	 * @param type
	 * @param period
	 * @param count
	 * @return List<StatStore>
	 * @throws DataStoreException
	 */
	List<StatStore> loadPlayerStats(StatType type, TimePeriod period, int count) throws DataStoreException;

	/**
	 * Save a Set of Player Stats to the Database
	 * 
	 * @param stats
	 * @throws DataStoreException
	 */
	void savePlayerStats(Set<StatStore> stats) throws DataStoreException;

	/**
	 * Load a Players Archievements
	 * 
	 * @param player
	 * @return
	 * @throws DataStoreException
	 */
	Set<AchievementStore> loadAchievements(OfflinePlayer player) throws DataStoreException;

	/**
	 * Save a Set of players archievements
	 * 
	 * @param achievements
	 * @throws DataStoreException
	 */
	void saveAchievements(Set<AchievementStore> achievements) throws DataStoreException;

	/**
	 * Load all bounties for the given player directly from the Sql Database
	 * 
	 * @param mPlayer
	 * @return Set<Bounty>
	 * @throws DataStoreException
	 */
	Set<Bounty> loadBounties(OfflinePlayer mPlayer) throws DataStoreException;

	/**
	 * Save the Bounty Sets direktly to the Database
	 * 
	 * @param bountyDataSet
	 * @throws DataStoreException
	 */
	void saveBounties(Set<Bounty> bountyDataSet) throws DataStoreException;

	/**
	 * Fixes error in the database
	 * 
	 * @throws SQLException
	 * @throws DataStoreException
	 */
	void databaseFixLeaderboard() throws DataStoreException;

	/**
	 * Delete all achievements data from the database
	 * 
	 * @throws SQLException
	 * @throws DataStoreException
	 */
	void resetAchievements() throws DataStoreException;

	/**
	 * Delete all statistics data from the database
	 * 
	 * @throws SQLException
	 * @throws DataStoreException
	 */
	void resetStatistics() throws DataStoreException;

	/**
	 * Delete all bounties from the database
	 * 
	 * @throws SQLException
	 * @throws DataStoreException
	 */
	void resetBounties() throws DataStoreException;

	/**
	 * Convert all tables to use UTF-8 character set.
	 * 
	 * @param database_name
	 * @throws DataStoreException
	 */
	void databaseConvertToUtf8(String database_name) throws DataStoreException;

	Set<ExtendedMob> loadMobs() throws DataStoreException;

	void insertMobs(Set<ExtendedMob> mobs) throws DataStoreException;

	void updateMobs(Set<ExtendedMob> mobs) throws DataStoreException;

	void insertMissingVanillaMobs();

	void insertMissingMythicMobs();

	void insertMissingMythicMobs(String mob);

	void insertMissingCitizensMobs();

	void insertCitizensMobs(String mob);

	void insertTARDISWeepingAngelsMobs();

	void insertTARDISWeepingAngelsMobs(String mob);

	void insertMysteriousHalloweenMobs();

	void insertMysteriousHalloweenMobs(String mob);

	void insertCustomMobs();

	void insertCustomMobs(String mob);

	void insertInfernalMobs();

	void insertEliteMobs();

	void insertEliteMobs(String mob);

	void insertBossMobs();

	void insertBossMobs(String mob);

	/**
	 * Delete expired or cancelled bounties from database;
	 * 
	 * @throws DataStoreException
	 */
	void deleteExpiredBounties();

	/**
	 * Delete all players which is not known on the server.
	 */
	void databaseDeleteOldPlayers() throws DataStoreException;
	
}
