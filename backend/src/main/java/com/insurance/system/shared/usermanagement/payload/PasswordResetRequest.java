package com.insurance.system.shared.usermanagement.payload;

import com.insurance.system.shared.usermanagement.commons.Password;
import jakarta.validation.constraints.NotBlank;

public class PasswordResetRequest {
  @Password
  private String password;
  @NotBlank
  private String confirmPassword;
  
  public PasswordResetRequest(String password, String confirmPassword) {
    this.password = password;
    this.confirmPassword = confirmPassword;
  }
  
  public PasswordResetRequest() {}
  
  public void setPassword(String password) {
    this.password = password;
  }
  
  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }

  
  public String toString() {
    return "PasswordResetRequest(password=" + getPassword() + ", confirmpassword=" + getConfirmPassword() + ")";
  }
  
  public String getPassword() {
    return this.password;
  }
  
  public String getConfirmPassword() {
    return this.confirmPassword;
  }
}
