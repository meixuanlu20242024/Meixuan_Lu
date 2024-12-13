package com.insurance.system.shared.endpoints;

import com.insurance.system.shared.domain.payload.InsurerRequest;
import com.insurance.system.shared.domain.payload.PageableObj;
import com.insurance.system.shared.domain.payload.RetailClientRequest;
import com.insurance.system.shared.domain.payload.SanctionAmlCheckRequest;
import com.insurance.system.shared.domain.repository.InsurerRepository;
import com.insurance.system.shared.domain.repository.RetailClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.insurance.system.shared.domain.filters.RetailClientSpecification;
import com.insurance.system.shared.domain.service.RetailClientService;
import com.insurance.system.shared.domain.service.SanctionsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping({"/api/v2/clients"})
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RetailClientControllerRest {
  private static final Logger log = LoggerFactory.getLogger(RetailClientControllerRest.class);

  private final RetailClientRepository retailClientRepository;
  private final RetailClientService retailClientService;
  private final SanctionsService sanctionsService;

  private final InsurerRepository insurerRepository;
  @Autowired
  public RetailClientControllerRest(RetailClientRepository retailClientRepository, RetailClientService retailClientService, RetailClientSpecification retailClientSpecification, SanctionsService sanctionsService, InsurerRepository insurerRepository) {
    this.retailClientRepository = retailClientRepository;
    this.retailClientService = retailClientService;
    this.sanctionsService = sanctionsService;
    this.insurerRepository = insurerRepository;
  }

  @GetMapping({"/"})


  public ResponseEntity<?> clients(PageableObj pageableObj) {
    return  retailClientService.allClientsPaginated(pageableObj);
  }
  @GetMapping({"/all"})

  public ResponseEntity<?> allClients() {
    return ResponseEntity.ok().body(this.retailClientRepository.findAll());
  }

@GetMapping({"/{id}"})

  public ResponseEntity<?> getClientById(@PathVariable Long id ) {
    return ResponseEntity.ok().body(this.retailClientRepository.findById(id));
  }
  @PostMapping({"/edit/{id}"})
  public ResponseEntity<?> editClient(@Valid @RequestBody RetailClientRequest request , @PathVariable Long id) {

    return retailClientService.editClient(request ,  id);

  }
  @PostMapping({"/list"})
  public ResponseEntity<?> allClientPaginated(@RequestBody PageableObj pageableObj) {
    return  retailClientService.allClientsPaginated(pageableObj);
  }
  @PostMapping({"/create"})
  public ResponseEntity<?> saveClient(@Valid @RequestBody RetailClientRequest retailClientRequest) {

    return retailClientService.createClient(retailClientRequest);

  }

  @PostMapping({"/aml-check"})
  public ResponseEntity<?> amlCheck(@Valid @RequestBody SanctionAmlCheckRequest request) {
    return sanctionsService.checkSanctionsbyName(request);
  }
//  =================================== Insurer ===================================
  @PostMapping({"/create-insurer"})
  public ResponseEntity<?> saveInsurer(@Valid @RequestBody InsurerRequest request) {

    return retailClientService.createInsurer(request);

  }

  @PostMapping({"/edit-insurer/{id}"})
  public ResponseEntity<?> editInsurer(@Valid @RequestBody InsurerRequest request ,  @PathVariable Long id) {

    return retailClientService.editInsurer(request ,  id);

  }

  @PostMapping({"/list-insurers"})
  public ResponseEntity<?> allInsurersPaginated(@RequestBody PageableObj pageableObj) {
    return retailClientService.allInsurersPaginated(pageableObj);
  }
  @GetMapping({"/insurer/{id}"})

  public ResponseEntity<?> getInsurerById(@PathVariable Long id ) {
    return ResponseEntity.ok().body(this.insurerRepository.findById(id));
  }
  @GetMapping({"/all-insurers"})

  public ResponseEntity<?> allInsurers() {
    return ResponseEntity.ok().body(this.insurerRepository.findAll());
  }

}
