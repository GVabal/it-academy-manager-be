package lt.akademija.itacademymanager.controller;

import lombok.AllArgsConstructor;
import lt.akademija.itacademymanager.model.ProfilePicture;
import lt.akademija.itacademymanager.service.ProfilePictureService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("api/profile-pictures")
public class ProfilePictureController {
    private final ProfilePictureService profilePictureService;

    @PreAuthorize("hasAnyRole('ADMIN', 'LECTURER', 'MANAGER')")
    @GetMapping("{id}")
    public ResponseEntity<byte[]> getPicture(@PathVariable int id) {
        ProfilePicture profilePicture = profilePictureService.getPictureById(id);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(profilePicture.getBytes());
    }
}
