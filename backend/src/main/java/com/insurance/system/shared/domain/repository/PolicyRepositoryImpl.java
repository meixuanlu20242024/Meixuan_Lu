package com.insurance.system.shared.domain.repository;

import com.insurance.system.motorpolicy.domain.models.MotorPolicy;
import com.insurance.system.motorpolicy.domain.models.MotorPolicyClaim;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

@Repository
public class PolicyRepositoryImpl {
  @Autowired
  private EntityManager entityManager;
  public Double getTotalSumInsured(Specification<MotorPolicy> specification) {
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Double> query = builder.createQuery(Double.class);
    Root<MotorPolicy> root = query.from(MotorPolicy.class);

    Predicate predicate = specification.toPredicate(root, query, builder);
    query.where(predicate);
    Expression<Double> sumInsuredExpression = builder.sum(root.get("sumInsured"));
    query.select(builder.coalesce(sumInsuredExpression, 0.0));

    return entityManager.createQuery(query).getSingleResult();
  }

  public Double getTotalPremium(Specification<MotorPolicy> specification) {
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Double> query = builder.createQuery(Double.class);
    Root<MotorPolicy> root = query.from(MotorPolicy.class);

    Predicate predicate = specification.toPredicate(root, query, builder);
    query.where(predicate);
    Expression<Double> sumInsuredExpression = builder.sum(root.get("premium"));
    query.select(builder.coalesce(sumInsuredExpression, 0.0));

    return entityManager.createQuery(query).getSingleResult();
  }


  public Double getTotalClaim(Specification<MotorPolicyClaim> specification) {
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Double> query = builder.createQuery(Double.class);
    Root<MotorPolicyClaim> root = query.from(MotorPolicyClaim.class);

    Predicate predicate = specification.toPredicate(root, query, builder);
    query.where(predicate);
    Expression<Double> sumInsuredExpression = builder.sum(root.get("lossAmount"));
    query.select(builder.coalesce(sumInsuredExpression, 0.0));

    return entityManager.createQuery(query).getSingleResult();
  }


}



