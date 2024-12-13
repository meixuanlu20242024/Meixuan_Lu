package com.insurance.system.shared.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.insurance.system.shared.domain.models.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Page<Invoice> findAllByPolicyIdAndPolicyType(Long id, String policyName, Pageable pageable);

    Page<Invoice> findAllByInsurerId(Long insurerId, Pageable pageable);
}
