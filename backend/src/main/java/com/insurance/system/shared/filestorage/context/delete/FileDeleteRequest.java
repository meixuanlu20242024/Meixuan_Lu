package com.insurance.system.shared.filestorage.context.delete;

public class FileDeleteRequest {
  private final String fileName;
  
  private final String directory;
  
  public String getDirectory() {
    return this.directory;
  }
  
  private FileDeleteRequest(String fileName, String directory) {
    this.fileName = fileName;
    this.directory = directory;
  }
  
  public static FileDeleteRequest createFileDeleteRequest(String fileName, String directory) {
    return new FileDeleteRequest(fileName, directory);
  }
  
  public String getFileName() {
    return this.fileName;
  }
  
  public String toString() {
    return "FileDeleteRequest{fileName='" + this.fileName + '\'' + '}';
  }
}
