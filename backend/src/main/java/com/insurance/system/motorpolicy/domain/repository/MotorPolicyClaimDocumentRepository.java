package com.insurance.system.motorpolicy.domain.repository;

import com.insurance.system.motorpolicy.domain.models.MotorPolicyClaimDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MotorPolicyClaimDocumentRepository extends JpaRepository<MotorPolicyClaimDocument, Long> {
  Optional<MotorPolicyClaimDocument> findByFileName(String paramString);
}
