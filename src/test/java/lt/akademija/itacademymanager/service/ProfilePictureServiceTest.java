package lt.akademija.itacademymanager.service;

import lt.akademija.itacademymanager.exception.profilepicture.ProfilePictureFileSizeTooLargeException;
import lt.akademija.itacademymanager.exception.profilepicture.ProfilePictureInvalidException;
import lt.akademija.itacademymanager.exception.profilepicture.ProfilePictureNotFoundException;
import lt.akademija.itacademymanager.model.ProfilePicture;
import lt.akademija.itacademymanager.repository.ProfilePictureRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProfilePictureServiceTest {

    @InjectMocks
    ProfilePictureService profilePictureService;

    @Mock
    ProfilePictureRepository profilePictureRepository;

    @Test
    void getPictureById_success() {
        ProfilePicture picture = new ProfilePicture("".getBytes());
        when(profilePictureRepository.findById(1)).thenReturn(Optional.of(picture));

        ProfilePicture result = profilePictureService.getPictureById(1);

        assertThat(result).isEqualTo(picture);
    }

    @Test
    void getPictureById_failure_doesNotExist() {
        when(profilePictureRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ProfilePictureNotFoundException.class, () -> {
            profilePictureService.getPictureById(1);
        });
    }

    @Test
    void storePicture_success() throws IOException {
        MultipartFile multipartFile = mockMultipartImage();
        ProfilePicture profilePicture = new ProfilePicture(multipartFile.getBytes());
        when(profilePictureRepository.save(any())).thenReturn(profilePicture);

        ProfilePicture result = profilePictureService.storePicture(multipartFile);

        assertThat(result).isEqualTo(profilePicture);
    }

    @Test
    void storePicture_failure_emptyFile() {
        MultipartFile multipartFile = mockMultipartEmpty();

        assertThrows(ProfilePictureInvalidException.class, () -> {
           profilePictureService.storePicture(multipartFile);
        });
    }

    @Test
    void storePicture_failure_notPicture() {
        MultipartFile multipartFile = mockMultipartNonImage();

        assertThrows(ProfilePictureInvalidException.class, () -> {
            profilePictureService.storePicture(multipartFile);
        });
    }

    @Test
    void storePicture_failure_tooBig() throws IOException {
        MultipartFile multipartFile = mockMultipartLargeImage();

        assertThrows(ProfilePictureFileSizeTooLargeException.class, () -> {
            profilePictureService.storePicture(multipartFile);
        });
    }

    @Test
    void deletePicture_success() {
        ProfilePicture picture = new ProfilePicture("".getBytes());
        picture.setId(1);
        when(profilePictureRepository.findById(picture.getId())).thenReturn(Optional.of(picture));

        profilePictureService.deletePicture(picture.getId());

        verify(profilePictureRepository, times(1)).delete(picture);
    }

    @Test
    void deletePicture_failure_notFound() {
        when(profilePictureRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(ProfilePictureNotFoundException.class, () -> {
            profilePictureService.deletePicture(1);
        });
    }

    private MultipartFile mockMultipartImage() throws IOException {
        File pictureFile = new File("src/test/resources/profile-picture.png");
        FileInputStream input = new FileInputStream(pictureFile);
        return new MockMultipartFile(
                "profile-picture", pictureFile.getName(),
                "image/png", input.readAllBytes()
        );
    }

    private MultipartFile mockMultipartEmpty() {
        return new MockMultipartFile("empty", null, null, (byte[]) null);
    }

    private MultipartFile mockMultipartNonImage() {
        return new MockMultipartFile(
                "not-a-picture", null,
                "txt/plain", new byte[]{1,2,3}
        );
    }

    private MultipartFile mockMultipartLargeImage() throws IOException {
        File pictureFile = new File("src/test/resources/profile-picture-1mb.png");
        FileInputStream input = new FileInputStream(pictureFile);
        return new MockMultipartFile(
                "profile-picture-1mb", pictureFile.getName(),
                "image/png", input.readAllBytes()
        );
    }
}
