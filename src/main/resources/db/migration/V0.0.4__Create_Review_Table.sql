CREATE TABLE review (
id integer PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
overallGrade integer NOT NULL,
overallComment VARCHAR(255) NOT NULL,
abilityToLearnGrade integer NOT NULL,
abilityToLearnComment VARCHAR(255),
motivationGrade integer NOT NULL,
motivationComment VARCHAR(255),
extraMileGrade integer NOT NULL,
extraMileComment VARCHAR(255),
communicationGrade integer NOT NULL,
communicationComment VARCHAR(255),
CONSTRAINT studentId FOREIGN KEY(id) REFERENCES student(id),
CONSTRAINT streamId FOREIGN KEY(id) REFERENCES stream(id)
)