package com.micro.security;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo  extends MongoRepository<UserEntity, String> {
    Optional<UserEntity> findByUsername(String username);
}
