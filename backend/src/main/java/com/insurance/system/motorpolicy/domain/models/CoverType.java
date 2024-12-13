package com.insurance.system.motorpolicy.domain.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.insurance.system.shared.utils.EnumSerializerCIB;

@JsonSerialize(using = EnumSerializerCIB.class)
public enum CoverType {
  Comprehensive("Comprehensive"),
  RTA("RTA"),
  Full_Third_Party("Full Third Party"),
  Full_Third_Party_Fire_And_Theft("Full Third Part Fire and Theft");
  
  private final String coverType;
  
  CoverType(String coverType) {
    this.coverType = coverType;
  }
  
  public String getCoverType() {
    return this.coverType;
  }
}