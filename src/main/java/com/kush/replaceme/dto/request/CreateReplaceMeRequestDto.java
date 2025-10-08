package com.kush.replaceme.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Builder;


@Builder
public record CreateReplaceMeRequestDto(


        @NotEmpty @Size(max = 80) String name
) {

}
