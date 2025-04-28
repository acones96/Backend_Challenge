package com.tekton.challenge_backend.service;

import com.tekton.challenge_backend.model.Logs;
import com.tekton.challenge_backend.repository.LogsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LogsServiceTest {
    @InjectMocks
    LogsService mockLogsService;

    @Mock
    LogsRepository mockLogsRepository;

    @Mock
    Pageable mockPageable;

    @Mock
    Page<Logs> mockPageLogs;

    @Test
    void testSaveLog() {
        Logs log = new Logs(null, LocalDateTime.now(), "POST /api/calculate", "num1=10.0, num2=20.0", "33.0", null);

        mockLogsService.saveLog(log.getEndpoint(), log.getParameter(), log.getResponse(), log.getError());

        verify(mockLogsRepository, times(1)).save(log);
    }

    @Test
    void testGetLogs() {
        when(mockLogsRepository.findAll(mockPageable)).thenReturn(mockPageLogs);
        Page<Logs> response = mockLogsService.getLogs(mockPageable);

        assertEquals(mockPageLogs, response);
        verify(mockLogsRepository,times(1)).findAll(mockPageable);
    }
}