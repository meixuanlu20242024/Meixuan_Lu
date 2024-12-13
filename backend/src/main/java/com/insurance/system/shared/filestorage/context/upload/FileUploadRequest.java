package com.insurance.system.shared.filestorage.context.upload;

import lombok.Lombok;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadRequest {
  private final MultipartFile file;
  
  private String directory;
  
  public FileUploadRequest(MultipartFile file, String directory) {
    this.file = file;
    this.directory = directory;
  }
  
  public static FileUploadRequest createFileUploadRequest(MultipartFile file, String directory) {
    Lombok.checkNotNull(file, "File is required");
    return new FileUploadRequest(file, directory);
  }
  
  public MultipartFile getFile() {
    return this.file;
  }
  
  public String getDirectory() {
    return this.directory;
  }
  
  public void setDirectory(String directory) {
    this.directory = directory;
  }
  
  public String toString() {
    return "FileUploadRequest{file=" + this.file + '}';
  }
}
