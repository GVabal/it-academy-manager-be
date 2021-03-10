package lt.akademija.itacademymanager.payload;

import lombok.Data;
import lt.akademija.itacademymanager.model.Student;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class StudentNewRequest {
    @NotBlank(message = "First name must not be blank.")
    @Size(max = 255, message = "First name is too long.")
    private final String firstName;

    @NotBlank(message = "Last name must not be blank.")
    @Size(max = 255, message = "Last name is too long.")
    private final String lastName;

    @URL(message = "Picture URL is invalid.")
    @Pattern(regexp = "(^.+(\\.(?i)(jpg|png|gif|bmp))$)", message = "URL is not for a picture.")
    private final String pictureUrl;

    @NotBlank(message = "Occupation must not be blank.")
    @Size(max = 255, message = "Occupation is too long.")
    private final String occupation;

    @NotBlank(message = "Direction must not be blank.")
    @Size(max = 255, message = "Direction is too long.")
    private final String direction;

    public Student toStudent() {
        return new Student(firstName, lastName, pictureUrl, occupation, direction);
    }
}
