package lt.akademija.itacademymanager.service;

import lombok.AllArgsConstructor;
import lt.akademija.itacademymanager.exception.profilepicture.ProfilePictureFileSizeTooLargeException;
import lt.akademija.itacademymanager.exception.profilepicture.ProfilePictureInvalidException;
import lt.akademija.itacademymanager.exception.profilepicture.ProfilePictureNotFoundException;
import lt.akademija.itacademymanager.model.ProfilePicture;
import lt.akademija.itacademymanager.repository.ProfilePictureRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@AllArgsConstructor
@Service
public class ProfilePictureService {
    private static final long MAX_FILE_SIZE_BYTES = 500000;

    private final ProfilePictureRepository profilePictureRepository;

    public ProfilePicture getPictureById(int id) {
        return profilePictureRepository.findById(id)
                .orElseThrow(() -> new ProfilePictureNotFoundException("No picture with id " + id));
    }

    public ProfilePicture storePicture(byte[] picture) throws IOException {
//        validateProfilePictureFile(picture);
        ProfilePicture profilePicture = new ProfilePicture(picture);
        return profilePictureRepository.save(profilePicture);
    }

    protected void validateProfilePictureFile(MultipartFile file) {
        if (file.isEmpty() || !file.getContentType().contains("image/")) {
            throw new ProfilePictureInvalidException();
        }
        if (file.getSize() > MAX_FILE_SIZE_BYTES) {
            throw new ProfilePictureFileSizeTooLargeException();
        }
    }
}
