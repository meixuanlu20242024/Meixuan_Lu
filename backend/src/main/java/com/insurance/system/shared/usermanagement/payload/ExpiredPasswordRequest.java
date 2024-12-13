package com.insurance.system.shared.usermanagement.payload;

import com.insurance.system.shared.usermanagement.commons.Password;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ExpiredPasswordRequest {
  @NotBlank
  @Email
  private String email;
  
  @NotBlank
  private String oldPassword;
  
  @Password
  private String newPassword;
  
  @NotBlank
  private String confirmNewPassword;
  
  public ExpiredPasswordRequest(String email, String oldPassword, String newPassword, String confirmNewPassword) {
    this.email = email;
    this.oldPassword = oldPassword;
    this.newPassword = newPassword;
    this.confirmNewPassword = confirmNewPassword;
  }
  
  public ExpiredPasswordRequest() {}
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public void setOldPassword(String oldPassword) {
    this.oldPassword = oldPassword;
  }
  
  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }
  
  public void setConfirmNewPassword(String confirmNewPassword) {
    this.confirmNewPassword = confirmNewPassword;
  }

  
  public String toString() {
    return "ExpiredPasswordRequest(email=" + getEmail() + ", oldPassword=" + getOldPassword() + ", newPassword=" + getNewPassword() + ", confirmNewPassword=" + getConfirmNewPassword() + ")";
  }
  
  public String getEmail() {
    return this.email;
  }
  
  public String getOldPassword() {
    return this.oldPassword;
  }
  
  public String getNewPassword() {
    return this.newPassword;
  }
  
  public String getConfirmNewPassword() {
    return this.confirmNewPassword;
  }
}
