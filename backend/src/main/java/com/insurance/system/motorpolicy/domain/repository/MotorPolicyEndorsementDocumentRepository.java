package com.insurance.system.motorpolicy.domain.repository;

import com.insurance.system.motorpolicy.domain.models.MotorPolicyEndorsementDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MotorPolicyEndorsementDocumentRepository extends JpaRepository<MotorPolicyEndorsementDocument, Long> {
  Optional<MotorPolicyEndorsementDocument> findByFileName(String paramString);
}
