package com.insurance.system.shared.usermanagement.payload;

public class FileUploadDto {
  private String fileName;
  
  private String originalFilename;
  
  public FileUploadDto(String fileName, String originalFilename) {
    this.fileName = fileName;
    this.originalFilename = originalFilename;
  }
  
  public FileUploadDto() {}
  
  public void setFileName(String fileName) {
    this.fileName = fileName;
  }
  
  public void setOriginalFilename(String originalFilename) {
    this.originalFilename = originalFilename;
  }
  

  
  public String toString() {
    return "FileUploadDto(fileName=" + getFileName() + ", originalFilename=" + getOriginalFilename() + ")";
  }
  
  public String getFileName() {
    return this.fileName;
  }
  
  public String getOriginalFilename() {
    return this.originalFilename;
  }
}
