import java.util.*;
import java.sql.*;

public class StudentInternshipSystem {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/studentinternshipdb?useSSL=false";
    static final String USER = "root";
    static final String PASS = "Ayush-2004";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            conn.setAutoCommit(false); // Set auto commit as false to start transaction
            stmt = conn.createStatement();
            Scanner sc = new Scanner(System.in);

            System.out.println("Welcome to Student Internship Management System");

            mainMenu(conn, stmt,sc);

            sc.close();
            // conn.close();

        } catch (SQLException se) {
            se.printStackTrace();
            try {
                if (conn != null) {
                    System.out.println("Rolling back transaction...");
                    conn.rollback(); // Rollback transaction if there's an error
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true); // Restore auto-commit mode
                    // conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    static void mainMenu(Connection conn,Statement stmt, Scanner sc) {
        System.out.println("How do you want to sign in?");
        System.out.println("1. Student");
        System.out.println("2. Admin");
        System.out.println("3. Exit");
        System.out.print("Please enter your choice: ");
        int choice = Integer.parseInt(sc.nextLine());

        switch (choice) {
            case 1:
                studentMenu(conn,stmt, sc);
                break;
            case 2:
                adminMenu(conn,stmt, sc);
                break;
            case 3:
                System.out.println("Exiting system. Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                mainMenu(conn, stmt,sc);
                break;
        }
    }

    static void studentMenu(Connection conn, Statement stmt,Scanner sc) {
        // Student menu code here
        System.out.println("Student Menu");
        System.out.println("1. View My Internships");
        System.out.println("2. Exit");

        System.out.print("Please enter your choice: ");
        int choice = Integer.parseInt(sc.nextLine());

        switch (choice) {
            case 1:
                viewMyInternships(conn,stmt, sc);
                break;
            case 2:
                System.out.println("Exiting student menu.");
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
                studentMenu(conn, stmt,sc);
                break;
        }
    }

    static void adminMenu(Connection conn, Statement stmt,Scanner sc) {
        // Admin menu code here
        System.out.println("Admin Menu");
        System.out.println("1. View All Internships");
        System.out.println("2. Add New Internship");
        System.out.println("3. Delete Internship");
        System.out.println("4. Exit");

        System.out.print("Please enter your choice: ");
        int choice = Integer.parseInt(sc.nextLine());

        switch (choice) {
            case 1:
                viewAllInternships(stmt);
                break;
            case 2:
                addNewInternship(conn, stmt,sc);
                break;
            case 3:
                deleteInternship(conn,stmt, sc);
                break;
            case 4:
                System.out.println("Exiting admin menu.");
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
                adminMenu(conn,stmt, sc);
                break;
        }
    }
    // static void viewAvailableInternships(Statement stmt) {
//     try {
//         String sql = "SELECT i.*, c.name " +
//                      "FROM Internship i " +
//                      "INNER JOIN Company c ON i.company_id = c.company_id";
//         ResultSet rs = stmt.executeQuery(sql);
//         while (rs.next()) {
//             int internshipId = rs.getInt("internship_id");
//             int companyId = rs.getInt("company_id");
//             String companyName = rs.getString("name");
//             String startDate = rs.getString("start_date");
//             String endDate = rs.getString("end_date");
//             String supervisorName = rs.getString("supervisor_name");
//             String department = rs.getString("department");
            
//             System.out.println("Internship ID: " + internshipId);
//             System.out.println("Company ID: " + companyId);
//             System.out.println("Company Name: " + companyName);
//             System.out.println("Start Date: " + startDate);
//             System.out.println("End Date: " + endDate);
//             System.out.println("Supervisor Name: " + supervisorName);
//             System.out.println("Department: " + department);
//             System.out.println();
//         }
//         rs.close();
//     } catch (SQLException e) {
//         e.printStackTrace();
//     }
// }


    // static void applyForInternship(Connection conn, Statement stmt, Scanner sc) {
    //     try {
    //         System.out.println("Enter Internship ID to apply: ");
    //         int internshipId = Integer.parseInt(sc.nextLine());
    //         System.out.println("Enter Student ID: ");
    //         int studentId = Integer.parseInt(sc.nextLine());

    //         // Insert into student_internship table
    //         String insertStudentInternshipQuery = "INSERT INTO student_internship (student_id, internship_id) VALUES (?, ?)";
    //         try (PreparedStatement insertStudentInternshipStmt = conn.prepareStatement(insertStudentInternshipQuery)) {
    //             insertStudentInternshipStmt.setInt(1, studentId);
    //             insertStudentInternshipStmt.setInt(2, internshipId);
    //             int rowsAffected = insertStudentInternshipStmt.executeUpdate();
    //             if (rowsAffected > 0) {
    //                 System.out.println("Successfully applied for internship.");
    //             } else {
    //                 System.out.println("Failed to apply for internship.");
    //             }
    //         }

    //         // You can perform other updates related to the internship or student tables here
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }

//     static void viewMyInternships(Connection conn, Statement stmt, Scanner sc) {
//     try {
//         System.out.println("Enter Student ID: ");
//         int studentId = Integer.parseInt(sc.nextLine());
//         String sql = "SELECT i.* FROM Internship i INNER JOIN Student_Internship si ON i.internship_id = si.internship_id WHERE si.student_id = ?";
//         PreparedStatement preparedStatement = conn.prepareStatement(sql);
//         preparedStatement.setInt(1, studentId);
//         ResultSet rs = preparedStatement.executeQuery();
//         while (rs.next()) {
//             int internshipId = rs.getInt("internship_id");
//             int companyId = rs.getInt("company_id");
//             String startDate = rs.getString("start_date");
//             String endDate = rs.getString("end_date");
//             String supervisorName = rs.getString("supervisor_name");
//             String department = rs.getString("department");
            
//             System.out.println("Internship ID: " + internshipId);
//             System.out.println("Company ID: " + companyId);
//             System.out.println("Start Date: " + startDate);
//             System.out.println("End Date: " + endDate);
//             System.out.println("Supervisor Name: " + supervisorName);
//             System.out.println("Department: " + department);
//             System.out.println();
//         }
//         rs.close();
//         preparedStatement.close();
//     } catch (SQLException e) {
//         e.printStackTrace();
//     }
// }

    static void viewMyInternships(Connection conn, Statement stmt, Scanner sc) {
    try {
        System.out.println("Enter Student ID: ");
        int studentId = Integer.parseInt(sc.nextLine());
        String sql = "SELECT i.*, c.name " +
                     "FROM Internship i " +
                     "INNER JOIN Company c ON i.company_id = c.company_id " +
                     "INNER JOIN Student s ON i.student_id = s.student_id " +
                     "WHERE i.student_id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, studentId);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            int internshipId = rs.getInt("internship_id");
            int companyId = rs.getInt("company_id");
            String companyName = rs.getString("name");
            String startDate = rs.getString("start_date");
            String endDate = rs.getString("end_date");
            String supervisorName = rs.getString("supervisor_name");
            String department = rs.getString("department");
            
            System.out.println("Internship ID: " + internshipId);
            System.out.println("Company ID: " + companyId);
            System.out.println("Company Name: " + companyName);
            System.out.println("Start Date: " + startDate);
            System.out.println("End Date: " + endDate);
            System.out.println("Supervisor Name: " + supervisorName);
            System.out.println("Department: " + department);
            System.out.println();
        }
        rs.close();
        preparedStatement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
 

    static void viewAllInternships(Statement stmt) {
        try {
            String sql = "SELECT * FROM Internship";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int internshipId = rs.getInt("internship_id");
                int companyId = rs.getInt("company_id");
                String startDate = rs.getString("start_date");
                String endDate = rs.getString("end_date");
                String supervisorName = rs.getString("supervisor_name");
                String department = rs.getString("department");
                
                System.out.println("Internship ID: " + internshipId);
                System.out.println("Company ID: " + companyId);
                System.out.println("Start Date: " + startDate);
                System.out.println("End Date: " + endDate);
                System.out.println("Supervisor Name: " + supervisorName);
                System.out.println("Department: " + department);
                System.out.println();
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // static void addNewInternship(Connection conn, Statement stmt, Scanner sc) {
    //     try {
    //         System.out.println("Enter Company ID: ");
    //         int companyId = Integer.parseInt(sc.nextLine());
    //         System.out.println("Enter Start Date (YYYY-MM-DD): ");
    //         String startDate = sc.nextLine();
    //         System.out.println("Enter End Date (YYYY-MM-DD): ");
    //         String endDate = sc.nextLine();
    //         System.out.println("Enter Supervisor Name: ");
    //         String supervisorName = sc.nextLine();
    //         System.out.println("Enter Department: ");
    //         String department = sc.nextLine();

    //         // Insert into internship table
    //         String insertInternshipQuery = "INSERT INTO Internship (company_id, start_date, end_date, supervisor_name, department) VALUES (?, ?, ?, ?, ?)";
    //         try (PreparedStatement insertInternshipStmt = conn.prepareStatement(insertInternshipQuery, Statement.RETURN_GENERATED_KEYS)) {
    //             insertInternshipStmt.setInt(1, companyId);
    //             insertInternshipStmt.setString(2, startDate);
    //             insertInternshipStmt.setString(3, endDate);
    //             insertInternshipStmt.setString(4, supervisorName);
    //             insertInternshipStmt.setString(5, department);
    //             int rowsAffected = insertInternshipStmt.executeUpdate();
    //             if (rowsAffected > 0) {
    //                 System.out.println("New internship added successfully.");

    //                 // Get the generated internship_id
    //                 ResultSet generatedKeys = insertInternshipStmt.getGeneratedKeys();
    //                 if (generatedKeys.next()) {
    //                     int internshipId = generatedKeys.getInt(1);

    //                     // Now you can perform any additional updates, like inserting into student_internship table
    //                 }
    //             } else {
    //                 System.out.println("Failed to add new internship.");
    //             }
    //         }

    //         // Other updates related to student_internship or other tables can be performed here
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }





    //     static void addNewInternship(Connection conn, Statement stmt, Scanner sc) {
//     try {
//         System.out.println("Enter Company ID: ");
//         int companyId = Integer.parseInt(sc.nextLine());
//         System.out.println("Enter Start Date (YYYY-MM-DD): ");
//         String startDate = sc.nextLine();
//         System.out.println("Enter End Date (YYYY-MM-DD): ");
//         String endDate = sc.nextLine();
//         System.out.println("Enter Supervisor Name: ");
//         String supervisorName = sc.nextLine();
//         System.out.println("Enter Department: ");
//         String department = sc.nextLine();

//         // Insert into internship table
//         String insertInternshipQuery = "INSERT INTO Internship (company_id, start_date, end_date, supervisor_name, department) VALUES (?, ?, ?, ?, ?)";
//         try (PreparedStatement insertInternshipStmt = conn.prepareStatement(insertInternshipQuery, Statement.RETURN_GENERATED_KEYS)) {
//             insertInternshipStmt.setInt(1, companyId);
//             insertInternshipStmt.setString(2, startDate);
//             insertInternshipStmt.setString(3, endDate);
//             insertInternshipStmt.setString(4, supervisorName);
//             insertInternshipStmt.setString(5, department);
//             int rowsAffected = insertInternshipStmt.executeUpdate();
//             if (rowsAffected > 0) {
//                 System.out.println("New internship added successfully.");

//                 // Get the generated internship_id
//                 ResultSet generatedKeys = insertInternshipStmt.getGeneratedKeys();
//                 if (generatedKeys.next()) {
//                     int internshipId = generatedKeys.getInt(1);

//                     // Update the count for the relevant student
//                     System.out.println("Enter Student ID: ");
//                     int studentId = Integer.parseInt(sc.nextLine());
//                     String updateStudentCountQuery = "UPDATE Student SET count = count + 1 WHERE student_id = ?";
//                     try (PreparedStatement updateStudentCountStmt = conn.prepareStatement(updateStudentCountQuery)) {
//                         updateStudentCountStmt.setInt(1, studentId);
//                         int countRowsAffected = updateStudentCountStmt.executeUpdate();
//                         if (countRowsAffected > 0) {
//                             System.out.println("Student count updated successfully.");
//                         } else {
//                             System.out.println("Failed to update student count.");
//                         }
//                     }
//                 }
//             } else {
//                 System.out.println("Failed to add new internship.");
//             }
//         }
//     } catch (SQLException e) {
//         e.printStackTrace();
//     }
// }


    static void addNewInternship(Connection conn, Statement stmt, Scanner sc) {
    try {
        System.out.println("Enter Company ID: ");
        int companyId = Integer.parseInt(sc.nextLine());
        System.out.println("Enter Student ID: ");
        int studentId = Integer.parseInt(sc.nextLine());
        System.out.println("Enter Start Date (YYYY-MM-DD): ");
        String startDate = sc.nextLine();
        System.out.println("Enter End Date (YYYY-MM-DD): ");
        String endDate = sc.nextLine();
        System.out.println("Enter Supervisor Name: ");
        String supervisorName = sc.nextLine();
        System.out.println("Enter Department: ");
        String department = sc.nextLine();

        // Insert into internship table
        String insertInternshipQuery = "INSERT INTO Internship (company_id, student_id, start_date, end_date, supervisor_name, department) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement insertInternshipStmt = conn.prepareStatement(insertInternshipQuery, Statement.RETURN_GENERATED_KEYS)) {
            insertInternshipStmt.setInt(1, companyId);
            insertInternshipStmt.setInt(2, studentId);
            insertInternshipStmt.setString(3, startDate);
            insertInternshipStmt.setString(4, endDate);
            insertInternshipStmt.setString(5, supervisorName);
            insertInternshipStmt.setString(6, department);
            int rowsAffected = insertInternshipStmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("New internship added successfully.");

                // Get the generated internship_id
                ResultSet generatedKeys = insertInternshipStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int internshipId = generatedKeys.getInt(1);

                    // Update the count for the relevant student
                    String updateStudentCountQuery = "UPDATE Student SET count = count + 1 WHERE student_id = ?";
                    try (PreparedStatement updateStudentCountStmt = conn.prepareStatement(updateStudentCountQuery)) {
                        updateStudentCountStmt.setInt(1, studentId);
                        int countRowsAffected = updateStudentCountStmt.executeUpdate();
                        if (countRowsAffected > 0) {
                            System.out.println("Student count updated successfully.");
                        } else {
                            System.out.println("Failed to update student count.");
                        }
                    }
                }
            } else {
                System.out.println("Failed to add new internship.");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}



//     static void deleteInternship(Connection conn, Statement stmt, Scanner sc) {
//     try {
//         System.out.println("Enter Internship ID to delete: ");
//         int internshipId = Integer.parseInt(sc.nextLine());
//         String sql = "DELETE FROM Internship WHERE internship_id = ?";
//         PreparedStatement preparedStatement = conn.prepareStatement(sql);
//         preparedStatement.setInt(1, internshipId);
//         int rowsAffected = preparedStatement.executeUpdate();
//         if (rowsAffected > 0) {
//             System.out.println("Internship deleted successfully.");
//         } else {
//             System.out.println("Failed to delete internship.");
//         }
//         preparedStatement.close();
//     } catch (SQLException e) {
//         e.printStackTrace();
//         }
// }
    static void deleteInternship(Connection conn, Statement stmt, Scanner sc) {
    try {
        System.out.println("Enter Internship ID to delete: ");
        int internshipId = Integer.parseInt(sc.nextLine());

        // Retrieve the student ID associated with the internship before deleting it
        String retrieveStudentIdQuery = "SELECT student_id FROM Internship WHERE internship_id = ?";
        try (PreparedStatement retrieveStudentIdStmt = conn.prepareStatement(retrieveStudentIdQuery)) {
            retrieveStudentIdStmt.setInt(1, internshipId);
            ResultSet resultSet = retrieveStudentIdStmt.executeQuery();

            int studentId = -1; // Initialize with a default value
            if (resultSet.next()) {
                studentId = resultSet.getInt("student_id");
            }

            // Delete the internship
            String deleteInternshipQuery = "DELETE FROM Internship WHERE internship_id = ?";
            try (PreparedStatement deleteInternshipStmt = conn.prepareStatement(deleteInternshipQuery)) {
                deleteInternshipStmt.setInt(1, internshipId);
                int rowsAffected = deleteInternshipStmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Internship deleted successfully.");

                    // Decrement the count for the relevant student
                    String updateStudentCountQuery = "UPDATE Student SET count = count - 1 WHERE student_id = ?";
                    try (PreparedStatement updateStudentCountStmt = conn.prepareStatement(updateStudentCountQuery)) {
                        updateStudentCountStmt.setInt(1, studentId);
                        int countRowsAffected = updateStudentCountStmt.executeUpdate();
                        if (countRowsAffected > 0) {
                            System.out.println("Student count updated successfully.");
                        } else {
                            System.out.println("Failed to update student count.");
                        }
                    }
                } else {
                    System.out.println("Failed to delete internship.");
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

}