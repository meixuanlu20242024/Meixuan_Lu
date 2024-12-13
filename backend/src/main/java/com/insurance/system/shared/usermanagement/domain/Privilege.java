package com.insurance.system.shared.usermanagement.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "privilege")
public class Privilege {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(nullable = false)
  private UserType scope;
  
  @Column(nullable = false, unique = true)
  private String name;
  
  public Privilege(UserType scope, String name) {
    this.scope = scope;
    this.name = name;
  }
  
  public Privilege() {}
  
  public Long getId() {
    return this.id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public String getName() {
    return this.name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public UserType getScope() {
    return this.scope;
  }
  
  public void setScope(UserType scope) {
    this.scope = scope;
  }
}
