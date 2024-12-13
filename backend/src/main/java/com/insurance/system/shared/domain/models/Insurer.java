package com.insurance.system.shared.domain.models;

import com.insurance.system.shared.domain.payload.InsurerRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Insurer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull(message = "name cannot be empty.")
  private String name;

  private String address ;
  private String  telephoneNumber ;
  private String  faxNumber ;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date incorporationDate ;
  private String  mobileNumber ;
  private String  registrationNumber ;
  @Email
  @NotNull(message = "Enter valid email ")
  @Column(unique=true)
  private String  email ;
  public Insurer(InsurerRequest insurerRequest) {
    this.name = insurerRequest.getName();
    this.faxNumber = insurerRequest.getFaxNumber();
    this.telephoneNumber = insurerRequest.getTelephoneNumber();
    this.incorporationDate = insurerRequest.getIncorporationDate();
    this.mobileNumber = insurerRequest.getMobileNumber();
    this.email = insurerRequest.getEmail();
    this.address = insurerRequest.getAddress();
    this.registrationNumber = insurerRequest.getRegistrationNumber();

  }
}
