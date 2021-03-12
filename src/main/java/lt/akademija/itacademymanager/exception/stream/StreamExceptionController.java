package lt.akademija.itacademymanager.exception.stream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class StreamExceptionController {

    @ControllerAdvice
    public class PersonExceptionController {

        @ExceptionHandler(value = StreamNotFoundException.class)
        public ResponseEntity<Object> exception(StreamNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(value = StreamAlreadyExistsException.class)
        public ResponseEntity<Object> exception(StreamAlreadyExistsException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
