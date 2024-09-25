package databaseConnector;

import java.sql.*;
public class SignupForm
{
	private static String first_name;
	private static String last_name;
	private static String email;
	private static String password;

	
	public static void setFirstName(String first_name)
	{
		SignupForm.first_name = first_name;
	}
	
	public static void setLastName(String last_name)
	{
		SignupForm.last_name = last_name;
	}
	
	
	public static void setEmail(String email)
	{
		SignupForm.email = email;
	}
	
	
	public static void  setPassword(String password)
	{
		SignupForm.password = password;
	}
	
	
	
	
	public static String getFirstName()
	{
		return first_name;
	}
	
	
	
	
	
	
	public static void saveToDB()
	{
		try {
			
			String statement = "INSERT INTO contacts (first_name, last_name, email, encrypted_password) "
								+ "VALUES (\'"+first_name+"\'"+",\'"+last_name+"\'"+",\'"+email+"\'"+",\'"+password+"\');";
			
			
			// 1. Get a connection to database
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo4jdbc", "newUser", "%AliHasan2012");// I know I am giving away my password. But I am too lazy
			
			// 2. Create a statement
			Statement myStmt = myConn.createStatement();
			
			// 3.Execute SQL query
			myStmt.executeUpdate(statement);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
