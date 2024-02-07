package com.lufecrx.sistema.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataAcessObject {

	private static final String URL = "jdbc:mysql://uk6db6rbmmnpj0pj:OKir2CawY3CuaK9OvStK@bi7b7b3bcrn0k5kxhowt-mysql.services.clever-cloud.com:3306/bi7b7b3bcrn0k5kxhowt";
	private static final String USER = "uk6db6rbmmnpj0pj";
	private static final String PASSWD = "OKir2CawY3CuaK9OvStK";

	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWD);
		} catch (SQLException e) {
			System.out.println("Error while connecting!");
		}
		return connection;
	}

	public static void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println("Error while closing connection!");
		}
	}	
}