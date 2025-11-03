package in.sp.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The {@code DatabaseConnection} class manages the creation of JDBC connections
 * to the MySQL database used by the Student Management System (SMS).
 * <p>
 * It centralizes database connection details such as URL, username, and password,
 * making the application easier to configure and maintain.
 * <p>
 * Example usage:
 * <pre>
 *     try (Connection conn = DatabaseConnection.getConnection()) {
 *         System.out.println("Connected to database successfully!");
 *     } catch (SQLException e) {
 *         e.printStackTrace();
 *     }
 * </pre>
 */
public class DatabaseConnection {

    /** JDBC URL of the MySQL database (includes timezone parameter). */
    private static final String URL = "jdbc:mysql://localhost:3306/studentdb?serverTimezone=UTC";

    /** Username for database authentication. */
    private static final String USER = "root";

    /** Password for database authentication. */
    private static final String PASSWORD = "root";

    /**
     * Establishes and returns a new connection to the configured MySQL database.
     *
     * @return A live {@link Connection} object to the student database
     * @throws SQLException if a database access error occurs or connection fails
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
