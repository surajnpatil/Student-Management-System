# 🎓 Student Management System (Java + MySQL)

A simple **console-based Student Management System** built using **Java JDBC** and **MySQL**.  
This application allows administrators to **add, view, update, delete, and search** student records, and view key statistics like total students, highest marks, and lowest marks.

---

## 📑 Table of Contents

| Section | Description |
|----------|-------------|
| [🚀 Features](#-features) | Overview of the app’s main functions |
| [🧩 Project Structure](#-project-structure) | Description of source files |
| [🛠️ Technologies Used](#️-technologies-used) | Tools and libraries |
| [⚙️ Prerequisites](#️-prerequisites) | Requirements before running |
| [🗃️ Database Setup (MySQL)](#️-database-setup-mysql) | SQL setup for tables |
| [▶️ How to Run](#️-how-to-run-the-application) | Steps to execute the program |
| [💻 Sample Menu Output](#-sample-menu-output) | Example program output |
| [📈 Available Operations](#-available-operations) | Features table |
| [💡 Future Enhancements](#-future-enhancements) | Planned improvements |
| [🧑‍💻 Author](#-author) | Developer info |
| [🪪 License](#-license) | License details |

---

## 🚀 Features

✅ **Secure Admin Login** — Authenticates credentials stored in the database.  
✅ **Student Management** — Add, view, update, and delete student records.  
✅ **Search Functionality** — Search by Roll No, Name, Department, or Marks Range.  
✅ **Statistics Dashboard** — View total students, highest %, and lowest %.  
✅ **MySQL Integration** — Uses JDBC for reliable data persistence.

---

## 🧩 Project Structure
```
in/sp/main/
├── Main.java # Entry point of the application
├── UI.java # Handles console menu & user interaction
├── Student.java # Student entity (POJO)
├── StudentDao.java # CRUD operations on 'students' table
├── AdminService.java # Admin analytics (total, highest, lowest marks)
├── LoginService.java # Handles admin authentication
├── DatabaseConnection.java # Manages MySQL connection
```

---

## 🛠️ Technologies Used

| Component | Version / Description |
|------------|------------------------|
| **Java** | 17 |
| **MySQL Server** | 8.0.37 |
| **JDBC Driver** | mysql-connector-j-8.0.33.jar |
| **Database** | MySQL |
| **IDE** | Eclipse  |

---

## ⚙️ Prerequisites

Before running the project, ensure you have:

- ✅ Installed **Java JDK 17+**
- ✅ Installed **MySQL Server 8.0.37**
- ✅ Added **mysql-connector-j-8.0.33.jar** to your project classpath  
- ✅ Configured your IDE for Java and JDBC support

---

## 🗃️ Database Setup (MySQL)

1. **Open MySQL Workbench** (or your SQL client).  
2. **Create and use the database:**
   ```sql
   CREATE DATABASE studentdb1;
   USE studentdb1;

3. **Create the students table:**
  ```sql
   CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    roll_no VARCHAR(50) UNIQUE,
    department VARCHAR(100),
    email VARCHAR(100),
    phone VARCHAR(20),
    sub1 DOUBLE,
    sub2 DOUBLE,
    sub3 DOUBLE,
    percentage DOUBLE,
    grade VARCHAR(10)
); 
```

4. **Create the admin_users table:**
  ```sql
CREATE TABLE admin_users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE,
    password VARCHAR(50)
);
```

5.**Insert a sample admin account:**
```sql
INSERT INTO admin_users (username, password)
VALUES ('admin', 'admin123');

```
▶️ **How to Run the Application**

1. **Clone the repository**
git clone https://github.com/surajnpatil/Student-Management-System.git

2. **Open the project in your Eclipse IDE.**

3. **Add `mysql-connector-j-8.0.33.jar` to your project classpath:** 
  **Eclipse → Package Explorer → Right-click Project → Build Path → Configure Build Path → Libraries tab → Add External JARs → Browse to C:\Program Files\MySQL\Connector J 8.0\mysql-connector-j-8.0.33.jar → Open → Apply and Close → Verify under Referenced Libraries** ✅



4. **Check the database credentials in `DatabaseConnection.java`:**

   ```java
   private static final String URL = "jdbc:mysql://localhost:3306/studentdb1?serverTimezone=UTC";
   private static final String USER = "root";
   private static final String PASSWORD = "root";


5.**Run Main.java.**

6.**Log in with:**  
 >     username: admin  
 >     password: admin123


**7.Follow the console menu prompts.**

**💻 Sample Menu Output**  
--- SmartStudent Menu ---
1. Add Student
2. View Students
3. Update Student
4. Delete Student
5. Search Student
6. Statistics
0. Exit
Choice:

📈**Available Operations**
| Option                | Description                                         |
| --------------------- | --------------------------------------------------- |
| **1. Add Student**    | Add a new student record (auto-calculates grade)    |
| **2. View Students**  | View all stored student details                     |
| **3. Update Student** | Modify existing student information                 |
| **4. Delete Student** | Remove a student by Roll No                         |
| **5. Search Student** | Search by Roll No, Name, Department, or Marks Range |
| **6. Statistics**     | Display total students, highest %, and lowest %     |
| **0. Exit**           | Safely exit the application                         |


**💡 Future Enhancements**

**🔹 Add multiple user roles (Admin / Faculty)**

**🔹 Implement GPA or subject-wise grading**

**🔹 Add GUI (JavaFX or Swing)**

**🔹 Export reports to CSV or PDF**  

**🧑‍💻 Author**

**Suraj Patil**  
**📧surajpatil1505@gmail.com**  
**🗓️ 2025**

**🪪 License**

**This project is open-source and free to use for educational and personal learning purposes.**
