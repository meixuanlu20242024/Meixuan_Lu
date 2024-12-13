package com.insurance.system.shared.domain.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Data
public class ClientRenewalNotification extends  BaseEntity{

  @Enumerated(EnumType.STRING)
  private PoliciesEnum policyName;

  private Long policyId;

  @CreatedDate
  private Date LastNotificationDate ;






}
