package com.insurance.system.shared.domain.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SanctionIndividual {
    private String dataId;
    private String versionNum;
    private String firstName;
    private String lastName;
    private String unListType;
    private String referenceNumber;
    private String listedOn;
    private String comments;
    private List<String> designations = new ArrayList<>();
    private String nationality;
    private String listType;
    private String lastDayUpdated;
    private List<String> aliases = new ArrayList<>();
    private String addressCountry;
    private String dateOfBirthType;
    private String dateOfBirthYear;
    private String placeOfBirthCity;
    private String placeOfBirthCountry;
    private List<String> documentNumbers = new ArrayList<>();

}