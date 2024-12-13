package com.insurance.system.motorpolicy.domain.service;

import com.insurance.system.motorpolicy.domain.filters.MotorPolicyFilterSpecification;
import com.insurance.system.motorpolicy.domain.models.MotorPolicy;
import com.insurance.system.motorpolicy.domain.models.MotorPolicyClaim;
import com.insurance.system.motorpolicy.domain.models.MotorPolicyEndorsement;
import com.insurance.system.motorpolicy.domain.repository.MotorPolicyClaimRepository;
import com.insurance.system.motorpolicy.domain.repository.MotorPolicyEndorsementRepository;
import com.insurance.system.motorpolicy.domain.repository.MotorPolicyRepository;
import com.insurance.system.shared.domain.payload.PolicyReportFilterRequest;
import com.insurance.system.shared.domain.repository.PolicyRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Optional;

@Service
public class MotorPolicyAccountServiceImpl implements MotorPolicyAccountService {
  private static final Logger log = LoggerFactory.getLogger(MotorPolicyAccountServiceImpl.class);
  
  private final MotorPolicyRepository motorPolicyRepository;
  private final PolicyRepositoryImpl policyRepositoryImpl;
  private final MotorPolicyClaimRepository motorPolicyClaimRepository;
  
  private final MotorPolicyEndorsementRepository motorPolicyEndorsementRepository;
  
    public MotorPolicyAccountServiceImpl(MotorPolicyRepository motorPolicyRepository, PolicyRepositoryImpl policyRepositoryImpl, MotorPolicyClaimRepository motorPolicyClaimRepository, MotorPolicyEndorsementRepository motorPolicyEndorsementRepository) {
    this.motorPolicyRepository = motorPolicyRepository;
    this.policyRepositoryImpl = policyRepositoryImpl;
    this.motorPolicyClaimRepository = motorPolicyClaimRepository;
    this.motorPolicyEndorsementRepository = motorPolicyEndorsementRepository;
  }
  
  public Double getTotalSumInsured() {
    return this.motorPolicyRepository.getTotalSumInsured();
  }
  
  public Double getTotalClaim() {
    return this.motorPolicyClaimRepository.getTotalLossAmount();
  }
  
  public Double getTotalEndorsements() {
    return this.motorPolicyEndorsementRepository.TotalEndorsements();
  }
  
  public Double getTotalSumInsuredByPolicy(Long id) {
    return this.motorPolicyRepository.TotalSumInsuredByPolicy(id);
  }
  
  public Double getTotalLossAmountByPolicy(Long Id) {
    return this.motorPolicyClaimRepository.getTotalLossAmountByPolicy(Id);
  }
  public long getRePolicyCount() {
    return this.motorPolicyRepository.count();
  }

  @Override
  public long getRePolicyCount(PolicyReportFilterRequest policyReportFilterRequest) {
    Specification<MotorPolicy> specification = new MotorPolicyFilterSpecification().filterMotorPolicy(policyReportFilterRequest);
    if (specification != null)
      return this.motorPolicyRepository.count(specification);
    return 0;
  }


  public long getRePolicyClaimsCount() {
    return this.motorPolicyClaimRepository.count();
  }
  @Override
  public long getRePolicyClaimsCount(PolicyReportFilterRequest policyReportFilterRequest) {
    Specification<MotorPolicyClaim> specification = new MotorPolicyFilterSpecification().filterMotorPolicyClaim(policyReportFilterRequest);
    if (specification != null)
      return this.motorPolicyClaimRepository.count(specification);
    return 0;
  }
  public String getLastClaimsTest(Long id) {
    log.info("# are we in here ");
    Optional<MotorPolicy> policy = this.motorPolicyRepository.findById(id);
    Optional<MotorPolicyClaim> lastupdated = ((MotorPolicy)policy.get()).getPolicyClaim().stream().max(Comparator.comparing(MotorPolicyClaim::getId));
    if (lastupdated.isPresent())
      log.info("last updated = " + ((MotorPolicyClaim)lastupdated.get()).getId()); 
    return "lastupdated";
  }



  @Override
  public long getRePolicyEndorsementsCount(PolicyReportFilterRequest policyReportFilterRequest) {
    Specification<MotorPolicyEndorsement> specification = new MotorPolicyFilterSpecification().filterMotorPolicyEndorsement(policyReportFilterRequest);
    if (specification != null)
      return this.motorPolicyEndorsementRepository.count(specification);
    return 0;
  }

  @Override
  public Double getTotalSumInsured(PolicyReportFilterRequest policyReportFilterRequest) {
    Specification<MotorPolicy> specification = new MotorPolicyFilterSpecification().filterMotorPolicy(policyReportFilterRequest);
    if (specification != null) {
      return this.policyRepositoryImpl.getTotalSumInsured(specification);
    }
    return 0.0;
  }

  @Override
  public Double getTotalPremium(PolicyReportFilterRequest policyReportFilterRequest) {
    Specification<MotorPolicy> specification = new MotorPolicyFilterSpecification().filterMotorPolicy(policyReportFilterRequest);
    if (specification != null) {
      return this.policyRepositoryImpl.getTotalPremium(specification);
    }
    return 0.0;
  }

  @Override
  public Double getTotalClaim(PolicyReportFilterRequest policyReportFilterRequest) {
    Specification<MotorPolicyClaim> specification = new MotorPolicyFilterSpecification().filterMotorPolicyClaim(policyReportFilterRequest);
    if (specification != null) {
      return this.policyRepositoryImpl.getTotalClaim(specification);
    }
    return 0.0;
  }


  public long getRePolicyEndorsementsCount() {
    return this.motorPolicyEndorsementRepository.count();
  }
}
