package com.insurance.system.shared.domain.models;

import com.insurance.system.shared.domain.payload.RetailClientRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RetailClient {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull(message = "name cannot be empty.")
  private String name;

  private String permanentAddress ;
  private String  mailingAddress ;
  private String  telephoneNumber ;
  private String  faxNumber ;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date IncorporationDate ;
  private String  nationality ;
  private String  identityNumber ;
  private String  occupationLineOfBusiness ;
  private String  sourceOfFunds ;
  private String  contactPerson ;
  private String  mobileNumber ;
  @Email
  @NotNull(message = "Enter valid email ")
  @Column(unique=true)
  private String  email ;

  @Enumerated(EnumType.STRING)
  @NotNull(message = "clientType is required")
  private ClientType clientType;

  @OneToOne(cascade = {CascadeType.PERSIST})
  @JoinColumn(name = "renewal_id")
  private ClientRenewalNotification renewal;

  
  public void setClientType(@NotNull(message = "clientType is required") ClientType clientType) {
    this.clientType = clientType;
  }
  

//  public String toString() {
//    return "RetailClient(id=" + getId() + ", name=" + getName() + ", details=" + ", clientType=" + getClientType() + ")";
//  }

  
  @NotNull(message = "clientType is required")
  public ClientType getClientType() {
    return this.clientType;
  }

  public RetailClient(RetailClientRequest retailClientRequest) {


    this.name = retailClientRequest.getName();
    this.permanentAddress = retailClientRequest.getPermanentAddress();
    this.faxNumber = retailClientRequest.getFaxNumber();
    this.mailingAddress = retailClientRequest.getMailingAddress();
    this.telephoneNumber = retailClientRequest.getTelephoneNumber();
    this.IncorporationDate = retailClientRequest.getIncorporationDate();
    this.nationality = retailClientRequest.getNationality();
    this.identityNumber = retailClientRequest.getIdentityNumber();
    this.occupationLineOfBusiness = retailClientRequest.getOccupationLineOfBusiness();
    this.sourceOfFunds = retailClientRequest.getSourceOfFunds();
    this.contactPerson = retailClientRequest.getContactPerson();
    this.mobileNumber = retailClientRequest.getMobileNumber();
    this.email = retailClientRequest.getEmail();
    this.clientType = retailClientRequest.getClientType();




  }
}
