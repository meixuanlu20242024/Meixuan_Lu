package com.insurance.system.shared.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.insurance.system.shared.domain.models.PoliciesEnum;
import com.insurance.system.shared.domain.models.PolicyName;

import java.util.Optional;

@Repository
public interface PolicyNameRepository extends JpaRepository<PolicyName, Long> {
    Optional<PolicyName> findByPolicyName(PoliciesEnum policyName);

    Page<PolicyName> findAll(Specification<PolicyName> specification, Pageable pageable);
}

