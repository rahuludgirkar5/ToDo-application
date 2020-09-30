package com.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;


public class DB_Connect {
	
	private static final String url="jdbc:mysql://localhost:3306/tqproject";
	private static final String user="root";
	private static final String pass="root";
	
	private static Connection con=null;
	public final static Connection getConnect() 
	{
		try
		{
			
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,user,pass);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}

}
