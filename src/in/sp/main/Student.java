package in.sp.main;

/**
 * The {@code Student} class represents a student entity in the
 * Student Management System (SMS).
 * <p>
 * It stores student details such as personal information, academic scores,
 * calculated percentage, and grade. This class acts as a data model (POJO)
 * for transferring student information between different layers of the application,
 * such as DAO and UI.
 *
 * <p><strong>Database Table Expected:</strong> {@code students}
 * <pre>
 * Columns:
 *   - id (INT, PRIMARY KEY, AUTO_INCREMENT)
 *   - name (VARCHAR)
 *   - roll_no (VARCHAR)
 *   - department (VARCHAR)
 *   - email (VARCHAR)
 *   - phone (VARCHAR)
 *   - sub1 (DOUBLE)
 *   - sub2 (DOUBLE)
 *   - sub3 (DOUBLE)
 *   - percentage (DOUBLE)
 *   - grade (VARCHAR)
 * </pre>
 */
public class Student {

    /** Unique identifier for the student (primary key). */
    private int id;

    /** Full name of the student. */
    private String name;

    /** Roll number uniquely identifying the student. */
    private String rollNo;

    /** Department or branch to which the student belongs. */
    private String department;

    /** Email address of the student. */
    private String email;

    /** Contact phone number of the student. */
    private String phone;

    /** Marks obtained in Subject 1. */
    private double sub1;

    /** Marks obtained in Subject 2. */
    private double sub2;

    /** Marks obtained in Subject 3. */
    private double sub3;

    /** Average percentage calculated from subject marks. */
    private double percentage;

    /** Grade assigned to the student based on percentage. */
    private String grade;

    /**
     * Default constructor for creating an empty {@code Student} object.
     * <p>
     * Typically used when data will be populated later through setters.
     */
    public Student() {
    }

    /**
     * Parameterized constructor for initializing a {@code Student} object
     * with full details.
     *
     * @param name        Name of the student
     * @param rollNo      Roll number of the student
     * @param department  Name of the Department  
     * @param email       Student's email address
     * @param phone       Student's phone number
     * @param sub1        Marks obtained in Subject 1
     * @param sub2        Marks obtained in Subject 2
     * @param sub3        Marks obtained in Subject 3
     * @param percentage  Calculated average percentage
     * @param grade       Academic grade based on percentage
     */
    public Student(String name, String rollNo, String department, String email, String phone,
                   double sub1, double sub2, double sub3, double percentage, String grade) {
        this.name = name;
        this.rollNo = rollNo;
        this.department = department;
        this.email = email;
        this.phone = phone;
        this.sub1 = sub1;
        this.sub2 = sub2;
        this.sub3 = sub3;
        this.percentage = percentage;
        this.grade = grade;
    }

    // ───────────────────────────────
    // Getters and Setters
    // ───────────────────────────────

    /** @return Student ID */
    public int getId() {
        return id;
    }

    /** @param id Sets the student's unique ID */
    public void setId(int id) {
        this.id = id;
    }

    /** @return Student's full name */
    public String getName() {
        return name;
    }

    /** @param name Sets the student's name */
    public void setName(String name) {
        this.name = name;
    }

    /** @return Student's roll number */
    public String getRollNo() {
        return rollNo;
    }

    /** @param rollNo Sets the student's roll number */
    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    /** @return Department name */
    public String getDepartment() {
        return department;
    }

    /** @param department Sets the student's department */
    public void setDepartment(String department) {
        this.department = department;
    }

    /** @return Student's email address */
    public String getEmail() {
        return email;
    }

    /** @param email Sets the student's email address */
    public void setEmail(String email) {
        this.email = email;
    }

    /** @return Student's phone number */
    public String getPhone() {
        return phone;
    }

    /** @param phone Sets the student's phone number */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /** @return Marks in Subject 1 */
    public double getSub1() {
        return sub1;
    }

    /** @param sub1 Sets marks in Subject 1 */
    public void setSub1(double sub1) {
        this.sub1 = sub1;
    }

    /** @return Marks in Subject 2 */
    public double getSub2() {
        return sub2;
    }

    /** @param sub2 Sets marks in Subject 2 */
    public void setSub2(double sub2) {
        this.sub2 = sub2;
    }

    /** @return Marks in Subject 3 */
    public double getSub3() {
        return sub3;
    }

    /** @param sub3 Sets marks in Subject 3 */
    public void setSub3(double sub3) {
        this.sub3 = sub3;
    }

    /** @return Average percentage */
    public double getPercentage() {
        return percentage;
    }

    /** @param percentage Sets the student's percentage */
    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    /** @return Student's academic grade */
    public String getGrade() {
        return grade;
    }

    /** @param grade Sets the student's grade */
    public void setGrade(String grade) {
        this.grade = grade;
    }
}
