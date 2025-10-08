package com.kush.replaceme;

import com.kush.replaceme.dto.request.CreateReplaceMeRequestDto;

import java.util.UUID;

public final class TestDataBuilder {

    private TestDataBuilder() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static CreateReplaceMeRequestDto buildCreateReplaceMeDto() {
        return CreateReplaceMeRequestDto.builder()
                                        .name(UUID.randomUUID() + "-test-name")
                                        .build();
    }
}
