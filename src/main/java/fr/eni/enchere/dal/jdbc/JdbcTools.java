package fr.eni.enchere.dal.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcTools {

	static String url;
	static String user;
	static String password;
	static String driver;

	static {
		System.out.println("JdbcTools");
		url = Settings.getValeur("url");
		user = Settings.getValeur("user");
		password = Settings.getValeur("password");
		driver = Settings.getValeur("driver");
	}

	public static Connection getConnection() throws SQLException {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return DriverManager.getConnection(url, user, password);
	}
}
