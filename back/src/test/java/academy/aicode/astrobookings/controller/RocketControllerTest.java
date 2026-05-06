package academy.aicode.astrobookings.controller;

import academy.aicode.astrobookings.model.Rocket;
import academy.aicode.astrobookings.model.RocketRange;
import academy.aicode.astrobookings.service.RocketService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RocketController.class)
public class RocketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RocketService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldReturnAllRockets() throws Exception {
        Rocket rocket = new Rocket(UUID.randomUUID(), "Falcon 9", 7, RocketRange.Earth);
        when(service.getAllRockets()).thenReturn(List.of(rocket));

        mockMvc.perform(get("/rockets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Falcon 9"));
    }

    @Test
    public void shouldCreateRocket() throws Exception {
        Rocket rocket = new Rocket(null, "Falcon Heavy", 9, RocketRange.Mars);
        Rocket created = new Rocket(UUID.randomUUID(), "Falcon Heavy", 9, RocketRange.Mars);
        
        when(service.createRocket(any(Rocket.class))).thenReturn(created);

        mockMvc.perform(post("/rockets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(rocket)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Falcon Heavy"));
    }

    @Test
    public void shouldReturnBadRequestWhenInvalidData() throws Exception {
        Rocket rocket = new Rocket(null, "", 10, null);
        
        when(service.createRocket(any(Rocket.class))).thenThrow(new IllegalArgumentException("Invalid data"));

        mockMvc.perform(post("/rockets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(rocket)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnNotFoundWhenRocketDoesNotExist() throws Exception {
        UUID id = UUID.randomUUID();
        when(service.getRocketById(id)).thenReturn(Optional.empty());

        mockMvc.perform(get("/rockets/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldDecommissionRocket() throws Exception {
        UUID id = UUID.randomUUID();
        doNothing().when(service).decommissionRocket(id);

        mockMvc.perform(delete("/rockets/" + id))
                .andExpect(status().isNoContent());
    }
}
