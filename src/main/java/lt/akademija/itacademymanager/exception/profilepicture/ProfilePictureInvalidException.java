package lt.akademija.itacademymanager.exception.profilepicture;

public class ProfilePictureInvalidException extends RuntimeException {
    public ProfilePictureInvalidException() {
        super("Picture is invalid.");
    }
}
