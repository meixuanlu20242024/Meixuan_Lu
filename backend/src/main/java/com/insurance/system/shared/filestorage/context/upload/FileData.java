package com.insurance.system.shared.filestorage.context.upload;

public class FileData {
  private String fileName;
  
  private String fileType;
  
  private String fileValue;
  
  public void setFileName(String fileName) {
    this.fileName = fileName;
  }
  
  public void setFileType(String fileType) {
    this.fileType = fileType;
  }
  
  public void setFileValue(String fileValue) {
    this.fileValue = fileValue;
  }

  
  public String toString() {
    return "FileData(fileName=" + getFileName() + ", fileType=" + getFileType() + ", fileValue=" + getFileValue() + ")";
  }
  
  public String getFileName() {
    return this.fileName;
  }
  
  public String getFileType() {
    return this.fileType;
  }
  
  public String getFileValue() {
    return this.fileValue;
  }
}
