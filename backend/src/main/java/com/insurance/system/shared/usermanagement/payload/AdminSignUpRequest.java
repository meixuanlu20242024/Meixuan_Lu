package com.insurance.system.shared.usermanagement.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class AdminSignUpRequest {
  private Long roles;
  
  private Long companyId;
  
  @NotBlank
  private String lastName;
  
  @NotBlank
  private String firstName;
  
  @Email
  private String email;
  
  @NotBlank
  private String nationalId;
  
  @NotBlank
  private String mobile;
  
  public AdminSignUpRequest(Long roles, Long companyId, String lastName, String firstName, String email, String nationalId, String mobile) {
    this.roles = roles;
    this.companyId = companyId;
    this.lastName = lastName;
    this.firstName = firstName;
    this.email = email;
    this.nationalId = nationalId;
    this.mobile = mobile;
  }
  
  public AdminSignUpRequest() {}
  
  public void setRoles(Long roles) {
    this.roles = roles;
  }
  
  public void setCompanyId(Long companyId) {
    this.companyId = companyId;
  }
  
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public void setNationalId(String nationalId) {
    this.nationalId = nationalId;
  }
  
  public void setMobile(String mobile) {
    this.mobile = mobile;
  }
  

  
  public String toString() {
    return "AdminSignUpRequest(roles=" + getRoles() + ", companyId=" + getCompanyId() + ", lastName=" + getLastName() + ", firstName=" + getFirstName() + ", email=" + getEmail() + ", nationalId=" + getNationalId() + ", mobile=" + getMobile() + ")";
  }
  
  public Long getRoles() {
    return this.roles;
  }
  
  public Long getCompanyId() {
    return this.companyId;
  }
  
  public String getLastName() {
    return this.lastName;
  }
  
  public String getFirstName() {
    return this.firstName;
  }
  
  public String getEmail() {
    return this.email;
  }
  
  public String getNationalId() {
    return this.nationalId;
  }
  
  public String getMobile() {
    return this.mobile;
  }
}
