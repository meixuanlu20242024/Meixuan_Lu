package com.insurance.system.shared.domain.filters;

import com.insurance.system.shared.domain.payload.PolicyFilterRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import com.insurance.system.shared.domain.models.RetailClient;

import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class PolicyFilterSpecification<T>{
  public Specification<T> getFilteredPolicies(PolicyFilterRequest request) {
    return (root, query, criteriaBuilder) -> {
        Join<T, RetailClient> policyJoin = root.join("insured");
        List<Predicate> predicates = new ArrayList<>();
//        if (request.getInsured() != null)
//            predicates.add(criteriaBuilder.equal(policyJoin.get("name"), request.getInsured()));
//        if(request.getCurrency() != null)
//          predicates.add(criteriaBuilder.equal((Expression)root.get("currency"), request.currency));
        if (request.getKeywords() != null)
//          predicates.add(criteriaBuilder.like(criteriaBuilder.lower((Expression)root.get("policyNo")), "%" + request.getKeywords().toLowerCase() + "%"));
          predicates.add(criteriaBuilder.like(criteriaBuilder.lower((Expression)policyJoin.get("name")), "%" + request.getKeywords().toLowerCase() + "%"));
        query.orderBy(new Order[] { criteriaBuilder.desc((Expression)root.get("id")) });
        return criteriaBuilder.and(predicates.<Predicate>toArray(new Predicate[0]));
      };
  }


}
