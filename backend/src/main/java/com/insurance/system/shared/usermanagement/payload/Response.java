package com.insurance.system.shared.usermanagement.payload;

public class Response {
  private boolean success;
  
  private String narrative;
  
  public Response(boolean success, String narrative) {
    this.success = success;
    this.narrative = narrative;
  }
  
  public Response() {}
  
  public void setSuccess(boolean success) {
    this.success = success;
  }
  
  public void setNarrative(String narrative) {
    this.narrative = narrative;
  }

  
  public String toString() {
    return "Response(success=" + isSuccess() + ", narrative=" + getNarrative() + ")";
  }
  
  public boolean isSuccess() {
    return this.success;
  }
  
  public String getNarrative() {
    return this.narrative;
  }
}
