package lt.akademija.itacademymanager.controller;

import lombok.AllArgsConstructor;
import lt.akademija.itacademymanager.config.JwtTokenUtil;
import lt.akademija.itacademymanager.model.ApplicationUser;
import lt.akademija.itacademymanager.payload.request.ReviewNewRequest;
import lt.akademija.itacademymanager.payload.response.ReviewResponse;
import lt.akademija.itacademymanager.service.ReviewService;
import lt.akademija.itacademymanager.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("api/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;

    @PreAuthorize("hasRole('LECTURER')")
    @PostMapping
    public ResponseEntity<ReviewResponse> addReview(@RequestBody @Valid ReviewNewRequest request,
                                                    @RequestHeader(value = "Authorization") String token) {
        String email = jwtTokenUtil.getEmailFromToken(token.substring(7));
        ApplicationUser user = userService.loadUserByEmail(email);
        return new ResponseEntity<>(reviewService.addReview(request, user), HttpStatus.CREATED);
    }
}
