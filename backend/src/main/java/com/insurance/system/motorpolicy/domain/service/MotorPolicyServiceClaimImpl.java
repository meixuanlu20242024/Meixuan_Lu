package com.insurance.system.motorpolicy.domain.service;

import com.insurance.system.motorpolicy.domain.models.MotorPolicy;
import com.insurance.system.motorpolicy.domain.models.MotorPolicyClaim;
import com.insurance.system.motorpolicy.domain.models.MotorPolicyClaimDocument;
import com.insurance.system.motorpolicy.domain.repository.MotorPolicyClaimRepository;
import com.insurance.system.motorpolicy.domain.repository.MotorPolicyRepository;
import com.insurance.system.motorpolicy.payload.MotorPolicyClaimRequest;
import com.insurance.system.shared.domain.payload.FileUploadDetails;
import com.insurance.system.shared.filestorage.context.File;
import com.insurance.system.shared.filestorage.context.FileDao;
import com.insurance.system.shared.filestorage.context.FileDto;
import com.insurance.system.shared.filestorage.context.delete.FileDeleteRequest;
import com.insurance.system.shared.filestorage.context.delete.FileDeleteService;
import com.insurance.system.shared.filestorage.context.upload.FileUploadRequest;
import com.insurance.system.shared.filestorage.context.upload.FileUploadService;
import com.insurance.system.shared.filestorage.context.verify.FileVerificationService;
import com.insurance.system.shared.usermanagement.payload.ApiResponse;
import com.insurance.system.shared.utils.CustomBeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MotorPolicyServiceClaimImpl implements MotorPolicyClaimService {
  private static final Logger log = LoggerFactory.getLogger(MotorPolicyServiceClaimImpl.class);
  
  private final FileUploadService fileUploadService;
  
  private final MotorPolicyRepository motorPolicyRepository;
  
  private final MotorPolicyClaimRepository motorPolicyClaimRepository;
  
  private final FileDeleteService fileDeleteService;
  
  private final FileDao fileDao;
  private final FileVerificationService fileVerificationService;

  @Autowired
  public MotorPolicyServiceClaimImpl(FileUploadService fileUploadService, MotorPolicyRepository motorPolicyRepository, MotorPolicyClaimRepository motorPolicyClaimRepository, FileDeleteService fileDeleteService, FileDao fileDao, FileVerificationService fileVerificationService) {
    this.fileUploadService = fileUploadService;
    this.motorPolicyRepository = motorPolicyRepository;
    this.motorPolicyClaimRepository = motorPolicyClaimRepository;
    this.fileDeleteService = fileDeleteService;
    this.fileDao = fileDao;
    this.fileVerificationService = fileVerificationService;
  }
  
  public String createClaim(MotorPolicyClaim policyClaim, MultipartFile[] files, Long policyId) throws Exception {

    Optional<MotorPolicy> policy = this.motorPolicyRepository.findById(policyId);
    policyClaim.setId(null);
////    should we use the following?
//    policyClaim.setClaimNumber(generateUniqueClaimNumber());
//
    policyClaim.setPolicy(policy.get());
    policyClaim.setCreatedAt(new Date());
    policyClaim.setCreatedBy("admin");
    String filename = "";
    List<MotorPolicyClaimDocument> motorPolicyClaimDocuments = new ArrayList<>();
    for (MultipartFile file : files) {
      FileUploadRequest fileUploadRequest = FileUploadRequest.createFileUploadRequest(file, "allassetrisk2/");
      File savedFile = this.fileUploadService.uploadFile(fileUploadRequest);
      filename = FileDto.createFileDto(savedFile).getFileName();
      MotorPolicyClaimDocument motorPolicyClaimDocument = new MotorPolicyClaimDocument();
      motorPolicyClaimDocument.setFileName(filename);
      motorPolicyClaimDocument.setOriginalName(file.getOriginalFilename());
      motorPolicyClaimDocument.setPolicyClaim(policyClaim);
      motorPolicyClaimDocuments.add(motorPolicyClaimDocument);
    } 
    log.error("##################### check id " + policyClaim.getId());
    policyClaim.setMotorPolicyClaimDocuments(motorPolicyClaimDocuments);
    this.motorPolicyClaimRepository.save(policyClaim);
    return null;
  }

  @Override
  public ResponseEntity<?> createClaimV2(MotorPolicyClaimRequest claimRequest) throws Exception {
    Optional<MotorPolicy> policy = this.motorPolicyRepository.findById(claimRequest.getInsuredId());
    if (!policy.isPresent()) {
      return ResponseEntity.badRequest().body(new ApiResponse(false, "Cannot create claim , Policy not Found with id " + claimRequest.getInsuredId()));

    }


    MotorPolicyClaim policyClaim = new MotorPolicyClaim();
    BeanUtils.copyProperties(claimRequest, policyClaim , CustomBeanUtils.getNullPropertyNames(claimRequest));

    policyClaim.setId(null);
//    //    should we use the following?
//    policyClaim.setClaimNumber(generateUniqueClaimNumber());

    policyClaim.setPolicy(policy.get());
    policyClaim.setCreatedAt(new Date());
    policyClaim.setCreatedBy("admin");
    List<MotorPolicyClaimDocument> motorPolicyClaimDocuments = new ArrayList<>();
    for (FileUploadDetails request : claimRequest.getMotorPolicyClaimDocuments()) {

      MotorPolicyClaimDocument motorPolicyClaimDocument = new MotorPolicyClaimDocument();
      motorPolicyClaimDocument.setFileName(request.getFileName());
      motorPolicyClaimDocument.setOriginalName(request.getOriginalName());
      motorPolicyClaimDocument.setPolicyClaim(policyClaim);
      motorPolicyClaimDocuments.add(motorPolicyClaimDocument);
    }

    log.error("##################### check id " + policyClaim.getId());
    policyClaim.setMotorPolicyClaimDocuments(motorPolicyClaimDocuments);
    this.motorPolicyClaimRepository.save(policyClaim);
    return ResponseEntity.ok().body(new ApiResponse(true, "Claim created successfully"));
  }

  public String updateClaim(Long id, MotorPolicyClaim policyClaim) throws Exception {
    Optional<MotorPolicyClaim> existingPolicy = this.motorPolicyClaimRepository.findById(id);
    BeanUtils.copyProperties(policyClaim, existingPolicy.get(), CustomBeanUtils.getNullPropertyNames(policyClaim));
    this.motorPolicyClaimRepository.save(existingPolicy.get());
    return null;
  }
  
  public String addPolicyClaimDocuments(MotorPolicyClaim policyClaim, MultipartFile[] files) throws Exception {
    String filename = "";
    List<MotorPolicyClaimDocument> motorPolicyClaimDocuments = new ArrayList<>();
    for (MultipartFile file : files) {
      FileUploadRequest fileUploadRequest = FileUploadRequest.createFileUploadRequest(file, "allassetrisk2/endorsements/");
      File savedFile = this.fileUploadService.uploadFile(fileUploadRequest);
      filename = FileDto.createFileDto(savedFile).getFileName();
      MotorPolicyClaimDocument rePolicyEndorsement = new MotorPolicyClaimDocument();
      rePolicyEndorsement.setFileName(filename);
      rePolicyEndorsement.setOriginalName(file.getOriginalFilename());
      rePolicyEndorsement.setPolicyClaim(policyClaim);
      motorPolicyClaimDocuments.add(rePolicyEndorsement);
    } 
    log.error("##################### check id " + policyClaim.getId());
    policyClaim.setMotorPolicyClaimDocuments(motorPolicyClaimDocuments);
    this.motorPolicyClaimRepository.save(policyClaim);
    return null;
  }
  
  public List<MotorPolicyClaim> AllPolicyClaims() {
    return this.motorPolicyClaimRepository.findAll();
  }
  
  public Page<MotorPolicyClaim> allClaimsByPolicyId(Long id, Pageable pageable) {
    Optional<MotorPolicy> policy = this.motorPolicyRepository.findById(id);
    return this.motorPolicyClaimRepository.findAllByPolicy(policy.get(), pageable);
  }

  @Override
  public Page<MotorPolicyClaim> AllPolicyClaims(Pageable pageable) {
    return this.motorPolicyClaimRepository.findAll(pageable);
  }

  @Override
  public List<MotorPolicyClaim> allClaimsByPolicyId(MotorPolicy motorPolicy) {
    return this.motorPolicyClaimRepository.findAllByPolicy(motorPolicy);
  }

  private void sendDeleteRequest(String documentName) throws Exception {
    log.info(" are we even deleting anything ,  doc name is ", documentName);
    Optional<File> fileuploaded = this.fileDao.findByName(documentName);
    FileDeleteRequest request = FileDeleteRequest.createFileDeleteRequest(((File)fileuploaded.get()).getName(), ((File)fileuploaded.get()).getDirectory());
    this.fileDeleteService.deleteFile(request);
  }

//  should we add this?
  private String generateUniqueClaimNumber() {
    return "CLAIM-" + System.currentTimeMillis();  // Time Stamp to generate unique ID
  }

}
