package com.insurance.system.shared.filestorage.context.upload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.servlet.ServletRequest;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadContext {
  private String fileName;
  
  private MultipartFile file;
  
  private String fileType;
  
  @JsonIgnore
  private ServletRequest servletRequest;
  
  public void setFileName(String fileName) {
    this.fileName = fileName;
  }
  
  public void setFile(MultipartFile file) {
    this.file = file;
  }
  
  public void setFileType(String fileType) {
    this.fileType = fileType;
  }
  
  @JsonIgnore
  public void setServletRequest(ServletRequest servletRequest) {
    this.servletRequest = servletRequest;
  }

  
  public String toString() {
    return "FileUploadContext(fileName=" + getFileName() + ", file=" + getFile() + ", fileType=" + getFileType() + ", servletRequest=" + getServletRequest() + ")";
  }
  
  public String getFileName() {
    return this.fileName;
  }
  
  public MultipartFile getFile() {
    return this.file;
  }
  
  public String getFileType() {
    return this.fileType;
  }
  
  public ServletRequest getServletRequest() {
    return this.servletRequest;
  }
}
