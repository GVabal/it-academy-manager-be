package lt.akademija.itacademymanager.controller;

import lombok.AllArgsConstructor;
import lt.akademija.itacademymanager.model.Stream;
import lt.akademija.itacademymanager.payload.request.StreamNewRequest;
import lt.akademija.itacademymanager.service.StreamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/streams")
public class StreamController {

    private final StreamService streamService;

    @PreAuthorize("hasAnyRole('ADMIN', 'LECTURER', 'MANAGER')")
    @GetMapping
    public ResponseEntity<List<Stream>> getStreams() {
        return new ResponseEntity<>(streamService.getAllStreams(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Stream> addStream(@RequestBody @Valid StreamNewRequest request) {
        return new ResponseEntity<>(streamService.addStream(request.toStream()), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStream(@PathVariable("id") int id) {
        streamService.deleteStream(id);
        return ResponseEntity.noContent().build();
    }
}
