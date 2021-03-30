package lt.akademija.itacademymanager.exception.user;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String email) {
        super("Email: " + email + " doesn't exist");
    }
}
