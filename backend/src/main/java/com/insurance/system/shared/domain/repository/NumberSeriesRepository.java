package com.insurance.system.shared.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.insurance.system.shared.domain.models.NumberSeries;
import com.insurance.system.shared.domain.models.NumberSeriesType;

import java.util.Optional;

@Repository
public interface NumberSeriesRepository extends JpaRepository<NumberSeries, Long> {
    Optional<NumberSeries> findByEntityType(NumberSeriesType entityType);
}