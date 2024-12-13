package com.insurance.system.shared.usermanagement.payload;

import java.util.Set;
import jakarta.validation.constraints.NotNull;

public class RolesRequest {
  @NotNull
  private Long user_id;
  
  @NotNull
  private Set<Long> role_id;
  
  public RolesRequest(@NotNull Long user_id, @NotNull Set<Long> role_id) {
    this.user_id = user_id;
    this.role_id = role_id;
  }
  
  public RolesRequest() {}
  
  public void setUser_id(@NotNull Long user_id) {
    this.user_id = user_id;
  }
  
  public void setRole_id(@NotNull Set<Long> role_id) {
    this.role_id = role_id;
  }

  
  public String toString() {
    return "RolesRequest(user_id=" + getUser_id() + ", role_id=" + getRole_id() + ")";
  }
  
  @NotNull
  public Long getUser_id() {
    return this.user_id;
  }
  
  @NotNull
  public Set<Long> getRole_id() {
    return this.role_id;
  }
}
