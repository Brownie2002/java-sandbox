package com.brownie.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class SQLiteJDBC {

	private String jdbcDriverClass;
	private String jdbcDatabaseUrl;

	private static Connection connection;
	private static ResourceBundle bundle;

	public SQLiteJDBC() {
		// TODO Auto-generated constructor stub
		bundle = ResourceBundle.getBundle("connection");
		jdbcDriverClass = bundle.getString("JDBC_DRIVER_CLASS");
		jdbcDatabaseUrl = bundle.getString("JDBC_DATABASE_URL");
	}

	public static void main(String args[]) {

	}

	
	public void createDB(){
		    Statement stmt = null;
		    try {
		      Class.forName(jdbcDriverClass);
		      connection = DriverManager.getConnection(jdbcDatabaseUrl);
		      System.out.println("Opened database successfully");
		      
		      stmt = connection.createStatement();
		      String sql = "DROP TABLE COMPANY ; CREATE TABLE COMPANY " +
		                   "(ID INT PRIMARY KEY     NOT NULL," +
		                   " NAME           TEXT    NOT NULL, " + 
		                   " AGE            INT     NOT NULL, " + 
		                   " ADDRESS        CHAR(50), " + 
		                   " SALARY         REAL)"; 
		      stmt.executeUpdate(sql);
		      stmt.close();
		      connection.close();
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }
		    System.out.println("Table created successfully");

	}
	
	
	public void populateDB() {
		
		Statement stmt = null;
		try {
			Class.forName(jdbcDriverClass);
			connection = DriverManager.getConnection(jdbcDatabaseUrl);
			connection.setAutoCommit(false);
			System.out.println("Opened database successfully");
		
			stmt = connection.createStatement();
			String sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
					+ "VALUES (1, 'Paul', 32, 'California', 20000.00 );";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
					+ "VALUES (2, 'Allen', 25, 'Texas', 15000.00 );";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
					+ "VALUES (3, 'Teddy', 23, 'Norway', 20000.00 );";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
					+ "VALUES (4, 'Mark', 25, 'Rich-Mond ', 65000.00 );";
			stmt.executeUpdate(sql);

			stmt.close();
			connection.commit();
			connection.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Records created successfully");
	}

	public void showItems() {
		Statement stmt = null;
		try {

			Class.forName(jdbcDriverClass);
			connection = DriverManager.getConnection(jdbcDatabaseUrl);
			connection.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = connection.createStatement();
			String sql = "DELETE from COMPANY where ID=2;";
			stmt.executeUpdate(sql);
			connection.commit();

			ResultSet rs = stmt.executeQuery("SELECT * FROM COMPANY;");
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String address = rs.getString("address");
				float salary = rs.getFloat("salary");
				System.out.println("ID = " + id);
				System.out.println("NAME = " + name);
				System.out.println("AGE = " + age);
				System.out.println("ADDRESS = " + address);
				System.out.println("SALARY = " + salary);
				System.out.println();
			}
			rs.close();
			stmt.close();
			connection.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
	}

}
