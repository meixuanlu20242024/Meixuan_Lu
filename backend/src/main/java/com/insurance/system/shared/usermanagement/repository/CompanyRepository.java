package com.insurance.system.shared.usermanagement.repository;

import com.insurance.system.shared.usermanagement.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {
  Company findById(Long id);
}
