package com.insurance.system.shared.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "PolicyNames")
public class PolicyName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Enumerated(EnumType.STRING)
    private PoliciesEnum policyName;

    @Column
    private String policyDescription;

    @Column
    private String policyCode;

    @Column  double commissionRate;

    @Column
    private String avatar;

    public PolicyName(PoliciesEnum policy , String policyDescription, String policyCode, String avatar) {
        this.policyName = policy;
        this.policyDescription = policyDescription;
        this.policyCode = policyCode;
        this.avatar = avatar;


    }
}
