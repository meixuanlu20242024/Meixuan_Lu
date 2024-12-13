package com.insurance.system.shared.domain.service;

import org.springframework.http.ResponseEntity;
import com.insurance.system.shared.domain.payload.SanctionAmlCheckRequest;

public interface ISanctionsService {
    ResponseEntity<?> checkSanctionsbyName(SanctionAmlCheckRequest request);
}
