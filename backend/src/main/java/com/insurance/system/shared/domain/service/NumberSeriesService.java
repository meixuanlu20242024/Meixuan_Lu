package com.insurance.system.shared.domain.service;

import org.springframework.http.ResponseEntity;
import com.insurance.system.shared.domain.payload.NumberSeriesDTO;

public interface NumberSeriesService {
    String generateNumber(NumberSeriesDTO entityType);
    ResponseEntity<?> getLastUsedNumber(NumberSeriesDTO entityType) ;

}
