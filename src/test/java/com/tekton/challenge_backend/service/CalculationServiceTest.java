package com.tekton.challenge_backend.service;

import com.tekton.challenge_backend.api.PercentageClient;
import com.tekton.challenge_backend.model.Calculation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CalculationServiceTest {
    @InjectMocks
    CalculationService mockCalculationService;

    @Mock
    LogsService mockLogsService;

    @Mock
    PercentageClient mockPercentageClient;

    @Test
    void testCalculatePercentage() {
        String endpoint = "POST /api/calculate";
        Calculation calculation = new Calculation(10.0, 20.0);

        when(mockPercentageClient.getPercentage()).thenReturn(10.0);

        ResponseEntity<?> response = mockCalculationService.calculatePercentage(calculation, endpoint);

        assertEquals(33.0, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(mockLogsService, times(1)).saveLog(endpoint, calculation.toString(), "33.0", null);
    }
}