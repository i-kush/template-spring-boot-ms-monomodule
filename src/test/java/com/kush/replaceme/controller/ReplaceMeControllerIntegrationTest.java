package com.kush.replaceme.controller;

import com.kush.replaceme.BaseIntegrationTest;
import com.kush.replaceme.TestDataBuilder;
import com.kush.replaceme.dto.request.CreateReplaceMeRequestDto;
import com.kush.replaceme.dto.response.ReplaceMeResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class ReplaceMeControllerIntegrationTest extends BaseIntegrationTest {

    public static final String BASE_URL = "/api/replaceme";

    @Test
    void create() {
        CreateReplaceMeRequestDto request = TestDataBuilder.buildCreateReplaceMeDto();
        ResponseEntity<ReplaceMeResponseDto> response = restTemplate.postForEntity(BASE_URL,
                                                                                   buildRequest(request),
                                                                                   ReplaceMeResponseDto.class);

        Assertions.assertEquals(HttpStatus.CREATED.value(), response.getStatusCode().value());
        assertReplaceMe(request, response);
    }

    @Test
    void get() {
        CreateReplaceMeRequestDto request = TestDataBuilder.buildCreateReplaceMeDto();
        ResponseEntity<ReplaceMeResponseDto> createResponse = restTemplate.postForEntity(BASE_URL,
                                                                                         buildRequest(request),
                                                                                         ReplaceMeResponseDto.class);

        Assertions.assertEquals(HttpStatus.CREATED.value(), createResponse.getStatusCode().value());
        assertReplaceMe(request, createResponse);
        ResponseEntity<ReplaceMeResponseDto> getResponse = restTemplate.exchange(BASE_URL + "/{id}",
                                                                                 HttpMethod.GET,
                                                                                 buildRequest(request),
                                                                                 ReplaceMeResponseDto.class,
                                                                                 createResponse.getBody().id());

        Assertions.assertEquals(HttpStatus.OK.value(), getResponse.getStatusCode().value());
        assertReplaceMe(request, getResponse);
    }

    private void assertReplaceMe(CreateReplaceMeRequestDto request, ResponseEntity<ReplaceMeResponseDto> response) {
        ReplaceMeResponseDto responseBody = response.getBody();
        Assertions.assertNotNull(responseBody);
        Assertions.assertNotNull(responseBody.id());
        Assertions.assertEquals(request.name(), responseBody.name());
    }
}
