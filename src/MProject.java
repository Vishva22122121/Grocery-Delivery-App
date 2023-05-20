
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.lang.String;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MProject {
    static int choice, ID, Phone;
    static String Selection, Email, Username, AddressLine1, AddressLine2, Password, url, username, password, cusername, categories,cpassword, adm, Phno,orderedItems, itemID, itemName, totalCost, email, cphno, address;
    static int userId;
    static float quantity;
    static int price;
    static String A, B, C, a, b, c, d,e;
    static int orderID;
    static Date currentDate;
   
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
                    System.out.println("Enter User ID");
                    userId = Integer.parseInt(sc.nextLine());
                    System.out.println("Enter Username : ");
                    Username = sc.nextLine();
                    System.out.println("Enter Password: ");
                    Password = sc.nextLine();
                    
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vp", "root", "root");
                        String query = "SELECT * FROM customer WHERE userId='" + userId + "' AND cusername = '" + Username + "' AND cpassword = '" + Password + "'";
                        PreparedStatement statement = con.prepareStatement(query);
                        ResultSet result = statement.executeQuery();
            
                        if (result.next()) {
                            System.out.println("Login successful!");
                            System.out.println("+-----------------------------------+");
                            System.out.println("|                Menu               |");
                            System.out.println("+-----------------------------------+");
                            try {
                                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vp", "root", "root");
                                Statement st = conn.createStatement();
                                ResultSet rs = st.executeQuery("SELECT * FROM grocery_items");
                    
                                System.out.println("Welcome to the Grocery Store! Here is our menu:");
                                System.out.println("+--------------------------------------------------------+");
                                System.out.printf("| %-10s| %-30s | %-10s| %n", "Item ID", "Item Name", "Price");
                                System.out.println("+--------------------------------------------------------+");
                                while (rs.next()) {
                                    String itemID = rs.getString("itemID");
                                    String itemName = rs.getString("itemName");
                                    double itemPrice = rs.getDouble("price");
                                    System.out.printf("| %-10s| %-30s| $%-10f| %n", itemID, itemName, itemPrice);
                                }
                                System.out.println("+--------------------------------------------------------+");
                                
                                // Prompt the user to enter their user ID and the number of orders they want to place
                                System.out.print("Enter User ID: ");
                                int userId = Integer.parseInt(sc.nextLine());
                                System.out.print("Enter the number of orders: ");
                                int numOrders = Integer.parseInt(sc.nextLine());

                                // Initialize arrays to store item IDs and quantities
                                String[] itemIds = new String[numOrders];
                                int[] quantities = new int[numOrders];

                                // Get item IDs and quantities for each order from input
                                for (int i = 0; i < numOrders; i++) {
                                    System.out.print("Enter Item ID for Order " + (i + 1) + ": ");
                                    itemIds[i] = sc.nextLine();
                                    System.out.print("Enter Quantity for Order " + (i + 1) + ": ");
                                    quantities[i] = Integer.parseInt(sc.nextLine());
                                }

                                // Process each order and store them in the order table
                                for (int i = 0; i < numOrders; i++) {
                                    String itemId = itemIds[i];
                                    int quantity = quantities[i];

                                    // Fetch item details from the grocery_items table
                                    String itemName = getItemName(itemId);
                                    int price = getItemPrice(itemId);

                                    // Calculate the total cost
                                    //double totalCost = calculateTotalCost( price, quantity);

                                    // Generate a unique order ID
                                    int orderId = generateOrderID();

                                    // Store the order in the order table
                                    storeOrder(userId, itemId, itemName, price, quantity, orderId);
                                }

                                // Display the order details to the user
                                //String[][] orderTable = getOrderTable(userId);
                                try {
                                    Connection conn5 = DriverManager.getConnection("jdbc:mysql://localhost:3306/vp", "root", "root");
                                    Statement st5 = conn5.createStatement();
                                    ResultSet rs5 = st5.executeQuery("SELECT orders.userId, grocery_items.itemID, grocery_items.itemName, " +
                                                                    "grocery_items.price, orders.quantity, orders.orderID " +
                                                                    "FROM grocery_items " +
                                                                    "INNER JOIN orders " +
                                                                    "ON grocery_items.itemID = orders.itemID " +
                                                                    "WHERE orders.userId = " + userId);
                                
                                    // Display the order details to the user
                                    if (rs5.next()) {
                                        System.out.println("\nOrder Details:");
                                        System.out.println("+-------------------------------------------------------------+");
                                        System.out.println("| User ID | Item ID | Item Name | Price | Quantity | Order ID |");
                                        System.out.println("+-------------------------------------------------------------+");
                                        do {
                                            String orderUserId = rs5.getString("userId");
                                            String itemId = rs5.getString("itemID");
                                            String itemName = rs5.getString("itemName");
                                            double price = rs5.getDouble("price");
                                            int quantity = rs5.getInt("quantity");
                                            int orderId = rs5.getInt("orderID");
                                            System.out.format("| %-7s |%-9s | %-9s | $%-5.2f | %-8d | %-7d |%n", orderUserId, itemId, itemName, price, quantity, orderId);
                                        } while (rs5.next());
                                        System.out.println("--------------------------------------------------------------+");
                                    } else {
                                        System.out.println("No orders found for user " + userId);
                                    }
                                } catch (SQLException e) {
                                    System.out.println("Error: " + e.getMessage());
                                }
                                
                            } catch (SQLException e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        }else {
                            System.out.println("Username or password is incorrect. Please sign up.");
                        }
                    } catch (ClassNotFoundException | SQLException e) {
                        System.out.println("Error: " + e.getMessage());
                    }        
                                
                }  else if (choice == 2) {
                    System.out.println("**SignUp**");
                    System.out.println("Enter User ID: ");
                    userId = Integer.parseInt(sc.nextLine());
                    System.out.println("Enter Username: ");
                    cusername = sc.nextLine();
                    System.out.println("Enter Email: ");
                    email = sc.nextLine();
                    System.out.println("Enter Password: ");
                    cpassword = sc.nextLine();
                    System.out.println("Enter Phone Number: ");
                    cphno = sc.nextLine();
                    System.out.println("Enter Address: ");
                    address = sc.nextLine();
                
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vp", "root", "root");
                        String query = "INSERT INTO customer (userId, cusername, cpassword, email, address, cphno) VALUES (?, ?, ?, ?, ?, ?)";
                        PreparedStatement statement = con.prepareStatement(query);
                        statement.setInt(1, userId);
                        statement.setString(2, cusername);
                        statement.setString(3, cpassword);
                        statement.setString(4, email);
                        statement.setString(5, address);
                        statement.setString(6, cphno);
                        int rowsInserted = statement.executeUpdate();
                        if (rowsInserted > 0) {
                            System.out.println("User registered successfully!");
                        } else {
                            System.out.println("Error registering user.");
                        }
                        con.close();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
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
                System.out.println("Enter Phone number: ");
                Phno =sc.nextLine();
                System.out.println("Enter Email: ");
                Email= sc.nextLine();
                try {
                    Connection conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/vp", "root", "root");
                    String query = "SELECT * FROM supplier WHERE susername = '" + Username + "' AND spassword = '" + Password + "' AND sphno = '" + Phno + "' AND semail ='"+ Email +"'";
                    PreparedStatement stmt = conn2.prepareStatement(query);
                    ResultSet result2 = stmt.executeQuery();
                    if (result2.next()) {
                        System.out.println("Login successful!");
                        try {
                            // Establish database connection
                            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vp", "root", "root");
                
                            // Create SQL query to retrieve order table for delivery
                            String query1 = "SELECT orders.userId, orders.itemID, grocery_items.itemName, grocery_items.price, " +
                                           "orders.quantity, orders.orderID " +
                                           "FROM orders INNER JOIN grocery_items " +
                                           "ON orders.itemID = grocery_items.itemID " +
                                           "WHERE orders.status = 'pending'";
                
                            // Create a Statement object to execute the query
                            Statement statement = connection.createStatement();
                
                            // Execute the query and get the ResultSet
                            ResultSet resultSet = statement.executeQuery(query1);
                
                            // Display the order table for delivery
                            System.out.println("Order Table for Delivery:");
                            System.out.println("+-----------------------------------------------------------+");
                            System.out.println("| User ID | Item ID | Item Name | Price | Quantity | Order ID |");
                            System.out.println("+-----------------------------------------------------------+");
                            while (resultSet.next()) {
                                int userId = resultSet.getInt("userId");
                                String itemId = resultSet.getString("itemID");
                                String itemName = resultSet.getString("itemName");
                                double price = resultSet.getDouble("price");
                                int quantity = resultSet.getInt("quantity");
                                int orderId = resultSet.getInt("orderID");
                                System.out.format("| %-8d | %-7s | %-9s | $%-5.2f | %-8d | %-8d |%n", userId, itemId, itemName, price, quantity, orderId);
                            }
                            System.out.println("+-----------------------------------------------------------+");
                
                            // Close the ResultSet, Statement, and database connection
                            resultSet.close();
                            statement.close();
                            connection.close();

                            // Pick and deliver an order
                            pickAndDeliverOrder(orderID);
                        } catch (SQLException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        
                    } else {
                        System.out.println("Enter correct Username or Password");
                    }
        
                    conn2.close();
                } catch (SQLException e) {
                    System.out.println("Error: " + e.getMessage());
                }
        
                break;
            case "C":
                System.out.println("---------Admin----------");
                System.out.println("**Login**");
                System.out.println("Enter Username: ");
                Username = sc.nextLine();
                System.out.println("Enter Password: ");
                Password = (sc.nextLine());

                try{
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vp","root", "root");
                    String query = "SELECT * FROM Gadmin WHERE ausername = '" + Username + "' AND apassword = '" + Password + "'";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    ResultSet result = stmt.executeQuery();
                    if (result.next()) {
                        System.out.println("Login successful!");
                        System.out.println("a. Add a Supplier ");
                        System.out.println("b. Edit details of Supplier ");
                        System.out.println("c. View all Supplier");
                        System.out.println("d. Delete a Supplier");
                        System.out.println("e. Display the order");
                        adm = sc.nextLine();
                        switch(adm){
                            case "a":
                                System.out.println("Enter Supplier's Username:");
                                String susername = sc.nextLine();
                                System.out.println("Enter Supplier's Password: ");
                                String spassword = sc.nextLine();
                                System.out.println("Enter Supplier's Phone number");
                                String sphno = sc.nextLine();
                                System.out.println("Enter Supplier's Email");
                                String semail = sc.nextLine();
                                    try{
                                        Connection sconn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/vp","root", "root");
                                        String squery1 = "INSERT INTO supplier VALUES (?, ?, ?, ?)" ;
                                        PreparedStatement sstmt2 = sconn1.prepareStatement(squery1);
                                        sstmt2.setString(1, susername);
                                        sstmt2.setString(2, spassword);
                                        sstmt2.setString(3, sphno);
                                        sstmt2.setString(4, semail);

                                        sstmt2.execute();
                                        System.out.println("Supplier added successfully");
                                        sconn1.close();
                                    }catch (SQLException e) {
                                        System.out.println("Error: " + e.getMessage());
                                    }
                                break;    
                            case "b":
                                System.out.println("Enter Supplier's Username:");
                                susername = sc.nextLine();
                                System.out.println("Enter Supplier's Password: ");
                                spassword = sc.nextLine();
                                System.out.println("Enter Supplier's Phone number");
                                sphno = sc.nextLine();
                                System.out.println("Enter Supplier's Email");
                                semail = sc.nextLine();
                                    try {
                                        Connection sconn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/vp","root", "root");
                                        String squery2 = "UPDATE supplier WHERE (?, ?, ?, ?)";
                                        PreparedStatement sstmt2 = sconn2.prepareStatement(squery2);
                                        sstmt2.setString(1, susername);
                                        sstmt2.setString(2, spassword);
                                        sstmt2.setString(3, sphno);
                                        sstmt2.setString(4, semail);
                                        sstmt2.executeUpdate();
                                        System.out.println("Supplier edited successfully");

                                        sconn2.close();
                                        sconn2.close();
                                    } catch (SQLException e) {
                                        System.out.println("Error: " + e.getMessage());
                                    }
                                break;
                            case "c":
                                try {
                                    Connection sconn3 = DriverManager.getConnection("jdbc:mysql://localhost:3306/vp","root", "root");
                                    String squery3 = "SELECT * FROM supplier";
                                    PreparedStatement sstmt3 = sconn3.prepareStatement(squery3);
                                    ResultSet sresult3 = sstmt3.executeQuery();
                                    System.out.println("+---------------------------------------------------------------------------------+");
                                    System.out.printf("| %-15s | %-15s | %-15s | %-25s |\n", "Username", "Password", "Phone Number", "Email");
                                    System.out.println("+---------------------------------------------------------------------------------+");
                                    while (sresult3.next()){
                                        susername = sresult3.getString("susername");
                                        spassword = sresult3.getString("spassword");
                                        sphno = sresult3.getString("sphno");
                                        semail =sresult3.getString("semail");
                                        System.out.printf("| %-15s | %-15s | %-15s | %-25s |\n", susername, spassword, sphno, semail);

                                    
                                    }
                                    System.out.println("+---------------------------------------------------------------------------------+");

                                    sresult3.close();
                                    sstmt3.close();
                                    sconn3.close();
                                }catch (SQLException e) {
                                        System.out.println("Error: " + e.getMessage());
                                }
                                break;
                            case "d":
                                System.out.println("Enter Supplier's Username:");
                                susername = sc.nextLine();
                                System.out.println("Enter Supplier's Password: ");
                                spassword = sc.nextLine();
                                System.out.println("Enter Supplier's Phone number");
                                sphno = sc.nextLine();
                                System.out.println("Enter Supplier's Email");
                                semail = sc.nextLine();
                                try {
                                        Connection sconn4 = DriverManager.getConnection("jdbc:mysql://localhost:3306/vp","root", "root");
                                        String squery4 = "DELETE FROM supplier WHERE (?, ?, ?, ?)";
                                        PreparedStatement sstmt4 = sconn4.prepareStatement(squery4);
                                        sstmt4.setString(1, susername);
                                        sstmt4.setString(2, spassword);
                                        sstmt4.setString(3, sphno);
                                        sstmt4.setString(4, semail);
                                        sstmt4.execute();
                                        
                                        sconn4.close();
                                } catch (SQLException e) {
                                        System.out.println("Error: " + e.getMessage());
                                }
                                break;
                            case "e":
                            try {
                                // Establish database connection
                                Connection connection6 = DriverManager.getConnection("jdbc:mysql://localhost:3306/vp", "root", "root");
                    
                                // Create SQL query to retrieve order table for delivery
                                String query6 = "SELECT orders.userId, orders.itemID, grocery_items.itemName, grocery_items.price, " +
                                               "orders.quantity, orders.orderID " +
                                               "FROM orders INNER JOIN grocery_items " +
                                               "ON orders.itemID = grocery_items.itemID " +
                                               "WHERE orders.status = 'pending'";
                    
                                // Create a Statement object to execute the query
                                Statement statement = connection6.createStatement();
                    
                                // Execute the query and get the ResultSet
                                ResultSet resultSet = statement.executeQuery(query6);
                    
                                // Display the order table for delivery
                                System.out.println("Order Table for Delivery:");
                                System.out.println("+-----------------------------------------------------------+");
                                System.out.println("| User ID | Item ID | Item Name | Price | Quantity | Order ID |");
                                System.out.println("+-----------------------------------------------------------+");
                                while (resultSet.next()) {
                                    int userId = resultSet.getInt("userId");
                                    String itemId = resultSet.getString("itemID");
                                    String itemName = resultSet.getString("itemName");
                                    double price = resultSet.getDouble("price");
                                    int quantity = resultSet.getInt("quantity");
                                    int orderId = resultSet.getInt("orderID");
                                    System.out.format("| %-8d | %-7s | %-9s | $%-5.2f | %-8d | %-8d |%n", userId, itemId, itemName, price, quantity, orderId);
                                }
                                System.out.println("+-----------------------------------------------------------+");
                    
                                // Close the database connection
                                connection6.close();
                            } catch (SQLException e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                            default:
                                System.out.println("");
                        }


                    } else {
                        System.out.println("Enter correct Username or Password");
                    }
                    
                } catch (SQLException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            break;

            default:
            System.out.println("Invalid choice. Please select A, B, or C");
        }
    }

    public static String[][] getOrderTable(int userId) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String[][] orderTable = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vp", "root", "root");
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("SELECT * FROM orders WHERE userId = "+userId);
    
            // Initialize the order table array with the correct number of rows
            rs.last();
            orderTable = new String[rs.getRow()][7];
            rs = stmt.executeQuery("SELECT * FROM orders WHERE userId = "+userId);
    
            // Iterate over the result set and populate the order table array
            int i = 0;
            while (rs.next()) {
                orderTable[i][0] = rs.getString("userId");
                orderTable[i][1] = rs.getString("itemId");
                orderTable[i][2] = rs.getString("itemName");
                orderTable[i][3] = String.format("%.2f", rs.getDouble("price"));
                orderTable[i][4] = Integer.toString(rs.getInt("quantity"));
                orderTable[i][5] = Integer.toString(rs.getInt("orderID"));
                i++;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("Working");

        return orderTable;
    }
    
    
    private static String getItemName(String itemId) {
        Map<String, String> groceryItems = new HashMap<>();
        // Populate the groceryItems map with item ids and item names
    
        String itemName = groceryItems.get(itemId); 
        return itemName != null ? itemName : ""; // return empty string if item name not found
    }
    

    private static int getItemPrice(String itemId) {
        // Your code to fetch item price from the grocery_item database based on the item ID
        // Replace this with your actual database query
        int price = 0;
        // ...
        return price;
    }
    
    private static void storeOrder(int userId, String itemId, String itemName, int price, int quantity,  int orderID) {
        // Your code to store the order in the order database
        // Replace this with your actual database insertion code
        System.out.println(userId);
        System.out.println(itemId);
        System.out.println(itemName);
        System.out.println(price);
        System.out.println(quantity);
        System.out.println(orderID);

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vp","root", "root");
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO orders (userId, itemID, itemName, price, quantity, orderID) VALUES (" +
                    userId + ", '" + itemId + "', '" + itemName + "', '" + price + "', '" + quantity + "', '" + orderID + "')";
            stmt.executeUpdate(sql);
            conn.close();
            System.out.println("Order stored successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int generateOrderID() {
        Random random = new Random();
        return random.nextInt(100000); // Generate a random integer between 0 and 99999
    }

    //public static double calculateTotalCost(double price, int quantity) {
        //double totalCost = price * quantity;
        //return totalCost;
   // }
    
   public static void pickAndDeliverOrder(int orderID2) {
    try {
        // Establish database connection
        Connection connection7 = DriverManager.getConnection("jdbc:mysql://localhost:3306/vp", "root", "root");

        // Update the order status to 'delivered' for the selected order
        String updateQuery = "UPDATE orders SET status = 'delivered' WHERE orderID = ?";
        PreparedStatement updateStatement = connection7.prepareStatement(updateQuery);
        updateStatement.setInt(1, orderID2);
        int rowsUpdated = updateStatement.executeUpdate();

        if (rowsUpdated > 0) {
            System.out.println("Order with ID " + orderID2 + " has been delivered successfully.");
        } else {
            System.out.println("Failed to deliver the order with ID " + orderID2 + ".");
        }

        // Close the PreparedStatement and database connection
        updateStatement.close();
        connection7.close();
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }
}