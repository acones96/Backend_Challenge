package com.tekton.challenge_backend.service;

import com.tekton.challenge_backend.api.PercentageClient;
import com.tekton.challenge_backend.model.Calculation;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CalculationService {
    private final PercentageClient percentageClient;
    private final LogsService logsService;

    public CalculationService(PercentageClient percentageClient, LogsService logsService) {
        this.percentageClient = percentageClient;
        this.logsService = logsService;
    }

    public ResponseEntity<?> calculatePercentage (Calculation request, String endpoint) {
        try {
            double sum = request.getNum1() + request.getNum2();
            double percentage = getPercentage();
            double result = sum + (sum * (percentage / 100));

            logsService.saveLog(endpoint, request.toString(), String.valueOf(result), null);

            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @Cacheable(value = "percentageCache", key = "'percentage'")
    public double getPercentage() {
        double percentage = percentageClient.getPercentage();
        updateCache(percentage);
        return percentage;
    }

    @CacheEvict(value = "percentageCache", key = "'percentage'")
    public void updateCache(double percentage) {
        System.out.println("Cache updated");
    }


}
