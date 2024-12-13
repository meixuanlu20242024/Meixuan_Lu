package com.insurance.system.shared.domain.filters;

import com.insurance.system.shared.domain.payload.PolicyFilterRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class PolicyRatesFilterSpecification<T>{
  public Specification<T> getFilteredPoliciesRates(PolicyFilterRequest request) {
    return (root, query, criteriaBuilder) -> {
        List<Predicate> predicates = new ArrayList<>();
        if (request.getKeywords() != null)
//            add predicates for name or email

            predicates.add(criteriaBuilder.like(criteriaBuilder.lower((Expression)root.get("policyName")), "%" + request.getKeywords().toLowerCase() + "%"));
        query.orderBy(new Order[] { criteriaBuilder.desc((Expression)root.get("id")) });
        return criteriaBuilder.and(predicates.<Predicate>toArray(new Predicate[0]));
      };
  }


}
