package com.insurance.system.motorpolicy.endpoints;

import com.insurance.system.motorpolicy.domain.models.MotorPolicy;
import com.insurance.system.motorpolicy.domain.repository.MotorPolicyRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping({"/api/v2/motor-policy-document"})
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MotorPolicyDocumentControllerV2 {

    private final MotorPolicyRepository motorPolicyRepository;

    public MotorPolicyDocumentControllerV2(MotorPolicyRepository motorPolicyRepository) {
        this.motorPolicyRepository = motorPolicyRepository;
    }

    @GetMapping({"/view/{id}"})
    public ResponseEntity<?> viewDocuments(@PathVariable Long id) {
        Optional<MotorPolicy> policy = this.motorPolicyRepository.findById(id);
        if (!policy.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(policy.get().getMotorPolicyDocuments());
    }

}
