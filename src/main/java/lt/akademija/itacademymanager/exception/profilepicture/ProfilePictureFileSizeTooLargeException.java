package lt.akademija.itacademymanager.exception.profilepicture;

public class ProfilePictureFileSizeTooLargeException extends RuntimeException {
    public ProfilePictureFileSizeTooLargeException() {
        super("File size is too large.");
    }
}
