package lt.akademija.itacademymanager.exception.stream;

public class StreamNotFoundException extends RuntimeException {
    public StreamNotFoundException(String name) {
        super("Stream " + name + " doesn't exist");
    }
}
