package com.insurance.system.shared.usermanagement.payload;

import java.sql.Date;
import java.text.SimpleDateFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class BulkSmsRespose {
  @Id
  private String batchId;
  
  private String batchNumber;
  
  private String status;
  
  private String narrative;
  
  private String date;
  
  public void setBatchId(String batchId) {
    this.batchId = batchId;
  }
  
  public void setBatchNumber(String batchNumber) {
    this.batchNumber = batchNumber;
  }
  
  public void setStatus(String status) {
    this.status = status;
  }
  
  public void setNarrative(String narrative) {
    this.narrative = narrative;
  }
  
  public void setDate(String date) {
    this.date = date;
  }
  

  
  public String toString() {
    return "BulkSmsRespose(batchId=" + getBatchId() + ", batchNumber=" + getBatchNumber() + ", status=" + getStatus() + ", narrative=" + getNarrative() + ", date=" + getDate() + ")";
  }
  
  public String getBatchId() {
    return this.batchId;
  }
  
  public String getBatchNumber() {
    return this.batchNumber;
  }
  
  public String getStatus() {
    return this.status;
  }
  
  public String getNarrative() {
    return this.narrative;
  }
  
  public String getDate() {
    return this.date;
  }
  
  BulkSmsRespose(String batchId, String batchNumber, String status, String narrative) {
    this.batchId = batchId;
    this.batchNumber = batchNumber;
    this.status = status;
    this.narrative = narrative;
  }
  
  public BulkSmsRespose() {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date datee = new Date(System.currentTimeMillis());
    this.date = formatter.format(datee);
  }
}
