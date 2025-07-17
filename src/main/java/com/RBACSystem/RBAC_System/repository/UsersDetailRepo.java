package com.RBACSystem.RBAC_System.repository;

import com.RBACSystem.RBAC_System.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersDetailRepo extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
}
