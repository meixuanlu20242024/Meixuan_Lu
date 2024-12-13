package com.insurance.system.motorpolicy.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.insurance.system.motorpolicy.domain.models.CoverType;
import com.insurance.system.motorpolicy.domain.models.FleetIndividual;
import com.insurance.system.shared.domain.models.Currency;
import com.insurance.system.shared.domain.models.Insurer;
import com.insurance.system.shared.domain.models.PolicyStatus;
import com.insurance.system.shared.domain.models.RetailClient;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class MotorPolicyRequest {
    private Long id;
    private PolicyStatus policyStatus;
    private RetailClient insured;
    private Insurer insurer;
    private Currency currency;
    private FleetIndividual fleetIndividual;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date periodFrom;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date periodTo;
    private MultipartFile[] motorPolicyDocuments;
    private MultipartFile policyExcel;
  private String policyNo;
    private CoverType coverType;
    private double sumInsured;
    private double premium;
    private double rate;
    private double stampDuty;
    private double governmentLevy;
}
