package lt.akademija.itacademymanager.controller;

import lombok.AllArgsConstructor;
import lt.akademija.itacademymanager.config.JwtTokenUtil;
import lt.akademija.itacademymanager.exception.user.UserNotFoundException;
import lt.akademija.itacademymanager.payload.request.UserNewRequest;
import lt.akademija.itacademymanager.payload.response.JwtResponse;
import lt.akademija.itacademymanager.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class JwtAuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    @PostMapping(value = "api/login")
    public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody UserNewRequest authenticationRequest) {

        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

        final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(authenticationRequest.getEmail(), authenticationRequest.getRole(), token));
    }

    private void authenticate(String email, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (BadCredentialsException e) {
            throw new UserNotFoundException(email);
        }
    }
}
