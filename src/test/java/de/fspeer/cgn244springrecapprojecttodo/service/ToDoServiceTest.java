package de.fspeer.cgn244springrecapprojecttodo.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import de.fspeer.cgn244springrecapprojecttodo.models.ToDo;
import de.fspeer.cgn244springrecapprojecttodo.models.ToDoDTO;
import de.fspeer.cgn244springrecapprojecttodo.repository.ToDoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.NoSuchElementException;
import java.util.Optional;


class ToDoServiceTest {

    @Mock
    private IdService idService;

    @Mock
    private ToDoRepository toDoRepository;

    @InjectMocks
    private ToDoService toDoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test für createToDo()
    @Test
    void testCreateToDo() {
        // Mock input DTO and generated ID
        ToDoDTO toDoDTO = new ToDoDTO("New ToDo", "OPEN");
        when(idService.generateId()).thenReturn("1234");

        // Mock repository save method
        ToDo toDo = new ToDo("1234", toDoDTO.description(), toDoDTO.status());
        when(toDoRepository.save(any(ToDo.class))).thenReturn(toDo);

        // Call the method
        ToDo result = toDoService.createToDo(toDoDTO);

        // Assert the result
        assertNotNull(result);
        assertEquals("1234", result.id());
        assertEquals("New ToDo", result.description());
        assertEquals("OPEN", result.status());
    }

    // Test für getDetailsToDoById()
    @Test
    void testGetDetailsToDoById() {
        // Mock a valid ToDo object
        ToDo toDo = new ToDo("1", "Test description", "OPEN");
        when(toDoRepository.findById("1")).thenReturn(Optional.of(toDo));

        // Call the method
        ToDo result = toDoService.getDetailsToDoById("1");

        // Assert the result
        assertNotNull(result);
        assertEquals("1", result.id());
        assertEquals("Test description", result.description());
    }

    // Test für getDetailsToDoById() when ToDo not found
    @Test
    void testGetDetailsToDoById_NotFound() {
        // Mock repository returning an empty Optional
        when(toDoRepository.findById("1")).thenReturn(Optional.empty());

        // Call the method and expect an exception
        assertThrows(NoSuchElementException.class, () -> toDoService.getDetailsToDoById("1"));
    }

    // Test für editToDo()
    @Test
    void testEditToDo() {
        // Mock an edited ToDo
        ToDo editedToDo = new ToDo("1", "Updated description", "DONE");
        when(toDoRepository.save(editedToDo)).thenReturn(editedToDo);

        // Call the method
        ToDo result = toDoService.editToDo(editedToDo);

        // Assert the result
        assertNotNull(result);
        assertEquals("1", result.id());
        assertEquals("Updated description", result.description());
        assertEquals("DONE", result.status());
    }

    // Test für deleteToDo()
    @Test
    void testDeleteToDo() {
        // Mock the repository deleteById method
        doNothing().when(toDoRepository).deleteById("1");

        // Call the method
        String result = toDoService.deleteToDo("1");

        // Assert the result
        assertEquals("ToDo successfully deleted!", result);

        // Verify deleteById was called
        verify(toDoRepository, times(1)).deleteById("1");
    }
}
