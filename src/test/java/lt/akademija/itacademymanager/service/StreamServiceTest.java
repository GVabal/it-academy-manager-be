package lt.akademija.itacademymanager.service;

import lt.akademija.itacademymanager.exception.stream.StreamAlreadyExistsException;
import lt.akademija.itacademymanager.exception.stream.StreamNotFoundException;
import lt.akademija.itacademymanager.model.Stream;
import lt.akademija.itacademymanager.repository.StreamRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class StreamServiceTest {

    @InjectMocks
    StreamService streamService;

    @Mock
    StreamRepository streamRepository;

    @Before
    public void initMocks() {
        MockitoAnnotations.openMocks(this);
    }

    Stream stream1 = new Stream("BB");
    Stream stream2 = new Stream("CC");

    @Test
    public void shouldGetListOfStreamsSuccessfully() {
        List<Stream> streams = new ArrayList<>();
        streams.add(stream1);
        streams.add(stream2);
        when(streamRepository.findAll()).thenReturn(streams);
        List<Stream> streamList = streamService.getAllStreams();

        assertEquals(2, streamList.size());
        verify(streamRepository, times(1)).findAll();
    }

    @Test
    public void shouldAddStreamSuccessfully() {
        when(streamRepository.save(any())).thenReturn(stream1);
        Stream returnedStream = streamService.addStream(stream1);

        assertEquals(stream1, returnedStream);
        verify(streamRepository, times(1)).save(any());
    }

    @Test
    public void shouldThrowException_WhenSavingExistingStream() {
        stream1.setId(1);
        streamRepository.save(stream1);
        when(streamRepository.getStreamByName(stream1.getName())).thenReturn(stream1);

        assertThrows(StreamAlreadyExistsException.class, () -> streamService.addStream(stream1));
        verify(streamRepository, times(1)).save(any());
        verify(streamRepository, times(1)).getStreamByName(any());
    }

    @Test
    public void shouldDeleteStreamById() {
        stream1.setId(1);
        streamRepository.save(stream1);
        when(streamRepository.existsById(stream1.getId())).thenReturn(true);
        when(streamRepository.findById(stream1.getId())).thenReturn(Optional.of(stream1));
        boolean beforeDelete = streamRepository.findById(1).isPresent();
        streamService.deleteStream(stream1.getId());
        when(streamRepository.findById(stream1.getId())).thenReturn(Optional.empty());
        boolean afterDelete = streamRepository.findById(stream1.getId()).isPresent();

        assertTrue(beforeDelete);
        assertFalse(afterDelete);
        verify(streamRepository, times(1)).deleteById(any());
        verify(streamRepository, times(1)).save(any());
        verify(streamRepository, times(2)).findById(any());
    }

    @Test
    public void shouldThrowException_WhenTryingDeleteNonExisting() {
        stream1.setId(1);
        streamRepository.save(stream1);
        assertThrows(StreamNotFoundException.class, () -> streamService.deleteStream(1));
        verify(streamRepository,times(1)).save(any());
    }

    @Test
    public void shouldReturnExistingStreamById() {
        stream1.setId(1);
        streamRepository.save(stream1);
        when(streamRepository.findById(stream1.getId())).thenReturn(Optional.ofNullable(stream1));
        Stream returnedStream = streamService.getStreamById(stream1.getId());

        assertEquals(stream1, returnedStream);
        verify(streamRepository,times(1)).save(any());
        verify(streamRepository,times(1)).findById(any());
    }

    @Test
    public void shouldThrowException_WhenTryingToGetNonExistingStreamById() {
        stream1.setId(1);
        assertThrows(StreamNotFoundException.class, () -> streamService.getStreamById(1));
        verify(streamRepository, times(1)).findById(any());
    }
}