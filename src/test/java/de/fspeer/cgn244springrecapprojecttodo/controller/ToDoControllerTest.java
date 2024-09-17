package de.fspeer.cgn244springrecapprojecttodo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.fspeer.cgn244springrecapprojecttodo.models.ToDo;
import de.fspeer.cgn244springrecapprojecttodo.models.ToDoDTO;
import de.fspeer.cgn244springrecapprojecttodo.service.ToDoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ToDoController.class)
class ToDoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ToDoService toDoService;

    @Autowired
    private ObjectMapper objectMapper;

    // Test für getAllToDos()
    @Test
    void testGetAllToDos() throws Exception {
        // Mock the service response
        ToDo toDo1 = new ToDo("1", "Test ToDo 1", "OPEN");
        ToDo toDo2 = new ToDo("2", "Test ToDo 2", "DONE");
        List<ToDo> toDoList = Arrays.asList(toDo1, toDo2);

        Mockito.when(toDoService.getAllToDos()).thenReturn(toDoList);

        // Perform the GET request
        mockMvc.perform(get("/api/todo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].description").value("Test ToDo 1"))
                .andExpect(jsonPath("$[1].description").value("Test ToDo 2"));
    }

    // Test für createToDo()
    @Test
    void testCreateToDo() throws Exception {
        // Mock the service response
        ToDoDTO toDoDTO = new ToDoDTO("New ToDo", "OPEN");
        ToDo createdToDo = new ToDo("1", toDoDTO.description(), toDoDTO.status());

        Mockito.when(toDoService.createToDo(any(ToDoDTO.class))).thenReturn(createdToDo);

        // Perform the POST request
        mockMvc.perform(post("/api/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(toDoDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.description").value("New ToDo"))
                .andExpect(jsonPath("$.status").value("OPEN"));
    }

    // Test für getDetailsToDoById()
    @Test
    void testGetDetailsToDoById() throws Exception {
        // Mock the service response
        ToDo toDo = new ToDo("1", "Test ToDo", "OPEN");

        Mockito.when(toDoService.getDetailsToDoById("1")).thenReturn(toDo);

        // Perform the GET request
        mockMvc.perform(get("/api/todo/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.description").value("Test ToDo"))
                .andExpect(jsonPath("$.status").value("OPEN"));
    }

    // Test für getDetailsToDoById() - Not Found
    @Test
    void testGetDetailsToDoById_NotFound() throws Exception {
        Mockito.when(toDoService.getDetailsToDoById("1"))
                .thenThrow(new NoSuchElementException());

        // Perform the GET request
        mockMvc.perform(get("/api/todo/1"))
                .andExpect(status().isNotFound());
    }

    // Test für editToDo()
    @Test
    void testEditToDo() throws Exception {
        // Mock the service response
        ToDo updatedToDo = new ToDo("1", "Updated ToDo", "DONE");

        Mockito.when(toDoService.editToDo(any(ToDo.class))).thenReturn(updatedToDo);

        // Perform the PUT request
        mockMvc.perform(put("/api/todo/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedToDo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.description").value("Updated ToDo"))
                .andExpect(jsonPath("$.status").value("DONE"));
    }

    // Test für deleteToDo()
    @Test
    void testDeleteToDo() throws Exception {
        // Mock the service response
        Mockito.when(toDoService.deleteToDo("1")).thenReturn("ToDo successfully deleted!");

        // Perform the DELETE request
        mockMvc.perform(delete("/api/todo/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("ToDo successfully deleted!"));
    }
}
