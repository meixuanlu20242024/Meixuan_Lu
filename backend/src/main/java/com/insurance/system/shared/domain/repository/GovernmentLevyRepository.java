package com.insurance.system.shared.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.insurance.system.shared.domain.models.GovermentLevy;

public interface GovernmentLevyRepository extends JpaRepository<GovermentLevy, Long> {}
