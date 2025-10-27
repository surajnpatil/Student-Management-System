package in.sp.main;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * The Main class serves as the entry point for the Student Management System (SMS).
 * It establishes a database connection and initializes the user interface (UI)
 * to handle all CRUD operations(insert ,update ,delete ,search,statistics) .
 */

public class Main {
	
	   /**
     * The main method initializes the database connection and launches the application UI.
     *
     * @param args Command-line arguments (not used)
     */
	
    public static void main(String[] args) {
    	// Try-with-resources automatically closes the database connection after use
        try (Connection conn = DatabaseConnection.getConnection()) {
        	
        	 // Initialize the User Interface with an active database connection
            UI ui = new UI(conn); 
            
            // Start the user interaction loop (menu-driven operations)
            ui.start();           
        } 
        catch (SQLException e) 
        {
        	  // Handles database connection failures or SQL-related issues
            System.err.println("Database connection failed: " + e.getMessage());
        }
    }
}
