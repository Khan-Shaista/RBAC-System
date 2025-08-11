package com.RBACSystem.RBAC_System.repository;

import com.RBACSystem.RBAC_System.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

}
