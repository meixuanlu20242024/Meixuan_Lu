package com.insurance.system.shared.usermanagement.payload;

public class RolePriviledgeDto {
  private Long id;
  
  private String name;
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  

  
  public String toString() {
    return "RolePriviledgeDto(id=" + getId() + ", name=" + getName() + ")";
  }
  
  public RolePriviledgeDto(Long id, String name) {
    this.id = id;
    this.name = name;
  }
  
  public RolePriviledgeDto() {}
  
  public Long getId() {
    return this.id;
  }
  
  public String getName() {
    return this.name;
  }
}
