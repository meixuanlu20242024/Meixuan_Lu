package com.insurance.system.shared.domain.models;


import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;

    // Getters and Setters
}


