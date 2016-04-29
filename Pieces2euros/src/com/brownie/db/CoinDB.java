package com.brownie.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.brownie.core.Coin;

public class CoinDB {

	private static final Logger logger = LogManager.getLogger(CoinDB.class);

	private String jdbcDriverClass;
	private String jdbcDatabaseUrl;

	private static final String tableReferenceCoins = "referenceCoins";
	private static final String tableOwnedCoins = "personnalCoins";

	private enum tableColumns {
		ID, REF_ID, PICTURES, NUMBER, COUNTRY, YEAR, REFERENCE, COMMENTS
	}

	private static Connection connection;
	private static ResourceBundle bundle;

	public CoinDB() {
		// TODO Auto-generated constructor stub
		bundle = ResourceBundle.getBundle("connection");
		jdbcDriverClass = bundle.getString("JDBC_DRIVER_CLASS");
		jdbcDatabaseUrl = bundle.getString("JDBC_DATABASE_URL");
	}

	public void createDB() {
		Statement stmt = null;
		try {
			Class.forName(jdbcDriverClass);
			connection = DriverManager.getConnection(jdbcDatabaseUrl);
			logger.info("Opened database successfully");

			stmt = connection.createStatement();
			String sql = "DROP TABLE " + tableReferenceCoins;
			try {
				stmt.executeUpdate(sql);
			} catch (Exception e) {
				logger.info("The table [" + tableReferenceCoins
						+ "] can't be DROP since it not exists.");
			}

			sql = "CREATE TABLE IF NOT EXISTS " + tableReferenceCoins + " ("
					+ tableColumns.ID + "            INTEGER PRIMARY KEY,"
					+ " " + tableColumns.PICTURES + "       TEXT," + " "
					+ tableColumns.COUNTRY + "        TEXT    NOT NULL, " + " "
					+ tableColumns.YEAR + "           INTEGER NOT NULL, " + " "
					+ tableColumns.REFERENCE + "      TEXT    NOT NULL, " + " "
					+ tableColumns.COMMENTS + "       CHAR(50));";
			stmt.executeUpdate(sql);

			sql = "CREATE TABLE IF NOT EXISTS " + tableOwnedCoins + " ("
					+ tableColumns.ID + "            INTEGER PRIMARY KEY,"
					+ " " + tableColumns.REF_ID + "         INTEGER NOT NULL,"
					+ " " + tableColumns.NUMBER + "         INTEGER NOT NULL);";
			stmt.executeUpdate(sql);

			stmt.close();
			connection.close();
		} catch (Exception e) {
			logger.error("Can't create the database " + e.getMessage());
			System.exit(0);
		}
		logger.info("Reference table created successfully");

	}

	public void populateDB() {

		Statement stmt = null;
		try {
			Class.forName(jdbcDriverClass);
			connection = DriverManager.getConnection(jdbcDatabaseUrl);
			connection.setAutoCommit(false);
			logger.info("Opened database successfully");

			stmt = connection.createStatement();
			String sql = "INSERT INTO " + tableReferenceCoins
					+ " (ID,COUNTRY,YEAR,REFERENCE) "
					+ "VALUES (null,'FINLANDE', 2007, 'COMMON' );";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO " + tableReferenceCoins
					+ " (ID,COUNTRY,YEAR,REFERENCE) "
					+ "VALUES (null,'FINLANDE', 2007, 'FE' );";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO " + tableReferenceCoins
					+ " (ID,COUNTRY,YEAR,REFERENCE) "
					+ "VALUES (null,'FRANCE', 2013, 'COMMON' );";
			stmt.executeUpdate(sql);

			stmt.close();
			connection.commit();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		if (logger.isInfoEnabled())
			logger.info("Records created successfully");
	}

	public List<Coin> getReferenceCoins() {
		Statement stmt = null;

		List<Coin> referenceCoins = new ArrayList<Coin>();

		try {

			Class.forName(jdbcDriverClass);
			connection = DriverManager.getConnection(jdbcDatabaseUrl);
			connection.setAutoCommit(false);
			logger.info("Opened database successfully");

			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM "
					+ tableReferenceCoins + ";");
			while (rs.next()) {

				int id = rs.getInt("ID");
				String country = rs.getString("COUNTRY");
				int year = rs.getInt("YEAR");
				String reference = rs.getString("REFERENCE");
				referenceCoins.add(new Coin(id, country, reference, year));
			}
			rs.close();
			stmt.close();
			connection.close();
		} catch (Exception e) {
			logger.error(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		if (logger.isInfoEnabled())
			logger.info("Operation done successfully");
		return referenceCoins;
	}

}
