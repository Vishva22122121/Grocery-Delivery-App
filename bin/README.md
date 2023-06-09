-Imported Packages:
java.util.HashMap: Used for handling key-value pairs in a map.
java.util.Map: Provides a mapping of keys to values.
java.util.Random: Enables generating random numbers.
java.util.Scanner: Allows reading user input.
java.lang.String: Represents a sequence of characters.
java.sql.Connection: Establishes a connection with a database.
java.sql.Date: Represents a specific date (imported from java.sql package).
java.sql.DriverManager: Manages the database drivers and connections.
java.sql.PreparedStatement: Used to execute parameterized SQL queries.
java.sql.ResultSet: Represents a table of data generated by executing a SQL query.
java.sql.SQLException: Handles exceptions related to database operations.
java.sql.Statement: Executes a SQL statement.


-Declared Variables:
Various primitive data types (int, float, String, Date) and strings (A, B, C, a, b, c, d) are declared to store different types of data related to user information, database connection details, and project management.
-public static void main(String[] args): This is the program's entry point that is main method
-Scanner sc= new Scanner(System.in): sc is the name of the Scanner object that is created to read user input from the console.
- System.out.println("Welcome To Online Shopping"): Prints a welcome message to the console.
-System.out.println("Please Select a User Type: "): It displays a prompt asking the user to choose a user type. 


- System.out.println("A. User"): Indicates the user type as "User" and displays option A.
System.out.println("B. Supplier"): Indicates that the user's type is "Supplier" and displays option B.
System.out.println("C. Admin"): Indicates that the user type is "Admin" and displays option C.
The user's input is retrieved and assigned to the Selection variable, which represents the selected user type. Selection = sc.nextLine().


- The switch statement determines the suitable case by looking at the value of the Selection variable.
When "User" is chosen as the user type, Case "A" is put into action.
- It shows a user menu with login and signup choices inside the "User" instance.
- Use choice = Integer.parseInt(sc.nextLine()) to read the user's selection.

- The user is prompted to provide their user ID, username, and password if they select option 1 (login).
- In order to verify the user's credentials, it connects to a MySQL database and runs a SELECT query.
- It displays a success message and then fetches and displays the grocery items menu if the login is successful and the query returns a result.
- Establishes a connection to the MySQL database using the supplied URL, username, and password by calling DriverManager.getConnection("jdbc:mysql://localhost:3306/vp", "root", "root").
- Builds a statement object to run SQL queries with the statement st = conn.createStatement().
- ResultSet rs = st.executeQuery("SELECT * FROM grocery_items"): Runs the SELECT query to get a list of every grocery item and saves the information in the ResultSet object rs.
- A welcome message for the grocery store is printed using system.out.println("Welcome to the Grocery Store! Here is our menu:").
- Prints a line to represent the table heading with the syntax System.out.println("+-------------------------------------------------------------------------------------------------------------------+")
- The column names for the grocery items table are printed using System.out.printf("|%-10s|%-30s |%-10s|%n", "Item ID", "Item Name", and "Price").
- Iterates through each row of the result set while (rs.next()) ....
- Inside the loop:
Retrieves the item ID from the current row of the result set using the string itemID = rs.getString("itemID").
Retrieves the item name from the current row of the result set using the string itemName = rs.getString("itemName") formula.
Retrieves the item price from the current row of the result set using the formula double itemPrice = rs.getDouble("price").
System.out.printf(", itemID, itemName, itemPrice", "|%-10s|%-30s| $%-10f|%n"): Prints the formatted row of the grocery item table.
Prints a line to close the table using the syntax System.out.println("+----------------------------------------------------------------------------------------------------------------------+")
- (System.out.print("Enter User ID: ")): Displays a prompt asking the user to input their user ID .
userId is an integer, int.parseInt(sc.nextLine()) takes a string input from the user and turns it into an integer, saving the result in the userId variable.
Displays a prompt asking the user to enter the number of orders with the syntax System.out.print("Enter the number of orders: ").
numOrders int = an integer.parseInt(sc.nextLine()) takes a string input from the user and turns it into an integer, saving the result in the numOrders variable.
itemIds as a string is new. Initialises an array itemIds with a size equal to the number of orders using the string [numOrders].
new for int[] amounts Initialises an array of quantities with a size equal to the number of orders using the value int[numOrders].
i++ for (int i = 0; i numOrders); ...: Iterates through numOrders times, putting the index value in front of the order.
- Inside the loop:
Retrieves the item ID from the current row of the result set using the string itemID = rs.getString("itemID").
Retrieves the item name from the current row of the result set using the string itemName = rs.getString("itemName") formula.
Retrieves the item price from the current row of the result set using the formula double itemPrice = rs.getDouble("price").
System.out.printf(", itemID, itemName, itemPrice", "|%-10s|%-30s| $%-10f|%n"): Prints the formatted row of the grocery item table.
Prints a line to close the table using the syntax System.out.println("+----------------------------------------------------------------------------------------------------------------------+")
- In next step, numOrders loop variable's orders are iterated over each time.
returns the item ID and amount for the current order from the corresponding arrays of itemIds and quantities.
Use the getItemName and getItemPrice methods to retrieve the item details (such as name and price) from the grocery_items table. The getItemName and getItemPrice methods retrieve the item name and price that are related to the specified item ID, respectively.
- utilises the generateOrderID function to produce a special order ID.
utilises the storeOrder method to store the order in the order table. The user ID, item ID, item name, price, quantity, and order ID are passed as inputs to the storeOrder method, which executes the necessary procedures to store the order in the order database.
- At the end the implementation details of the getItemName, getItemPrice, generateOrderID, and storeOrder which are provided documentation of the same:
-- private static String getItemName(String itemId): This method retrieves the item name associated with the given item ID. It uses a groceryItems map to store item IDs as keys and item names as values. The method fetches the item name from the map using the provided item ID. If the item name is found, it is returned; otherwise, an empty string is returned.
--private static int getItemPrice(String itemId): This method retrieves the item price associated with the given item ID. It is expected that the implementation fetches the item price from the grocery_item database based on the provided item ID. The current implementation initializes the price variable to 0, indicating that the actual database query for fetching the item price is not included in the provided code.
--private static void storeOrder(int userId, String itemId, String itemName, int price, int quantity, int orderID): This method stores the order information in the order database. It takes the user ID, item ID, item name, price, quantity, and order ID as parameters. The current implementation simply prints the provided order information to the console. To store the order in the database, you need to replace the printing statements with your actual database insertion code. The code snippet demonstrates an example of using JDBC to insert the order information into the orders table.
--public static int generateOrderID(): This method generates a random order ID. It uses the Random class to generate a random integer between 0 and 99999. The generated order ID is returned.

- In try-catch method:
It establishes a database connection using the DriverManager.getConnection method, specifying the URL, username, and password for the database.
It creates a Statement object (st5) to execute the SQL query.
The SQL query retrieves the relevant order details by joining the grocery_items and orders tables based on the user ID. It selects the userId, itemID, itemName, price, quantity, and orderID columns.
The executeQuery method is called on the Statement object to execute the SQL query and retrieve the results in a ResultSet object (rs5).
The code then checks if there are any orders (if (rs5.next())). If there are orders, it proceeds to display the order details to the user.
The order details are printed to the console in a formatted table format, showing the user ID, item ID, item name, price, quantity, and order ID for each order. This is achieved using a do-while loop that iterates through the rows of the ResultSet object.
If no orders are found for the user, a message indicating that no orders were found is displayed.
If any SQL exceptions occur during the execution of the code, the exceptions are caught and an error message is printed to the console.

- else part:
It checks if choice is equal to 2 (if (choice == 2)).
If the condition is true, it proceeds with the sign-up process.
The user is prompted to enter their user ID, username, email, password, phone number, and address.
The code establishes a database connection using the DriverManager.getConnection method, specifying the URL, username, and password for the database.
It prepares an SQL INSERT statement to insert the user's details into the customer table. The statement uses parameter placeholders (?) 
The user's details are set as parameters in the prepared statement using the appropriate setter methods (setInt, setString).
The executeUpdate method is called on the PreparedStatement object to execute the SQL statement and insert the user's details into the database. The method returns the number of rows affected.
The code checks if any rows were successfully inserted (if (rowsInserted > 0)). If so, it displays a success message indicating that the user was registered successfully. Otherwise, it displays an error message.
The database connection is closed using the close method.
If any exceptions occur during the execution of the code, they are caught, and the exception message is printed to the console.
If the user selects an invalid choice (neither 1 nor 2), it displays an "Invalid choice" message.
The break statement is used to exit the switch block.


- case(B):
-It displays the heading "Supplier" to provide context for the login process.
It prompts the user to enter their username by printing "Enter Username: ".
It reads the input for the username using the sc.nextLine() method and assigns it to the Username variable.
It prompts the user to enter their password by printing "Enter Password: ".
It reads the input for the password using the sc.nextLine() method and assigns it to the Password variable.
It prompts the user to enter their phone number by printing "Enter Phone number: ".
It reads the input for the phone number using the sc.nextLine() method and assigns it to the Phno variable.
It prompts the user to enter their email by printing "Enter Email: ".
It reads the input for the email using the sc.nextLine() method and assigns it to the Email variable.
- It establishes a database connection to the "vp" database using the DriverManager.getConnection() method.
It constructs a SQL query to select supplier records from the "supplier" table based on the provided username, password, phone number, and email.
It prepares the query using a PreparedStatement object and executes it to retrieve the result set.
If the result set contains a matching record, it indicates a successful login and proceeds with the next steps.
- In next steps, It establishes a database connection to the "vp" database using the DriverManager.getConnection() method.
It creates an SQL query (query1) to retrieve the order table for delivery. The query joins the "orders" table with the "grocery_items" table based on the item ID and filters the orders with a status of 'pending'.
It creates a Statement object and executes the query using the executeQuery() method to obtain a ResultSet.
It iterates over the ResultSet using a while loop and retrieves the relevant columns' values (userId, itemId, itemName, price, quantity, orderId) for each row.
It formats and displays the order table for delivery using the retrieved values.
It closes the ResultSet, Statement, and database connection to free up resources.
It calls the pickAndDeliverOrder() method to handle picking and delivering an order.
If an SQL exception occurs during the execution, it outputs an error message.
If the login process fails (no matching record found in the "supplier" table), it outputs an error message.
It closes the connection to the "vp" database.

-case(C):
- In next steps, it prompts the admin to enter a username and password.
It establishes a connection to a MySQL database using the JDBC driver.
It constructs a SQL query to check if the provided username and password match an entry in the "Gadmin" table.
It executes the query and retrieves the result set.
If the result set has at least one row (indicating a successful login), it displays a menu of options for the admin to choose from.
It reads the admin's choice from the input.
- By switch cases:
It displays admin whether to add a supplier, edit details of supplier, view all suppliers, display all suppliers and displays all the orders, and display total revenue
-- case"a"
In this case
It prompts the admin to enter the supplier's username.
It reads the supplier's username from the input.
It prompts the admin to enter the supplier's password.
It reads the supplier's password from the input.
It prompts the admin to enter the supplier's phone number.
It reads the supplier's phone number from the input.
It prompts the admin to enter the supplier's email.
It reads the supplier's email from the input.
It establishes a connection to the MySQL database using the JDBC driver.
It constructs an SQL query to insert the supplier details into the "supplier" table.
It prepares a statement with the query and sets the corresponding parameter values.
It executes the statement to insert the supplier data into the database.
If the execution is successful, it prints a message indicating that the supplier was added successfully.
It closes the database connection.
If there's an exception during the database operations, it catches the SQLException and prints an error message.

--case"b"
In this case
It prints a prompt asking the admin to enter the supplier's username.
It reads the supplier's username from the input.
It prints a prompt asking the admin to enter the supplier's password.
It reads the supplier's password from the input.
It prints a prompt asking the admin to enter the supplier's phone number.
It reads the supplier's phone number from the input.
It prints a prompt asking the admin to enter the supplier's email.
It reads the supplier's email from the input.
It establishes a connection to the MySQL database using the JDBC driver.
It constructs an SQL query to update the "supplier" table with the new supplier details.
A prepared statement is created with the query.
The supplier details are set as parameters in the prepared statement.
The executeUpdate method is used to execute the update query and modify the supplier's details in the database.
If the update is successful, it prints a message indicating that the supplier was edited successfully.
The database connection is closed.
If there's an exception during the database operations, it catches the SQLException and prints an error message.

--case"c"
In this case
It establishes a connection to the MySQL database using the JDBC driver.
It constructs an SQL query to select all rows from the "supplier" table.
A prepared statement is created with the query.
The executeQuery method is used to execute the query and retrieve the result set.
It prints a formatted header row for displaying the supplier details in a tabular format.
It iterates over each row in the result set using a while loop.
For each row, it retrieves the supplier details (username, password, phone number, email) from the result set.
It prints the supplier details in a formatted row within the table.
After printing all rows, it prints a formatted footer row for the table.
The result set, statement, and database connection are closed.
If there's an exception during the database operations, it catches the SQLException and prints an error message.

--case"d"
In this case
It prints a prompt asking the admin to enter the supplier's username.
It reads the supplier's username from the input.
It prints a prompt asking the admin to enter the supplier's password.
It reads the supplier's password from the input.
It prints a prompt asking the admin to enter the supplier's phone number.
It reads the supplier's phone number from the input.
It prints a prompt asking the admin to enter the supplier's email.
It reads the supplier's email from the input.
It establishes a connection to the MySQL database using the JDBC driver.
It constructs an SQL query to delete the supplier from the "supplier" table based on the provided supplier details.
A prepared statement is created with the query.
The supplier details are set as parameters in the prepared statement.
The execute method is used to execute the delete query and remove the supplier from the database.
The database connection is closed.
If there's an exception during the database operations, it catches the SQLException and prints an error message.

--case"e"
In this case as mentioned above we are displaying order details to admin

--case"f"
In this case
The getConnection method establishes a connection to the MySQL database.
The SQL query uses the SUM function to calculate the total revenue by summing the values in the "price" column of the "order" table.
A prepared statement is created with the query.
The executeQuery method is used to execute the query and retrieve the result set.
If there is a result, the total revenue value is extracted from the result set using the column alias "totalRevenue".
The total revenue is printed to the console.
The database connection is closed.
If there's an exception during the database operations, it catches the SQLException and prints an error message.

















