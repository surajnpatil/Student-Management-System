package in.sp.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * The {@code UI} class provides a command-line interface for managing
 * student records in the Student Management System (SMS).
 * <p>
 * It acts as the main interactive layer, allowing users to:
 * <ul>
 *     <li>Authenticate with login credentials</li>
 *     <li>Add, update, delete, and view students</li>
 *     <li>Search students by various filters</li>
 *     <li>View statistical summaries</li>
 * </ul>
 * 
 * This class interacts with:
 * <ul>
 *     <li>{@link StudentDao} for database CRUD operations</li>
 *     <li>{@link AdminService} for statistics and summaries</li>
 *     <li>{@link LoginService} for authentication</li>
 * </ul>
 */
public class UI {

    private Connection conn;
    private StudentDao studentDao;
    private AdminService adminService;
    private LoginService loginService;
    private Scanner sc;

    /**
     * Constructs the UI object and initializes all service dependencies.
     *
     * @param conn Active {@link Connection} to the database
     */
    public UI(Connection conn) {
        this.conn = conn;
        this.studentDao = new StudentDao(conn);
        this.adminService = new AdminService(conn);
        this.loginService = new LoginService(conn);
        this.sc = new Scanner(System.in);
    }

    /**
     * Starts the main user interaction loop after successful login.
     * Displays a menu-driven interface for performing various actions.
     *
     * @throws SQLException if any database operation fails
     */
    public void start() throws SQLException {

        // --- Login Authentication ---
        System.out.print("Enter username: ");
        String user = sc.nextLine();

        System.out.print("Enter password: ");
        String pass = sc.nextLine();

        // Authenticate user credentials
        if (!loginService.authenticate(user, pass)) {
            System.out.println("Invalid credentials! Exiting.");
            return;
        }

        System.out.println("Login successful! Welcome: " + user + ".");

        // --- Main Menu Loop ---
        int choice;
        do {
            System.out.println("\n--- SmartStudent Menu ---");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Search Student");
            System.out.println("6. Statistics");
            System.out.println("0. Exit");
            System.out.print("Choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            try {
                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        viewStudents();
                        break;
                    case 3:
                        updateStudent();
                        break;
                    case 4:
                        deleteStudent();
                        break;
                    case 5:
                        searchStudent();
                        break;
                    case 6:
                        showStatistics();
                        break;
                    case 0:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice!");
                        break;
                }
            } catch (SQLException e) {
                System.err.println("Database error: " + e.getMessage());
            }

        } while (choice != 0);
    }

    // ===================== CRUD OPERATIONS =====================

    /**
     * Prompts the user for student details and adds a new record to the database.
     *
     * @throws SQLException if insertion fails
     */
    private void addStudent() throws SQLException {
        System.out.print("Enter Roll No: ");
        String roll = sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Department: ");
        String dept = sc.nextLine();

        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        System.out.print("Enter Phone: ");
        String phone = sc.nextLine();

        System.out.print("Enter Sub1 Marks: ");
        double sub1 = sc.nextDouble();

        System.out.print("Enter Sub2 Marks: ");
        double sub2 = sc.nextDouble();

        System.out.print("Enter Sub3 Marks: ");
        double sub3 = sc.nextDouble();
        sc.nextLine(); // clear buffer

        // Calculate percentage and grade
        double percentage = calculateTotalMarks(sub1, sub2, sub3);
        percentage = Math.round(percentage * 100.0) / 100.0;
        String grade = calculateGrade(percentage);

        // Insert student record
        studentDao.addStudent(new Student(name, roll, dept, email, phone, sub1, sub2, sub3, percentage, grade));
        System.out.println("Student added successfully!");
    }

    /**
     * Retrieves and displays all student records from the database.
     *
     * @throws SQLException if retrieval fails
     */
    private void viewStudents() throws SQLException {
        List<Student> students = studentDao.getAllStudents();
        printStudentList(students);
    }

    /**
     * Updates an existing student’s information based on roll number.
     *
     * @throws SQLException if update fails
     */
    private void updateStudent() throws SQLException {
        System.out.print("Roll No to update: ");
        String uRoll = sc.nextLine();

        System.out.print("Enter new Name: ");
        String uName = sc.nextLine();

        System.out.print("Enter new Department: ");
        String uDept = sc.nextLine();

        System.out.print("Enter new Email: ");
        String uEmail = sc.nextLine();

        System.out.print("Enter new Phone: ");
        String uPhone = sc.nextLine();

        System.out.print("Enter Sub1 Marks: ");
        double uSub1 = sc.nextDouble();

        System.out.print("Enter Sub2 Marks: ");
        double uSub2 = sc.nextDouble();

        System.out.print("Enter Sub3 Marks: ");
        double uSub3 = sc.nextDouble();
        sc.nextLine();

        // Recalculate marks and grade
        double uPercentage = calculateTotalMarks(uSub1, uSub2, uSub3);
        String uGrade = calculateGrade(uPercentage);

        boolean updated = studentDao.updateStudent(
                uRoll,
                new Student(uName, uRoll, uDept, uEmail, uPhone, uSub1, uSub2, uSub3, uPercentage, uGrade)
        );

        System.out.println(updated ? "Student updated successfully!" : "Student not found!");
    }

    /**
     * Deletes a student record based on the roll number provided by the user.
     *
     * @throws SQLException if deletion fails
     */
    private void deleteStudent() throws SQLException {
        System.out.print("Roll No to delete: ");
        String dRoll = sc.nextLine();
        boolean deleted = studentDao.deleteStudent(dRoll);
        System.out.println(deleted ? "Student deleted successfully!" : "Student not found!");
    }

    /**
     * Allows searching students by roll number, name, department, or marks range.
     *
     * @throws SQLException if search fails
     */
    private void searchStudent() throws SQLException {
        System.out.println("Search by: 1.Rollno 2.Name 3.Dept 4.Marks Range");
        int sChoice = sc.nextInt();
        sc.nextLine();
        List<Student> result = null;

        switch (sChoice) {
            case 1:
                System.out.print("Enter Rollno: ");
                int sRollno = sc.nextInt();
                sc.nextLine();
                result = studentDao.searchByRollno(sRollno);
                break;
            case 2:
                System.out.print("Enter Name: ");
                String sName = sc.nextLine();
                result = studentDao.searchByName(sName);
                break;
            case 3:
                System.out.print("Enter Department: ");
                String sDept = sc.nextLine();
                result = studentDao.searchByDepartment(sDept);
                break;
            case 4:
                System.out.print("Enter Marks Threshold: ");
                double rMarks = sc.nextDouble();
                sc.nextLine();
                result = studentDao.searchByMarksRange(rMarks);
                break;
            default:
                System.out.println("Invalid search option!");
                return;
        }

        printStudentList(result);
    }

    /**
     * Displays system statistics such as total students, highest, and lowest marks.
     *
     * @throws SQLException if retrieval fails
     */
    private void showStatistics() throws SQLException {
        System.out.println("Total Students: " + adminService.getTotalStudents());
        System.out.println("Highest Marks: " + adminService.getHighestMarks());
        System.out.println("Lowest Marks: " + adminService.getLowestMarks());
    }

    // ===================== UTILITY METHODS =====================

    /**
     * Calculates the percentage of marks based on three subjects.
     *
     * @param s1 Subject 1 marks
     * @param s2 Subject 2 marks
     * @param s3 Subject 3 marks
     * @return Average marks as a percentage
     */
    private static double calculateTotalMarks(double s1, double s2, double s3) {
        return (s1 + s2 + s3) / 3.0;
    }

    /**
     * Determines the grade based on the given percentage.
     *
     * @param total Calculated percentage
     * @return Corresponding grade (A–F)
     */
    private static String calculateGrade(double total) {
        if (total >= 90) return "A";
        if (total >= 75) return "B";
        if (total >= 60) return "C";
        if (total >= 45) return "D";
        return "F";
    }

    /**
     * Prints a formatted table of student details.
     *
     * @param students List of {@link Student} objects to display
     */
    private static void printStudentList(List<Student> students) {
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("RollNo | Name | Dept | Sub1 | Sub2 | Sub3 | Percentage(%) | Grade | Email | Phone |");
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        for (Student s : students) {
            System.out.println(
                    s.getRollNo() + " | " +
                            s.getName() + " | " +
                            s.getDepartment() + " | " +
                            s.getSub1() + " | " +
                            s.getSub2() + " | " +
                            s.getSub3() + " | " +
                            String.format("%.2f", s.getPercentage()) + " | " +
                            s.getGrade() + " | " +
                            s.getEmail() + " | " +
                            s.getPhone()
            );
        }

        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }
}
