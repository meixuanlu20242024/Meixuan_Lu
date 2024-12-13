package com.insurance.system.shared.usermanagement.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
  @NotBlank
  @Email
  private String email;
  
  @NotBlank
  private String password;
  
  public LoginRequest(String email, String password) {
    this.email = email;
    this.password = password;
  }
  
  public LoginRequest() {}
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
  

  
  public String toString() {
    return "LoginRequest(email=" + getEmail() + ", password=" + getPassword() + ")";
  }
  
  public String getEmail() {
    return this.email;
  }
  
  public String getPassword() {
    return this.password;
  }
}
