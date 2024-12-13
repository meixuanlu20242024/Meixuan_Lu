package com.insurance.system.shared.usermanagement.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MobileAuthResponse {

    @NotBlank
    private boolean success ;
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String token;

    @NotBlank
    private Long userId ;

    @NotBlank
    private String username ;





}
