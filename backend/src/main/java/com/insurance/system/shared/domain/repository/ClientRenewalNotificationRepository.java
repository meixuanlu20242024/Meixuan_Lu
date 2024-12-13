package com.insurance.system.shared.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.insurance.system.shared.domain.models.ClientRenewalNotification;
import com.insurance.system.shared.domain.models.PoliciesEnum;

import java.util.Optional;

@Repository
public interface ClientRenewalNotificationRepository extends JpaRepository<ClientRenewalNotification, Long> {
    Optional<ClientRenewalNotification> findOneByPolicyNameAndPolicyId(PoliciesEnum policyName, Long policyNo);
}


