package com.insurance.system.motorpolicy.domain.repository;

import com.insurance.system.motorpolicy.domain.models.MotorPolicy;
import com.insurance.system.motorpolicy.domain.models.MotorPolicyClaim;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotorPolicyClaimRepository extends JpaRepository<MotorPolicyClaim, Long> {
  List<MotorPolicyClaim> findAllByPolicy(MotorPolicy paramMotorPolicy);
  
  Page<MotorPolicyClaim> findAllByPolicy(MotorPolicy paramMotorPolicy, Pageable paramPageable);
  
  @Query(value = "SELECT COALESCE(SUM(lossAmount),0)  FROM MotorPolicyClaim", nativeQuery = true)
  Double getTotalLossAmount();
  
  @Query(value = "SELECT COALESCE(SUM(sumInsured),0)  FROM MotorPolicyClaim WHERE Id=:id", nativeQuery = true)
  Double getTotalLossAmountByPolicy(Long id);

  int count(Specification<MotorPolicyClaim> filteredClaims);


}
