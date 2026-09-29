package fr.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public abstract class Dao<T> {
	private static final String URL =
	        "jdbc:mariadb://127.0.0.1:3306/formation";

	private static final String USER = "abinet002";
	private static final String PASSWORD = "";
	
	protected Connection getconnection() throws SQLException{
		return DriverManager.getConnection(URL,USER,PASSWORD);
		
	}
	public abstract List<T> getAll();
	public abstract T getById(int id);
	public abstract int create (T t);
	public abstract boolean update (T t);
	public abstract boolean delete(int id);
	
}
