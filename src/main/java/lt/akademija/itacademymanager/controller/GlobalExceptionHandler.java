package lt.akademija.itacademymanager.controller;

import lt.akademija.itacademymanager.exception.InvalidTokenException;
import lt.akademija.itacademymanager.exception.StudentNotFoundException;
import lt.akademija.itacademymanager.exception.profilepicture.ProfilePictureFailedToUploadException;
import lt.akademija.itacademymanager.exception.profilepicture.ProfilePictureFileSizeTooLargeException;
import lt.akademija.itacademymanager.exception.profilepicture.ProfilePictureInvalidException;
import lt.akademija.itacademymanager.exception.profilepicture.ProfilePictureNotFoundException;
import lt.akademija.itacademymanager.exception.stream.StreamAlreadyExistsException;
import lt.akademija.itacademymanager.exception.stream.StreamNotFoundException;
import lt.akademija.itacademymanager.exception.user.NoSuchRoleException;
import lt.akademija.itacademymanager.exception.user.UserAlreadyExistsException;
import lt.akademija.itacademymanager.exception.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleStudentNotFoundException(StudentNotFoundException exception) {
        return generateResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = StreamNotFoundException.class)
    public ResponseEntity<Map<String, String>> exception(StreamNotFoundException exception) {
        return generateResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = StreamAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> exception(StreamAlreadyExistsException exception) {
        return generateResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        String message = exception.getBindingResult().getFieldError().getDefaultMessage();
        return generateResponse(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProfilePictureFileSizeTooLargeException.class)
    public ResponseEntity<Map<String, String>> handleProfilePictureFileSizeTooLargeException(ProfilePictureFileSizeTooLargeException exception) {
        return generateResponse(exception.getMessage(), HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(ProfilePictureInvalidException.class)
    public ResponseEntity<Map<String, String>> handlePictureInvalidException(ProfilePictureInvalidException exception) {
        return generateResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProfilePictureNotFoundException.class)
    public ResponseEntity<Map<String, String>> handlePictureNotFoundException(ProfilePictureNotFoundException exception) {
        return generateResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProfilePictureFailedToUploadException.class)
    public ResponseEntity<Map<String, String>> handleProfilePictureFailedToUploadException(ProfilePictureFailedToUploadException exception) {
        return generateResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = UserAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> exception(UserAlreadyExistsException exception) {
        return generateResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> exception(UserNotFoundException exception) {
        return generateResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = NoSuchRoleException.class)
    public ResponseEntity<Map<String, String>> exception(NoSuchRoleException exception) {
        return generateResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidTokenException.class)
    public ResponseEntity<Map<String, String>> exception(InvalidTokenException exception) {
        return generateResponse(exception.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    private Map<String, String> generateResponseBody(HttpStatus status, String message) {
        Map<String, String> map = new HashMap<>();
        map.put("code", Integer.toString(status.value()));
        map.put("error", status.getReasonPhrase());
        map.put("message", message);
        map.put("timestamp", LocalDateTime.now().toString());
        return map;
    }

    private ResponseEntity<Map<String, String>> generateResponse(String message, HttpStatus status) {
        return new ResponseEntity<>(generateResponseBody(status, message), status);
    }
}
