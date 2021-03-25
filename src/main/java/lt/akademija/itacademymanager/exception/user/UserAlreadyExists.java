package lt.akademija.itacademymanager.exception.user;

public class UserAlreadyExists extends RuntimeException{
    public UserAlreadyExists(String errorMessage) {
        super(errorMessage);
    }
}
