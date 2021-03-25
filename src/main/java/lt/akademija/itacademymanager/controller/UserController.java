package lt.akademija.itacademymanager.controller;


import lombok.AllArgsConstructor;
import lt.akademija.itacademymanager.model.ApplicationUser;
import lt.akademija.itacademymanager.payload.request.UserNewRequest;
import lt.akademija.itacademymanager.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApplicationUser> addUser(@RequestBody @Valid UserNewRequest userNewRequest){
        return userService.addUser(userNewRequest);
    }
}
