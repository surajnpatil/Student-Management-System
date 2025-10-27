package in.sp.main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The {@code AdminService} class provides administrative and analytical
 * operations for the Student Management System (SMS).
 * <p>
 * It allows the administrator to generate useful statistics from the
 * {@code students} table, such as:
 * <ul>
 *     <li>Total number of students</li>
 *     <li>Highest percentage scored</li>
 *     <li>Lowest percentage scored</li>
 * </ul>
 * <p>
 * This class is mainly used by the {@link UI} layer when the user selects
 * the "Statistics" option in the main menu.
 */
public class AdminService {

    /** Active database connection used to execute SQL queries. */
    private Connection conn;

    /**
     * Constructs an {@code AdminService} instance with the specified database connection.
     *
     * @param conn Active {@link Connection} object
     */
    public AdminService(Connection conn) {
        this.conn = conn;
    }

    // ===========================================================
    // =============== TOTAL STUDENTS COUNT ======================
    // ===========================================================

    /**
     * Retrieves the total number of students currently stored in the database.
     *
     * @return Total number of student records
     * @throws SQLException if a database access error occurs
     */
    public int getTotalStudents() throws SQLException {
        String sql = "SELECT COUNT(*) AS total FROM students";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next())
                return rs.getInt("total");
        }
        return 0;
    }

    // ===========================================================
    // =============== HIGHEST PERCENTAGE RETRIEVAL ==============
    // ===========================================================

    /**
     * Retrieves the highest percentage obtained among all students.
     *
     * @return The highest percentage value, or {@code 0} if no records exist
     * @throws SQLException if a database access error occurs
     */
    public double getHighestMarks() throws SQLException {
        String sql = "SELECT MAX(percentage) AS max_marks FROM students";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            if (rs.next())
                return rs.getDouble("max_marks");
        }
        return 0;
    }

    // ===========================================================
    // =============== LOWEST PERCENTAGE RETRIEVAL ===============
    // ===========================================================

    /**
     * Retrieves the lowest percentage obtained among all students.
     *
     * @return The lowest percentage value, or {@code 0} if no records exist
     * @throws SQLException if a database access error occurs
     */
    public double getLowestMarks() throws SQLException {
        String sql = "SELECT MIN(percentage) AS min_marks FROM students";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            if (rs.next())
                return rs.getDouble("min_marks");
        }
        return 0;
    }
}
