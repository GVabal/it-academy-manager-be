package lt.akademija.itacademymanager.controller;

import lombok.AllArgsConstructor;
import lt.akademija.itacademymanager.model.Stream;
import lt.akademija.itacademymanager.payload.StreamNewRequest;
import lt.akademija.itacademymanager.service.StreamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<Stream> addStream(@RequestBody @Valid StreamNewRequest request) {
        streamService.addStream(request.toStream());
        return new ResponseEntity<>(request.toStream(), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteStream(@RequestBody @Valid StreamNewRequest request) {
        streamService.deleteStream(request.toStream());
        return ResponseEntity.ok().build();
    }
}