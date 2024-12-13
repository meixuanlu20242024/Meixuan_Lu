package com.insurance.system.shared.domain.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PolicyAccountingDT0 {



    Long policyCount;
    Long policyClaimCount;
    Long policyEndorsementsCount;
    Double totalSumInsured ;
    Double totalLoss ;
    Double totalEndorsements ;
    PolicyRenewalDatesResponse renewalDates ;
    Double totalPremium ;
    Double totalPremiumDue ;
    Double totalPremiumPaid ;
    Double totalPremiumOutstanding ;
    Double totalClaims ;
    Double totalClaimsPaid ;
    Double totalClaimsOutstanding ;



}
