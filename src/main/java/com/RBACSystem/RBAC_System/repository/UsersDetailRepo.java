package com.RBACSystem.RBAC_System.repository;

import com.RBACSystem.RBAC_System.model.Role;
import com.RBACSystem.RBAC_System.model.Users2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersDetailRepo extends JpaRepository<Users2, Long> {
    Optional<Users2> findByUsername(String username);


    List<Users2> findByRole(Role role);

    List<Users2> findByRoleAndCreatedBy(Role role, Users2 createdBy);

}
