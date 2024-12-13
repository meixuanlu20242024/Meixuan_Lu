package com.insurance.system.shared.domain.service;


import com.insurance.system.shared.domain.filters.InsurerSpecification;
import com.insurance.system.shared.domain.filters.RetailClientSpecification;
import com.insurance.system.shared.domain.models.Insurer;
import com.insurance.system.shared.domain.models.PoliciesEnum;
import com.insurance.system.shared.domain.models.PolicyName;
import com.insurance.system.shared.domain.models.RetailClient;
import com.insurance.system.shared.domain.payload.*;
import com.insurance.system.shared.domain.repository.InsurerRepository;
import com.insurance.system.shared.domain.repository.PolicyNameRepository;
import com.insurance.system.shared.domain.repository.RetailClientRepository;
import com.insurance.system.shared.utils.CustomBeanUtils;
import com.insurance.system.shared.domain.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.insurance.system.shared.usermanagement.payload.ApiResponse;

import java.util.*;

@Service
public class RetailClientServiceImpl implements RetailClientService {
  private static final Logger log = LoggerFactory.getLogger(RetailClientServiceImpl.class);

  private final RetailClientRepository retailClientRepository;
  private final InsurerRepository insurerRepository;
 private final UserRepository userRepository;

  private final PolicyNameRepository policyNameRepository ;


  @Autowired
  public RetailClientServiceImpl(RetailClientRepository retailClientRepository, InsurerRepository insurerRepository, UserRepository userRepository,  PolicyNameRepository policyNameRepository) {
    this.retailClientRepository = retailClientRepository;
    this.insurerRepository = insurerRepository;
    this.userRepository = userRepository;
      this.policyNameRepository = policyNameRepository;
  }




  @Override
  public String updateClient(Long clientId, RetailClient retailClient) throws Exception {


    Optional<RetailClient> existingPolicy = this.retailClientRepository.findById(clientId);

    BeanUtils.copyProperties(retailClient, existingPolicy.get(), CustomBeanUtils.getNullPropertyNames(retailClient));
    this.retailClientRepository.save(existingPolicy.get());

    return null;
  }

  @Override
  public ResponseEntity<?> createClient(RetailClientRequest retailClientRequest) {

    boolean existingClient = this.retailClientRepository.existsByEmail(retailClientRequest.getEmail());
    if (existingClient) {
      return ResponseEntity.badRequest().body(new ApiResponse(false, "Client with email " + retailClientRequest.getEmail() + " already exists"));
    }
    RetailClient retailClient = new RetailClient(retailClientRequest);
    this.retailClientRepository.save(retailClient);
    return ResponseEntity.ok().body(new ApiResponse(true, "Client created successfully")) ;
  }

  @Override
  public ResponseEntity<?> createInsurer(InsurerRequest request) {
    boolean existingClient = this.insurerRepository.existsByEmail(request.getEmail());
    if (existingClient) {
      return ResponseEntity.badRequest().body(new ApiResponse(false, "Insurer with email " + request.getEmail() + " already exists"));
    }
    Insurer insurer = new Insurer(request);
    this.insurerRepository.save(insurer);
    return ResponseEntity.ok().body(new ApiResponse(true, "Insurer created successfully")) ;
  }



  @Override
  public ResponseEntity<?> editInsurer(InsurerRequest request , Long id) {

    Optional<Insurer> existingPolicy = this.insurerRepository.findById(id);

    if (!existingPolicy.isPresent()) {
      return ResponseEntity.badRequest().body(new ApiResponse(false, "Insurer with id " + id + " does not exist"));
    }
    BeanUtils.copyProperties(request, existingPolicy.get(), CustomBeanUtils.getNullPropertyNames(request));
    this.insurerRepository.save(existingPolicy.get());
    return ResponseEntity.ok().body(new ApiResponse(true, "Insurer updated successfully")) ;
  }

  @Override
  public ResponseEntity<?> editClient(RetailClientRequest request, Long id) {
    Optional<RetailClient> existingPolicy = this.retailClientRepository.findById(id);

    if (!existingPolicy.isPresent()) {
      return ResponseEntity.badRequest().body(new ApiResponse(false, "Client with id " + id + " does not exist"));
    }
    BeanUtils.copyProperties(request, existingPolicy.get(), CustomBeanUtils.getNullPropertyNames(request));
    this.retailClientRepository.save(existingPolicy.get());
    return ResponseEntity.ok().body(new ApiResponse(true, "Client updated successfully")) ;
  }

  @Override
  public ResponseEntity<?> allClientsPaginated(PageableObj pageableObj) {
    RetailClientFilterRequest policyFilterRequest = new RetailClientFilterRequest();
      policyFilterRequest.setKeywords(pageableObj.getGlobalFilter());
      Pageable pageable = PageRequest.of(pageableObj.getPage(), pageableObj.getSize());

      Specification<RetailClient> specification = new RetailClientSpecification().getFilteredClients(policyFilterRequest);
        Page<RetailClient> objects = this.retailClientRepository.findAll(specification, pageable);
        return ResponseEntity.ok().body(objects);
  }

  @Override
  public ResponseEntity<?> allInsurersPaginated(PageableObj pageableObj) {
    InsurerFilterRequest policyFilterRequest = new InsurerFilterRequest();
    policyFilterRequest.setKeywords(pageableObj.getGlobalFilter());
    Pageable pageable = PageRequest.of(pageableObj.getPage(), pageableObj.getSize());

    Specification<Insurer> specification = new InsurerSpecification().getFilteredInsurers(policyFilterRequest);
    Page<Insurer> objects = this.insurerRepository.findAll(specification, pageable);
    return ResponseEntity.ok().body(objects);
  }

  private List<Map<String, Object>> prepareData(List<Object[]> resultList) {
    if (!resultList.isEmpty()) {
      List<Map<String, Object>> data = new ArrayList<>();
      for (Object[] result : resultList) {
        Map<String, Object> map = new HashMap<>();
         double amount =    (double)result[5]  ;
         String policyName = (String) result[0];
          map.put("policyType", result[0]);
        map.put("totalAmount", result[1]);
        map.put("currency", result[2]);

        if ((policyName.equals(PoliciesEnum.MotorPolicy.toString()) )){
            map.put("governmentLevy", amount * 0.12);
          }else{ map.put("governmentLevy", 0.00);}
        map.put("stampDuty", amount * 0.05 );
        map.put("premium", result[5]);
        map.put("brokerage_perc", this.getCommissionRate((String) result[0]));
        map.put("brokerageAmount",amount - (amount * (100-22.5) /100) );
        map.put("netDueToInsurer", (amount * (100-22.5) /100));

        data.add(map);
      }
      return data;
    }
    return  null;
  }

    private double getCommissionRate(String policyType) {
        Optional<PolicyName> policyName =  policyNameRepository.findByPolicyName(PoliciesEnum.valueOf(policyType)) ;
        return policyName.map(PolicyName::getCommissionRate).orElse(22.5);
    }
}
