package com.insurance.system.shared.domain.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.insurance.system.shared.domain.models.PoliciesEnum;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RenewalReminderBodyDTO implements Serializable {
    String policyNo;
    PoliciesEnum policyName;
    String insured;
    String email;

}
