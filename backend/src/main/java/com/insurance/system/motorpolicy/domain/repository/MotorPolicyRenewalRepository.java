package com.insurance.system.motorpolicy.domain.repository;

import com.insurance.system.motorpolicy.domain.models.renewal.MotorPolicyRenewal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface MotorPolicyRenewalRepository extends JpaRepository<MotorPolicyRenewal, Long> {
  @Query(value = "SELECT COALESCE(SUM(sumInsured),0)  FROM MotorPolicyRenewal", nativeQuery = true)
  Double getTotalSumInsured();
  
  @Query(value = "SELECT COALESCE(SUM(sumInsured),0)  FROM MotorPolicyRenewal WHERE Id=:id", nativeQuery = true)
  Double TotalSumInsuredByPolicy(Long id);
  
  Optional<MotorPolicyRenewal> findByPolicyNo(String paramString);
  
  Page<MotorPolicyRenewal> findAll(Pageable paramPageable);

  long countByPeriodToBetween(Date today, Date thirtyDaysFromToday);
  long countByPeriodToGreaterThan(Date ninetyDaysFromToday);
  long countByPeriodTo(Date due);

  long countByPeriodToLessThan(Date today);

  Page<MotorPolicyRenewal> findAllByPeriodToLessThan(Date today, Pageable pageable);

  Page<MotorPolicyRenewal> findByPeriodTo(Date today, Pageable pageable);

  Page<MotorPolicyRenewal> findAllByPeriodToBetween(Date today, Date sevenDays, Pageable pageable);

  Page<MotorPolicyRenewal> findAllByPeriodToGreaterThan(Date ninetyDaysFromToday, Pageable pageable);

    Page<MotorPolicyRenewal> findAll(Specification<MotorPolicyRenewal> filteredPolicies, Pageable pageable);
}



