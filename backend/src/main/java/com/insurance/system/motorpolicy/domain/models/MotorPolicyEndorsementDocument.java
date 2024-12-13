package com.insurance.system.motorpolicy.domain.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name ="MotorPolicyEndorsementDocument")
public class MotorPolicyEndorsementDocument {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long Id;
  
  @ManyToOne
  private MotorPolicyEndorsement policyEndorsement;
  
  private String fileName;
  
  private String originalName;
  
  @Enumerated(EnumType.STRING)
  private MotorPolicyEnumDocumentType motorPolicyEnumDocumentType;
  
  public void setId(Long Id) {
    this.Id = Id;
  }
  
  public void setPolicyEndorsement(MotorPolicyEndorsement policyEndorsement) {
    this.policyEndorsement = policyEndorsement;
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
    return other instanceof MotorPolicyEndorsementDocument;
  }

  
  public String toString() {
    return "MotorPolicyEndorsementDocument(Id=" + getId() + ", policyEndorsement=" + getPolicyEndorsement() + ", fileName=" + getFileName() + ", originalName=" + getOriginalName() + ", motorPolicyEnumDocumentType=" + getMotorPolicyEnumDocumentType() + ")";
  }
  
  public Long getId() {
    return this.Id;
  }
  
  public MotorPolicyEndorsement getPolicyEndorsement() {
    return this.policyEndorsement;
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
