package com.insurance.system.shared.usermanagement.payload;

public class SmsGatewayResponse {
  private boolean successful;
  
  private String narrative;
  
  public void setSuccessful(boolean successful) {
    this.successful = successful;
  }
  
  public void setNarrative(String narrative) {
    this.narrative = narrative;
  }
  

  
  public String toString() {
    return "SmsGatewayResponse(successful=" + isSuccessful() + ", narrative=" + getNarrative() + ")";
  }
  
  public SmsGatewayResponse(boolean successful, String narrative) {
    this.successful = successful;
    this.narrative = narrative;
  }
  
  public boolean isSuccessful() {
    return this.successful;
  }
  
  public String getNarrative() {
    return this.narrative;
  }
}
