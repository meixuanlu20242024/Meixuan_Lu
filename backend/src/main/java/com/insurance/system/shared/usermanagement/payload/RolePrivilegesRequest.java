package com.insurance.system.shared.usermanagement.payload;

import java.util.Set;

public class RolePrivilegesRequest {
  private Long roleid;
  
  private Set<Long> privileges;
  
  public RolePrivilegesRequest(Long roleid, Set<Long> privileges) {
    this.roleid = roleid;
    this.privileges = privileges;
  }
  
  public RolePrivilegesRequest() {}
  
  public void setRoleid(Long roleid) {
    this.roleid = roleid;
  }
  
  public void setPrivileges(Set<Long> privileges) {
    this.privileges = privileges;
  }
  

  
  public String toString() {
    return "RolePrivilegesRequest(roleid=" + getRoleid() + ", privileges=" + getPrivileges() + ")";
  }
  
  public Long getRoleid() {
    return this.roleid;
  }
  
  public Set<Long> getPrivileges() {
    return this.privileges;
  }
}
