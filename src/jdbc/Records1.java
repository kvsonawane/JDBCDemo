package jdbc;

import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;


public class Records1 {
	public static void main(String[] args)throws ClassNotFoundException {
		
		Scanner sc = new Scanner(System.in);
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/samples", "root", "Nashik@15");
			
			Statement st = con.createStatement();
			
			//CREATE THE TABLE
			String createTableQuery = "CREATE TABLE ANIME("+"Sr_No INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "+"Anime_Name VARCHAR(50) NOT NULL, "+"Director VARCHAR(50) NOT NULL, "+"Author VARCHAR(50) NOT NULL, "+"Genre VARCHAR(32), "+"Imdb_Rating INT NOT NULL)";
			
			st.executeUpdate(createTableQuery);
			
			while(true)
			{
				System.out.println("Choose an option: ");
				System.out.println("A. Insert an Anime Record");
				System.out.println("B. Delete an Anime Record");
				System.out.println("C. Update an Anime Record");
				System.out.println("D. Select all Anime Records");
				System.out.println("E. Select an Anime Record based on condition: \na.Based on Sr_No \nb.Based on Anime_Name \nc.Based on Director \nd.Based on Author \ne.Based on Genre \nf.Based on Rating");
				System.out.println("F. Exit");
				char choice = sc.next().charAt(0);
				
				sc.nextLine();
				
				//INSERT THE DATA using Scanner class
				if(choice=='A')
				{
					System.out.println("Enter the number of Anime Records you want to insert: ");
					int r = sc.nextInt();
					
					sc.nextLine();
					
					for(int i=0; i<r; i++)
					{
						System.out.println("Enter the Sr_No: ");
						int sr_no = sc.nextInt();
						
						sc.nextLine();
						
						System.out.println("Enter the Anime Name: ");
						String anime = sc.nextLine();
						
						sc.nextLine();
						
						System.out.println("Enter the Director's Name: ");
						String director = sc.nextLine();
						
						sc.nextLine();
						
						System.out.println("Enter the Author's Name: ");
						String author = sc.nextLine();
						
						sc.nextLine();
						
						System.out.println("Enter the Genre of Anime: ");
						String genre = sc.nextLine();
						
						sc.nextLine();
						
						System.out.println("Enter the Imdb Rating: ");
						int rating = sc.nextInt();
						
						String sql1 = "INSERT INTO ANIME (Sr_No, Anime_Name, Director, Author, Genre, Imdb_Rating) VALUES (" 
					              + sr_no + ", '" 
					              + anime + "', '" 
					              + director + "', '" 
					              + author + "', '" 
					              + genre + "', " 
					              + rating + ")";

						int n = st.executeUpdate(sql1);
						
						if(n>0)
						{
							System.out.println("\nADDED");
						}
						
						else
						{
							System.out.println("\nFAILED");
						}
					}
				}
				
				
				//DELETE THE DATA/RECORD using Scanner class
				else if(choice=='B')
				{
					//it is like a WHERE clause where we put the condition
					System.out.println("Enter the Sr_No to Delete an Anime Record: ");
					int sr_no = sc.nextInt();
					
					sc.nextLine();
					
					String sql2 = "DELETE FROM ANIME WHERE Sr_No = "+sr_no;
					int n = st.executeUpdate(sql2);
					
					if(n>0)
					{
						System.out.println("\nDELETED");
					}
					
					else
					{
						System.out.println("\nFAILED");
					}
				}
				
				//UPDATE THE DATA/RECORD using Scanner class
				else if(choice=='C')
				{
					System.out.println("Enter the Sr_No to Update an Anime Record: ");
					int sr_no = sc.nextInt();
					
					sc.nextLine();
					
					System.out.println("Enter the new Anime Name: ");
					String anime = sc.nextLine();
					
					sc.nextLine();
					
					System.out.println("Enter the new Director's Name: ");
					String director = sc.nextLine();
					
					sc.nextLine();
					
					System.out.println("Enter the new Author's Name: ");
					String author = sc.nextLine();
					
					sc.nextLine();
					
					System.out.println("Enter the Genre of new Anime: ");
					String genre = sc.nextLine();
					
					System.out.println("Enter the Imdb Rating of new Anime: ");
					int rating = sc.nextInt();
					
					String sql3 = "UPDATE ANIME SET Anime_Name = '"+anime+"', Director = '"+director+"', Author = '"+author+"', Genre = '"+genre+"', Imdb_Rating = "+rating+" WHERE Sr_No = "+sr_no;
					int n = st.executeUpdate(sql3);
					
					if(n>0)
					{
						System.out.println("\nUPDATED");
					}
					
					else
					{
						System.out.println("\nFAILED");
					}
				}
				
				else if(choice=='D')
				{
					String sql4 = "SELECT * FROM ANIME";
					ResultSet rs = st.executeQuery(sql4);
					
					while(rs.next())
					{
						int sr_no = rs.getInt("Sr_No");
						String anime = rs.getString("Anime_Name");
						String director = rs.getString("Director");
						String author = rs.getString("Author");
						String genre = rs.getString("Genre");
						int rating = rs.getInt("Imdb_Rating");
						
						System.out.println("Sr_No: "+sr_no+", Anime_Name: "+anime+", Director: "+director+", Author: "+author+", Genre: "+genre+", Imdb_Rating: "+rating);
					}
				}
				
				else if(choice=='E')
				{
					if(choice=='a')
					{
						System.out.println("Enter the Sr_No to select the record: ");
						int sr_no = sc.nextInt();
						
						sc.nextLine();
						
						String sql5 = "SELECT * FROM ANIME WHERE Sr_No = " + sr_no;
						ResultSet rs = st.executeQuery(sql5);
						
						if(rs.next())
						{
							String anime = rs.getString("Anime_Name");
							String director = rs.getString("Director");
							String author = rs.getString("Author");
							String genre = rs.getString("Genre");
							int rating = rs.getInt("Imdb_Rating");
							
							System.out.println("Sr_No: "+sr_no+", Anime_Name: "+anime+", Director: "+director+", Author: "+author+", Genre: "+genre+", Imdb_Rating: "+rating);
						}
						
						else
						{
							System.out.println("Record not Found");
						}
					}
					
					else if(choice=='b')
					{
						System.out.println("Enter the Anime Name to select the record: ");
						String anime = sc.nextLine();
							
						String sql6 = "SELECT * FROM ANIME WHERE Anime_Name = '" + anime + "'";
						ResultSet rs = st.executeQuery(sql6);
						
						if(rs.next())
						{
							int sr_no = rs.getInt("Sr_No");
							String director = rs.getString("Director");
							String author = rs.getString("Author");
							String genre = rs.getString("Genre");
							int rating = rs.getInt("Imdb_Rating");
							
							System.out.println("Sr_No: "+sr_no+", Anime_Name: "+anime+", Director: "+director+", Author: "+author+", Genre: "+genre+", Imdb_Rating: "+rating);
						}
						
						else
						{
							System.out.println("Record not Found");
						}
					}
					
					else if(choice=='c')
					{
						System.out.println("Enter the Director's name to select the record: ");
						String director = sc.nextLine();
						
						String sql7 = "SELECT * FROM ANIME WHERE Director = '" + director + "'";
						ResultSet rs = st.executeQuery(sql7);
						
						if(rs.next())
						{
							int sr_no = rs.getInt("Sr_No");
							String anime = rs.getString("Anime_Name");
							String author = rs.getString("Author");
							String genre = rs.getString("Genre");
							int rating = rs.getInt("Imdb_Rating");
							
							System.out.println("Sr_No: "+sr_no+", Anime_Name: "+anime+", Director: "+director+", Author: "+author+", Genre: "+genre+", Imdb_Rating: "+rating);
						}
						
						else
						{
							System.out.println("Record not Found");
						}
					}
					
					else if(choice=='d')
					{
						System.out.println("Enter the Author's name to select the record: ");
						String author = sc.nextLine();
						
						String sql8 = "SELECT * FROM ANIME WHERE Author = '" + author + "'";
						ResultSet rs = st.executeQuery(sql8);
						
						if(rs.next())
						{
							int sr_no = rs.getInt("Sr_No");
							String anime = rs.getString("Anime_Name");
							String director = rs.getString("Director");
							String genre = rs.getString("Genre");
							int rating = rs.getInt("Imdb_Rating");
							
							System.out.println("Sr_No: "+sr_no+", Anime_Name: "+anime+", Director: "+director+", Author: "+author+", Genre: "+genre+", Imdb_Rating: "+rating);
						}
						
						else
						{
							System.out.println("Record not Found");
						}
					}
					
					else if(choice=='e')
					{
						System.out.println("Enter the Genre to select the record: ");
						String genre = sc.nextLine();
						
						String sql8 = "SELECT * FROM ANIME WHERE Genre = '" + genre + "'";
						ResultSet rs = st.executeQuery(sql8);
						
						if(rs.next())
						{
							int sr_no = rs.getInt("Sr_No");
							String anime = rs.getString("Anime_Name");
							String director = rs.getString("Director");
							String author = rs.getString("Author");
							int rating = rs.getInt("Imdb_Rating");
							
							System.out.println("Sr_No: "+sr_no+", Anime_Name: "+anime+", Director: "+director+", Author: "+author+", Genre: "+genre+", Imdb_Rating: "+rating);
						}
						
						else
						{
							System.out.println("Record not Found");
						}
					}
					
					else if(choice=='f')
					{
						System.out.println("Enter the Rating to select the record: ");
						int rating = sc.nextInt();
						
						sc.nextLine();
						
						String sql9 = "SELECT * FROM ANIME WHERE Imdb_Rating = " + rating;
						ResultSet rs = st.executeQuery(sql9);
						
						if(rs.next())
						{
							int sr_no = rs.getInt("Sr_No");
							String anime = rs.getString("Anime_Name");
							String director = rs.getString("Director");
							String author = rs.getString("Author");
							String genre = rs.getString("Genre");
							
							System.out.println("Sr_No: "+sr_no+", Anime_Name: "+anime+", Director: "+director+", Author: "+author+", Genre: "+genre+", Imdb_Rating: "+rating);
						}
						
						else
						{
							System.out.println("Record not Found");
						}
					}
					
					else
					{
						System.out.println("Invalid choice. Please try again");
					}
				}
				
				else if(choice=='F')
				{
					break;
				}
				
				else
				{
					System.out.println("Invalid choice. Please try again");
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
