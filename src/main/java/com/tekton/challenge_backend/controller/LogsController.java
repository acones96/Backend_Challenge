package com.tekton.challenge_backend.controller;

import com.tekton.challenge_backend.model.Logs;
import com.tekton.challenge_backend.service.LogsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LogsController {
    private static final Logger logger = LoggerFactory.getLogger(LogsController.class);
    private final LogsService logsService;

    public LogsController(LogsService logsService) {
        this.logsService = logsService;
    }

    @GetMapping("/history")
    public ResponseEntity<Page<Logs>> getHistory(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        logger.info("Getting history...");
        Pageable pageable = PageRequest.of(page, size, Sort.by("dateTime").descending());
        return ResponseEntity.ok(logsService.getLogs(pageable));
    }
}
