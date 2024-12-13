package com.insurance.system.shared.domain.service;


import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import com.insurance.system.shared.domain.models.PolicyName;
import com.insurance.system.shared.domain.payload.PageableObj;
import com.insurance.system.shared.domain.payload.PolicyNameDTO;
import com.insurance.system.shared.domain.payload.PolicyReportFilterRequest;

import java.util.Date;
import java.util.List;

public interface PolicyService {
  ResponseEntity<?> policiesReportData(PolicyReportFilterRequest policyReportFilterRequest);
  Double calculatePremium(Date paramDate1, Date paramDate2, Double paramDouble1, Double paramDouble2);
  
  Double calculateStampDuty(Double paramDouble);
  
  Double calculateGvtLevy(Double paramDouble1, Double paramDouble2);
  
  String isPolicyPeriodValid(Date paramDate1, Date paramDate2, Double paramDouble1, Double paramDouble2);

  ResponseEntity renewalCheck();

  ResponseEntity<?> setPolicyRates(List<PolicyNameDTO> policyNameDTOList);


  Page<PolicyName> AllPoliciesRatesPaginated(PageableObj pageableObj);

  ResponseEntity<?> listInvoices(PageableObj pageableObj, Long id, String policyName);

    ResponseEntity<?> adjustCommission(PolicyNameDTO policyNameDTo);

  ResponseEntity<?> listInvoicesByInsurer(PageableObj pageableObj, Long insurerId);
}
