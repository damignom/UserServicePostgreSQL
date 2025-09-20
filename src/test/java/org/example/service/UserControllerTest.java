package org.example.service;

import org.example.controller.UserController;
import org.example.dtos.UserDTO;
import org.example.mappers.UserMapper;
import org.example.models.User;
import org.example.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService  userService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testGetUserById() throws Exception {
        UserDTO userDTO = new UserDTO(1, "Roma", "roma@example.com", 26, LocalDateTime.now());
        when(userService.getUser(1L)).thenReturn(userDTO);

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Roma"))
                .andExpect(jsonPath("$.age").value(26))
                .andExpect(jsonPath("$.email").value("roma@example.com"));

        verify(userService, times(1)).getUser(1L);
    }

    @Test
    void testGetAllUsers() throws Exception{
        UserDTO userDTO1 = new UserDTO(1, "Roma", "roma@example.com", 26, LocalDateTime.now());
        UserDTO userDTO2 = new UserDTO(2, "Nast", "Nast@example.com", 25, LocalDateTime.now());

        when(userService.getAllUsers()).thenReturn(List.of(userDTO1, userDTO2));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Roma"))
                .andExpect(jsonPath("$[1].name").value("Nast"));

    }

    @Test
    void testCreateUser() throws Exception {
        UserDTO userDTO = new UserDTO(1, "Roma", "roma@example.com", 26, null);
        UserDTO createdUser = new UserDTO(1, "Roma", "roma@example.com", 26, LocalDateTime.now());

        when(userService.createUser(any(UserDTO.class))).thenReturn(createdUser);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Roma"));

        verify(userService, times(1)).createUser(any(UserDTO.class));
    }

    @Test
    void testDeleteUser() throws Exception {
        doNothing().when(userService).deleteUser(1L);

        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isNoContent());

        verify(userService, times(1)).deleteUser(1L);
    }
}
