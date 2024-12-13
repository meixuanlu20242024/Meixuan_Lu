package com.insurance.system.motorpolicy.endpoints;

import com.insurance.system.motorpolicy.domain.models.MotorPolicy;
import com.insurance.system.motorpolicy.domain.models.MotorPolicyClaim;
import com.insurance.system.motorpolicy.domain.models.MotorPolicyClaimStatus;
import com.insurance.system.motorpolicy.domain.repository.MotorPolicyClaimRepository;
import com.insurance.system.motorpolicy.domain.repository.MotorPolicyRepository;
import com.insurance.system.motorpolicy.domain.service.MotorPolicyClaimService;
import com.insurance.system.motorpolicy.payload.MotorPolicyClaimRequest;
import com.insurance.system.shared.domain.payload.FileUploadDetails;
import com.insurance.system.shared.domain.payload.PageableObj;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping({"/api/v2/motor-policy-claim"})
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MotorPolicyClaimControllerV2 {

    private final MotorPolicyClaimService motorPolicyClaimService;
    private final MotorPolicyRepository motorPolicyRepository;
    private final MotorPolicyClaimRepository motorPolicyClaimRepository;

    @Autowired
    public MotorPolicyClaimControllerV2(MotorPolicyClaimService motorPolicyClaimService, MotorPolicyRepository motorPolicyRepository, MotorPolicyClaimRepository motorPolicyClaimRepository) {
        this.motorPolicyClaimService = motorPolicyClaimService;
        this.motorPolicyRepository = motorPolicyRepository;
        this.motorPolicyClaimRepository = motorPolicyClaimRepository;
    }

    // List all claims for a specific policy
    @GetMapping({"/list/{id}"})
    public ResponseEntity<?> allClaimsByPolicyId(@PathVariable("id") Long id, Pageable pageable) {
        Optional<MotorPolicy> policy = this.motorPolicyRepository.findById(id);
        if (!policy.isPresent()) {
            return ResponseEntity.badRequest().body("Cannot list claims, Policy not Found with id " + id);
        }

        List<MotorPolicyClaim> claims = this.motorPolicyClaimService.allClaimsByPolicyId(policy.get());
        return ResponseEntity.ok().body(claims);
    }

    // Retrieve all policy claims in a paginated format
    @PostMapping({"/all-claims"})
    public ResponseEntity<?> allPolicyClaims(@RequestBody PageableObj pageableObj) {

        Pageable pageable = PageRequest.of(pageableObj.getPage(), pageableObj.getSize());
        Page<MotorPolicyClaim> claims = this.motorPolicyClaimService.AllPolicyClaims(pageable);
        return ResponseEntity.ok().body(claims);
    }

    // Create a new motor policy claim
    @PostMapping({"/create"})
    public ResponseEntity<?> createClaimForm(@Valid @RequestBody MotorPolicyClaimRequest claimRequest) throws Exception {
        // Log file details during claim creation
        for (FileUploadDetails fileUploadDetails : claimRequest.getMotorPolicyClaimDocuments()) {
            log.info("File name: {}", fileUploadDetails.getFileName());
        }
        return this.motorPolicyClaimService.createClaimV2(claimRequest);
    }

    // Get the status of motor policy claims
    @GetMapping("/statuses")
    public ResponseEntity<?> getPolicyStatuses() {
        return ResponseEntity.ok().body(MotorPolicyClaimStatus.values());
    }
}
