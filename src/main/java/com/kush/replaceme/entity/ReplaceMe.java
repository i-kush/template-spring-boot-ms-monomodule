package com.kush.replaceme.entity;

import lombok.Builder;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
@Builder
public record ReplaceMe(
        @Id
        UUID id,
        String name
) {
}
