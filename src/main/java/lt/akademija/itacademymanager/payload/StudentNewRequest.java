package lt.akademija.itacademymanager.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class StudentNewRequest {
    @NotBlank(message = "First name must not be blank.")
    @Size(max = 25, message = "First name is too long.")
    private final String firstName;

    @NotBlank(message = "Last name must not be blank.")
    @Size(max = 25, message = "Last name is too long.")
    private final String lastName;

    @NotBlank(message = "Occupation must not be blank.")
    @Size(max = 50, message = "Occupation is too long.")
    private final String occupation;

    @NotBlank(message = "Direction must not be blank.")
    @Size(max = 50, message = "Direction is too long.")
    private final String direction;
}
