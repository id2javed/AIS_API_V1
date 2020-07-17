package com.app.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.app.dbconfig.DBConfiguration;

/*
* Developer    : Pallavi Dhage
* Created Date : 2019-07-25
* Updated Date : 
* Description  : Open and Close Database Connection class
*  */

public class DBConnection {
	public static Connection getMainConnection() {
		
		Connection con =null;
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(DBConfiguration.PROJECT_DATABASE, DBConfiguration.PROJECT_USER_NAME,
					DBConfiguration.PROJECT_PASSWORD);
		}catch (Exception e) {
			System.err.println("Connection Estalish Failed");
		}
		return con;
		
	}
	
	public static Connection getCloseConnection(Connection con) {
		try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		return con;
		
	}

	public static Connection gethistoryDataConnection() {
		Connection con =null;
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(DBConfiguration.HISTORY_DATABASE, DBConfiguration.HISTORY_USER_NAME,
					DBConfiguration.HISTORY_PASSWORD);
		}catch (Exception e) {
			System.err.println("Connection Estalish Failed");
		}
		return con;
		
	}
	

}
