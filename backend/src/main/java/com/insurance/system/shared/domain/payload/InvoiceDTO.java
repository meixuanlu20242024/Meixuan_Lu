package com.insurance.system.shared.domain.payload;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDTO {

    private long id;
    private String createdAt;
    private String createdBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date paymentDate;
    private double paymentAmount;
    @NotBlank
    private String paymentMethod;
    @NotBlank
    private String paymentReference;
    @NotBlank
    private String policyType;
    private long policyId;
    private String policyName;
    private long insurerId;
    private String insurerName;
    private String insurerAddress;


}


