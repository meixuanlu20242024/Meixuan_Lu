package com.insurance.system.motorpolicy.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name ="MotorPolicyClaim")
public class MotorPolicyClaim {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long Id;
  
  @ManyToOne
  @JsonIgnore
  private MotorPolicy policy;
  
  @OneToMany(mappedBy = "policyClaim", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
  private List<MotorPolicyClaimDocument> motorPolicyClaimDocuments;
  
  @NotNull(message = "enter valid Date for date of loss ! ")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date dateOfLoss;
  
  @NotNull(message = "enter valid Date for notification Date ")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date notificationDate;

  @Enumerated(EnumType.STRING)
  @NotNull(message = "Claim ststus is required")
  private MotorPolicyClaimStatus claimStatus;
  
  @NotBlank(message = "time of loss cannot be empty.")
  private String timeOfLoss;
  
  @NotNull(message = "lossAmount cannot be empty.")
  private Double lossAmount;
  
  @NotNull(message = "Claim number cannot be empty.")
  private String claimNumber;
  
  @NotNull(message = "lossDetails cannot be empty.")
  private String lossDetails;
  
  private Date createdAt;
  
  private String createdBy;
  
  private Date updatedAt;
  
  public void setId(Long Id) {
    this.Id = Id;
  }
  
  public void setPolicy(MotorPolicy policy) {
    this.policy = policy;
  }
  
  public void setMotorPolicyClaimDocuments(List<MotorPolicyClaimDocument> motorPolicyClaimDocuments) {
    this.motorPolicyClaimDocuments = motorPolicyClaimDocuments;
  }
  
  public void setDateOfLoss(@NotNull(message = "enter valid Date for date of loss ! ") Date dateOfLoss) {
    this.dateOfLoss = dateOfLoss;
  }
  
  public void setNotificationDate(@NotNull(message = "enter valid Date for notification Date ") Date notificationDate) {
    this.notificationDate = notificationDate;
  }
  
  public void setTimeOfLoss(String timeOfLoss) {
    this.timeOfLoss = timeOfLoss;
  }
  
  public void setLossAmount(@NotNull(message = "lossAmount cannot be empty.") Double lossAmount) {
    this.lossAmount = lossAmount;
  }
  
  public void setClaimNumber(@NotNull(message = "Claim number cannot be empty.") String claimNumber) {
    this.claimNumber = claimNumber;
  }
  
  public void setLossDetails(@NotNull(message = "lossDetails cannot be empty.") String lossDetails) {
    this.lossDetails = lossDetails;
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
  

  protected boolean canEqual(Object other) {
    return other instanceof MotorPolicyClaim;
  }
  

  
  public String toString() {
    return "MotorPolicyClaim(Id=" + getId() + ", policy=" + getPolicy() + ", motorPolicyClaimDocuments=" + getMotorPolicyClaimDocuments() + ", dateOfLoss=" + getDateOfLoss() + ", notificationDate=" + getNotificationDate() + ", timeOfLoss=" + getTimeOfLoss() + ", lossAmount=" + getLossAmount() + ", claimNumber=" + getClaimNumber() + ", lossDetails=" + getLossDetails() + ", createdAt=" + getCreatedAt() + ", createdBy=" + getCreatedBy() + ", updatedAt=" + getUpdatedAt() + ")";
  }
  
  public MotorPolicyClaim() {
    this.motorPolicyClaimDocuments = new ArrayList<>();
  }
  
  public Long getId() {
    return this.Id;
  }
  
  public MotorPolicy getPolicy() {
    return this.policy;
  }
  
  public List<MotorPolicyClaimDocument> getMotorPolicyClaimDocuments() {
    return this.motorPolicyClaimDocuments;
  }
  
  @NotNull(message = "enter valid Date for date of loss ! ")
  public Date getDateOfLoss() {
    return this.dateOfLoss;
  }
  
  @NotNull(message = "enter valid Date for notification Date ")
  public Date getNotificationDate() {
    return this.notificationDate;
  }
  
  public String getTimeOfLoss() {
    return this.timeOfLoss;
  }
  
  @NotNull(message = "lossAmount cannot be empty.")
  public Double getLossAmount() {
    return this.lossAmount;
  }
  
  @NotNull(message = "Claim number cannot be empty.")
  public String getClaimNumber() {
    return this.claimNumber;
  }
  
  @NotNull(message = "lossDetails cannot be empty.")
  public String getLossDetails() {
    return this.lossDetails;
  }
  
  public Date getCreatedAt() {
    return this.createdAt;
  }
  
  public String getCreatedBy() {
    return this.createdBy;
  }

  public MotorPolicyClaimStatus getClaimStatus() {
    return claimStatus;
  }

  public void setClaimStatus(MotorPolicyClaimStatus claimStatus) {
    this.claimStatus = claimStatus;
  }

  public Date getUpdatedAt() {
    return this.updatedAt;
  }
}
