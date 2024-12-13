package com.insurance.system.shared.usermanagement.payload;

import java.util.Set;
import jakarta.validation.constraints.NotNull;

public class PrivilegesRequest {
  @NotNull
  private Long userid;
  
  @NotNull
  private Set<Long> privileges;
  
  public PrivilegesRequest(@NotNull Long userid, @NotNull Set<Long> privileges) {
    this.userid = userid;
    this.privileges = privileges;
  }
  
  public PrivilegesRequest() {}
  
  public void setUserid(@NotNull Long userid) {
    this.userid = userid;
  }
  
  public void setPrivileges(@NotNull Set<Long> privileges) {
    this.privileges = privileges;
  }

  
  public String toString() {
    return "PrivilegesRequest(userid=" + getUserid() + ", privileges=" + getPrivileges() + ")";
  }
  
  @NotNull
  public Long getUserid() {
    return this.userid;
  }
  
  @NotNull
  public Set<Long> getPrivileges() {
    return this.privileges;
  }
}
