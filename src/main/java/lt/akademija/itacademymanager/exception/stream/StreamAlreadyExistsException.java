package lt.akademija.itacademymanager.exception.stream;

public class StreamAlreadyExistsException extends RuntimeException {
    public StreamAlreadyExistsException(String name) {
        super("Stream with name " + name + " already exists");
    }
}
