# Project Description: Student Internship Management System

This project implements a Student Internship Management System using Java and JDBC to manage student internships efficiently. The system provides functionalities for both students and administrators, including viewing, adding, and deleting internships. It includes SQL scripts to create and alter the necessary database tables and to insert initial data. It allows CRUD operations.

# Key Features:
- Student and Admin Menus: Different interfaces for students and administrators to manage their respective tasks.
- nternship Management: Students can view their internships, and administrators can view all internships, add new internships, and delete existing ones.
- Transaction Management: Uses transactions to ensure data integrity, with rollback mechanisms in case of errors.
- Database Integration: Utilizes MySQL for database operations, including table creation, data insertion, and relational constraints.

# Database Schema:
- Student: Stores student information with fields for name, email, major, and internship count.
- Company: Contains details about companies offering internships.
- Internship: Records internship details, linking students to companies.
- Feedback: Captures feedback on internships, with foreign key constraints to maintain referential integrity.

# SQL Scripts:
- create.sql: Defines the database schema, creating tables for students, companies, internships, and feedback.
- insert.sql: Inserts initial data into the database tables.
- alter.sql: Adds foreign key constraints to ensure relational integrity between tables.

This project showcases the integration of Java with MySQL, emphasizing data management and transaction control within a real-world application context.
