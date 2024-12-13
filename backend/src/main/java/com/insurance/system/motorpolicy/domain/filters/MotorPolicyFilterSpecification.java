package com.insurance.system.motorpolicy.domain.filters;

import com.insurance.system.motorpolicy.domain.models.MotorPolicy;
import com.insurance.system.motorpolicy.domain.models.MotorPolicyClaim;
import com.insurance.system.motorpolicy.domain.models.MotorPolicyEndorsement;
import com.insurance.system.shared.domain.models.Currency;
import com.insurance.system.shared.domain.payload.PolicyReportFilterRequest;
import com.insurance.system.shared.utils.Utils;
import jakarta.persistence.criteria.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class MotorPolicyFilterSpecification{


  public Specification<MotorPolicy> filterMotorPolicy(PolicyReportFilterRequest policyReportFilterRequest){
      return  (root, query, criteriaBuilder) -> {
          List<Predicate> predicates = new ArrayList<>();
          if (policyReportFilterRequest.getInsured() != null)
              predicates.add(criteriaBuilder.equal((Expression)root.get("insured"), policyReportFilterRequest.getInsured()));
          if(root.getModel().getAttribute("currency") != null && policyReportFilterRequest.getCurrency() != null){
              Path<Currency> currencyPath = root.get("currency");
              predicates.add(criteriaBuilder.equal(currencyPath, policyReportFilterRequest.getCurrency()));
          }

          if (policyReportFilterRequest.getYear() != null){
              Expression<Integer> monthExpression = criteriaBuilder.function("month", Integer.class, root.get("periodFrom"));
              Expression<Integer> yearExpression = criteriaBuilder.function("year", Integer.class, root.get("periodFrom"));


              if(policyReportFilterRequest.getPeriod() != 0 && policyReportFilterRequest.getPeriod() != 1 ){
                  predicates.add(Utils.createQuarterFilter(criteriaBuilder , root , Integer.parseInt(policyReportFilterRequest.getYear()) , policyReportFilterRequest.getPeriod() , "periodFrom", null));

              }
              else{
                  Predicate yearPredicate = criteriaBuilder.equal(yearExpression, policyReportFilterRequest.getYear());
                  predicates.add(criteriaBuilder.equal(yearExpression, policyReportFilterRequest.getYear()));
                  if (policyReportFilterRequest.getMonth() != 0){
                      Month selectedMonth  = Utils.getMonthFromNumericValue(policyReportFilterRequest.getMonth());
                      Predicate monthPredicate = criteriaBuilder.equal(monthExpression, selectedMonth.getValue());
                      Predicate combinedPredicate = criteriaBuilder.and(yearPredicate, monthPredicate);
                      predicates.add(combinedPredicate);
                  }
                  else{
                      predicates.add(yearPredicate);
                  }


              }





          }
              if (root.getModel().getAttribute("id") != null) {
                  query.orderBy(new Order[]{criteriaBuilder.desc(root.get("id"))});
              } else if (root.getModel().getAttribute("Id") != null) {
                  query.orderBy(new Order[]{criteriaBuilder.desc(root.get("Id"))});
              }

//          query.orderBy(new Order[] { criteriaBuilder.desc((Expression)root.get("Id")) });
          return criteriaBuilder.and(predicates.<Predicate>toArray(new Predicate[0]));
      };
  }

  public Specification<MotorPolicyClaim> filterMotorPolicyClaim(PolicyReportFilterRequest policyReportFilterRequest)
  {
      return  (root, query, criteriaBuilder) -> {
          List<Predicate> predicates = new ArrayList<>();
          Join<MotorPolicyClaim, MotorPolicy> policyJoin = root.join("policy");

          if (policyReportFilterRequest.getInsured() != null)
              predicates.add(criteriaBuilder.equal(policyJoin.get("insured"), policyReportFilterRequest.getInsured()));
          if(policyReportFilterRequest.getCurrency() != null){
              Path<Currency> currencyPath = policyJoin.get("currency");
              predicates.add(criteriaBuilder.equal(currencyPath, policyReportFilterRequest.getCurrency()));
          }

          if (policyReportFilterRequest.getYear() != null){
              Expression<Integer> monthExpression = criteriaBuilder.function("month", Integer.class, policyJoin.get("periodFrom"));
              Expression<Integer> yearExpression = criteriaBuilder.function("year", Integer.class, policyJoin.get("periodFrom"));


              if(policyReportFilterRequest.getPeriod() != 0 && policyReportFilterRequest.getPeriod() != 1 ){
                  predicates.add(Utils.createQuarterFilter(criteriaBuilder , root , Integer.parseInt(policyReportFilterRequest.getYear()) , policyReportFilterRequest.getPeriod() , "periodFrom" , policyJoin));

              }
              else{
                  Predicate yearPredicate = criteriaBuilder.equal(yearExpression, policyReportFilterRequest.getYear());
                  predicates.add(criteriaBuilder.equal(yearExpression, policyReportFilterRequest.getYear()));
                  if (policyReportFilterRequest.getMonth() != 0){
                      Month selectedMonth  = Utils.getMonthFromNumericValue(policyReportFilterRequest.getMonth());
                      Predicate monthPredicate = criteriaBuilder.equal(monthExpression, selectedMonth.getValue());
                      Predicate combinedPredicate = criteriaBuilder.and(yearPredicate, monthPredicate);
                      predicates.add(combinedPredicate);
                  }
                  else{
                      predicates.add(yearPredicate);
                  }


              }
          }
          query.orderBy(new Order[]{criteriaBuilder.desc(root.get("Id"))});
          return criteriaBuilder.and(predicates.<Predicate>toArray(new Predicate[0]));
      };
  }


    public Specification<MotorPolicyEndorsement> filterMotorPolicyEndorsement(PolicyReportFilterRequest policyReportFilterRequest) {
        return  (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Join<MotorPolicyClaim, MotorPolicy> policyJoin = root.join("policy");

            if (policyReportFilterRequest.getInsured() != null)
                predicates.add(criteriaBuilder.equal(policyJoin.get("insured"), policyReportFilterRequest.getInsured()));
            if(policyReportFilterRequest.getCurrency() != null){
                Path<Currency> currencyPath = policyJoin.get("currency");
                predicates.add(criteriaBuilder.equal(currencyPath, policyReportFilterRequest.getCurrency()));
            }

            if (policyReportFilterRequest.getYear() != null){
                Expression<Integer> monthExpression = criteriaBuilder.function("month", Integer.class, policyJoin.get("periodFrom"));
                Expression<Integer> yearExpression = criteriaBuilder.function("year", Integer.class, policyJoin.get("periodFrom"));


                if(policyReportFilterRequest.getPeriod() != 0 && policyReportFilterRequest.getPeriod() != 1 ){
                    predicates.add(Utils.createQuarterFilter(criteriaBuilder , root , Integer.parseInt(policyReportFilterRequest.getYear()) , policyReportFilterRequest.getPeriod() , "periodFrom" , policyJoin));

                }
                else{
                    Predicate yearPredicate = criteriaBuilder.equal(yearExpression, policyReportFilterRequest.getYear());
                    predicates.add(criteriaBuilder.equal(yearExpression, policyReportFilterRequest.getYear()));
                    if (policyReportFilterRequest.getMonth() != 0){
                        Month selectedMonth  = Utils.getMonthFromNumericValue(policyReportFilterRequest.getMonth());
                        Predicate monthPredicate = criteriaBuilder.equal(monthExpression, selectedMonth.getValue());
                        Predicate combinedPredicate = criteriaBuilder.and(yearPredicate, monthPredicate);
                        predicates.add(combinedPredicate);
                    }
                    else{
                        predicates.add(yearPredicate);
                    }


                }
            }
            query.orderBy(new Order[]{criteriaBuilder.desc(root.get("Id"))});
            return criteriaBuilder.and(predicates.<Predicate>toArray(new Predicate[0]));
        };
    }
}
