package lt.akademija.itacademymanager.controller;

import lombok.AllArgsConstructor;
import lt.akademija.itacademymanager.exception.stream.StreamAlreadyExistsException;
import lt.akademija.itacademymanager.exception.stream.StreamNotFoundException;
import lt.akademija.itacademymanager.model.Stream;
import lt.akademija.itacademymanager.service.StreamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/streams")
public class StreamController {


    private final StreamService streamService;

    @GetMapping
    public ResponseEntity<List<Stream>> getStreams() {
        return new ResponseEntity<>(streamService.getAllStreams(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addStream(@RequestBody Stream stream) throws StreamAlreadyExistsException {
        streamService.addStream(stream);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteStream(@RequestBody Stream stream) throws StreamNotFoundException {
        streamService.deleteStream(stream);
        return ResponseEntity.ok().build();
    }
}
