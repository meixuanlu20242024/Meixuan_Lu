package com.insurance.system.shared.usermanagement.payload;

public class ArticleUploadDto {
  private String fileName;
  
  private String title;
  
  private String description;
  
  public void setFileName(String fileName) {
    this.fileName = fileName;
  }
  
  public void setTitle(String title) {
    this.title = title;
  }
  
  public void setDescription(String description) {
    this.description = description;
  }

  public String toString() {
    return "ArticleUploadDto(fileName=" + getFileName() + ", title=" + getTitle() + ", description=" + getDescription() + ")";
  }
  
  public ArticleUploadDto(String fileName, String title, String description) {
    this.fileName = fileName;
    this.title = title;
    this.description = description;
  }
  
  public ArticleUploadDto() {}
  
  public String getFileName() {
    return this.fileName;
  }
  
  public String getTitle() {
    return this.title;
  }
  
  public String getDescription() {
    return this.description;
  }
}
