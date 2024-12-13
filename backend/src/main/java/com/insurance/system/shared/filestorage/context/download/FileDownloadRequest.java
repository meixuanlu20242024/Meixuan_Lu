package com.insurance.system.shared.filestorage.context.download;

import lombok.Lombok;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileDownloadRequest {
  private static final Logger log = LoggerFactory.getLogger(FileDownloadRequest.class);
  
  private final String fileName;
  
  private final String directory;
  
  public String getDirectory() {
    return this.directory;
  }
  
  private FileDownloadRequest(String fileName, String directory) {
    this.fileName = fileName;
    this.directory = directory;
  }
  
  public static FileDownloadRequest createFileDownloadRequest(String fileName, String directory) {
    Lombok.checkNotNull(fileName, "File name cannot be null");
    return new FileDownloadRequest(fileName, directory);
  }
  
  public String getFileName() {
    return this.fileName;
  }
  
  public String toString() {
    return "FileDownloadRequest{fileName='" + this.fileName + '\'' + '}';
  }
}
