package com.insurance.system.shared.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.insurance.system.shared.domain.models.RetailClient;

@Repository
public interface RetailClientRepository extends JpaRepository<RetailClient, Long> {
  Optional<RetailClient> findByName(String paramString);

    Page<RetailClient> findAll(Specification<RetailClient> filteredClients, Pageable pageable);

    boolean existsByEmail(String email);


}
