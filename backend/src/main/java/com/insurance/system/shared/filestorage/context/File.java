package com.insurance.system.shared.filestorage.context;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Lombok;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "files")
@EntityListeners({AuditingEntityListener.class})
public class File {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  
  @Column(nullable = false, unique = true)
  private String name;
  
  private String contentType;
  
  private String directory;
  
  @Column(nullable = false)
  private Date datePosted;
  
  private String originalFileName;
  
  public void setId(long id) {
    this.id = id;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public void setContentType(String contentType) {
    this.contentType = contentType;
  }
  
  public void setDirectory(String directory) {
    this.directory = directory;
  }
  
  public void setDatePosted(Date datePosted) {
    this.datePosted = datePosted;
  }
  
  public void setOriginalFileName(String originalFileName) {
    this.originalFileName = originalFileName;
  }
  
  protected File() {}
  
  public long getId() {
    return this.id;
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getContentType() {
    return this.contentType;
  }
  
  public String getDirectory() {
    return this.directory;
  }
  
  public Date getDatePosted() {
    return this.datePosted;
  }
  
  public String getOriginalFileName() {
    return this.originalFileName;
  }
  
  private File(String name, String directory, String originalFileName, String fileType) {
    this.name = name;
    this.directory = directory;
    this.originalFileName = originalFileName;
    this.contentType = fileType;
  }
  
  public static File createFile(String fileName, String directory, String originalFileName, String fileType) {
    Lombok.checkNotNull(fileName, "File name is required");
    return new File(fileName, directory, originalFileName, fileType);
  }
  
  @PrePersist
  private void setDatePosted() {
    this.datePosted = Timestamp.valueOf(LocalDateTime.now());
  }
}
