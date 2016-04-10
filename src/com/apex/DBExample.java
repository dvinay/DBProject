package com.apex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class DBExample {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:8889/company";// mac
	//static final String DB_URL = "jdbc:mysql://localhost:3306/company";// windows
	//jdbc:<dbms name>://<idaddress>:<port number>/<database name>
	static final String USER = "root";
	static final String PASS = "root";//mac
	//static final String PASS = ""; // windows
	public static void main(String[] args) 
	{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			Class.forName(JDBC_DRIVER); // loading drivers
			
			System.out.println("Connecting to database...");
			conn=DriverManager.getConnection(DB_URL,USER,PASS);//connecting to my database
			System.out.println("Creating statement...");
			stmt = conn.createStatement();//create a statement
			String sql;
			sql = "SELECT fname, lname, empid, salary FROM employee";//select string
			rs = stmt.executeQuery(sql);
			//executeQuery = select
			//executeUpdate = insert,update
			while(rs.next())//more than one row
			{
				String fn = rs.getString("fname");//database fname => fn 
				String ln = rs.getString("lname");//database lname => ln 
				int id = rs.getInt("empid");//database empid => id 
				int salary = rs.getInt("salary");//database salary => salary 
				System.out.println(fn+" "+ln+" "+id+" "+salary);
			}//end of data
			rs.close();
			stmt.close();
			conn.close();
		}
		catch(Exception e)
		{
			System.out.println("Database connection problem:"+e);
		}
		finally
		{
			try{
			if(rs!=null)
				rs.close();
			if(stmt!=null)
				stmt.close();
			if(conn!=null)
				conn.close();
			}
			catch(Exception e)
			{
				System.out.println("Result set closing fail:"+e);
			}
		}
	}
}

