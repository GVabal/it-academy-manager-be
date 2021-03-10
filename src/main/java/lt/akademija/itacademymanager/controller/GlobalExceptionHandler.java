package lt.akademija.itacademymanager.controller;

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
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        String message = exception.getBindingResult().getFieldError().getDefaultMessage();
        return generateResponse(message, HttpStatus.BAD_REQUEST);
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
