package lt.akademija.itacademymanager.payload.request;

import javax.validation.constraints.*;
import lombok.Getter;

@Getter
public class LoginRequest {

    @NotBlank(message = "Email must not be blank.")
    @Size(max = 50, message = "Email is too long.")
    @Email(regexp = ".+@.+\\..+", message = "Bad email")
    private String email;

    @NotBlank(message = "Password must not be blank.")
    @Size(max = 255, message = "Password is too long.")
    @Size(min = 8, message = "Password is too short.")
    private String password;
}
