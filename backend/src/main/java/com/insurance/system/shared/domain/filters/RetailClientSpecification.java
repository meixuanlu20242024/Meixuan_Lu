package com.insurance.system.shared.domain.filters;

import com.insurance.system.shared.domain.payload.RetailClientFilterRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import com.insurance.system.shared.domain.models.RetailClient;

import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class RetailClientSpecification {
  public Specification<RetailClient> getFilteredClients(RetailClientFilterRequest request) {
    return (root, query, criteriaBuilder) -> {
        List<Predicate> predicates = new ArrayList<>();
        if (request.getClientType() != null)
          predicates.add(criteriaBuilder.equal((Expression)root.get("clientType"), request.getClientType()));
        if (request.getKeywords() != null)
//            add predicates for name or email



          predicates.add(criteriaBuilder.like(criteriaBuilder.lower((Expression)root.get("name")), "%" + request.getKeywords().toLowerCase() + "%"));
//          predicates.add(criteriaBuilder.like(criteriaBuilder.lower((Expression)root.get("identityNumber")), "%" + request.getKeywords().toLowerCase() + "%"));
//          predicates.add(criteriaBuilder.like(criteriaBuilder.lower((Expression)root.get("email")), "%" + request.getKeywords().toLowerCase() + "%"));
        query.orderBy(new Order[] { criteriaBuilder.desc((Expression)root.get("id")) });
        return criteriaBuilder.and(predicates.<Predicate>toArray(new Predicate[0]));
      };
  }
}
