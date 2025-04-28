package com.tekton.challenge_backend.controller;

import com.tekton.challenge_backend.model.Logs;
import com.tekton.challenge_backend.service.LogsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LogsControllerTest {

    @InjectMocks
    LogsController mockLogsController;

    @Mock
    LogsService mockLogsService;

    @Test
    void testGetHistory() {
        int page = 0;
        int size = 3;
        Pageable pageable = PageRequest.of(page, size, Sort.by("dateTime").descending());

        Logs log1 = new Logs(1L, LocalDateTime.of(1,1,1,1,1,1).toString(), "endpoint", "parameter", "response", null);
        Logs log2 = new Logs(2L, LocalDateTime.of(1,1,1,1,1,2).toString(), "endpoint", "parameter", null, "error");
        Logs log3 = new Logs(3L, LocalDateTime.of(1,1,1,1,1,3).toString(), "endpoint", "parameter", "response", null);

        List<Logs> logsList = List.of(log1, log2, log3);
        Page<Logs> logsPage = new PageImpl<>(logsList, pageable, logsList.size());

        when(mockLogsService.getLogs(pageable)).thenReturn(logsPage);

        ResponseEntity<Page<Logs>> response = mockLogsController.getHistory(page, size);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(3, response.getBody().getTotalElements());
        assertEquals(log1.getId(), response.getBody().getContent().getFirst().getId());

        verify(mockLogsService, times(1)).getLogs(pageable);
    }
}