package com.tekton.challenge_backend.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CalculationTest {

    @Test
    void testToString() {
        Calculation calculation = new Calculation(10.0, 20.0);
        assertEquals("num1=10.0, num2=20.0", calculation.toString());
    }

    @Test
    void testSettersAndGetters() {
        Calculation calculation = new Calculation();
        calculation.setNum1(10.0);
        calculation.setNum2(20.0);

        assertEquals(10.0, calculation.getNum1());
        assertEquals(20.0, calculation.getNum2());
    }
}