package com.kush.replaceme.repository;

import com.kush.replaceme.entity.ReplaceMe;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

public interface ReplaceMeRepository extends CrudRepository<ReplaceMe, UUID> {

}
