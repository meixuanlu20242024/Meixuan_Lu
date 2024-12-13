package com.insurance.system.shared.domain.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PolicyNameDTO {
    String policyName;
    String policyDescription;
    String policyCode;
    String avatar;
    double commissionRate;

}
