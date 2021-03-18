package lt.akademija.itacademymanager.payload.request;

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

    @URL(message = "Picture URL is invalid.")
    @Pattern(regexp = "(^.+(\\.(?i)(jpg|png|gif|bmp))$)", message = "URL is not for a picture.")
    @Size(max = 255, message = "Picture URL is too long.")
    private final String pictureUrl;

    @Size(max = 50, message = "Occupation is too long.")
    private final String occupation;

    @Size(max = 50, message = "Direction is too long.")
    private final String direction;
}
