package com.insurance.system.shared.domain.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GovermentLevy {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  private Double governmentLevy;
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public void setGovernmentLevy(Double governmentLevy) {
    this.governmentLevy = governmentLevy;
  }
  

  public String toString() {
    return "GovermentLevy(id=" + getId() + ", governmentLevy=" + getGovernmentLevy() + ")";
  }
  
  public Long getId() {
    return this.id;
  }
  
  public Double getGovernmentLevy() {
    return this.governmentLevy;
  }
}
