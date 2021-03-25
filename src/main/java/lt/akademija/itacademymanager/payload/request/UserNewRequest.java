package lt.akademija.itacademymanager.payload.request;

import lombok.Data;
import lt.akademija.itacademymanager.model.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserNewRequest {

    @NotBlank(message = "First name must not be blank.")
    @Size(max = 30, message = "First name is too long.")
    private String firstName;

    @NotBlank(message = "Last name must not be blank.")
    @Size(max = 30, message = "Last name is too long.")
    private String lastName;

    @NotBlank(message = "Email must not be blank.")
    @Size(max = 50, message = "Email is too long.")
    @Email(regexp = ".+@.+\\..+", message = "Bad email")
    private String email;

    @NotBlank(message = "Password must not be blank.")
    @Size(max = 255, message = "Password is too long.")
    @Size(min = 8, message = "Password is too short.")
    private String password;

    @NotBlank(message = "Role must not be blank.")
    private String role;
}
