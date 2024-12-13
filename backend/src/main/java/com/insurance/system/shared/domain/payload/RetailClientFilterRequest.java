package com.insurance.system.shared.domain.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.insurance.system.shared.domain.models.ClientType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetailClientFilterRequest {
  public String keywords;
  public ClientType clientType;


}
