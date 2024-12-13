package com.insurance.system.shared.usermanagement.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.insurance.system.shared.usermanagement.domain.Role;
import com.insurance.system.shared.usermanagement.domain.UserType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Role findByName(String paramString);
  
  Optional<Role> findByIdAndScope(Long paramLong, UserType paramUserType);
  
  Role findByNameAndScope(String paramString, UserType paramUserType);
  
  List<Role> findByScope(UserType paramUserType);
  
  Page<Role> findAllByScope(UserType paramUserType, Pageable paramPageable);
  
  List<Role> findAllByScopeAndIdIn(UserType paramUserType, Set<Long> paramSet);
  
  @Transactional
  @Modifying
  @Query(value = "delete from role_privileges where role_privileges.role_privilege_id in :role_privilege_id and role_privileges.role_id= :role_id", nativeQuery = true)
  void deleteRolePriviledges(Set<Long> role_privilege_id, Long role_id);
  
  @Transactional
  @Modifying
  @Query(value = "SELECT id , name FROM privilege c INNER JOIN role_privileges sc ON sc.role_privilege_id = c.id WHERE role_id= :roleid AND scope= :scope ", nativeQuery = true)
  List<Map<Long, String>> getRolePrivileges(Long roleid, UserType scope);
}
