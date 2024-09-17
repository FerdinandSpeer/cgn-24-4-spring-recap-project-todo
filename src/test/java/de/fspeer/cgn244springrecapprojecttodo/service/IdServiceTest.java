package de.fspeer.cgn244springrecapprojecttodo.service;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class IdServiceTest {

    @Test
    void generateId() {
        // Call the method
        String id = new IdService().generateId();

        // Ensure the ID is not null or empty
        assertNotNull(id);
        assertFalse(id.isEmpty());

        // Ensure the ID is a valid UUID
        assertDoesNotThrow(() -> UUID.fromString(id));
    }

}