package com.tekton.challenge_backend.service;

import com.tekton.challenge_backend.model.Logs;
import com.tekton.challenge_backend.repository.LogsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LogsService {
    private final LogsRepository logsRepository;

    public LogsService(LogsRepository logsRepository) {
        this.logsRepository = logsRepository;
    }

    @Async
    public void saveLog (String endpoint, String parameter, String response, String error) {
        Logs log = Logs.builder()
                .dateTime(LocalDateTime.now())
                .endpoint(endpoint)
                .parameter(parameter)
                .response(response)
                .error(error)
                .build();
        logsRepository.save(log);
    }

    public Page<Logs> getLogs (Pageable pageable) {
        return logsRepository.findAll(pageable);
    }
}
