package lt.akademija.itacademymanager.exception.stream;

public class StreamNotFoundException extends RuntimeException {
    public StreamNotFoundException(int id) {
        super("Stream with id: " + id + " doesn't exist");
    }
}
