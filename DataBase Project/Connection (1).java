import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.sql.*;

public class Connection {
   static java.sql.Connection con = null;
   static Statement stmt = null;
   static PreparedStatement ps = null;
   static ResultSet rs = null;
   static Scanner input = new Scanner(System.in);
   static String currentAttribute = "";

   public static void main(String[] args) {
	  
      try {
         String jdbcUrl = "jdbc:mysql://localhost:3306/UniSchedular";
         String username = "root";
         String password = "";
      
            // Establish the database connection
         con = DriverManager.getConnection(jdbcUrl, username, password);
         stmt = con.createStatement();
      
         boolean alwaysTrue = true;
         
      
         do {
            System.out.println("Room Table");
            System.out.println("1) Insert new record");
            System.out.println("2) Display all Records");
            System.out.println("3) Exit");
            System.out.println("Choose an operation:");
            int queryChoice = input.nextInt();
         
            switch (queryChoice) {
               case 1: // Insert new record
                  try {
                     boolean isTrue = true;
                     while (isTrue) {
                                // Insert new record
                        System.out.println("Please Enter the Room information: ");
                     
                                // 1
                        System.out.println("Enter the Room Number: ");
                        currentAttribute = "RoomNo";
                        int roomNo = input.nextInt();
                        
                        if(roomNo == 0 )  {
                        	System.err.println("Invalid Input, the  Room Number should be positive");
                        	break; } 
                     
                                // 2
                        System.out.println("Enter the Building Number: ");
                        currentAttribute = "BuildingNo";
                        int buildingNo = input.nextInt();
                        
                        if( buildingNo == 0 )  {
                        	System.err.println("Invalid Input, the Building Number should be positive");
                        	break; } 
                     
                                // 3
                        System.out.println("Enter the Room Capacity: ");
                        currentAttribute = "Capacity";
                        int capacity = input.nextInt();
                     
                                // 4
                        System.out.println("Enter the Room Type (C for Classroom or L for Lab): ");
                        currentAttribute = "RoomType";
                        String roomType = input.next();
                        
                        if (!roomType.equalsIgnoreCase("C") && !roomType.equalsIgnoreCase("L")) {
                            throw new IllegalArgumentException("Invalid room type! Please enter 'C' for Classroom or 'L' for Lab.");
                        }
                     
                                // 5
                        System.out.println("Enter the Floor Number: ");
                        currentAttribute = "FloorNo";
                        int floorNo = input.nextInt();
                        
                     
                     
                                // Inserting the data to the database
                        stmt.executeUpdate("INSERT INTO Room VALUES(" + roomNo + "," + buildingNo + ","
                                        + capacity + ",'" + roomType + "'," + floorNo + ")");
                     
                        System.out.println("New record has been inserted into The Room Table successfully");
                     
                                // Asking the user if they want to add more records
                        System.out.println("Do you want to insert a new record? (Y | N)");
                        char newRecord = input.next().charAt(0);
                        newRecord = Character.toUpperCase(newRecord);
                     
                        boolean wrongChar = true;
                     
                                // Verify the input
                        if (!(newRecord == 'N' || newRecord == 'Y')) {
                           while (wrongChar) {
                              System.out.println("Invalid character! Please use only (Y | N).");
                              newRecord = input.next().charAt(0);
                              newRecord = Character.toUpperCase(newRecord);
                              if (newRecord == 'N' || newRecord == 'Y')
                                 wrongChar = false;
                           }
                        }
                     
                        if (newRecord == 'N')
                           isTrue = false;
                     }
                  } catch (SQLException sqle) {
                     if (sqle.getErrorCode() == 1062)
                        System.err.println("Primary key constraint violation! both Room Number and Building Number cannot be duplicated");
                     else if (sqle.getErrorCode() == 1452)
                        System.err.println("Foreign key constraint violation! The entered building number does not exist");
                     else if (sqle.getErrorCode() == 1048)
                        System.err.println("Not NULL constraint violation! All values cannot be NULL");
                     else if (sqle.getErrorCode() == 1265 || sqle.getErrorCode() == 1366)
                        System.err.println("Domain constraint violation!");
                     else {
                        System.err.println(sqle.getErrorCode());
                        System.err.println(sqle.getMessage());
                     }
                  }
               
                  break;
            
               case 2: // Display all records
                   	
                  java.sql.Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
               
                  Statement statement = connection.createStatement();
               
                  String sql = "SELECT * FROM room";
               
                  ResultSet resultSet = statement.executeQuery(sql);
                           
                  int rowNumber = 1; 
               
                  while (resultSet.next()) {
                     int buildingNo = resultSet.getInt("BuildingNO");
                     int roomNo = resultSet.getInt("RoomNo");
                     int floorNumber = resultSet.getInt("FloorNo");
                     String roomType = resultSet.getString("Roomtype");
                     int roomCapacity = resultSet.getInt("Capacity");
                     System.out.print(  rowNumber + ") ");
                     System.out.print("Building No: " + buildingNo + " ");
                     System.out.print("Room No: " + roomNo + " ");
                     System.out.print("Floor Number: " + floorNumber + " ");
                     System.out.print("Room Type: " + roomType + " ");
                     System.out.print("Room Capacity: " + roomCapacity + " ");
                               
                     System.out.println();
                  
                     rowNumber =  rowNumber + 1; 
                  
                              
                  }
               
                  resultSet.close();
                  statement.close();
                  connection.close();
                  System.out.println();
                  break;
            
               case 3: // Exit
                  alwaysTrue = false;
                  break;
                               
               default:
                  System.out.println("Invalid choice! Please select a valid option."); 
                  break;
            }
                           
         } while (alwaysTrue);
                           
         try {
                               // Closing the database connection
            if (rs != null)
               rs.close();
            if (stmt != null)
               stmt.close();
            if (con != null)
               con.close();
         } catch (SQLException e) {
            e.printStackTrace();
         } finally {
                               // Close the database connection
            if (con != null) {
               try {
                  con.close();
                  System.out.println("Database connection closed.");
               } catch (SQLException e) {
                  e.printStackTrace();
               }
            }
            input.close(); }
         }
                           
      catch (SQLException sqle) {
         if (sqle.getErrorCode() == 1062) {
            System.err.println("Primary key constraint violation! A record with the same BuildingNo and RoomNo already exists.");
         } else if (sqle.getErrorCode() == 1452) {
            System.err.println("Foreign key constraint violation! Either TechnicianEmpID or MediaProvidedCode references a non-existing record.");
         } else if (sqle.getErrorCode() == 1048) {
            System.err.println("Not NULL constraint violation! One or more required attributes are missing.");
         } else if (sqle.getErrorCode() == 1265 || sqle.getErrorCode() == 1366) {
            System.err.println("Domain constraint violation! Invalid data type or value provided for an attribute.");
         } else {
            System.err.println(sqle.getMessage());
         }
      }
      catch (InputMismatchException ex) {
         switch (currentAttribute) {
            case "BuildingNo":
               System.err.println("Domain constraint violation! Building number must be an integer value.");
               break;
            case "RoomNo":
               System.err.println("Domain constraint violation! Room number must be an integer value.");
               break;
            case "FloorNo":
               System.err.println("Domain constraint violation! Floor number must be an integer value.");
               break;
            case "RoomType":
               System.err.println("Domain constraint violation! Room type must be a string.");
               break;
            case "Capacity":
               System.err.println("Domain constraint violation! Capacity must be an integer value.");
               break;
            default:
               System.err.println("Invalid input for attribute: " + currentAttribute);
               break;
         }
         input.next();
      } catch (IllegalArgumentException ex) {
          System.err.println(ex.getMessage());
       } catch (Exception e) {
         System.err.print(e.getMessage());
      }
   
   }}
