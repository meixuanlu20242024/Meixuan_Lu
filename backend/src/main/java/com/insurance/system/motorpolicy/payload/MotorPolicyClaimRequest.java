package com.insurance.system.motorpolicy.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.insurance.system.motorpolicy.domain.models.MotorPolicyClaimStatus;
import com.insurance.system.shared.domain.models.RetailClient;
import com.insurance.system.shared.domain.payload.FileUploadDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MotorPolicyClaimRequest {


    private List<FileUploadDetails> motorPolicyClaimDocuments;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date dateOfLoss;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date notificationDate;
    private RetailClient insured;
    private Long insuredId;
    private String timeOfLoss;
    private double lossAmount;
    private String claimNumber;
    private String lossDetails;
    private Date createdAt;
    private String createdBy;

//    @JsonDeserialize(using = EnumDeserializerCIB.class, as = MotorPolicyClaimStatus.class)
    private MotorPolicyClaimStatus claimStatus;

}
