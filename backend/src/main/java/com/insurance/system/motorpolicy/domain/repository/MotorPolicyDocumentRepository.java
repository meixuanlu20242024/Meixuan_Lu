package com.insurance.system.motorpolicy.domain.repository;

import com.insurance.system.motorpolicy.domain.models.MotorPolicyDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MotorPolicyDocumentRepository extends JpaRepository<MotorPolicyDocument, Long> {
  Optional<MotorPolicyDocument> findByFileName(String paramString);
}
