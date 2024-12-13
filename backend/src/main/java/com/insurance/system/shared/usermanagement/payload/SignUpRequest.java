package com.insurance.system.shared.usermanagement.payload;

import com.insurance.system.shared.usermanagement.commons.Password;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class SignUpRequest {
  private String lastName;
  
  @NotBlank
  private String firstName;
  
  @Email
  private String email;
  
  @Password
  private String password;
  
  @NotBlank
  private String nationalId;
  
  private boolean textVerification;
  
  @NotBlank
  private String mobile;
  
  private Long roles;
  
  public SignUpRequest(String lastName, String firstName, String email, String password, String nationalId, boolean textVerification, String mobile, Long roles) {
    this.lastName = lastName;
    this.firstName = firstName;
    this.email = email;
    this.password = password;
    this.nationalId = nationalId;
    this.textVerification = textVerification;
    this.mobile = mobile;
    this.roles = roles;
  }
  
  public SignUpRequest() {}
  
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
  
  public void setNationalId(String nationalId) {
    this.nationalId = nationalId;
  }
  
  public void setTextVerification(boolean textVerification) {
    this.textVerification = textVerification;
  }
  
  public void setMobile(String mobile) {
    this.mobile = mobile;
  }
  
  public void setRoles(Long roles) {
    this.roles = roles;
  }

  
  public String toString() {
    return "SignUpRequest(lastName=" + getLastName() + ", firstName=" + getFirstName() + ", email=" + getEmail() + ", password=" + getPassword() + ", nationalId=" + getNationalId() + ", textVerification=" + isTextVerification() + ", mobile=" + getMobile() + ", roles=" + getRoles() + ")";
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
  
  public String getPassword() {
    return this.password;
  }
  
  public String getNationalId() {
    return this.nationalId;
  }
  
  public boolean isTextVerification() {
    return this.textVerification;
  }
  
  public String getMobile() {
    return this.mobile;
  }
  
  public Long getRoles() {
    return this.roles;
  }
}
