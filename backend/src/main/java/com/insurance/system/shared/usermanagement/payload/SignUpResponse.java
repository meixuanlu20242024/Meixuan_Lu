package com.insurance.system.shared.usermanagement.payload;

public class SignUpResponse {
  private boolean success;
  
  private String message;
  
  private Long userid;
  
  public SignUpResponse(boolean success, String message, Long userid) {
    this.success = success;
    this.message = message;
    this.userid = userid;
  }
  
  public SignUpResponse() {}
  
  public void setSuccess(boolean success) {
    this.success = success;
  }
  
  public void setMessage(String message) {
    this.message = message;
  }
  
  public void setUserid(Long userid) {
    this.userid = userid;
  }
  

  
  public String toString() {
    return "SignUpResponse(success=" + isSuccess() + ", message=" + getMessage() + ", userid=" + getUserid() + ")";
  }
  
  public boolean isSuccess() {
    return this.success;
  }
  
  public String getMessage() {
    return this.message;
  }
  
  public Long getUserid() {
    return this.userid;
  }
}
