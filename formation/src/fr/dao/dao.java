package fr.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dao {
	private static final String URL =
	        "jdbc:mariadb://127.0.0.1:3306/formation";

	private static final String USER = "abinet002";
	private static final String PASSWORD = "";
	
	protected Connection getconnection() throws SQLException{
		return DriverManager.getConnection(URL,USER,PASSWORD);
		
	}
}
