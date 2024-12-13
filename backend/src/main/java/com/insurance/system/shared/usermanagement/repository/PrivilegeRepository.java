package com.insurance.system.shared.usermanagement.repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.insurance.system.shared.usermanagement.domain.Privilege;
import com.insurance.system.shared.usermanagement.domain.UserType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
  Privilege findByName(String paramString);
  
  List<Privilege> findAllByScope(UserType paramUserType);
  
  List<Privilege> findAllByScopeAndIdIn(UserType paramUserType, Set<Long> paramSet);
  
  Page<Privilege> findAllByScope(UserType paramUserType, Pageable paramPageable);
  
  @Transactional
  @Modifying
  @Query(value = "SELECT sc.id , sc.name FROM users_privileges c INNER JOIN privilege sc ON  c.privilege_id = sc.id   WHERE sc.id NOT IN (SELECT p.privilege_id from users_privileges p where p.user_id =:userid ) AND sc.scope= :scope ", nativeQuery = true)
  List<Map<Long, String>> getUnassignedUserPrivileges(Long userid, UserType scope);
  
  @Transactional
  @Modifying
  @Query(value = "SELECT id , name FROM privilege c INNER JOIN users_privileges sc ON c.id = sc.privilege_id   WHERE user_id= :userid AND scope= :scope ", nativeQuery = true)
  List<Map<Long, String>> getUserPrivileges(Long userid, UserType scope);
  
  @Transactional
  @Modifying
  @Query(value = "SELECT sc.id , sc.name FROM role_privileges c INNER JOIN privilege sc ON  c.role_privilege_id = sc.id   WHERE sc.id NOT IN (SELECT p.role_privilege_id from role_privileges p where p.role_id =:roleid ) AND sc.scope= :scope ", nativeQuery = true)
  List<Map<Long, String>> getUnassignedRolePrivileges(Long roleid, UserType scope);
}
