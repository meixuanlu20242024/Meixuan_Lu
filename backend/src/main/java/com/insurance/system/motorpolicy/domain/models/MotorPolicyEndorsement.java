package com.insurance.system.motorpolicy.domain.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.insurance.system.shared.domain.models.Currency;
import com.insurance.system.shared.domain.models.PolicyStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name ="MotorPolicyEndorsement")
public class MotorPolicyEndorsement {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long Id;
  
  @ManyToOne
  private MotorPolicy policy;
  
  @Enumerated(EnumType.STRING)
  @NotNull(message = "policy Status is required")
  private PolicyStatus policyStatus;
  
  @OneToMany(mappedBy = "policyEndorsement", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
  private List<MotorPolicyEndorsementDocument> motorPolicyEndorsementDocuments;
  
  @Enumerated(EnumType.STRING)
  @NotNull(message = "Currency is required")
  private Currency currency;
  
  @NotNull(message = "enter valid Date for Period From! ")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date periodFrom;
  
  @NotNull(message = "enter valid date for Period To !")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date periodTo;
  
  @NotBlank(message = "policy Number cannot be empty.")
  @Column(unique=true)
  private String policyNo;
  
  private String fleetIndividualDocument;
  
  private String extensionsDocument;
  
  private String excessesDocument;
  
  private Date createdAt;
  
  private String createdBy;
  
  private Date updatedAt;
  
  @NotNull(message = "sumInsured is required")
  private Double sumInsured;
  
  private Double premium;
  
  @NotNull(message = "rate is required")
  @Min(value = 0L, message = "Rate should be between 0 and 100")
  @Max(value = 100L, message = "Rate should be between 0 and 100")
  private Double rate;
  
  private Double stampDuty;
  
  private Double governmentLevy;
  
  public void setId(Long Id) {
    this.Id = Id;
  }
  
  public void setPolicy(MotorPolicy policy) {
    this.policy = policy;
  }
  
  public void setPolicyStatus(@NotNull(message = "policy Status is required") PolicyStatus policyStatus) {
    this.policyStatus = policyStatus;
  }
  
  public void setMotorPolicyEndorsementDocuments(List<MotorPolicyEndorsementDocument> motorPolicyEndorsementDocuments) {
    this.motorPolicyEndorsementDocuments = motorPolicyEndorsementDocuments;
  }
  
  public void setCurrency(@NotNull(message = "Currency is required") Currency currency) {
    this.currency = currency;
  }
  
  public void setPeriodFrom(@NotNull(message = "enter valid Date for Period From! ") Date periodFrom) {
    this.periodFrom = periodFrom;
  }
  
  public void setPeriodTo(@NotNull(message = "enter valid date for Period To !") Date periodTo) {
    this.periodTo = periodTo;
  }
  
  public void setPolicyNo(String policyNo) {
    this.policyNo = policyNo;
  }
  
  public void setFleetIndividualDocument(String fleetIndividualDocument) {
    this.fleetIndividualDocument = fleetIndividualDocument;
  }
  
  public void setExtensionsDocument(String extensionsDocument) {
    this.extensionsDocument = extensionsDocument;
  }
  
  public void setExcessesDocument(String excessesDocument) {
    this.excessesDocument = excessesDocument;
  }
  
  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }
  
  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }
  
  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }
  
  public void setSumInsured(@NotNull(message = "sumInsured is required") Double sumInsured) {
    this.sumInsured = sumInsured;
  }
  
  public void setPremium(Double premium) {
    this.premium = premium;
  }
  
  public void setRate(@NotNull(message = "rate is required") Double rate) {
    this.rate = rate;
  }
  
  public void setStampDuty(Double stampDuty) {
    this.stampDuty = stampDuty;
  }
  
  public void setGovernmentLevy(Double governmentLevy) {
    this.governmentLevy = governmentLevy;
  }
  

  protected boolean canEqual(Object other) {
    return other instanceof MotorPolicyEndorsement;
  }
  

  public String toString() {
    return "MotorPolicyEndorsement(Id=" + getId() + ", policy=" + getPolicy() + ", policyStatus=" + getPolicyStatus() + ", motorPolicyEndorsementDocuments=" + getMotorPolicyEndorsementDocuments() + ", currency=" + getCurrency() + ", periodFrom=" + getPeriodFrom() + ", periodTo=" + getPeriodTo() + ", policyNo=" + getPolicyNo() + ", fleetIndividualDocument=" + getFleetIndividualDocument() + ", extensionsDocument=" + getExtensionsDocument() + ", excessesDocument=" + getExcessesDocument() + ", createdAt=" + getCreatedAt() + ", createdBy=" + getCreatedBy() + ", updatedAt=" + getUpdatedAt() + ", sumInsured=" + getSumInsured() + ", premium=" + getPremium() + ", rate=" + getRate() + ", stampDuty=" + getStampDuty() + ", governmentLevy=" + getGovernmentLevy() + ")";
  }
  
  public MotorPolicyEndorsement() {
    this.motorPolicyEndorsementDocuments = new ArrayList<>();
  }
  
  public Long getId() {
    return this.Id;
  }
  
  public MotorPolicy getPolicy() {
    return this.policy;
  }
  
  @NotNull(message = "policy Status is required")
  public PolicyStatus getPolicyStatus() {
    return this.policyStatus;
  }
  
  public List<MotorPolicyEndorsementDocument> getMotorPolicyEndorsementDocuments() {
    return this.motorPolicyEndorsementDocuments;
  }
  
  @NotNull(message = "Currency is required")
  public Currency getCurrency() {
    return this.currency;
  }
  
  @NotNull(message = "enter valid Date for Period From! ")
  public Date getPeriodFrom() {
    return this.periodFrom;
  }
  
  @NotNull(message = "enter valid date for Period To !")
  public Date getPeriodTo() {
    return this.periodTo;
  }
  
  public String getPolicyNo() {
    return this.policyNo;
  }
  
  public String getFleetIndividualDocument() {
    return this.fleetIndividualDocument;
  }
  
  public String getExtensionsDocument() {
    return this.extensionsDocument;
  }
  
  public String getExcessesDocument() {
    return this.excessesDocument;
  }
  
  public Date getCreatedAt() {
    return this.createdAt;
  }
  
  public String getCreatedBy() {
    return this.createdBy;
  }
  
  public Date getUpdatedAt() {
    return this.updatedAt;
  }
  
  @NotNull(message = "sumInsured is required")
  public Double getSumInsured() {
    return this.sumInsured;
  }
  
  public Double getPremium() {
    return this.premium;
  }
  
  @NotNull(message = "rate is required")
  public Double getRate() {
    return this.rate;
  }
  
  public Double getStampDuty() {
    return this.stampDuty;
  }
  
  public Double getGovernmentLevy() {
    return this.governmentLevy;
  }
}
