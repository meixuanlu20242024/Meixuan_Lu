package com.insurance.system.shared.usermanagement.payload;

public class ExpiredCredentialResponse {
  private boolean isexpired;
  
  private String message;
  
  public ExpiredCredentialResponse(boolean isexpired, String message) {
    this.isexpired = isexpired;
    this.message = message;
  }
  
  public ExpiredCredentialResponse() {}
  
  public void setIsexpired(boolean isexpired) {
    this.isexpired = isexpired;
  }
  
  public void setMessage(String message) {
    this.message = message;
  }
  

  public String toString() {
    return "ExpiredCredentialResponse(isexpired=" + isIsexpired() + ", message=" + getMessage() + ")";
  }
  
  public boolean isIsexpired() {
    return this.isexpired;
  }
  
  public String getMessage() {
    return this.message;
  }
}
