package com.insurance.system.shared.domain.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.insurance.system.shared.domain.models.Currency;
import com.insurance.system.shared.domain.models.RetailClient;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PolicyFilterRequest {
  public String keywords;
  public RetailClient insured;
  public Currency currency;
  public PolicyFilterDateRangeRequest policyFilterDateRangeRequest;
  public PolicyFilterRequest(String keywords, RetailClient insured, Currency currency ){
    this.keywords = keywords;
    this.insured = insured;
    this.currency = currency;
  }

  public PolicyFilterRequest(Currency currency, PolicyFilterDateRangeRequest policyFilterDateRangeRequest){
    this.currency = currency;
    this.policyFilterDateRangeRequest = policyFilterDateRangeRequest;
  }





}
