package com.micro.security;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "users")
public class UserEntity {

    @Id
    private String id;

    private String username;
    private String password;
    private List<String> roles; // Ejemplo: ["USER", "ADMIN"]
}