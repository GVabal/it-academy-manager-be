package lt.akademija.itacademymanager.service;

import lt.akademija.itacademymanager.model.Stream;

import java.util.List;

public interface StreamService {
    List<Stream> getAllStreams();

    void addStream(Stream stream);

    void deleteStream(Stream stream);
}
