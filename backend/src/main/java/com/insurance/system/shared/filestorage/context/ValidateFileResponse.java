package com.insurance.system.shared.filestorage.context;

public class ValidateFileResponse {
  private Boolean valid;
  
  public String toString() {
    return "ValidateFileResponse(valid=" + getValid() + ")";
  }
  

  
  public void setValid(Boolean valid) {
    this.valid = valid;
  }
  
  public Boolean getValid() {
    return this.valid;
  }
}
