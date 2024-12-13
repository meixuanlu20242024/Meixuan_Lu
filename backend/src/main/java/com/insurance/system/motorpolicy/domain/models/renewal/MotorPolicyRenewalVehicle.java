package com.insurance.system.motorpolicy.domain.models.renewal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.Expose;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Table(name ="MotorPolicyRenewalVehicle")
public class MotorPolicyRenewalVehicle {

  @jakarta.persistence.Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Expose
  private Long Id;

  @ManyToOne
  private MotorPolicyRenewal policy;
  @Expose
  private String name;
  @Expose
  private String make;
  @Expose
  private String model;
  @Expose
  private String carYear;
  @Expose
  private String registrationNumber;


}
