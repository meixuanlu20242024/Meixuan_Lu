package com.insurance.system.shared.domain.service;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;


import com.insurance.system.shared.domain.filters.PolicyRatesFilterSpecification;
import com.insurance.system.shared.domain.models.*;
import com.insurance.system.shared.domain.payload.*;
import com.insurance.system.shared.domain.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.insurance.system.shared.domain.repository.GovernmentLevyRepository;

@Service
public class PolicyServiceImpl implements PolicyService {
  //private final MotorPolicyService motorPolicyService;
  private final InsurerRepository insurerRepository ;
  private final InvoiceRepository invoiceRepository ;
  private static final Logger log = LoggerFactory.getLogger(PolicyServiceImpl.class);
  
  private final StampDutyRepository stampDutyRepository;
  
  private final GovernmentLevyRepository governmentLevyRepository;
  private final PolicyNameRepository policyNameRepository;


  private LocalDate currentDate = LocalDate.now();


  @Autowired
  public PolicyServiceImpl(
//          @Lazy MotorPolicyService motorPolicyService,
          InsurerRepository insurerRepository, InvoiceRepository invoiceRepository, StampDutyRepository stampDutyRepository, GovernmentLevyRepository governmentLevyRepository, PolicyNameRepository policyNameRepository) {
//    this.motorPolicyService = motorPolicyService;
    this.insurerRepository = insurerRepository;
    this.invoiceRepository = invoiceRepository;

    this.stampDutyRepository = stampDutyRepository;
    this.governmentLevyRepository = governmentLevyRepository;
    this.policyNameRepository = policyNameRepository;
  }

  @Override
  public ResponseEntity<?> policiesReportData(PolicyReportFilterRequest policyReportFilterRequest) {


    return null;


  }

  public Double calculatePremium(Date dateFrom, Date dateTo, Double sumInsured, Double rate) {
    long dateBeforeInMs = dateFrom.getTime();
    long dateAfterInMs = dateTo.getTime();
    long timeDiff = Math.abs(dateAfterInMs - dateBeforeInMs);
    long policyPeriod = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
    Double premium = Double.valueOf(sumInsured.doubleValue() * rate.doubleValue() / 100.0D * policyPeriod / this.currentDate.lengthOfYear());
    log.info("__________________current length of year _________________________{}", Integer.valueOf(this.currentDate.lengthOfYear()));
    log.info("the days to test between is {}", Long.valueOf(policyPeriod));
    return premium;
  }
  
  public Double calculateStampDuty(Double premium) {
    Double stampduty = Double.valueOf(((StampDuty)this.stampDutyRepository.findAll().get(0)).getStampDuty().doubleValue() / 100.0D);
    return Double.valueOf(premium.doubleValue() * stampduty.doubleValue());
  }
  
  public Double calculateGvtLevy(Double premium, Double stampDuty) {
    Double governmentLevy = Double.valueOf(((GovermentLevy)this.governmentLevyRepository.findAll().get(0)).getGovernmentLevy().doubleValue() / 100.0D);
    return Double.valueOf((premium.doubleValue() - stampDuty.doubleValue()) * governmentLevy.doubleValue());
  }
  
  public String isPolicyPeriodValid(Date dateFrom, Date dateTo, Double sumInsured, Double rate) {
    long dateBeforeInMs = dateFrom.getTime();
    long dateAfterInMs = dateTo.getTime();
    long timeDiff = Math.abs(dateAfterInMs - dateBeforeInMs);
    long policyPeriod = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
    Double annualpremium = Double.valueOf(sumInsured.doubleValue() * rate.doubleValue() / 100.0D);
    Double premium = Double.valueOf(sumInsured.doubleValue() * rate.doubleValue() / 100.0D * policyPeriod / this.currentDate.lengthOfYear());
    log.info("the premium  is {}", premium);
    log.info("the anualpremium  is {}", annualpremium);
    if (premium.doubleValue() > annualpremium.doubleValue())
      return "Premium" + premium + " cannot be greater than sum insured " + sumInsured + " ,  adjust the cabinet period "; 
    return null;
  }
  @Override
  public ResponseEntity renewalCheck() {
    return null;
  }
  @Override
  public ResponseEntity<?> setPolicyRates(List<PolicyNameDTO> policyNameDTOList) {
//    List<String> errors = new ArrayList<>();
//    for (PolicyNameDTO policy : policyNameDTOList) {
//
//
//
//        Optional<PolicyName> policyName = policyNameRepository.findByPolicyName(policy.getPolicyName());
//
//
//        if (!policyName.isPresent()) {
//           errors.add("Policy "+policy.getPolicyName()+" not found");
//        }
//        else {
//            policyName.get().setCommissionRate(policy.getCommissionRate());
//          policyNameRepository.save(policyName.get());
//        }
//
//    }
    return ResponseEntity.ok().body("Policy Rates Updated Successfully");
  }

  @Override
  public Page<PolicyName> AllPoliciesRatesPaginated(PageableObj pageableObj) {

    PolicyFilterRequest policyFilterRequest = new PolicyFilterRequest();
    policyFilterRequest.setKeywords(pageableObj.getGlobalFilter());
    Pageable pageable = PageRequest.of(pageableObj.getPage(), pageableObj.getSize());

    Specification<PolicyName> specification = new PolicyRatesFilterSpecification().getFilteredPoliciesRates(policyFilterRequest);
    return this.policyNameRepository.findAll(specification, pageable );

  }



  @Override
  public ResponseEntity<?> listInvoices(PageableObj pageableObj, Long id, String policyName) {
    Pageable pageable = PageRequest.of(pageableObj.getPage(), pageableObj.getSize());
    Page<Invoice> invoices =  this.invoiceRepository.findAllByPolicyIdAndPolicyType(id, policyName ,  pageable );
    return ResponseEntity.ok().body(invoices) ;
  }

  @Override
  public ResponseEntity<?> adjustCommission(PolicyNameDTO policyNameDTo) {

   return null ;

  }

  @Override
    public ResponseEntity<?> listInvoicesByInsurer(PageableObj pageableObj, Long insurerId) {


    return ResponseEntity.ok().body(null);
    }


  public static interface EmailService {
    void sendEmail(String to, String subject, String body);
    boolean sendRenewalReminder(RenewalReminderBodyDTO policy , long days, PoliciesEnum policyName) ;
  }

  @Service
  @Slf4j
  public static class EmailServiceImpl implements EmailService {
    final Environment env;

    public EmailServiceImpl(Environment env) {
      this.env = env;
    }

    @Override
    public void sendEmail(String to, String subject, String body) {



    }
    @Override
    public boolean sendRenewalReminder(RenewalReminderBodyDTO policy , long days, PoliciesEnum policyName){
       return false ;
    }
  }


}
