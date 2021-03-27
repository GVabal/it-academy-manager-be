package lt.akademija.itacademymanager.service;

import lombok.AllArgsConstructor;
import lt.akademija.itacademymanager.exception.user.NoSuchRoleException;
import lt.akademija.itacademymanager.exception.user.UserAlreadyExistsException;
import lt.akademija.itacademymanager.exception.user.UserNotFoundException;
import lt.akademija.itacademymanager.model.ApplicationUser;
import lt.akademija.itacademymanager.model.Role;
import lt.akademija.itacademymanager.payload.request.UserNewRequest;
import lt.akademija.itacademymanager.repository.UserRepository;
import lt.akademija.itacademymanager.security.PasswordEncoder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void addUser(UserNewRequest request) {
        if (isRoleValid(request)) {
            ApplicationUser user = new ApplicationUser(
                    request.getFullName(),
                    request.getEmail(),
                    request.getPassword(),
                    request.getRole()
            );
            if (userRepository.existsByEmail(user.getEmail())) {
                throw new UserAlreadyExistsException("Email already exists.");
            }
            user.setPassword(passwordEncoder.encoder().encode(user.getPassword()));
            userRepository.save(user);
        } else throw new NoSuchRoleException(request.getRole());
    }

    private boolean isRoleValid(UserNewRequest request) {
        List<Role> roles = Arrays.asList(Role.values());
        List<String> enumValues = roles.stream().map(Role::getRole).collect(Collectors.toList());
        return enumValues.contains(request.getRole()) && !request.getRole().equals("ADMIN");
    }

    public ApplicationUser loadUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ApplicationUser applicationUser = loadUserByEmail(email);
        return User.withUsername(applicationUser.getEmail()).password(applicationUser.getPassword()).roles(applicationUser.getRole()).build();
    }

    public void authenticateRole(UserNewRequest request){
        if(!loadUserByEmail(request.getEmail()).getRole().equals(request.getRole())){
            throw new NoSuchRoleException(request.getRole());
        }
    }
}
