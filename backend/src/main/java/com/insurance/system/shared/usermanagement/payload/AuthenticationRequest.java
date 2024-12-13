package com.insurance.system.shared.usermanagement.payload;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String username;
    private String password;
}

