package com.insurance.system.shared.usermanagement.payload;

public class LoginResponse {
  private boolean success;
  
  private String email;
  
  public LoginResponse(boolean success, String email) {
    this.success = success;
    this.email = email;
  }
  
  public LoginResponse() {}
  
  public void setSuccess(boolean success) {
    this.success = success;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  

  
  public String toString() {
    return "LoginResponse(success=" + isSuccess() + ", email=" + getEmail() + ")";
  }
  
  public boolean isSuccess() {
    return this.success;
  }
  
  public String getEmail() {
    return this.email;
  }
}
