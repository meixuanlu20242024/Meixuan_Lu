package com.insurance.system.shared.domain.models.datatables;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Search {
  private String value;
  
  private String regex;
  
  @JsonProperty("value")
  public String getValue() {
    return this.value;
  }
  
  @JsonProperty("value")
  public void setValue(String value) {
    this.value = value;
  }
  
  @JsonProperty("regex")
  public String getRegex() {
    return this.regex;
  }
  
  @JsonProperty("regex")
  public void setRegex(String value) {
    this.regex = value;
  }
}
