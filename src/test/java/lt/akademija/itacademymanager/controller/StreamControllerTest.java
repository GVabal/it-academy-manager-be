package lt.akademija.itacademymanager.controller;

import lt.akademija.itacademymanager.exception.stream.StreamAlreadyExistsException;
import lt.akademija.itacademymanager.exception.stream.StreamNotFoundException;
import lt.akademija.itacademymanager.model.Stream;
import lt.akademija.itacademymanager.service.StreamService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(StreamController.class)
@SpringBootTest
@AutoConfigureMockMvc
class StreamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StreamService streamService;

    @Test
    void getStreams_success() throws Exception {
        when(streamService.getAllStreams()).thenReturn(List.of());

        mockMvc.perform(get("/api/streams"))
                .andExpect(status().isOk())
                .andExpect(result ->
                        assertEquals(MediaType.APPLICATION_JSON_VALUE, result.getResponse().getContentType()))
                .andExpect(result ->
                        assertEquals("[]", result.getResponse().getContentAsString()));
    }

    @Test
    void addStream_success() throws Exception {
        Stream stream = new Stream("test");
        stream.setId(1);
        when(streamService.addStream(any())).thenReturn(stream);

        mockMvc.perform(post("/api/streams")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"test\"}"))
                .andExpect(status().isCreated())
                .andExpect(result ->
                        assertEquals(MediaType.APPLICATION_JSON_VALUE, result.getResponse().getContentType()))
                .andExpect(result ->
                        assertEquals("{\"id\":1,\"name\":\"test\"}", result.getResponse().getContentAsString()));
    }

    @Test
    void addStream_failure_noRequestBody() throws Exception {
        mockMvc.perform(post("/api/streams"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void addStream_failure_invalidRequestBody() throws Exception {
        mockMvc.perform(post("/api/streams")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(result ->
                        assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException));
    }

    @Test
    void addStream_failure_streamAlreadyExists() throws Exception {
        when(streamService.addStream(any())).thenThrow(StreamAlreadyExistsException.class);

        mockMvc.perform(post("/api/streams")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"test\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(result ->
                        assertTrue(result.getResolvedException() instanceof StreamAlreadyExistsException));
    }

    @Test
    void deleteStream_success() throws Exception {
        doNothing().when(streamService).deleteStream(anyInt());

        mockMvc.perform(delete("/api/streams/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteStream_failure_doesNotExist() throws Exception {
        doThrow(StreamNotFoundException.class).when(streamService).deleteStream(anyInt());

        mockMvc.perform(delete("/api/streams/1"))
                .andExpect(status().isNotFound())
                .andExpect(result ->
                        assertTrue(result.getResolvedException() instanceof StreamNotFoundException));
    }
}
