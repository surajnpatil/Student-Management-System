package in.sp.main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code StudentDao} class provides Data Access Object (DAO) functionality
 * for interacting with the {@code students} table in the database.
 * <p>
 * It encapsulates all SQL operations related to student data, including:
 * <ul>
 *     <li>Inserting new student records</li>
 *     <li>Updating existing student details</li>
 *     <li>Deleting student records</li>
 *     <li>Retrieving and searching student data</li>
 * </ul>
 *
 * This class is used by the {@link UI} layer to abstract database operations
 * and maintain a clean separation of concerns.
 */
public class StudentDao {

    /** Active database connection used for executing SQL statements. */
    private Connection conn;

    /**
     * Constructs a {@code StudentDao} object with the specified database connection.
     *
     * @param conn Active {@link Connection} instance
     */
    public StudentDao(Connection conn) {
        this.conn = conn;
    }

    // ===========================================================
    // =============== INSERT / ADD STUDENT =======================
    // ===========================================================

    /**
     * Inserts a new student record into the {@code students} table.
     *
     * @param s The {@link Student} object containing all student details
     * @throws SQLException if the insertion fails
     */
    public void addStudent(Student s) throws SQLException {
        String sql = "INSERT INTO students(name, roll_no, department, email, phone, sub1, sub2, sub3, percentage, grade) "
                   + "VALUES(?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getName());
            ps.setString(2, s.getRollNo());
            ps.setString(3, s.getDepartment());
            ps.setString(4, s.getEmail());
            ps.setString(5, s.getPhone());
            ps.setDouble(6, s.getSub1());
            ps.setDouble(7, s.getSub2());
            ps.setDouble(8, s.getSub3());
            ps.setDouble(9, s.getPercentage());
            ps.setString(10, s.getGrade());
            ps.executeUpdate();
        }
    }

    // ===========================================================
    // =============== READ / GET ALL STUDENTS ===================
    // ===========================================================

    /**
     * Retrieves all students from the database and returns them as a list.
     *
     * @return A {@link List} of {@link Student} objects representing all records
     * @throws SQLException if the query fails
     */
    public List<Student> getAllStudents() throws SQLException {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Student s = new Student(
                        rs.getString("name"),
                        rs.getString("roll_no"),
                        rs.getString("department"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getDouble("sub1"),
                        rs.getDouble("sub2"),
                        rs.getDouble("sub3"),
                        rs.getDouble("percentage"),
                        rs.getString("grade")
                );
                s.setId(rs.getInt("id"));
                list.add(s);
            }
        }
        return list;
    }

    // ===========================================================
    // =============== UPDATE STUDENT DETAILS ====================
    // ===========================================================

    /**
     * Updates an existing student's details using their roll number as a reference.
     *
     * @param rollNo The roll number of the student to update
     * @param s      The {@link Student} object containing updated details
     * @return {@code true} if the student was successfully updated, {@code false} otherwise
     * @throws SQLException if the update query fails
     */
    public boolean updateStudent(String rollNo, Student s) throws SQLException {
        String sql = "UPDATE students SET name=?, department=?, sub1=?, sub2=?, sub3=?, "
                   + "percentage=?, grade=?, email=?, phone=? WHERE roll_no=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getName());
            ps.setString(2, s.getDepartment());
            ps.setDouble(3, s.getSub1());
            ps.setDouble(4, s.getSub2());
            ps.setDouble(5, s.getSub3());
            ps.setDouble(6, s.getPercentage());
            ps.setString(7, s.getGrade());
            ps.setString(8, s.getEmail());
            ps.setString(9, s.getPhone());
            ps.setString(10, rollNo);
            return ps.executeUpdate() > 0;
        }
    }

    // ===========================================================
    // =============== DELETE STUDENT RECORD =====================
    // ===========================================================

    /**
     * Deletes a student record from the database based on the roll number.
     *
     * @param rollNo The roll number of the student to delete
     * @return {@code true} if the record was deleted, {@code false} if not found
     * @throws SQLException if the deletion fails
     */
    public boolean deleteStudent(String rollNo) throws SQLException {
        String sql = "DELETE FROM students WHERE roll_no=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, rollNo);
            return ps.executeUpdate() > 0;
        }
    }

    // ===========================================================
    // =============== SEARCH STUDENTS ===========================
    // ===========================================================

    /**
     * Searches for a student by their roll number.
     *
     * @param sRollno The student's roll number
     * @return A list of matching {@link Student} objects (typically 0 or 1)
     * @throws SQLException if the query fails
     */
    public List<Student> searchByRollno(int sRollno) throws SQLException {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE roll_no=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, sRollno);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student s = new Student(
                        rs.getString("name"),
                        rs.getString("roll_no"),
                        rs.getString("department"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getDouble("sub1"),
                        rs.getDouble("sub2"),
                        rs.getDouble("sub3"),
                        rs.getDouble("percentage"),
                        rs.getString("grade")
                );
                s.setId(rs.getInt("id"));
                list.add(s);
            }
        }
        return list;
    }

    /**
     * Searches for students by name (partial matches allowed).
     *
     * @param name The name or partial name to search for
     * @return A list of matching {@link Student} objects
     * @throws SQLException if the query fails
     */
    public List<Student> searchByName(String name) throws SQLException {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE name LIKE ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student s = new Student(
                        rs.getString("name"),
                        rs.getString("roll_no"),
                        rs.getString("department"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getDouble("sub1"),
                        rs.getDouble("sub2"),
                        rs.getDouble("sub3"),
                        rs.getDouble("percentage"),
                        rs.getString("grade")
                );
                s.setId(rs.getInt("id"));
                list.add(s);
            }
        }
        return list;
    }

    /**
     * Searches for students belonging to a particular department.
     *
     * @param dept The department name or part of it
     * @return A list of students in that department
     * @throws SQLException if the query fails
     */
    public List<Student> searchByDepartment(String dept) throws SQLException {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE department LIKE ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + dept + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student s = new Student(
                        rs.getString("name"),
                        rs.getString("roll_no"),
                        rs.getString("department"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getDouble("sub1"),
                        rs.getDouble("sub2"),
                        rs.getDouble("sub3"),
                        rs.getDouble("percentage"),
                        rs.getString("grade")
                );
                s.setId(rs.getInt("id"));
                list.add(s);
            }
        }
        return list;
    }

    /**
     * Retrieves all students whose percentage is greater than or equal to the specified value.
     *
     * @param rMarks Minimum percentage threshold
     * @return A list of students meeting the criteria
     * @throws SQLException if the query fails
     */
    public List<Student> searchByMarksRange(double rMarks) throws SQLException {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE percentage >= ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, rMarks);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student s = new Student(
                        rs.getString("name"),
                        rs.getString("roll_no"),
                        rs.getString("department"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getDouble("sub1"),
                        rs.getDouble("sub2"),
                        rs.getDouble("sub3"),
                        rs.getDouble("percentage"),
                        rs.getString("grade")
                );
                s.setId(rs.getInt("id"));
                list.add(s);
            }
        }
        return list;
    }
}
