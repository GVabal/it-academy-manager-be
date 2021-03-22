package lt.akademija.itacademymanager.controller;

import lt.akademija.itacademymanager.exception.profilepicture.ProfilePictureNotFoundException;
import lt.akademija.itacademymanager.model.ProfilePicture;
import lt.akademija.itacademymanager.service.ProfilePictureService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProfilePictureController.class)
class ProfilePictureControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfilePictureService profilePictureService;

    @Test
    void getPicture_success() throws Exception {
        ProfilePicture picture = new ProfilePicture("".getBytes());
        when(profilePictureService.getPictureById(1)).thenReturn(picture);

        mockMvc.perform(get("/api/profile-pictures/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getPicture_failure_notFound() throws Exception {
        when(profilePictureService.getPictureById(anyInt())).thenThrow(ProfilePictureNotFoundException.class);

        mockMvc.perform(get("/api/profile-pictures/1"))
                .andExpect(status().isNotFound())
                .andExpect(result ->
                        assertTrue(result.getResolvedException() instanceof ProfilePictureNotFoundException));
    }
}
