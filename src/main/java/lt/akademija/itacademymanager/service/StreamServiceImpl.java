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
    public Stream addStream(Stream stream) throws StreamAlreadyExistsException {
        if (streamRepository.getStreamByName(stream.getName()) != null) {
            throw new StreamAlreadyExistsException(stream.getName());
        }
        return streamRepository.save(stream);
    }

    @Override
    public void deleteStream(int id) throws StreamNotFoundException {
        if (!streamRepository.existsById(id)) {
            throw new StreamNotFoundException(id);
        }
        streamRepository.deleteById(id);

    }
}
