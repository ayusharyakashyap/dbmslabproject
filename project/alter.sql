ALTER TABLE Internship
ADD CONSTRAINT fk_student_id
FOREIGN KEY (student_id) REFERENCES Student(student_id);

ALTER TABLE Internship
ADD CONSTRAINT fk_company_id
FOREIGN KEY (company_id) REFERENCES Company(company_id);

ALTER TABLE Feedback
ADD CONSTRAINT fk_internship_id
FOREIGN KEY (internship_id) REFERENCES Internship(internship_id)
ON DELETE CASCADE;
