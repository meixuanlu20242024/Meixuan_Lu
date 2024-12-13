package com.insurance.system.shared.usermanagement.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PasswordPolicy {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(nullable = false)
  private Integer minimum_length;
  
  @Column(nullable = false)
  private Integer expiration_days;
  
  @Column(nullable = false)
  private Boolean ambiguous_chars;
  
  @Column(nullable = false)
  private Boolean lower_chars;
  
  @Column(nullable = false)
  private Boolean upper_chars;
  
  @Column(nullable = false)
  private Boolean include_numbers;
  
  @Column(nullable = false)
  private Boolean include_symbols;
  
  public Long getId() {
    return this.id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public Integer getMinimumLength() {
    return this.minimum_length;
  }
  
  public void setMinimum_length(Integer minimum_length) {
    this.minimum_length = minimum_length;
  }
  
  public Boolean getAmbiguousChars() {
    return this.ambiguous_chars;
  }
  
  public void setAmbiguous_chars(Boolean ambiguous_chars) {
    this.ambiguous_chars = ambiguous_chars;
  }
  
  public Boolean getLowerChars() {
    return this.lower_chars;
  }
  
  public void setLower_chars(Boolean lower_chars) {
    this.lower_chars = lower_chars;
  }
  
  public Boolean getUpperChars() {
    return this.upper_chars;
  }
  
  public void setUpper_chars(Boolean upper_chars) {
    this.upper_chars = upper_chars;
  }
  
  public Boolean getIncludeNumbers() {
    return this.include_numbers;
  }
  
  public void setInclude_numbers(Boolean include_numbers) {
    this.include_numbers = include_numbers;
  }
  
  public Boolean getInclude_symbols() {
    return this.include_symbols;
  }
  
  public void setInclude_symbols(Boolean include_symbols) {
    this.include_symbols = include_symbols;
  }
  
  public Integer getExpiration_days() {
    return this.expiration_days;
  }
  
  public void setExpiration_days(Integer expiration_days) {
    this.expiration_days = expiration_days;
  }
}
