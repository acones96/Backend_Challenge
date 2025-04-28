package com.tekton.challenge_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Calculation {
    private double num1;
    private double num2;

    @Override
    public String toString() {
        return  "num1=" + num1 + ", num2=" + num2;
    }
}
