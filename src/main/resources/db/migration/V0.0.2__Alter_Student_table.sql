DROP TABLE student;

CREATE TABLE student (
     id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
     first_name VARCHAR(255) NOT NULL,
     last_name VARCHAR(255) NOT NULL,
     picture_url VARCHAR(255),
     occupation VARCHAR(255) NOT NULL,
     direction VARCHAR(255) NOT NULL
);
