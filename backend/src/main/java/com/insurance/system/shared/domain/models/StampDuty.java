package com.insurance.system.shared.domain.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StampDuty {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  private Double stampDuty;
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public void setStampDuty(Double stampDuty) {
    this.stampDuty = stampDuty;
  }
  

  
  public String toString() {
    return "StampDuty(id=" + getId() + ", stampDuty=" + getStampDuty() + ")";
  }
  
  public Long getId() {
    return this.id;
  }
  
  public Double getStampDuty() {
    return this.stampDuty;
  }
}
