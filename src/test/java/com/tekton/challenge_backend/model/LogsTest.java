package com.tekton.challenge_backend.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LogsTest {

    @Test
    void testSettersAndGetters() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        Logs logs = new Logs();
        logs.setDateTime(LocalDateTime.of(1,1,1,0,0).format(formatter));
        logs.setEndpoint("POST /api");
        logs.setParameter("num1=20.0, num2=30.0");
        logs.setResponse("55.0");
        logs.setError(null);

        assertEquals(LocalDateTime.of(1,1,1,0,0).format(formatter), logs.getDateTime());
        assertEquals("POST /api", logs.getEndpoint());
        assertEquals("num1=20.0, num2=30.0", logs.getParameter());
        assertEquals("55.0", logs.getResponse());
        assertNull(logs.getError());
    }
}