package lt.akademija.itacademymanager.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserNewRequest {

    @NotBlank(message = "First name must not be blank.")
    @Size(max = 255, message = "First name is too long.")
    private String firstName;

    @NotBlank(message = "Last name must not be blank.")
    @Size(max = 255, message = "Last name is too long.")
    private String lastName;

    @NotBlank(message = "Email must not be blank.")
    @Size(max = 255, message = "Email is too long.")
    private String email;

    @NotBlank(message = "Password must not be blank.")
    @Size(max = 255, message = "Password is too long.")
    private String password;

    @NotBlank(message = "Role must not be blank.")
    @Size(max = 50, message = "Role is too long.")
    private String role;
}
