package com.insurance.system.shared.domain.repository;

import com.insurance.system.shared.usermanagement.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    Boolean existsByEmail(String mail);
}