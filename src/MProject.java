
import java.util.Scanner;
import java.lang.String;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MProject {
    static int choice, ID, Phone;
    static String Selection, Email, Username, AddressLine1, AddressLine2, Password, url, username, password, cusername, cpassword;
    static String A, B, C;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome To Online Shopping");
        System.out.println("Please Select a User Type: ");
        System.out.println("A. User ");
        System.out.println("B. Supplier ");
        System.out.println("C. Admin");
        Selection = sc.nextLine();
        
        switch (Selection) {
            case "A":
                System.out.println("----------User: ----------");
                System.out.println("1. Login");
                System.out.println("2. Sign Up");
                choice = Integer.parseInt(sc.nextLine());
                if (choice == 1) {
                    System.out.println("**Login**");
                    System.out.println("Enter Username : ");
                    Username = sc.nextLine();
                    System.out.println("Enter Password: ");
                    Password = sc.nextLine();
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vp", "root", "root");
                        String query = "SELECT * FROM customer WHERE cusername = '" + Username + "' AND cpassword = '" + Password + "'";
                        PreparedStatement statement = con.prepareStatement(query);
                        ResultSet result = statement.executeQuery();
            
                        if (result.next()) {
                            System.out.println("Login successful!");
                        } else {
                            System.out.println("Username or password is incorrect. Please sign up.");
                        }
            
                        con.close();
            
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                } else if (choice == 2) {
                    System.out.println("**SignUp**");
                    System.out.println("Enter ID: ");
                    ID = Integer.parseInt(sc.nextLine());
                    System.out.println("Enter Username: ");
                    Username = sc.nextLine();
                    System.out.println("Enter Email: ");
                    Email = sc.nextLine();
                    System.out.println("Enter Password: ");
                    Password = sc.nextLine();
                    System.out.println("Enter Phone Number: ");
                    Phone = sc.nextInt();
                    System.out.println("Enter AddressLine1: ");
                    AddressLine1 = sc.nextLine();
                    System.out.println("Enter AddressLine2: ");
                    AddressLine2 = sc.nextLine();
                } else {
                    System.out.println("Invalid choice. Please select 1 or 2.");
                }
                break;
            case "B":
                System.out.println("---------Supplier----------");
                System.out.println("**Login**");
                System.out.println("Enter Username: ");
                Username = sc.nextLine();
                System.out.println("Enter Password: ");
                Password = sc.nextLine();
                try {
                    Connection conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/vp", "root", "root");
                    System.out.println("Database connection established.");
                    String query2 = "SELECT * FROM supplier";
                    PreparedStatement stmt = conn2.prepareStatement(query2);
                    ResultSet result2 = stmt.executeQuery();
                    if (result2.next()) {
                        System.out.println("Login successful!");
                    } else {
                        System.out.println("Enter correct Username or Password");
                    }
        
                    conn2.close();
                } catch (SQLException e) {
                    System.err.println("Error: " + e.getMessage());
                }
                break;
            case "C":
                System.out.println("---------Admin----------");
                System.out.println("**Login**");
                System.out.println("Enter Username: ");
                Username = sc.nextLine();
                System.out.println("Enter Password: ");
                Password = (sc.nextLine());
                //Authenticate Admin
                url = "jdbc:mysql://localhost:3306/vp";
                username = "root";
                password = "root";
                // Colleect data from the database for Suppliers
                
                break;
            default:
                System.out.println("Invalid choice. Please select A, B, or C");
        }


    }

}
