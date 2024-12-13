package com.insurance.system.motorpolicy.domain.service;

import com.insurance.system.motorpolicy.domain.models.MotorPolicyEndorsement;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MotorPolicyEndorsementService {
  String createEndorsement(MotorPolicyEndorsement paramMotorPolicyEndorsement, MultipartFile[] paramArrayOfMultipartFile, Long paramLong) throws Exception;
  
  String addPolicyDocuments(MotorPolicyEndorsement paramMotorPolicyEndorsement, MultipartFile[] paramArrayOfMultipartFile) throws Exception;
  
  String updateEndorsement(Long paramLong, MotorPolicyEndorsement paramMotorPolicyEndorsement) throws Exception;
  
  Page<MotorPolicyEndorsement> RePoliciesPaginated(int paramInt1, int paramInt2, String paramString1, String paramString2);
  
  List<MotorPolicyEndorsement> AllPoliciesEndorsemets();
  
  List<MotorPolicyEndorsement> AllPoliciesEndorsementsByPolicyId(Long id);
}
