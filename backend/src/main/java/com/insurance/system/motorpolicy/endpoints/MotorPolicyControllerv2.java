package com.insurance.system.motorpolicy.endpoints;

import com.insurance.system.motorpolicy.domain.models.CoverType;
import com.insurance.system.motorpolicy.domain.models.FleetIndividual;
import com.insurance.system.motorpolicy.domain.models.MotorPolicy;
import com.insurance.system.motorpolicy.domain.models.renewal.MotorPolicyRenewal;
import com.insurance.system.motorpolicy.domain.repository.MotorPolicyClaimRepository;
import com.insurance.system.motorpolicy.domain.repository.MotorPolicyEndorsementRepository;
import com.insurance.system.motorpolicy.domain.repository.MotorPolicyRepository;
import com.insurance.system.motorpolicy.domain.service.MotorPolicyAccountService;
import com.insurance.system.motorpolicy.domain.service.MotorPolicyClaimService;
import com.insurance.system.motorpolicy.domain.service.MotorPolicyEndorsementService;
import com.insurance.system.motorpolicy.domain.service.MotorPolicyService;
import com.insurance.system.motorpolicy.payload.MotorPolicyRequest;
import com.insurance.system.shared.domain.filters.PolicyFilterSpecification;
import com.insurance.system.shared.domain.models.RenewalPeriods;
import com.insurance.system.shared.domain.payload.FileUploadDetails;
import com.insurance.system.shared.domain.payload.PageableObj;
import com.insurance.system.shared.domain.repository.RetailClientRepository;
import com.insurance.system.shared.domain.service.PolicyService;
import com.insurance.system.shared.filestorage.context.verify.FileVerificationService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/api/v2/motor-policy"})
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MotorPolicyControllerv2 {
  private static final Logger log = LoggerFactory.getLogger(MotorPolicyControllerv2.class);

  private final MotorPolicyService motorPolicyService;

  private final MotorPolicyEndorsementService motorPolicyEndorsementService;

  private final MotorPolicyClaimService motorPolicyClaimService;

  private final MotorPolicyRepository motorPolicyRepository;

  private final MotorPolicyClaimRepository motorPolicyClaimRepository;

  private final MotorPolicyEndorsementRepository motorPolicyEndorsementRepository;

  private final MotorPolicyAccountService motorPolicyAccountService;


  private final FileVerificationService fileVerificationService;

  private final RetailClientRepository retailClientRepository;

  private final PolicyService policyService;
  private final PolicyFilterSpecification<MotorPolicy> policyFilterSpecification;


  @Autowired
  public MotorPolicyControllerv2(MotorPolicyService motorPolicyService, MotorPolicyEndorsementService motorPolicyEndorsementService, MotorPolicyClaimService motorPolicyClaimService, MotorPolicyRepository motorPolicyRepository, MotorPolicyClaimRepository motorPolicyClaimRepository, MotorPolicyEndorsementRepository motorPolicyEndorsementRepository, MotorPolicyAccountService motorPolicyAccountService, FileVerificationService fileVerificationService, RetailClientRepository retailClientRepository, PolicyService policyService, PolicyFilterSpecification policyFilterSpecification) {
    this.motorPolicyService = motorPolicyService;
    this.motorPolicyEndorsementService = motorPolicyEndorsementService;
    this.motorPolicyClaimService = motorPolicyClaimService;
    this.motorPolicyRepository = motorPolicyRepository;
    this.motorPolicyClaimRepository = motorPolicyClaimRepository;
    this.motorPolicyEndorsementRepository = motorPolicyEndorsementRepository;
    this.motorPolicyAccountService = motorPolicyAccountService;

    this.fileVerificationService = fileVerificationService;
    this.retailClientRepository = retailClientRepository;
    this.policyService = policyService;
    this.policyFilterSpecification = policyFilterSpecification;

  }

  @PostMapping({"/list"})
  public ResponseEntity<?> list(@RequestBody PageableObj pageableObj) {


    return ResponseEntity.ok().body(motorPolicyService.AllPoliciesPaginated(pageableObj));
  }
  @GetMapping("/cover-types")
  public ResponseEntity<?> getPolicyStatuses() {
    return ResponseEntity.ok().body(CoverType.values());
  }

  @GetMapping("/fleet-individual")
  public ResponseEntity<?> getFleetIndividual() {
    return ResponseEntity.ok().body(FleetIndividual.values());
  }
//
//  @PostMapping({"/create"})
//  public ResponseEntity<?> savePolicy(@Valid @ModelAttribute MotorPolicyRequest policyRequest) throws Exception {
//
////    initialize policyExcel as empty file
//
//    MultipartFile[] files = policyRequest.getMotorPolicyDocuments();
//    List<String> fileErrorsList = new ArrayList<>();
//
//    for (MultipartFile file : files)
//      fileErrorsList.addAll(this.fileVerificationService.validateFileForUpload(file, "Documents files "));
//    if (policyRequest.getPeriodFrom().after(policyRequest.getPeriodTo()))
//      fileErrorsList.add("Period From  cannot be greater than Period To ");
//    String policyMsg = this.policyService.isPolicyPeriodValid(policyRequest.getPeriodFrom(), policyRequest.getPeriodTo(), policyRequest.getSumInsured(), policyRequest.getRate());
//    if (policyMsg != null)
//      fileErrorsList.add(policyMsg);
//    if (!fileErrorsList.isEmpty()) {
//      log.info("fileErrorsList" + fileErrorsList);
//      return ResponseEntity.badRequest().body(fileErrorsList);
//    }
//
//    return motorPolicyService.createPolicyV2(policyRequest, files, policyRequest.getPolicyExcel());
//
//  }

  @PostMapping({"/create"})
  public ResponseEntity<?> savePolicy(@Valid @ModelAttribute MotorPolicyRequest policyRequest) throws Exception {
    MultipartFile[] files = policyRequest.getMotorPolicyDocuments();
    List<String> fileErrorsList = new ArrayList<>();

    if (files != null) {
      for (MultipartFile file : files) {
        fileErrorsList.addAll(this.fileVerificationService.validateFileForUpload(file, "Documents files"));
      }
    }

    if (policyRequest.getPeriodFrom().after(policyRequest.getPeriodTo())) {
      fileErrorsList.add("Period From cannot be greater than Period To");
    }

    String policyMsg = this.policyService.isPolicyPeriodValid(
            policyRequest.getPeriodFrom(),
            policyRequest.getPeriodTo(),
            policyRequest.getSumInsured(),
            policyRequest.getRate()
    );

    if (policyMsg != null) {
      fileErrorsList.add(policyMsg);
    }

    if (!fileErrorsList.isEmpty()) {
      log.info("fileErrorsList: " + fileErrorsList);
      return ResponseEntity.badRequest().body(fileErrorsList);
    }

    return motorPolicyService.createPolicyV2(policyRequest, files, policyRequest.getPolicyExcel());
  }


  @PostMapping({"/edit"})
  public ResponseEntity<?> updatePolicy(@Valid @RequestBody MotorPolicyRequest policyRequest) throws Exception {

//    initialize policyExcel as empty file

    List<String> fileErrorsList = new ArrayList<>();


    if (policyRequest.getPeriodFrom().after(policyRequest.getPeriodTo()))
      fileErrorsList.add("Period From  cannot be greater than Period To ");
    String policyMsg = this.policyService.isPolicyPeriodValid(policyRequest.getPeriodFrom(), policyRequest.getPeriodTo(), policyRequest.getSumInsured(), policyRequest.getRate());
    if (policyMsg != null)
      fileErrorsList.add(policyMsg);
    if (!fileErrorsList.isEmpty()) {
      log.info("fileErrorsList" + fileErrorsList);
      return ResponseEntity.badRequest().body(fileErrorsList);
    }

    return motorPolicyService.updatePolicyV2(policyRequest, policyRequest.getPolicyExcel());

  }

  //=============================RENEWALS=================================
  @PostMapping({"/renew/{id}"})
  public ResponseEntity<?> renewPolicy(@PathVariable("id") Long id, @Valid @RequestBody MotorPolicyRequest policyRequest) {
    return this.motorPolicyService.renewPolicy(id, policyRequest);
  }


  @GetMapping({"/renewals/list/{id}"})
  public ResponseEntity<?> listRenewals(@PathVariable Long id, ModelMap model, @SortDefault({"id"}) Pageable pageable) {
//     fetch and return renewals for a given MotorPolicy ID.
    Page<MotorPolicyRenewal> objects = this.motorPolicyService.renewalsByPolicyIdPaginated(pageable, id);
    return ResponseEntity.ok().body(objects);
  }

  @PostMapping({"/renewal_period_list/{period}"})
  public ResponseEntity<?> getRenewalPeriodList(@PathVariable RenewalPeriods period, @RequestBody PageableObj pageableObj) {
    Pageable pageable = PageRequest.of(pageableObj.getPage(), pageableObj.getSize());
    Page<MotorPolicy> objects = this.motorPolicyService.renewalListPaginated(pageable, period);
    return ResponseEntity.ok().body(objects);
  }

  //=============================END RENEWALS=================================
  @GetMapping({"/{id}"})
  public ResponseEntity<?> viewPolicy(@PathVariable("id") Long id, Model model) {
    Optional<MotorPolicy> object = this.motorPolicyRepository.findById(id);
    if (!object.isPresent())
      return ResponseEntity.notFound().build();
    return ResponseEntity.ok().body(object.get());

  }


  @PostMapping({"/documents/add/{policyId}"})
  public ResponseEntity<?> addDocuments(@PathVariable Long policyId, @RequestBody List<FileUploadDetails> documents) throws Exception {

    return motorPolicyService.addDocumentsV2(policyId, documents);

  }
}