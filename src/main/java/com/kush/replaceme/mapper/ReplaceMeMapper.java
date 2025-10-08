package com.kush.replaceme.mapper;

import com.kush.replaceme.dto.request.CreateReplaceMeRequestDto;
import com.kush.replaceme.dto.response.ReplaceMeResponseDto;
import com.kush.replaceme.entity.ReplaceMe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public abstract class ReplaceMeMapper {

    @Mapping(target = "id", ignore = true)
    public abstract ReplaceMe toReplaceMeEntity(CreateReplaceMeRequestDto createReplaceMeDto);

    public abstract ReplaceMeResponseDto toGetReplaceMeDto(ReplaceMe replaceMeEntity);
}
