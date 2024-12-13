package com.insurance.system.shared.endpoints;

import com.insurance.system.shared.domain.payload.NumberSeriesDTO;
import com.insurance.system.shared.domain.payload.PageableObj;
import com.insurance.system.shared.domain.payload.PolicyNameDTO;
import com.insurance.system.shared.domain.repository.PolicyNameRepository;
import com.insurance.system.shared.domain.service.NumberSeriesService;
import com.insurance.system.shared.domain.service.PolicyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.insurance.system.shared.domain.models.PolicyStatus;
import com.insurance.system.shared.domain.models.RenewalPeriods;
import com.insurance.system.shared.usermanagement.payload.ApiResponse;

import jakarta.validation.Valid;


@RestController
@Slf4j
@RequestMapping("/api/v2/shared")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class RestPolicesController {

//    include
    private final PolicyNameRepository policyNameRepository;
    private final NumberSeriesService numberSeriesService;

    private final PolicyService policyService;

    @Autowired
    public RestPolicesController(PolicyNameRepository policyNameRepository, NumberSeriesService numberSeriesService, PolicyService policyService) {

        this.policyNameRepository = policyNameRepository;
        this.numberSeriesService = numberSeriesService;
        this.policyService = policyService;
    }

    @GetMapping("/all=policy-names")
    public ResponseEntity<?> getAllPolicyNames() {
        return ResponseEntity.ok().body(policyNameRepository.findAll());
    }

    @GetMapping("/policy-statuses")
    public  ResponseEntity<?> getPolicyStatuses(){
        return ResponseEntity.ok().body(PolicyStatus.values()) ;
    }
    @PostMapping({"/policy-commissions"})
    public ResponseEntity<?> list(@RequestBody PageableObj pageableObj) {


        return ResponseEntity.ok().body(policyService.AllPoliciesRatesPaginated(pageableObj));
    }
    @PostMapping({"/adjust-commissions-rate"})
    public ResponseEntity<?> adjustCommissionRate(@RequestBody PolicyNameDTO policyNameDTo) {


        return  policyService.adjustCommission(policyNameDTo) ;
    }

    @GetMapping("/renewal-periods")

    public  ResponseEntity<?> getRenewalPeriods(){
        return ResponseEntity.ok().body(RenewalPeriods.values()) ;
    }

    @PostMapping("/number-series/last-number")
    public ResponseEntity<?> getLastNumberSeries(@Valid @RequestBody NumberSeriesDTO numberSeriesDTO) {
        return numberSeriesService.getLastUsedNumber(numberSeriesDTO) ;
    }
    @PostMapping("/number-series/generate-number")
    public ResponseEntity<?> generateNumberSeries(@Valid @RequestBody NumberSeriesDTO numberSeriesDTO) {

        return ResponseEntity.ok().body(new ApiResponse(true ,  numberSeriesService.generateNumber(numberSeriesDTO))) ;
    }


    @PostMapping("/invoices/list/{policyId}/{policyName}")
    public ResponseEntity<?> listInvoices(@PathVariable("policyId") Long id ,  @PathVariable("policyName") String policyName  , @Valid @RequestBody PageableObj pageableObj) {

        log.info("policy id {} and name {} " , id ,    policyName );
        return policyService.listInvoices(pageableObj , id  , policyName) ;
    }

    @PostMapping("/invoices/list-by-insurer/{insurerId}")
    public ResponseEntity<?> listInvoicesByInsurer(@PathVariable("insurerId") Long insurerId , @Valid @RequestBody PageableObj pageableObj) {

        return policyService.listInvoicesByInsurer(pageableObj , insurerId) ;
    }









}