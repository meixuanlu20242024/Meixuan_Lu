package com.insurance.system.shared.usermanagement.payload;

import java.util.Set;

public class RolesResponse {
  private boolean success;
  
  private String message;
  
  private Set changed_roles;
  
  public RolesResponse(boolean success, String message, Set changed_roles) {
    this.success = success;
    this.message = message;
    this.changed_roles = changed_roles;
  }
  
  public RolesResponse() {}
  
  public void setSuccess(boolean success) {
    this.success = success;
  }
  
  public void setMessage(String message) {
    this.message = message;
  }
  
  public void setChanged_roles(Set changed_roles) {
    this.changed_roles = changed_roles;
  }
  

  public String toString() {
    return "RolesResponse(success=" + isSuccess() + ", message=" + getMessage() + ", changed_roles=" + getChanged_roles() + ")";
  }
  
  public boolean isSuccess() {
    return this.success;
  }
  
  public String getMessage() {
    return this.message;
  }
  
  public Set getChanged_roles() {
    return this.changed_roles;
  }
}
