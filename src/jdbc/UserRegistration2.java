package jdbc;

import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class UserRegistration2 {
	public static void main(String[] args)throws ClassNotFoundException {
		
		Scanner sc = new Scanner(System.in);
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/samples", "root", "Nashik@15");
			
			Statement st = con.createStatement();
			
			String createTableQuery = "CREATE TABLE PERSON("+"ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "+"Name VARCHAR(50) NOT NULL, "+"Email VARCHAR(50) NOT NULL UNIQUE, "+"Password VARCHAR(50) NOT NULL)";
			
			st.executeUpdate(createTableQuery);
			
			while(true)
			{
				System.out.println("1. Register");
				System.out.println("2. Login");
				System.out.println("3. Exit");
				
				System.out.println("Enter your choice: ");
				int choice = sc.nextInt();
				
				sc.nextLine();
				
				//if new user...
				if(choice==1)
				{
					//Getting user input from keyboard
					System.out.println("Enter the name: ");
					String name = sc.nextLine();
					
					sc.nextLine();
					
					System.out.println("Enter the email: ");
					String email = sc.nextLine();
					
					sc.nextLine();
					
					System.out.println("Enter the password: ");
					String password = sc.nextLine();
					
					try
					{
					
					String sql1 = "INSERT INTO PERSON (Name, Email, Password) VALUES (?, ?, ?)";
					
					PreparedStatement pst = con.prepareStatement(sql1);
					
					pst.setString(1, name);
					pst.setString(2, email);
					pst.setString(3, password);
					
					int rowsInserted = pst.executeUpdate();
					
					if(rowsInserted > 0)
					{
						System.out.println("\nRegistration Successful\n");
					}
					
					else
					{
						System.out.println("\nRegistration Failed\n");
					}
					
					pst.close();
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
				
				else if(choice==2)
				{
					//Getting user input from database
					System.out.println("Enter the email: ");
					String email = sc.nextLine();
					
					sc.nextLine();
					
					System.out.println("Enter the password: ");
					String password = sc.nextLine();
					
					String sql2 = "SELECT * FROM PERSON WHERE Email = ? AND Password = ?";
					
					PreparedStatement pst = con.prepareStatement(sql2);
					
					pst.setString(1, email);
					pst.setString(2, password);
					
					ResultSet rs = pst.executeQuery();
					
					if(rs.next())
					{
						System.out.println("\nLogin Successful! Welcome "+rs.getString("Name"));
					}
					
					else
					{
						System.out.println("\nInvalid email or password\n");
					}
					
					rs.close();
					
                    pst.close();
				}
				
				else if(choice==3)
				{
					break;
				}
				
				else
				{
					System.out.println("\nInvalid choice. Please try again\n");
				}
				
				
			}
			
			st.close();
			
			con.close();
		}
		
		catch(SQLException e)
		{
			System.out.println("Exception: "+e.getMessage());
		}
		
		sc.close();
	}

}
