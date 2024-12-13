package com.insurance.system.shared.usermanagement.payload;

public class PasswordPolicyResponse {
  private Integer minimum_length;
  
  private Boolean ambiguous_chars;
  
  private Boolean lower_chars;
  
  private Boolean upper_chars;
  
  private Boolean include_numbers;
  
  private Boolean include_symbols;
  
  private Integer expiration_days;
  
  public PasswordPolicyResponse() {}
  
  public void setMinimum_length(Integer minimum_length) {
    this.minimum_length = minimum_length;
  }
  
  public void setAmbiguous_chars(Boolean ambiguous_chars) {
    this.ambiguous_chars = ambiguous_chars;
  }
  
  public void setLower_chars(Boolean lower_chars) {
    this.lower_chars = lower_chars;
  }
  
  public void setUpper_chars(Boolean upper_chars) {
    this.upper_chars = upper_chars;
  }
  
  public void setInclude_numbers(Boolean include_numbers) {
    this.include_numbers = include_numbers;
  }
  
  public void setInclude_symbols(Boolean include_symbols) {
    this.include_symbols = include_symbols;
  }
  
  public void setExpiration_days(Integer expiration_days) {
    this.expiration_days = expiration_days;
  }

  
  public String toString() {
    return "PasswordPolicyResponse(minimum_length=" + getMinimum_length() + ", ambiguous_chars=" + getAmbiguous_chars() + ", lower_chars=" + getLower_chars() + ", upper_chars=" + getUpper_chars() + ", include_numbers=" + getInclude_numbers() + ", include_symbols=" + getInclude_symbols() + ", expiration_days=" + getExpiration_days() + ")";
  }
  
  public Integer getMinimum_length() {
    return this.minimum_length;
  }
  
  public Boolean getAmbiguous_chars() {
    return this.ambiguous_chars;
  }
  
  public Boolean getLower_chars() {
    return this.lower_chars;
  }
  
  public Boolean getUpper_chars() {
    return this.upper_chars;
  }
  
  public Boolean getInclude_numbers() {
    return this.include_numbers;
  }
  
  public Boolean getInclude_symbols() {
    return this.include_symbols;
  }
  
  public Integer getExpiration_days() {
    return this.expiration_days;
  }
  
  public PasswordPolicyResponse(Integer minimum_length, Boolean ambiguous_chars, Boolean lower_chars, Boolean upper_chars, Boolean include_numbers, Boolean include_symbols, Integer expiration_days) {
    this.minimum_length = minimum_length;
    this.ambiguous_chars = ambiguous_chars;
    this.lower_chars = lower_chars;
    this.upper_chars = upper_chars;
    this.include_numbers = include_numbers;
    this.include_symbols = include_symbols;
    this.expiration_days = expiration_days;
  }
}
