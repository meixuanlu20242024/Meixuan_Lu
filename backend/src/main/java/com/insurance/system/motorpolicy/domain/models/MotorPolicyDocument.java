package com.insurance.system.motorpolicy.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name ="MotorPolicyDocument")
public class MotorPolicyDocument {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long Id;
  
  @ManyToOne
  @JsonIgnore
  private MotorPolicy policy;
  
  private String fileName;
  
  private String originalName;
  
  @Enumerated(EnumType.STRING)
  private MotorPolicyEnumDocumentType motorPolicyEnumDocumentType;
  
  public void setId(Long Id) {
    this.Id = Id;
  }
  
  public void setPolicy(MotorPolicy policy) {
    this.policy = policy;
  }
  
  public void setFileName(String fileName) {
    this.fileName = fileName;
  }
  
  public void setOriginalName(String originalName) {
    this.originalName = originalName;
  }
  
  public void setMotorPolicyEnumDocumentType(MotorPolicyEnumDocumentType motorPolicyEnumDocumentType) {
    this.motorPolicyEnumDocumentType = motorPolicyEnumDocumentType;
  }
  

  
  protected boolean canEqual(Object other) {
    return other instanceof MotorPolicyDocument;
  }
  

  
//  public String toString() {
//    return "MotorPolicyDocument(Id=" + getId() + ", policy=" + getPolicy() + ", fileName=" + getFileName() + ", originalName=" + getOriginalName() + ", motorPolicyEnumDocumentType=" + getMotorPolicyEnumDocumentType() + ")";
//  }
  
  public Long getId() {
    return this.Id;
  }
  
  public MotorPolicy getPolicy() {
    return this.policy;
  }
  
  public String getFileName() {
    return this.fileName;
  }
  
  public String getOriginalName() {
    return this.originalName;
  }
  
  public MotorPolicyEnumDocumentType getMotorPolicyEnumDocumentType() {
    return this.motorPolicyEnumDocumentType;
  }
}
