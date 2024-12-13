package com.insurance.system.shared.usermanagement.repository;

import com.insurance.system.shared.usermanagement.domain.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface PasswordResetRepository extends JpaRepository<PasswordResetToken, Long> {
  PasswordResetToken findByToken(String paramString);
  
  @Transactional
  void deleteByToken(String paramString);
  
  boolean existsByToken(String paramString);
}
