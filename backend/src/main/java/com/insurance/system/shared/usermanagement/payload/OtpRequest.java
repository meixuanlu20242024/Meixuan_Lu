package com.insurance.system.shared.usermanagement.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class OtpRequest {
  @NotBlank
  @Email
  private String email;
  
  @NotBlank
  private String otp;
  
  public OtpRequest(String email, String otp) {
    this.email = email;
    this.otp = otp;
  }
  
  public OtpRequest() {}
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public void setOtp(String otp) {
    this.otp = otp;
  }

  
  public String toString() {
    return "OtpRequest(email=" + getEmail() + ", otp=" + getOtp() + ")";
  }
  
  public String getEmail() {
    return this.email;
  }
  
  public String getOtp() {
    return this.otp;
  }
}
