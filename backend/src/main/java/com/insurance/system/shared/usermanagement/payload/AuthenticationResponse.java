package com.insurance.system.shared.usermanagement.payload;


import lombok.Data;

@Data
public class AuthenticationResponse {
    private final String jwt;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
