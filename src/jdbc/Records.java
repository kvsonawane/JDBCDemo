package jdbc;

import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class Records {
	public static void main(String[] args)throws ClassNotFoundException{
		
		Scanner sc = new Scanner(System.in);
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/samples", "root", "Nashik@15");
			
			Statement s = con.createStatement();
			
			//CREATE A TABLE
			String createTableQuery = "CREATE TABLE SCHOOL1(" + "ID INT NOT NULL PRIMARY KEY," + "Name VARCHAR(50) NOT NULL," + "Age INT NOT NULL," + "Gender VARCHAR(50) NOT NULL)";
			
			s.executeUpdate(createTableQuery);
			
			while(true)
			{
				System.out.println("Choose an Option: ");
				System.out.println("1. Insert a student record");
				System.out.println("2. Delete a student record");
				System.out.println("3. Update a student record");
				System.out.println("4. Select all student records");
				System.out.println("5. Select a student record based on ID");
				System.out.println("6. Exit");
				int choice = sc.nextInt();
				sc.nextLine();
				
				//INSERT THE DATA using Scanner class
				if(choice==1)
				{
					System.out.println("Enter the number of Students: ");
					int r = sc.nextInt();
					sc.nextLine();
					
					for(int i=0; i<r; i++)
					{
						System.out.println("Enter Student ID: ");
						int id = sc.nextInt();
						
						sc.nextLine();
						
						System.out.println("Enter Student Name: ");
						String name = sc.nextLine();
						
						System.out.println("Enter Student Age: ");
						int age = sc.nextInt();
						
						sc.nextLine();
						
						System.out.println("Enter the Student's Gender: ");
						String gender = sc.nextLine();
						
						String sql1 = "INSERT INTO SCHOOL1 (ID, Name, Age, Gender) VALUES ("+id+",'"+name+"',"+age+",'"+gender+"')";
						int n = s.executeUpdate(sql1);
						
						if(n>0)
						{
							System.out.println("\nADDED");
						}
						
						else
						{
							System.out.println("\nFAILED");
						}
						
						System.out.println();
					}
				}
				
				//DELETE THE DATA using Scanner class
				else if(choice==2)
				{
					System.out.println("Enter the Student ID to delete: "); //because ID is a primary key
					int id = sc.nextInt();
					
					sc.nextLine();
					
					String sql2 = "DELETE FROM SCHOOL1 WHERE ID="+id;
					int n = s.executeUpdate(sql2);
					
					if(n>0)
					{
						System.out.println("\nDELETED");
					}
					
					else
					{
						System.out.println("\nFAILED");
					}
					
					System.out.println();
				}
				
				//UPDATE THE DATA using Scanner class
				else if(choice==3)
				{
					System.out.println("Enter the Student ID to update: "); //it is like where clause with condition
					int id = sc.nextInt();
					
					sc.nextLine();
					
					System.out.println("Enter the new Student name: ");
					String name = sc.nextLine();
						
					System.out.println("Enter the new Student age: ");
					int age = sc.nextInt();
					
					sc.nextLine();
					
					System.out.println("Enter the new Student Gender: ");
					String gender = sc.nextLine();
					
					String sql3 = "UPDATE SCHOOL1 SET Name = '"+name+"', Age = "+age+", Gender = '"+gender+"'WHERE ID = "+id;
					int n = s.executeUpdate(sql3);
					
					if(n>0)
					{
						System.out.println("UPDATED");
					}
					
					else
					{
						System.out.println("FAILED");
					}
					
					System.out.println();
				}
				
				//SELECT THE WHOLE DATA 
				else if(choice==4)
				{
					String sql4 = "SELECT * FROM SCHOOL1";
					ResultSet rs  = s.executeQuery(sql4);
					
					while(rs.next())
					{
						int id = rs.getInt("ID");
						String name = rs.getString("Name");
						int age = rs.getInt("Age");
						String gender = rs.getString("Gender");
						
						System.out.println("ID: "+id+", Name: "+name+", Age: "+age+", Gender: "+gender);
					}
					
					System.out.println();
				}
				
				//SELECT THE DATA BASED ON CONDITION using Scanner class
				else if(choice==5)
				{
					System.out.println("Enter the Student ID to select: ");
					int id = sc.nextInt();
					
					sc.nextLine();
					
					String sql5 = "SELECT * FROM SCHOOL1 WHERE ID = "+id;
					ResultSet rs = s.executeQuery(sql5);
					
					if(rs.next())
					{
						String name = rs.getString("Name");
						int age = rs.getInt("Age");
						String gender = rs.getString("Gender");
						
						System.out.println("ID: "+id+", Name: "+name+", Age: "+age+", Gender: "+gender);
					}
					
					else
					{
						System.out.println("Record not found");
					}
					
					System.out.println();
				}
				
				//TO EXIT
				else if(choice==6)
				{
					break;
				}
				
				else
				{
					System.out.println("Invalid choice. Please try again");
				}
			}
			
			s.close();
			con.close();
		
		}
		
		catch(SQLException e)
		{
			System.out.println(e);
		}
		
		sc.close();
	}

}
