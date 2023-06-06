package com.amazon.Classifieds.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
	
	public static String FILEPATH = "D:\\Joseph\\ATLAS\\OOAD\\MY CODE\\Eclipse\\Classifieds\\DBConfig.txt";
	public static String URL = "";
	
	Connection connection;
	Statement statement;
	
	private static DB db = new DB();
	
	public static DB getInstance() {
		return db;
	}
	
	private DB() {
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("[DB] Driver loaded successfully..");
			
			createConnection();
			
		} catch (Exception e) {
			System.err.println("Something went wrong "+e);
		}
	}
	
	private void createConnection() {
		
		try {
			File file = new File(FILEPATH);
			if(file.exists()) {
				FileReader reader = new FileReader(file);
				BufferedReader buffer = new BufferedReader(reader);
				
				URL = buffer.readLine();
				//USER = buffer.readLine();
				//PASSWORD = buffer.readLine();
				
				buffer.close();
				reader.close();
				
				System.out.println("[DB] Configured using File "+file.getName());
			}else {
				System.err.println("[DB] Cannot Read the DB Config File...");
			}
			connection = DriverManager.getConnection(URL);
			System.out.println("[DB] Connection Created Successfully....");
			
		} catch (SQLException e) {
			System.err.println("Something Went Wrong "+e);
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int executeSQL(String sql) {
		
		int result = 0;
		try {
			System.out.println("Executing sql statement "+sql);
			
			statement = connection.createStatement();
			result = statement.executeUpdate(sql);
			System.out.println("[DB] sql statement executed successfully");
		} catch (SQLException e) {
			System.err.println("Something Went Wrong "+e);
		}
		
		return result;
	}
	
	public ResultSet executeQuery(String sql) {
		
		ResultSet set = null;
		
		try {
			System.out.println("Executing sql statement "+sql);
			
			statement = connection.createStatement();
			set = statement.executeQuery(sql);
			System.out.println("[DB] sql statement executed successfully");
		}catch(SQLException e) {
			System.err.println("Something went wrong "+e);
		}
		
		return set;
	}
	
	public void closeConnection() {
		
		try {
			connection.close();
			System.out.println("[DB] connection closed successfully");
		}catch(SQLException e) {
			System.err.println("Something went wrong "+e);
		}
	}
}
