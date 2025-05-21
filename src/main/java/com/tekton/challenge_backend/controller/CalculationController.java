package com.tekton.challenge_backend.controller;

import com.tekton.challenge_backend.model.Calculation;
import com.tekton.challenge_backend.service.CalculationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CalculationController {
    private static final Logger logger = LoggerFactory.getLogger(CalculationController.class);
    private final CalculationService calculationService;

    public CalculationController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<?> calculate(@RequestBody Calculation request) {
            logger.info("Requesting calculation...");
            return calculationService.calculatePercentage(request, "POST /api/calculate");
    }
}
