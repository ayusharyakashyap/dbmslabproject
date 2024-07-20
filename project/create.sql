CREATE TABLE Student (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    email VARCHAR(100),
    major VARCHAR(100),
    count INT
);

CREATE TABLE Company (
    company_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    industry VARCHAR(100),
    location VARCHAR(100)
);

CREATE TABLE Internship (
    internship_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT,
    company_id INT,
    start_date DATE,
    end_date DATE,
    supervisor_name VARCHAR(100),
    department VARCHAR(100)
);

CREATE TABLE Feedback (
    feedback_id INT PRIMARY KEY AUTO_INCREMENT,
    internship_id INT,
    feedback_text TEXT,
    rating INT,
    submission_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);