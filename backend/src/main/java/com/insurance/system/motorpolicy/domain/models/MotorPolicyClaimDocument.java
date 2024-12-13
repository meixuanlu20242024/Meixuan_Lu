package com.insurance.system.motorpolicy.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name ="MotorPolicyClaimDocument")
public class MotorPolicyClaimDocument {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long Id;
  
  @ManyToOne
  @JsonIgnore
  private MotorPolicyClaim policyClaim;
  
  private String fileName;
  
  private String originalName;
  
  public void setId(Long Id) {
    this.Id = Id;
  }
  
  public void setPolicyClaim(MotorPolicyClaim policyClaim) {
    this.policyClaim = policyClaim;
  }
  
  public void setFileName(String fileName) {
    this.fileName = fileName;
  }
  
  public void setOriginalName(String originalName) {
    this.originalName = originalName;
  }
  

  
  protected boolean canEqual(Object other) {
    return other instanceof MotorPolicyClaimDocument;
  }
  

  
  public String toString() {
    return "MotorPolicyClaimDocument(Id=" + getId() + ", policyClaim=" + getPolicyClaim() + ", fileName=" + getFileName() + ", originalName=" + getOriginalName() + ")";
  }
  
  public Long getId() {
    return this.Id;
  }
  
  public MotorPolicyClaim getPolicyClaim() {
    return this.policyClaim;
  }
  
  public String getFileName() {
    return this.fileName;
  }
  
  public String getOriginalName() {
    return this.originalName;
  }
}
