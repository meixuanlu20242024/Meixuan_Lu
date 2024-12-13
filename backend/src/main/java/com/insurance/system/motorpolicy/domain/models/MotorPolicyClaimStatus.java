package com.insurance.system.motorpolicy.domain.models;

//@JsonSerialize(using = EnumSerializerCIB.class)

public enum MotorPolicyClaimStatus {
    Assessor_Appointed("Assessor Appointed"),
    Release_Signed("Release Signed"),
    Proof_Of_Payment("Proof Of Payment"),
    Settled("Settled"),
    Claims_Recovery("Claims Recovery");

    private final String displayName;

    MotorPolicyClaimStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
