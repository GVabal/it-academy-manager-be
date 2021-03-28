package lt.akademija.itacademymanager.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserNewRequest implements Serializable {

    @NotBlank(message = "Full name must not be blank.")
    @Size(max = 50, message = "Full name is too long.")
    private String fullName;

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
