package com.insurance.system.shared.domain.models.datatables;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Column {
  private String data;
  
  private String name;
  
  private String searchable;
  
  private String orderable;
  
  private Search search;
  
  @JsonProperty("data")
  public String getData() {
    return this.data;
  }
  
  @JsonProperty("data")
  public void setData(String value) {
    this.data = value;
  }
  
  @JsonProperty("name")
  public String getName() {
    return this.name;
  }
  
  @JsonProperty("name")
  public void setName(String value) {
    this.name = value;
  }
  
  @JsonProperty("searchable")
  public String getSearchable() {
    return this.searchable;
  }
  
  @JsonProperty("searchable")
  public void setSearchable(String value) {
    this.searchable = value;
  }
  
  @JsonProperty("orderable")
  public String getOrderable() {
    return this.orderable;
  }
  
  @JsonProperty("orderable")
  public void setOrderable(String value) {
    this.orderable = value;
  }
  
  @JsonProperty("search")
  public Search getSearch() {
    return this.search;
  }
  
  @JsonProperty("search")
  public void setSearch(Search value) {
    this.search = value;
  }
}
