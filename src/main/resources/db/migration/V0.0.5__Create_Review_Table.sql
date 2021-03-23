CREATE TABLE review (
id integer PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
overall_grade integer NOT NULL,
overall_comment VARCHAR(255) NOT NULL,
ability_to_learn_grade integer NOT NULL,
ability_to_learn_comment VARCHAR(255),
motivation_grade integer NOT NULL,
motivation_comment VARCHAR(255),
extra_mile_grade integer NOT NULL,
extra_mile_comment VARCHAR(255),
communication_grade integer NOT NULL,
communication_comment VARCHAR(255),
student_id integer,
stream_id integer,
CONSTRAINT fk_student_id FOREIGN KEY(student_id) REFERENCES student(id),
CONSTRAINT fk_stream_id FOREIGN KEY(stream_id) REFERENCES stream(id) ON DELETE SET NULL
);
