package jdbc;

import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

class DBConnection
{
	static final String URL = "jdbc:mysql://localhost:3306/samples";
	static final String USER = "root";
	static final String PASSWORD = "Nashik@15";
	
	public static Connection getConnection() throws SQLException
	{
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
}

class UserReg
{
	public static void registerUser()
	{
		Scanner sc = new Scanner(System.in);
		
		//Getting user input from keyboard
		System.out.println("Enter the name: ");
		String name = sc.nextLine();
		
		System.out.println("Enter the email: ");
		String email = sc.nextLine();
		
		System.out.println("Enter the password: ");
		String password = sc.nextLine();
		
		try
		{
			Connection con = DBConnection.getConnection();
			
			String sql1 = "INSERT INTO USERS (name, email, password) VALUES (?, ?, ?)";
			
			PreparedStatement pst = con.prepareStatement(sql1);
			
			pst.setString(1, name);
			pst.setString(2, email);
			pst.setString(3, password);
			
			int rowsInserted = pst.executeUpdate();
			
			if(rowsInserted > 0)
			{
				System.out.println("Registration Successful");
			}
			
			else
			{
				System.out.println("Registration Failed");
			}
		}
		
		catch(SQLException e)
		{
			if(e.getErrorCode() == 1062) //error code = 1062 indicates that there is a duplicate email
			{
				System.out.println("Email already exists. Please try a different email");
			}
			
			else
			{
				e.printStackTrace();
			}
		}
	}
}

class UserLogin
{
	public static void loginuser()
	{
		Scanner sc = new Scanner(System.in);
		
		//Getting user input from database
		System.out.println("Enter the email: ");
		String email = sc.nextLine();
		
		System.out.println("Enter the password: ");
		String password = sc.nextLine();
		
		//Check user's credentials in the database
		try
		{
			Connection con = DBConnection.getConnection();
			
			String sql2 = "SELECT * FROM USERS WHERE email = ? AND password = ?";
			
			PreparedStatement pst = con.prepareStatement(sql2);
			
			pst.setString(1, email);
			pst.setString(2, password);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next())
			{
				System.out.println("Login Successful! Welcome "+rs.getString("name"));
			}
			
			else
			{
				System.out.println("Invalid email or password");
			}
		}
		
		catch(SQLException e)
		{
			System.out.println("Exception: "+e.getMessage());
		}
	}
}

public class UserRegistration1 {
	public static void main(String[] args) {
		
		while(true)
		{
		Scanner sc = new Scanner(System.in);
		
		System.out.println("1. Register");
		System.out.println("2. Login");
		System.out.println("3. Exit");
		
		System.out.println("Enter your choice: ");
		int choice = sc.nextInt();
		
		sc.nextLine();
		
		switch(choice)
		{
		case 1:
			UserReg.registerUser();
			
			break;
			
		case 2:
			UserLogin.loginuser();
			
			break;
			
		case 3:
			System.out.println("Existing...");
			
			return;
			
		default:
				System.out.println("Invalid choice. Please try again");
		}
	}

}
}
