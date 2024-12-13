package com.insurance.system.shared.domain.service;

import org.springframework.http.ResponseEntity;
import com.insurance.system.shared.domain.models.RetailClient;
import com.insurance.system.shared.domain.payload.InsurerRequest;
import com.insurance.system.shared.domain.payload.PageableObj;
import com.insurance.system.shared.domain.payload.RetailClientRequest;

public interface RetailClientService {

  String updateClient(Long clientId, RetailClient retailClient) throws Exception;


  ResponseEntity<?> createClient(RetailClientRequest retailClientRequest);

    ResponseEntity<?> createInsurer(InsurerRequest request);


    ResponseEntity<?> editInsurer(InsurerRequest request ,  Long id);
  ResponseEntity<?> editClient(RetailClientRequest request, Long id);

  ResponseEntity<?> allClientsPaginated(PageableObj pageableObj);
  ResponseEntity<?> allInsurersPaginated(PageableObj pageableObj);
}
