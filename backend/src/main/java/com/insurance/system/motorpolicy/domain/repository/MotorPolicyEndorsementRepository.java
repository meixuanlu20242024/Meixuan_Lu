package com.insurance.system.motorpolicy.domain.repository;

import com.insurance.system.motorpolicy.domain.models.MotorPolicy;
import com.insurance.system.motorpolicy.domain.models.MotorPolicyEndorsement;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MotorPolicyEndorsementRepository extends JpaRepository<MotorPolicyEndorsement, Long> {
  @Query(value = "SELECT COALESCE(SUM(sumInsured),0)  FROM MotorPolicyEndorsement", nativeQuery = true)
  Double getTotalSumInsured();
  
  @Query(value = "SELECT COALESCE(SUM(sumInsured),0)  FROM MotorPolicyEndorsement WHERE Id=:id", nativeQuery = true)
  Double TotalEndorsementsByPolicy(Long id);
  
  @Query(value = "SELECT COALESCE(SUM(sumInsured),0)  FROM MotorPolicyEndorsement", nativeQuery = true)
  Double TotalEndorsements();
  
  List<MotorPolicyEndorsement> findAllByPolicy(MotorPolicy paramMotorPolicy);
  
  @Query(value = "SELECT * FROM MotorPolicyEndorsement WHERE rePolicy_Id=:id ", nativeQuery = true)
  Optional<MotorPolicyEndorsement> findByPolicyId(Long id);

  long count(Specification<MotorPolicyEndorsement> specification);
}
