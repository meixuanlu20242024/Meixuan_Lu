package com.insurance.system.shared.domain.models.reporting;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class BordeauxPolicySummary {


    private Double totalAmount;
    public BordeauxPolicySummary( Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
