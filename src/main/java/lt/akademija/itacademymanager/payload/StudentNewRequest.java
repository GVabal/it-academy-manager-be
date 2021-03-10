package lt.akademija.itacademymanager.payload;

import lombok.Data;
import lt.akademija.itacademymanager.model.Student;

import javax.validation.constraints.NotBlank;

@Data
public class StudentNewRequest {
    @NotBlank(message = "First name must not be blank.")
    private final String firstName;

    @NotBlank(message = "Last name must not be blank.")
    private final String lastName;

    private final String pictureUrl;

    @NotBlank(message = "Occupation must not be blank.")
    private final String occupation;

    @NotBlank(message = "Direction must not be blank.")
    private final String direction;

    public Student toStudent() {
        return new Student(firstName, lastName, pictureUrl, occupation, direction);
    }
}
