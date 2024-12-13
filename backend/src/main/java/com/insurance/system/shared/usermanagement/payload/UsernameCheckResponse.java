package com.insurance.system.shared.usermanagement.payload;

public class UsernameCheckResponse {
  private boolean isAvailable;
  
  private String message;
  
  public UsernameCheckResponse(boolean isAvailable, String message) {
    this.isAvailable = isAvailable;
    this.message = message;
  }
  
  public UsernameCheckResponse() {}
  
  public void setAvailable(boolean isAvailable) {
    this.isAvailable = isAvailable;
  }
  
  public void setMessage(String message) {
    this.message = message;
  }

  public String toString() {
    return "UsernameCheckResponse(isAvailable=" + isAvailable() + ", message=" + getMessage() + ")";
  }
  
  public boolean isAvailable() {
    return this.isAvailable;
  }
  
  public String getMessage() {
    return this.message;
  }
}
