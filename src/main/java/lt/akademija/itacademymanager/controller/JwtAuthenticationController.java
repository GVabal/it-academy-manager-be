package lt.akademija.itacademymanager.controller;

import lombok.AllArgsConstructor;
import lt.akademija.itacademymanager.config.JwtTokenUtil;
import lt.akademija.itacademymanager.payload.request.LoginRequest;
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

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class JwtAuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    @PostMapping(value = "api/login")
    public ResponseEntity<JwtResponse> createAuthenticationToken(@Valid @RequestBody LoginRequest loginRequest) {

        authenticate(loginRequest);

        final UserDetails userDetails = userService.loadUserByUsername(loginRequest.getEmail());
        String role = userService.getRole(loginRequest.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(userDetails.getUsername(), role, token));
    }

    private void authenticate(LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid credentials");
        }
    }
}
