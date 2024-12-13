package com.insurance.system.shared.domain.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RetailClientDTO {

    Long id;
    String name;
    String permanentAddress;
    String mailingAddress;
    String telephoneNumber;
    String faxNumber;


}
