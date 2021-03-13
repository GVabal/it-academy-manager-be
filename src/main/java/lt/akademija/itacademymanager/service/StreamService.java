package lt.akademija.itacademymanager.service;

import lt.akademija.itacademymanager.model.Stream;

import java.util.List;

public interface StreamService {
    List<Stream> getAllStreams();

    Stream addStream(Stream stream);

    void deleteStream(int id);
}
