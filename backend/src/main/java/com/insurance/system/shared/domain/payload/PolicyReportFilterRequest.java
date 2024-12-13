package com.insurance.system.shared.domain.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import com.insurance.system.shared.domain.models.Currency;
import com.insurance.system.shared.domain.models.PolicyName;
import com.insurance.system.shared.domain.models.RetailClient;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PolicyReportFilterRequest {

  private int period = 0;
  private Currency currency;
  private int month = 0;
  private String year;
  private RetailClient insured;
  @NonNull
  private PolicyName policyType;




}
