package lt.akademija.itacademymanager.controller;


import lombok.AllArgsConstructor;
import lt.akademija.itacademymanager.payload.request.UserNewRequest;
import lt.akademija.itacademymanager.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> addUser(@RequestBody @Valid UserNewRequest userNewRequest){
        userService.addUser(userNewRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
