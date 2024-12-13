package com.insurance.system.shared.domain.models.datatables;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class DataTableRequest {
  private String draw;
  
  private List<Column> columns;
  
  private List<Order> order;
  
  private String start;
  
  private String length;
  
  private Search search;
  
  private String empty;
  
  @JsonProperty("draw")
  public String getDraw() {
    return this.draw;
  }
  
  @JsonProperty("draw")
  public void setDraw(String value) {
    this.draw = value;
  }
  
  @JsonProperty("columns")
  public List<Column> getColumns() {
    return this.columns;
  }
  
  @JsonProperty("columns")
  public void setColumns(List<Column> value) {
    this.columns = value;
  }
  
  @JsonProperty("order")
  public List<Order> getOrder() {
    return this.order;
  }
  
  @JsonProperty("order")
  public void setOrder(List<Order> value) {
    this.order = value;
  }
  
  @JsonProperty("start")
  public String getStart() {
    return this.start;
  }
  
  @JsonProperty("start")
  public void setStart(String value) {
    this.start = value;
  }
  
  @JsonProperty("length")
  public String getLength() {
    return this.length;
  }
  
  @JsonProperty("length")
  public void setLength(String value) {
    this.length = value;
  }
  
  @JsonProperty("search")
  public Search getSearch() {
    return this.search;
  }
  
  @JsonProperty("search")
  public void setSearch(Search value) {
    this.search = value;
  }
  
  @JsonProperty("_")
  public String getEmpty() {
    return this.empty;
  }
  
  @JsonProperty("_")
  public void setEmpty(String value) {
    this.empty = value;
  }
}
