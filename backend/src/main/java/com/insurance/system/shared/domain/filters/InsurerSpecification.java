package com.insurance.system.shared.domain.filters;

import com.insurance.system.shared.domain.payload.InsurerFilterRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import com.insurance.system.shared.domain.models.Insurer;

import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class InsurerSpecification {
  public Specification<Insurer> getFilteredInsurers(InsurerFilterRequest request) {
    return (root, query, criteriaBuilder) -> {
        List<Predicate> predicates = new ArrayList<>();
        if (request.getKeywords() != null)
          predicates.add(criteriaBuilder.like(criteriaBuilder.lower((Expression)root.get("name")), "%" + request.getKeywords().toLowerCase() + "%"));
        query.orderBy(new Order[] { criteriaBuilder.desc((Expression)root.get("id")) });
        return criteriaBuilder.and(predicates.<Predicate>toArray(new Predicate[0]));
      };
  }
}
