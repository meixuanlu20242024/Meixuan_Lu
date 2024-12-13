package com.insurance.system.shared.domain.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SanctionAmlCheckRequest {
    @NotBlank(message = "First Name is required")
    private String firstName;
    private String lastName;
    private String referenceNumber;
    private String nationality;

}