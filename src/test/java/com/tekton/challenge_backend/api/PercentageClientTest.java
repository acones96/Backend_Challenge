package com.tekton.challenge_backend.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PercentageClientTest {
    @InjectMocks
    PercentageClient mockPercentageClient;

    @Test
    void getPercentage() {
        double response = mockPercentageClient.getPercentage();

        assertEquals(10.0, response);

    }
}