package com.insurance.system.motorpolicy.domain.repository;

import com.insurance.system.motorpolicy.domain.models.MotorPolicyVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotorPolicyVehicleRepository extends JpaRepository<MotorPolicyVehicle, Long> {

}



