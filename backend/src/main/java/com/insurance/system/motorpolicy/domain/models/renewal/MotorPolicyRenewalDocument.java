package com.insurance.system.motorpolicy.domain.models.renewal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.insurance.system.motorpolicy.domain.models.MotorPolicyEnumDocumentType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Table(name ="MotorPolicyRenewalDocument")
public class MotorPolicyRenewalDocument {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long Id;
  
  @ManyToOne
  private MotorPolicyRenewal policy;
  
  private String fileName;
  
  private String originalName;
  
  @Enumerated(EnumType.STRING)
  private MotorPolicyEnumDocumentType motorPolicyEnumDocumentType;
  

  
  public MotorPolicyEnumDocumentType getMotorPolicyEnumDocumentType() {
    return this.motorPolicyEnumDocumentType;
  }
}
