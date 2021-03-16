DROP TABLE student;

CREATE TABLE student (
     id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
     first_name VARCHAR(25) NOT NULL,
     last_name VARCHAR(25) NOT NULL,
     picture_url VARCHAR(255),
     occupation VARCHAR(50) NOT NULL,
     direction VARCHAR(50) NOT NULL
);
