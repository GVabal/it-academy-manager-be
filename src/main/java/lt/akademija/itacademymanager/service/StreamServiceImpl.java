package lt.akademija.itacademymanager.service;


import lombok.AllArgsConstructor;
import lt.akademija.itacademymanager.exception.stream.StreamAlreadyExistsException;
import lt.akademija.itacademymanager.exception.stream.StreamNotFoundException;
import lt.akademija.itacademymanager.model.Stream;
import lt.akademija.itacademymanager.repository.StreamRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@AllArgsConstructor
@Service
public class StreamServiceImpl implements StreamService {

    StreamRepository streamRepository;

    @Override
    public List<Stream> getAllStreams() {
        return streamRepository.findAll();
    }

    @Override
    public void addStream(Stream stream) throws StreamAlreadyExistsException {
        if (streamRepository.getStreamByName(stream.getName()) != null) {
            throw new StreamAlreadyExistsException(stream.getName());
        }
        streamRepository.save(stream);
    }

    @Override
    public void deleteStream(Stream stream) throws StreamNotFoundException {
        if (streamRepository.getStreamByName(stream.getName()) == null) {
            throw new StreamNotFoundException(stream.getName());
        }
        streamRepository.deleteByName(stream.getName());

    }
}
