package in.sp.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The {@code LoginService} class handles user authentication for the
 * Student Management System (SMS).
 * <p>
 * It verifies admin credentials by validating the provided username and
 * password against records stored in the {@code admin_users} table in
 * the database.
 * <p>
 * This class is typically invoked during the login process at the start
 * of the application to restrict access to authorized users only.
 *
 * <p><strong>Database Table Expected:</strong> {@code admin_users}
 * <pre>
 * Columns:
 *   - username (VARCHAR)
 *   - password (VARCHAR)
 * </pre>
 */
public class LoginService {

    /** Active database connection used for authentication queries. */
    private Connection conn;

    /**
     * Constructs a {@code LoginService} instance with the specified database connection.
     *
     * @param conn Active {@link Connection} object connected to the database
     */
    public LoginService(Connection conn) {
        this.conn = conn;
    }

    /**
     * Authenticates an admin user by validating the provided username and password.
     *
     * @param username Admin username to verify
     * @param password Admin password to verify
     * @return {@code true} if credentials are valid and user exists in the database,
     *         {@code false} otherwise
     * @throws SQLException if a database access error occurs
     */
    public boolean authenticate(String username, String password) throws SQLException {
        String sql = "SELECT * FROM admin_users WHERE username = ? AND password = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                // If a matching record exists, authentication is successful
                return rs.next();
            }
        }
    }
}
