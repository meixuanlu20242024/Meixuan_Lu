package com.insurance.system.shared.usermanagement.payload;

public class RoleCreateRequest {
  private String name;
  
  private String description;
  
  public RoleCreateRequest(String name, String description) {
    this.name = name;
    this.description = description;
  }
  
  public RoleCreateRequest() {}
  
  public void setName(String name) {
    this.name = name;
  }
  
  public void setDescription(String description) {
    this.description = description;
  }

  
  public String toString() {
    return "RoleCreateRequest(name=" + getName() + ", description=" + getDescription() + ")";
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getDescription() {
    return this.description;
  }
}
