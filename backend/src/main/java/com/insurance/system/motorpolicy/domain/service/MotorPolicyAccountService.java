package com.insurance.system.motorpolicy.domain.service;


import com.insurance.system.shared.domain.payload.PolicyReportFilterRequest;

public interface MotorPolicyAccountService {
  Double getTotalSumInsured();
  
  Double getTotalClaim();
  
  Double getTotalEndorsements();
  
  Double getTotalSumInsuredByPolicy(Long id);
  
  Double getTotalLossAmountByPolicy(Long id);
  
  long getRePolicyCount();
  long getRePolicyCount(PolicyReportFilterRequest policyReportFilterRequest );
  
  long getRePolicyClaimsCount();
  long getRePolicyClaimsCount(PolicyReportFilterRequest policyReportFilterRequest);
  
  long getRePolicyEndorsementsCount();
  
  String getLastClaimsTest(Long paramLong);


  long getRePolicyEndorsementsCount(PolicyReportFilterRequest policyReportFilterRequest);

  Double getTotalSumInsured(PolicyReportFilterRequest policyReportFilterRequest);

  Double getTotalPremium(PolicyReportFilterRequest policyReportFilterRequest);

  Double getTotalClaim(PolicyReportFilterRequest policyReportFilterRequest);
}
