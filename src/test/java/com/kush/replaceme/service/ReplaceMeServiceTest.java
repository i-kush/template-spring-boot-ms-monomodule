package com.kush.replaceme.service;

import com.kush.replaceme.exception.NotFoundException;
import com.kush.replaceme.repository.ReplaceMeRepository;
import com.kush.replaceme.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

class ReplaceMeServiceTest extends BaseTest {

    @InjectMocks
    private ReplaceMeService replaceMeService;

    @Mock
    private ReplaceMeRepository replaceMeRepository;

    @Test
    void whenReplaceMeIsNotNotFoundByIdThenErrorShouldBeThrown() {
        Mockito.when(replaceMeRepository.findById(ArgumentMatchers.any(UUID.class))).thenReturn(Optional.empty());

        Assertions.assertThrows(
                NotFoundException.class,
                () -> replaceMeService.findReplaceMeById(UUID.randomUUID()),
                "When no info in DB then exception should be thrown"
        );
    }

}
