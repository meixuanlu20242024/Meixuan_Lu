package com.insurance.system.shared.usermanagement.payload;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class PasswordPolicyRequest {
  @Min(1L)
  private Integer minimum_length;
  
  @NotNull
  private Boolean ambiguous_chars;
  
  @NotNull
  private Boolean lower_chars;
  
  @NotNull
  private Boolean upper_chars;
  
  @NotNull
  private Boolean include_numbers;
  
  @NotNull
  private Boolean include_symbols;
  
  @Min(value = 1L, message = "Expiration days must be greater than zero")
  private Integer expiration_days;
  
  public PasswordPolicyRequest(Integer minimum_length, @NotNull Boolean ambiguous_chars, @NotNull Boolean lower_chars, @NotNull Boolean upper_chars, @NotNull Boolean include_numbers, @NotNull Boolean include_symbols, Integer expiration_days) {
    this.minimum_length = minimum_length;
    this.ambiguous_chars = ambiguous_chars;
    this.lower_chars = lower_chars;
    this.upper_chars = upper_chars;
    this.include_numbers = include_numbers;
    this.include_symbols = include_symbols;
    this.expiration_days = expiration_days;
  }
  
  public PasswordPolicyRequest() {}
  
  public void setMinimum_length(Integer minimum_length) {
    this.minimum_length = minimum_length;
  }
  
  public void setAmbiguous_chars(@NotNull Boolean ambiguous_chars) {
    this.ambiguous_chars = ambiguous_chars;
  }
  
  public void setLower_chars(@NotNull Boolean lower_chars) {
    this.lower_chars = lower_chars;
  }
  
  public void setUpper_chars(@NotNull Boolean upper_chars) {
    this.upper_chars = upper_chars;
  }
  
  public void setInclude_numbers(@NotNull Boolean include_numbers) {
    this.include_numbers = include_numbers;
  }
  
  public void setInclude_symbols(@NotNull Boolean include_symbols) {
    this.include_symbols = include_symbols;
  }
  
  public void setExpiration_days(Integer expiration_days) {
    this.expiration_days = expiration_days;
  }

  
  public String toString() {
    return "PasswordPolicyRequest(minimum_length=" + getMinimum_length() + ", ambiguous_chars=" + getAmbiguous_chars() + ", lower_chars=" + getLower_chars() + ", upper_chars=" + getUpper_chars() + ", include_numbers=" + getInclude_numbers() + ", include_symbols=" + getInclude_symbols() + ", expiration_days=" + getExpiration_days() + ")";
  }
  
  public Integer getMinimum_length() {
    return this.minimum_length;
  }
  
  @NotNull
  public Boolean getAmbiguous_chars() {
    return this.ambiguous_chars;
  }
  
  @NotNull
  public Boolean getLower_chars() {
    return this.lower_chars;
  }
  
  @NotNull
  public Boolean getUpper_chars() {
    return this.upper_chars;
  }
  
  @NotNull
  public Boolean getInclude_numbers() {
    return this.include_numbers;
  }
  
  @NotNull
  public Boolean getInclude_symbols() {
    return this.include_symbols;
  }
  
  public Integer getExpiration_days() {
    return this.expiration_days;
  }
}
