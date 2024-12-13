package com.insurance.system.shared.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.insurance.system.shared.domain.models.StampDuty;

public interface StampDutyRepository extends JpaRepository<StampDuty, Long> {}
