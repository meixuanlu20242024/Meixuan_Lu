package com.insurance.system.motorpolicy.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.Expose;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Table(name ="MotorPolicyVehicle")
public class MotorPolicyVehicle {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long Id;

  @ManyToOne
  @JsonIgnore
  private  MotorPolicy policy;
  @Expose
  private String name;
  @Expose
  private String make;
  @Expose
  private String model;
  @Expose
//  private String vehicle_year;
  @Column(name = "vehicle_year")
  private String year;
  @Expose
  private String registrationNumber;
  private double price;

//  public String getYear() {
//    return year;
////    return vehicle_year;
//  }
//
////  public void setYear(String vehicle_year) {
////    this.vehicle_year = vehicle_year;
////  }
//
//  public void setYear(String year) {
//    this.year = year;
//  }


}
