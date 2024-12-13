package com.insurance.system.shared.usermanagement.repository;

import com.insurance.system.shared.usermanagement.domain.PasswordPolicy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordPolicyRepository extends JpaRepository<PasswordPolicy, Long> {}
