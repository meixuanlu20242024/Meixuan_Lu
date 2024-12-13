package com.insurance.system.motorpolicy.domain.service;

import com.insurance.system.motorpolicy.domain.models.MotorPolicy;
import com.insurance.system.motorpolicy.domain.models.MotorPolicyEndorsement;
import com.insurance.system.motorpolicy.domain.models.MotorPolicyEndorsementDocument;
import com.insurance.system.motorpolicy.domain.models.MotorPolicyEnumDocumentType;
import com.insurance.system.motorpolicy.domain.repository.MotorPolicyEndorsementRepository;
import com.insurance.system.motorpolicy.domain.repository.MotorPolicyRepository;
import com.insurance.system.shared.domain.service.PolicyService;
import com.insurance.system.shared.filestorage.context.File;
import com.insurance.system.shared.filestorage.context.FileDao;
import com.insurance.system.shared.filestorage.context.FileDto;
import com.insurance.system.shared.filestorage.context.delete.FileDeleteRequest;
import com.insurance.system.shared.filestorage.context.delete.FileDeleteService;
import com.insurance.system.shared.filestorage.context.upload.FileUploadRequest;
import com.insurance.system.shared.filestorage.context.upload.FileUploadService;
import com.insurance.system.shared.utils.CustomBeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MotorPolicyEndorsementServiceImpl implements MotorPolicyEndorsementService {
  private static final Logger log = LoggerFactory.getLogger(MotorPolicyEndorsementServiceImpl.class);
  
  private final FileUploadService fileUploadService;
  
  private final MotorPolicyEndorsementRepository motorPolicyEndorsementRepository;
  
  private final MotorPolicyRepository motorPolicyRepository;
  
  private final FileDeleteService fileDeleteService;
  
  private final FileDao fileDao;
  
  private final PolicyService policyService;
  
  public MotorPolicyEndorsementServiceImpl(FileUploadService fileUploadService, MotorPolicyEndorsementRepository motorPolicyEndorsementRepository, MotorPolicyRepository motorPolicyRepository, FileDeleteService fileDeleteService, FileDao fileDao, PolicyService policyService) {
    this.fileUploadService = fileUploadService;
    this.motorPolicyEndorsementRepository = motorPolicyEndorsementRepository;
    this.motorPolicyRepository = motorPolicyRepository;
    this.fileDeleteService = fileDeleteService;
    this.fileDao = fileDao;
    this.policyService = policyService;
  }
  
  public String createEndorsement(MotorPolicyEndorsement policy, MultipartFile[] files, Long policyId) throws Exception {

    Optional<MotorPolicy> motorPolicy = this.motorPolicyRepository.findById(policyId);
    policy.setId(null);
    policy.setPolicy(motorPolicy.get());
    policy.setCreatedAt(new Date());
    policy.setCreatedBy("admin");
    String filename = "";
    List<MotorPolicyEndorsementDocument> motorPolicyEndorsementDocuments = new ArrayList<>();
    for (MultipartFile file : files) {
      FileUploadRequest fileUploadRequest = FileUploadRequest.createFileUploadRequest(file, "allassetrisk2/endorsements/");
      File savedFile = this.fileUploadService.uploadFile(fileUploadRequest);
      filename = FileDto.createFileDto(savedFile).getFileName();
      MotorPolicyEndorsementDocument motorPolicyEndorsementDocument = new MotorPolicyEndorsementDocument();
      motorPolicyEndorsementDocument.setFileName(filename);
      motorPolicyEndorsementDocument.setOriginalName(file.getOriginalFilename());
      motorPolicyEndorsementDocument.setMotorPolicyEnumDocumentType(MotorPolicyEnumDocumentType.ENDORSEMENT);
      motorPolicyEndorsementDocument.setPolicyEndorsement(policy);
      motorPolicyEndorsementDocuments.add(motorPolicyEndorsementDocument);
    } 
    log.error("##################### check id " + policy.getId());
    policy.setMotorPolicyEndorsementDocuments(motorPolicyEndorsementDocuments);
    policy.setCreatedAt(new Date());
    policy.setCreatedBy("admin");
    Double premium = this.policyService.calculatePremium(policy.getPeriodFrom(), policy.getPeriodTo(), policy.getSumInsured(), policy.getRate());
    policy.setPremium(premium);
    Double stampDuty = this.policyService.calculateStampDuty(premium);
    policy.setStampDuty(stampDuty);
    this.motorPolicyEndorsementRepository.save(policy);
    return null;
  }
  
  public String addPolicyDocuments(MotorPolicyEndorsement motorPolicyEndorsement, MultipartFile[] files) throws Exception {
    String filename = "";
    List<MotorPolicyEndorsementDocument> motorPolicyEndorsementDocuments = new ArrayList<>();
    for (MultipartFile file : files) {
      FileUploadRequest fileUploadRequest = FileUploadRequest.createFileUploadRequest(file, "allassetrisk2/endorsements/");
      File savedFile = this.fileUploadService.uploadFile(fileUploadRequest);
      filename = FileDto.createFileDto(savedFile).getFileName();
      MotorPolicyEndorsementDocument motorPolicyEndorsementDocument = new MotorPolicyEndorsementDocument();
      motorPolicyEndorsementDocument.setFileName(filename);
      motorPolicyEndorsementDocument.setOriginalName(file.getOriginalFilename());
      motorPolicyEndorsementDocument.setMotorPolicyEnumDocumentType(MotorPolicyEnumDocumentType.ENDORSEMENT);
      motorPolicyEndorsementDocument.setPolicyEndorsement(motorPolicyEndorsement);
      motorPolicyEndorsementDocuments.add(motorPolicyEndorsementDocument);
    } 
    log.error("##################### check id " + motorPolicyEndorsement.getId());
    motorPolicyEndorsement.setMotorPolicyEndorsementDocuments(motorPolicyEndorsementDocuments);
    this.motorPolicyEndorsementRepository.save(motorPolicyEndorsement);
    return null;
  }
  
  public String updateEndorsement(Long id, MotorPolicyEndorsement policy) throws Exception {
    Optional<MotorPolicyEndorsement> existingPolicy = this.motorPolicyEndorsementRepository.findById(id);
    Double premium = this.policyService.calculatePremium(policy.getPeriodFrom(), policy.getPeriodTo(), policy.getSumInsured(), policy.getRate());
    policy.setPremium(premium);
    Double stampDuty = this.policyService.calculateStampDuty(premium);
    policy.setStampDuty(stampDuty);
    BeanUtils.copyProperties(policy, existingPolicy.get(), CustomBeanUtils.getNullPropertyNames(policy));
    this.motorPolicyEndorsementRepository.save(existingPolicy.get());
    return null;
  }
  
  public Page<MotorPolicyEndorsement> RePoliciesPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
    Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(new String[] { sortField }).ascending() : Sort.by(new String[] { sortField }).descending();
    PageRequest pageRequest = PageRequest.of(pageNo - 1, pageSize, sort);
    return this.motorPolicyEndorsementRepository.findAll((Pageable)pageRequest);
  }
  
  public List<MotorPolicyEndorsement> AllPoliciesEndorsemets() {
    return this.motorPolicyEndorsementRepository.findAll();
  }
  
  public List<MotorPolicyEndorsement> AllPoliciesEndorsementsByPolicyId(Long id) {
    Optional<MotorPolicy> policy = this.motorPolicyRepository.findById(id);
    return this.motorPolicyEndorsementRepository.findAllByPolicy(policy.get());
  }
  
  private void sendDeleteRequest(String documentName) throws Exception {
    log.info(" are we even deleting anything ,  doc name is ", documentName);
    Optional<File> fileuploaded = this.fileDao.findByName(documentName);
    FileDeleteRequest request = FileDeleteRequest.createFileDeleteRequest(((File)fileuploaded.get()).getName(), ((File)fileuploaded.get()).getDirectory());
    this.fileDeleteService.deleteFile(request);
  }
}
