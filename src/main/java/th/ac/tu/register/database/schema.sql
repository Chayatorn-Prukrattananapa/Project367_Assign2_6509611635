CREATE SCHEMA IF NOT EXISTS Register;

CREATE TABLE Register.Subjects (
    subjectId VARCHAR(10) PRIMARY KEY,
    subjectName VARCHAR(255) NOT NULL,
    section VARCHAR(10) NOT NULL,
    credits INT NOT NULL CHECK (credits > 0),
    maxSeats INT NOT NULL CHECK (maxSeats > 0),
    availableSeats INT NOT NULL CHECK (availableSeats >= 0 AND availableSeats <= maxSeats)
    PRIMARY KEY (subjectId, section)
);