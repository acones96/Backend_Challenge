package com.tekton.challenge_backend.controller;

import com.tekton.challenge_backend.model.Calculation;
import com.tekton.challenge_backend.service.CalculationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CalculationControllerTest {
    @Mock
    CalculationService mockCalculationService;

    @InjectMocks
    CalculationController mockCalculationController;

    @Mock
    Calculation mockCalculation;


    @Test
    void testCalculate() {
        Calculation request = new Calculation(10.0, 20.0);
        String endpoint = "POST /api/calculate";

        when(mockCalculationService.calculatePercentage(request, endpoint)).thenReturn(new ResponseEntity(33.0, HttpStatus.OK));

        ResponseEntity<?> response = mockCalculationController.calculate(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(33.0, response.getBody());

        verify(mockCalculationService, times(1)).calculatePercentage(request, endpoint);

    }
}