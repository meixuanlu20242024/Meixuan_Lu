package com.insurance.system.motorpolicy.domain.models.renewal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.insurance.system.motorpolicy.domain.models.CoverType;
import com.insurance.system.motorpolicy.domain.models.FleetIndividual;
import com.insurance.system.motorpolicy.domain.models.MotorPolicy;
import com.insurance.system.shared.domain.models.BaseEntity;
import com.insurance.system.shared.domain.models.Currency;
import com.insurance.system.shared.domain.models.PolicyStatus;
import com.insurance.system.shared.domain.models.RetailClient;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Table(name ="MotorPolicyRenewal")
public class MotorPolicyRenewal extends BaseEntity {


  protected boolean canEqual(Object other) {
    return other instanceof MotorPolicyRenewal;
  }





  public MotorPolicyRenewal()  {
    this.motorPolicyDocuments = new ArrayList<>();
  }
  
  private static final Logger log = LoggerFactory.getLogger(MotorPolicyRenewal.class);

  @ManyToOne
  @JsonIgnore
  private MotorPolicy policy;
  @Enumerated(EnumType.STRING)
  @NotNull(message = "policy Status is required")
  private PolicyStatus policyStatus;
  
  @NotNull(message = "insured name cannot be empty.")
  @ManyToOne
  @OnDelete(action = OnDeleteAction.NO_ACTION)
  private RetailClient insured;
  
  @Enumerated(EnumType.STRING)
  @NotNull(message = "Currency is required")
  private Currency currency;
  
  @Enumerated(EnumType.STRING)
  @NotNull(message = "Fleet or Individual selection  required")
  private FleetIndividual fleetIndividual;
  
  @NotNull(message = "enter valid Date for Period From! ")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date periodFrom;
  
  @NotNull(message = "enter valid date for Period To !")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date periodTo;

  
  @OneToMany(mappedBy = "policy", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
  private List<MotorPolicyRenewalDocument> motorPolicyDocuments;
  
  @NotBlank(message = "policy Number cannot be empty.")
  @Column(unique=true)
  private String policyNo;

  @Column(length = 65535 , columnDefinition="Text")
  private String tmpVehicles;

//  TODO check what happens on delete
  @OneToMany(mappedBy = "policy", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
  private List<MotorPolicyRenewalVehicle> motorVehicles;


  
  @Enumerated(EnumType.STRING)
  @NotNull(message = "CoverType is required")
  private CoverType coverType;
  
  @NotNull(message = "sumInsured is required")
  private Double sumInsured;
  
  private Double premium;
  
  @NotNull(message = "rate is required")
  @Min(value = 0L, message = "Rate should be between 0 and 100")
  @Max(value = 100L, message = "Rate should be between 0 and 100")
  private Double rate;
  
  private Double stampDuty;
  
  private Double governmentLevy;

}
