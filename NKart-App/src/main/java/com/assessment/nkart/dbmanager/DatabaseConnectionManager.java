package com.assessment.nkart.dbmanager;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnectionManager {

	private static Connection connection = null;

	private DatabaseConnectionManager() {
		// Private constructor
	}

	public static Connection getDatabaseConnection() {
		try {
			if (connection != null && !connection.isClosed()) {
				return connection;
			} else {
				/*
				 * // This will load the MySQL driver, each DB has its own driver
				 * Class.forName("com.mysql.cj.jdbc.Driver"); // Setup the connection with the
				 * DB connection =
				 * DriverManager.getConnection("jdbc:mysql://localhost:3306/nKart?" +
				 * "user=root&password=root"); // Statements allow to issue SQL queries to the
				 * database return connection;
				 */
				String host = System.getenv("DB_HOST");
				String port = System.getenv("DB_PORT");
				String database = "nKart"; // replace with your database name
				String user = System.getenv("MYSQL_USERNAME");
				String password = System.getenv("MYSQL_PASSWORD");

				String connectionUrl = String.format("jdbc:mysql://%s:%s/%s?user=%s&password=%s", host, port, database,
						user, password);
				System.out.println(connectionUrl);
				return DriverManager.getConnection(connectionUrl);

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	// You need to close the resultSet
	public static void closeDatabaseConnection() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
