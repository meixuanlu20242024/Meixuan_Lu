package com.insurance.system.motorpolicy.domain.repository;

import com.insurance.system.motorpolicy.domain.models.MotorPolicy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface MotorPolicyRepository extends JpaRepository<MotorPolicy, Long> {
  @Query(value = "SELECT COALESCE(SUM(sumInsured),0)  FROM MotorPolicy", nativeQuery = true)
  Double getTotalSumInsured();
  
  @Query(value = "SELECT COALESCE(SUM(sumInsured),0)  FROM MotorPolicy WHERE Id=:id", nativeQuery = true)
  Double TotalSumInsuredByPolicy(Long id);
  
  Optional<MotorPolicy> findByPolicyNo(String paramString);
  
  Page<MotorPolicy> findAll(Pageable paramPageable);

  long countByPeriodToBetween(Date today, Date thirtyDaysFromToday);
  long countByPeriodToGreaterThan(Date ninetyDaysFromToday);
  long countByPeriodTo(Date due);

  long countByPeriodToLessThan(Date today);

  Page<MotorPolicy> findAllByPeriodToLessThan(Date today, Pageable pageable);

  Page<MotorPolicy> findByPeriodTo(Date today, Pageable pageable);

  Page<MotorPolicy> findAllByPeriodToBetween(Date today, Date sevenDays, Pageable pageable);
  List<MotorPolicy> findAllByPeriodToBetween(Date today, Date sevenDays);

  Page<MotorPolicy> findAllByPeriodToGreaterThan(Date ninetyDaysFromToday, Pageable pageable);

    Page<MotorPolicy> findAll(Specification<MotorPolicy> filteredPolicies, Pageable pageable);
    int count(Specification<MotorPolicy> filteredPolicies);


}



