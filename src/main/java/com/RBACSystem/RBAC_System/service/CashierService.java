package com.RBACSystem.RBAC_System.service;

import com.RBACSystem.RBAC_System.model.Product;
import com.RBACSystem.RBAC_System.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CashierService {


    @Autowired
    ProductRepo repo;


    public List<Product> getAllProduct() {
        return repo.findAll();
    }

    public Product addProduct(Product prod) {
        return repo.save(prod);
    }

    public Product updateProduct(Long id, Product prod) {
        return repo.save(prod);
    }

    public Product getProduct(Long id) {
        return repo.findById(id).orElse(new Product());
    }

    public void deleteProduct(Long id) {
        repo.deleteById(id);
    }
}
