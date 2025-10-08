package com.kush.replaceme.service;

import com.kush.replaceme.config.RedisConfig;
import com.kush.replaceme.dto.request.CreateReplaceMeRequestDto;
import com.kush.replaceme.dto.response.ReplaceMeResponseDto;
import com.kush.replaceme.entity.ReplaceMe;
import com.kush.replaceme.exception.NotFoundException;
import com.kush.replaceme.mapper.ReplaceMeMapper;
import com.kush.replaceme.repository.ReplaceMeRepository;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReplaceMeService {

    private final ReplaceMeRepository replaceMeRepository;
    private final ReplaceMeMapper replaceMeConverter;

    @Transactional
    public ReplaceMeResponseDto createReplaceMe(CreateReplaceMeRequestDto createReplaceMeDto) {
        ReplaceMe replaceMeEntity = replaceMeConverter.toReplaceMeEntity(createReplaceMeDto);
        ReplaceMe createdReplaceMeEntity = replaceMeRepository.save(replaceMeEntity);
        return replaceMeConverter.toGetReplaceMeDto(createdReplaceMeEntity);
    }

    @Cacheable(value = RedisConfig.CACHE_NAME_REPLACEME, key = "#id")
    @Transactional(readOnly = true)
    public ReplaceMeResponseDto findReplaceMeById(UUID id) {
        ReplaceMe replaceMeEntity = replaceMeRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("No such id " + id));

        return replaceMeConverter.toGetReplaceMeDto(replaceMeEntity);
    }
}
