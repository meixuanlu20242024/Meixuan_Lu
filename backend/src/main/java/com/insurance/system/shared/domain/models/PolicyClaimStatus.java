package com.insurance.system.shared.domain.models;

//@JsonSerialize(using = EnumSerializerCIB.class)

public enum PolicyClaimStatus {
    Assessor_Appointed("Assessor Appointed"),
    Release_Signed("Release Signed"),
    Proof_Of_Payment("Proof Of Payment"),
    Settled("Settled"),
    Claims_Recovery("Claims Recovery");

    private final String displayName;

    PolicyClaimStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
