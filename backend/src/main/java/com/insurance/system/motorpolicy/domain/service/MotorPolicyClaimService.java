package com.insurance.system.motorpolicy.domain.service;

import com.insurance.system.motorpolicy.domain.models.MotorPolicy;
import com.insurance.system.motorpolicy.domain.models.MotorPolicyClaim;
import com.insurance.system.motorpolicy.payload.MotorPolicyClaimRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MotorPolicyClaimService {
  String createClaim(MotorPolicyClaim paramMotorPolicyClaim, MultipartFile[] paramArrayOfMultipartFile, Long paramLong) throws Exception;
  ResponseEntity<?> createClaimV2(MotorPolicyClaimRequest claimRequest) throws Exception;
  String updateClaim(Long paramLong, MotorPolicyClaim paramMotorPolicyClaim) throws Exception;
  
  String addPolicyClaimDocuments(MotorPolicyClaim paramMotorPolicyClaim, MultipartFile[] paramArrayOfMultipartFile) throws Exception;
  
  List<MotorPolicyClaim> AllPolicyClaims();
  
  Page<MotorPolicyClaim> allClaimsByPolicyId(Long paramLong, Pageable paramPageable);

  Page<MotorPolicyClaim> AllPolicyClaims(Pageable pageable);


    List<MotorPolicyClaim> allClaimsByPolicyId(MotorPolicy motorPolicy);
}
