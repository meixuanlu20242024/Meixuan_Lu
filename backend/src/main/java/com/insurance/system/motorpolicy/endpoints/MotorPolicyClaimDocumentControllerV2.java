package com.insurance.system.motorpolicy.endpoints;


import com.insurance.system.motorpolicy.domain.models.MotorPolicyClaim;
import com.insurance.system.motorpolicy.domain.repository.MotorPolicyClaimRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping({"/api/v2/motor-policy-claim-document"})
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MotorPolicyClaimDocumentControllerV2 {

    private final MotorPolicyClaimRepository motorPolicyRepository;

    public MotorPolicyClaimDocumentControllerV2(MotorPolicyClaimRepository motorPolicyRepository) {
        this.motorPolicyRepository = motorPolicyRepository;
    }

    @GetMapping({"/view/{id}"})
    public ResponseEntity<?> viewDocuments(@PathVariable Long id) {
        Optional<MotorPolicyClaim> policy = this.motorPolicyRepository.findById(id);
        if (!policy.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(policy.get().getMotorPolicyClaimDocuments());
    }

}
