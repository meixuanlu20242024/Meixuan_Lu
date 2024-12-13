package com.insurance.system.shared.domain.models.datatables;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Order {
  private String column;
  
  private String dir;
  
  @JsonProperty("column")
  public String getColumn() {
    return this.column;
  }
  
  @JsonProperty("column")
  public void setColumn(String value) {
    this.column = value;
  }
  
  @JsonProperty("dir")
  public String getDir() {
    return this.dir;
  }
  
  @JsonProperty("dir")
  public void setDir(String value) {
    this.dir = value;
  }
}
