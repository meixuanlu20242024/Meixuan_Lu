package com.insurance.system.shared.domain.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.insurance.system.shared.utils.EnumSerializerCIB;

@JsonSerialize(using = EnumSerializerCIB.class)
public enum NumberSeriesType {


    CLAIM("CL-"),
    ENDORSEMENT("EN-"),
    POLICY("PO-"),
    DR_NOTE("DN-"),
    CR_NOTE("CN-");

    private final String value;

    private NumberSeriesType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
