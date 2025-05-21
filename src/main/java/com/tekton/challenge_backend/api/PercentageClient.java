package com.tekton.challenge_backend.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PercentageClient {
    private static final Logger logger = LoggerFactory.getLogger(PercentageClient.class);

    public double getPercentage() {
        logger.info("Requesting percentage...");
        return 10.0;
    }
}
