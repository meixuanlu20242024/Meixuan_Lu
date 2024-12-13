package com.insurance.system.shared.usermanagement.payload;

public class ApiResponse {
  private boolean success;
  
  private String message;
  
  public ApiResponse(boolean success, String message) {
    this.success = success;
    this.message = message;
  }
  
  public ApiResponse() {}
  
  public void setSuccess(boolean success) {
    this.success = success;
  }
  
  public void setMessage(String message) {
    this.message = message;
  }
  

  
  public String toString() {
    return "ApiResponse(success=" + isSuccess() + ", message=" + getMessage() + ")";
  }
  
  public boolean isSuccess() {
    return this.success;
  }
  
  public String getMessage() {
    return this.message;
  }
}
