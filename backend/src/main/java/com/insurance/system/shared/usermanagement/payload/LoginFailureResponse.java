package com.insurance.system.shared.usermanagement.payload;

public class LoginFailureResponse {
  private Boolean success;
  
  private String message;
  
  private Integer attempts_left;
  
  public LoginFailureResponse() {}
  
  public void setSuccess(Boolean success) {
    this.success = success;
  }
  
  public void setMessage(String message) {
    this.message = message;
  }
  
  public void setAttempts_left(Integer attempts_left) {
    this.attempts_left = attempts_left;
  }

  
  public String toString() {
    return "LoginFailureResponse(success=" + getSuccess() + ", message=" + getMessage() + ", attempts_left=" + getAttempts_left() + ")";
  }
  
  public Boolean getSuccess() {
    return this.success;
  }
  
  public String getMessage() {
    return this.message;
  }
  
  public Integer getAttempts_left() {
    return this.attempts_left;
  }
  
  public LoginFailureResponse(Boolean success, String message, Integer attempts_left) {
    this.success = success;
    this.message = message;
    this.attempts_left = attempts_left;
  }
}
