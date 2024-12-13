package com.insurance.system.motorpolicy.domain.service;

import com.insurance.system.motorpolicy.domain.models.MotorPolicy;
import com.insurance.system.motorpolicy.domain.models.renewal.MotorPolicyRenewal;
import com.insurance.system.motorpolicy.payload.MotorPolicyRequest;
import com.insurance.system.shared.domain.models.PoliciesEnum;
import com.insurance.system.shared.domain.models.RenewalPeriods;
import com.insurance.system.shared.domain.payload.FileUploadDetails;
import com.insurance.system.shared.domain.payload.PageableObj;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MotorPolicyService {
  boolean createPolicy(MotorPolicy paramMotorPolicy, MultipartFile[] paramArrayOfMultipartFile, MultipartFile policyExcel) throws Exception;
  
  String updatePolicy(Long paramLong, MotorPolicy paramMotorPolicy) throws Exception;
  
  Page<MotorPolicy> PoliciesPaginated(int paramInt1, int paramInt2, String paramString1, String paramString2);
  
  List<MotorPolicy> AllPolicies();
  
  Page<MotorPolicy> AllPoliciesPaginated(PageableObj paramPageable);
  Page<MotorPolicy> renewalListPaginated(Pageable pageable, RenewalPeriods period);

  ResponseEntity<?> renewPolicy(Long id, MotorPolicyRequest policy);

  void sendRenewalEmail(PoliciesEnum policyName);

  ResponseEntity<?> createPolicyV2(MotorPolicyRequest policyRequest, MultipartFile[] files, MultipartFile policyExcel) throws Exception;



  ResponseEntity<?> updatePolicyV2(MotorPolicyRequest policyRequest, MultipartFile policyExcel);

  ResponseEntity<?> addDocumentsV2(Long policyId, List<FileUploadDetails> documents);

  Page<MotorPolicyRenewal> renewalsByPolicyIdPaginated(Pageable pageable, Long id);
}
