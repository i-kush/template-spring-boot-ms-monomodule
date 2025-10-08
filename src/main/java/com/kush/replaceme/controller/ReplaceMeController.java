package com.kush.replaceme.controller;

import com.kush.replaceme.dto.request.CreateReplaceMeRequestDto;
import com.kush.replaceme.dto.response.ReplaceMeResponseDto;
import com.kush.replaceme.service.ReplaceMeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/replaceme")
@RequiredArgsConstructor
public class ReplaceMeController {

    private final ReplaceMeService replaceMeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReplaceMeResponseDto createReplaceMe(@Valid @RequestBody CreateReplaceMeRequestDto createReplaceMeDto) {
        return replaceMeService.createReplaceMe(createReplaceMeDto);
    }

    @GetMapping("{id}")
    public ReplaceMeResponseDto createReplaceMe(@PathVariable(name = "id") UUID id) {
        return replaceMeService.findReplaceMeById(id);
    }
}
