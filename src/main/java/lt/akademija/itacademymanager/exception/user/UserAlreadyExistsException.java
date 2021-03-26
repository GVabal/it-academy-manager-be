package lt.akademija.itacademymanager.exception.user;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
