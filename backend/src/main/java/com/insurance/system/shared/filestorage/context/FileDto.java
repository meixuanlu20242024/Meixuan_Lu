package com.insurance.system.shared.filestorage.context;

import java.util.Objects;

public class FileDto {
  private final String fileName;
  
  private FileDto(String fileName) {
    this.fileName = fileName;
  }
  
  public static FileDto createFileDto(File file) {
    if (Objects.isNull(file))
      return null; 
    return new FileDto(file.getName());
  }
  
  public String getFileName() {
    return this.fileName;
  }
  
  public String toString() {
    return "FileDto{fileName=" + this.fileName + '}';
  }
}
