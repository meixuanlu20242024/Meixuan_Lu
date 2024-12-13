package com.insurance.system.shared.domain.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class InsurerRequest {

    @NotBlank
    String name;
    @NotBlank
    String faxNumber;
    @NotBlank
    String address;
    @NotBlank
    String telephoneNumber;
    @NotBlank
    String mobileNumber;
    @NotBlank
    String email;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    Date incorporationDate;
    String registrationNumber;



}
