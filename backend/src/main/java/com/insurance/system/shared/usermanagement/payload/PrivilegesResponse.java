package com.insurance.system.shared.usermanagement.payload;

import java.util.Set;

public class PrivilegesResponse {
  private boolean success;
  
  private String message;
  
  private Set changed_privileges;
  
  public PrivilegesResponse(boolean success, String message, Set changed_privileges) {
    this.success = success;
    this.message = message;
    this.changed_privileges = changed_privileges;
  }
  
  public PrivilegesResponse() {}
  
  public void setSuccess(boolean success) {
    this.success = success;
  }
  
  public void setMessage(String message) {
    this.message = message;
  }
  
  public void setChanged_privileges(Set changed_privileges) {
    this.changed_privileges = changed_privileges;
  }
  

  
  public String toString() {
    return "PrivilegesResponse(success=" + isSuccess() + ", message=" + getMessage() + ", changed_privileges=" + getChanged_privileges() + ")";
  }
  
  public boolean isSuccess() {
    return this.success;
  }
  
  public String getMessage() {
    return this.message;
  }
  
  public Set getChanged_privileges() {
    return this.changed_privileges;
  }
}
