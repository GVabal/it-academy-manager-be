package lt.akademija.itacademymanager.exception.user;

public class NoSuchRoleException extends RuntimeException{
    public NoSuchRoleException(String role) {
        super("Role: " + role + " doesn't exist");
    }
}
