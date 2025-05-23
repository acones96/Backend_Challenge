package com.tekton.challenge_backend.service;

import com.tekton.challenge_backend.model.Logs;
import com.tekton.challenge_backend.repository.LogsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class LogsService {
    private static final Logger logger = LoggerFactory.getLogger(LogsService.class);
    private final LogsRepository logsRepository;

    public LogsService(LogsRepository logsRepository) {
        this.logsRepository = logsRepository;
    }

    @Async
    public void saveLog (String endpoint, String parameter, String response, String error) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        Logs log = Logs.builder()
                .dateTime(LocalDateTime.now().format(formatter))
                .endpoint(endpoint)
                .parameter(parameter)
                .response(response)
                .error(error)
                .build();
        logsRepository.save(log);
        logger.info("Log saved successfully!");
    }

    public Page<Logs> getLogs (Pageable pageable) {
        return logsRepository.findAll(pageable);
    }
}
