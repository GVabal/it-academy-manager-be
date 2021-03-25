package lt.akademija.itacademymanager.service;


import lombok.AllArgsConstructor;
import lt.akademija.itacademymanager.exception.user.UserAlreadyExists;
import lt.akademija.itacademymanager.exception.user.UserNotFoundException;
import lt.akademija.itacademymanager.model.ApplicationUser;
import lt.akademija.itacademymanager.payload.request.UserNewRequest;
import lt.akademija.itacademymanager.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;




@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public ResponseEntity<ApplicationUser> addUser(UserNewRequest request){
        ApplicationUser user = new ApplicationUser(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                request.getRole()
        );
        if(!userRepository.existsByEmail(user.getEmail())){
            if(!userRepository.existsByPassword(user.getPassword())){
                user.setPassword(user.getPassword());
                userRepository.save(user);
                return new ResponseEntity(user, HttpStatus.CREATED);
            }
            else{
                throw new UserAlreadyExists("Email already exists.");
            }
        }
        else{
            throw new UserAlreadyExists("Password already exists.");
        }
    }

    public ApplicationUser loadUserByEmail(String email){
        if(userRepository.existsByEmail(email)){
            return userRepository.findByEmail(email);
        }
        else{
            throw new UserNotFoundException(email);
        }
    }

    public boolean authenticate(ApplicationUser user){
        String encodedPassword = user.getPassword();
        String role = user.getRole();
        ApplicationUser userData = loadUserByEmail(user.getEmail());
        return userData.getPassword().equals(encodedPassword) &&
                userData.getRole().equals(role);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ApplicationUser applicationUser = loadUserByEmail(email);
        return User.withUsername(applicationUser.getEmail()).password(applicationUser.getPassword()).authorities(applicationUser.getRole()).build();
    }
}
