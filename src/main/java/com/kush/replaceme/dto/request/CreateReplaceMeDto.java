package com.kush.replaceme.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record CreateReplaceMeDto(
        @NotEmpty @Size(max = 20) String name
) {

}
