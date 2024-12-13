package com.insurance.system.shared.usermanagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

@Entity
public class  Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(nullable = false)
  private String name;
  
  @Column(nullable = false)
  private String description;
  
  private UserType scope;
  
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "role_privileges", joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "role_privilege_id", referencedColumnName = "id")})
  @JsonIgnore
  private Set<Privilege> privileges;
  
  @OneToOne(cascade = {CascadeType.ALL})
  @JoinColumn(name = "company_id")
  @JsonIgnore
  private Company company;
  
  public Role() {}
  
  public Role(UserType scope, String name, String description) {
    this.scope = scope;
    this.name = name;
    this.description = description;
  }
  
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
  
  public Set<Privilege> getPrivileges() {
    return this.privileges;
  }
  
  public void setPrivileges(Set<Privilege> privileges) {
    this.privileges = privileges;
  }
  
  public UserType getScope() {
    return this.scope;
  }
  
  public void setScope(UserType scope) {
    this.scope = scope;
  }
  
  public String getDescription() {
    return this.description;
  }
  
  public void setDescription(String description) {
    this.description = description;
  }
}
