package com.apex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class DBExample2 {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:8889/company";// mac
	//static final String DB_URL = "jdbc:mysql://localhost:3306/company";// windows
	//jdbc:<dbms name>://<idaddress>:<port number>/<database name>
	static final String USER = "root";
	static final String PASS = "root";
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
			Scanner s = new Scanner(System.in);
			while(true)
			{
				System.out.println("select 1 for select, 2 for insert, 3 for update, 4 for delete");
				int input = s.nextInt();
				switch(input)
				{
					case 1: 
						sql = "SELECT fname, lname, empid, salary FROM employee";//select string
						rs = stmt.executeQuery(sql);
						while(rs.next())//more than one row
						{
							String fn = rs.getString("fname");//database fname => fn 
							String ln = rs.getString("lname");//database lname => ln 
							int id = rs.getInt("empid");//database empid => id 
							int salary = rs.getInt("salary");//database salary => salary 
							System.out.println(fn+" "+ln+" "+id+" "+salary);
						}//end of data
					break;
					case 2: 
						s.nextLine();
						System.out.println("Enter fname");
						String fn = s.nextLine();
						System.out.println("Enter lname");
						String ln = s.nextLine();
						System.out.println("Enter id");
						int id = s.nextInt();
						System.out.println("Enter salary"); 
						int salary = s.nextInt();
						sql = "INSERT INTO employee (fname,lname,empid,salary) VALUES (\'"+fn+"\',\'"+ln+"\',"+id+","+salary+")";
						System.out.println(sql);
						stmt.executeUpdate(sql);
						//executeQuery = select
						//executeUpdate = insert,update
					break;
					case 3:
						sql = "SELECT fname, lname, empid, salary FROM employee";//select string
						rs = stmt.executeQuery(sql);
						while(rs.next())//more than one row
						{
							String fn1 = rs.getString("fname");//database fname => fn 
							String ln1 = rs.getString("lname");//database lname => ln 
							int id1 = rs.getInt("empid");//database empid => id 
							int salary1 = rs.getInt("salary");//database salary => salary 
							System.out.println(fn1+" "+ln1+" "+id1+" "+salary1);
						}
						s.nextLine();
						System.out.println("Enter fname");
						String fn1 = s.nextLine();//apex1
						System.out.println("Enter which fname u want to change");
						String fn2 = s.nextLine();//apex
						sql = "UPDATE employee SET fname =\'"+fn1+"\'WHERE fname =\'"+fn2+"\'";
						System.out.println(sql);
						stmt.executeUpdate(sql);
						sql = "SELECT fname, lname, empid, salary FROM employee";//select string
						rs = stmt.executeQuery(sql);
						while(rs.next())//more than one row
						{
							String fn3 = rs.getString("fname");//database fname => fn 
							String ln3 = rs.getString("lname");//database lname => ln 
							int id3 = rs.getInt("empid");//database empid => id 
							int salary3 = rs.getInt("salary");//database salary => salary 
							System.out.println(fn3+" "+ln3+" "+id3+" "+salary3);
						}
					break;
					case 4:
						sql = "SELECT fname, lname, empid, salary FROM employee";//select string
						rs = stmt.executeQuery(sql);
						while(rs.next())//more than one row
						{
							String fn4 = rs.getString("fname");//database fname => fn 
							String ln4 = rs.getString("lname");//database lname => ln 
							int id4 = rs.getInt("empid");//database empid => id 
							int salary4 = rs.getInt("salary");//database salary => salary 
							System.out.println(fn4+" "+ln4+" "+id4+" "+salary4);
						}
						s.nextLine();
						System.out.println("Enter fname");
						String fn4 = s.nextLine();//apex1
						sql = "DELETE FROM employee WHERE fname =\'"+fn4+"\'";
						System.out.println(sql);
						stmt.executeUpdate(sql);
						sql = "SELECT fname, lname, empid, salary FROM employee";//select string
						rs = stmt.executeQuery(sql);
						while(rs.next())//more than one row
						{
							String fn3 = rs.getString("fname");//database fname => fn 
							String ln3 = rs.getString("lname");//database lname => ln 
							int id3 = rs.getInt("empid");//database empid => id 
							int salary3 = rs.getInt("salary");//database salary => salary 
							System.out.println(fn3+" "+ln3+" "+id3+" "+salary3);
						}
					break;
					default: System.exit(0);
				}
			}
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

