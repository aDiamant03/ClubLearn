CREATE SEQUENCE IF NOT EXISTS student_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS students
(
    id         BIGINT PRIMARY KEY DEFAULT nextval('student_id_seq'),
    name       VARCHAR(255) NOT NULL,
    surname    VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    grade      INT
);