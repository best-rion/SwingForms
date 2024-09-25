package databaseConnector;

import java.sql.*;

public class UserInfo
{
	private static String first_name;
	private static String last_name;
	private static String email;
	private static String password;
	
	public static void RetrieveInfoFromDB(String userValue)
	{
		try {
			// 1. Get a connection to database
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo4jdbc", "newUser", "%AliHasan2012");
			
			// 2. Create a statement
			Statement myStmt = myConn.createStatement();
			
			// 3.Execute SQL query
			ResultSet myRs = myStmt.executeQuery("select * from contacts where email = "+"\'"+userValue+"\';");
			
			while(myRs.next()) {			
				first_name = myRs.getString("first_name");
				last_name = myRs.getString("last_name");
				email = myRs.getString("email");
				password = myRs.getString("encrypted_password");
				
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static String getFirstName()
	{
		return first_name;
	}
	
	public static String getLastName()
	{
		return last_name;
	}
	
	public static String getEmail()
	{
		return email;
	}
	
	public static String getPassword()
	{
		return password;
	}

}
