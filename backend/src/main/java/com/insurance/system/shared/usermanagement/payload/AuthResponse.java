package com.insurance.system.shared.usermanagement.payload;

public class AuthResponse {
  private String accessToken;
  
  public AuthResponse(String accessToken, String tokenType) {
    this.accessToken = accessToken;
    this.tokenType = tokenType;
  }
  
  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }
  
  public void setTokenType(String tokenType) {
    this.tokenType = tokenType;
  }
  

  
  public String toString() {
    return "AuthResponse(accessToken=" + getAccessToken() + ", tokenType=" + getTokenType() + ")";
  }
  
  public String getAccessToken() {
    return this.accessToken;
  }
  
  private String tokenType = "Bearer";
  
  public String getTokenType() {
    return this.tokenType;
  }
  
  public AuthResponse(String accessToken) {
    this.accessToken = accessToken;
  }
  
  public AuthResponse() {}
}
