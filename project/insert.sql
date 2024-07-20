
INSERT INTO Student (name, email, major, count) VALUES
('John Doe', 'john.doe@example.com', 'Computer Science', 1),
('Alice Smith', 'alice.smith@example.com', 'Mechanical Engineering', 1),
('Bob Johnson', 'bob.johnson@example.com', 'Electrical Engineering', 1);


INSERT INTO Company (name, industry, location) VALUES
('ABC Inc.', 'Technology', 'New York'),
('XYZ Corp.', 'Finance', 'London'),
('123 Industries', 'Manufacturing', 'Tokyo');


INSERT INTO Internship (student_id, company_id, start_date, end_date, supervisor_name, department) VALUES
(1, 1, '2024-05-01', '2024-08-30', 'Sarah Johnson', 'Software Development'),
(2, 2, '2024-06-15', '2024-09-15', 'Michael Brown', 'Finance'),
(3, 3, '2024-07-01', '2024-10-01', 'Emily Clark', 'Engineering');


INSERT INTO Feedback (internship_id, feedback_text, rating) VALUES
(1, 'Great learning experience!', 5),
(2, 'Good internship program.', 4),
(3, 'Could be better.', 3);