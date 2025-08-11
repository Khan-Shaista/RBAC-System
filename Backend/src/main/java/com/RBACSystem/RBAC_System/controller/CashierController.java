package com.RBACSystem.RBAC_System.controller;


import com.RBACSystem.RBAC_System.model.Product;
import com.RBACSystem.RBAC_System.model.Users2;
import com.RBACSystem.RBAC_System.service.CashierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/Cashier")

public class CashierController {

    @Autowired
    CashierService service;

    @Autowired
    AuthenticationManager authenticationManager;


    @GetMapping("/product")
    public ResponseEntity<List<Product>> getAllProduct(){
         return ResponseEntity.ok(service.getAllProduct());
    }



    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestBody Product prod) {
        try {
            Product savedProduct = service.addProduct(prod);


            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        try {
            Product prod = service.getProduct(id);
            if (prod != null) {
                return ResponseEntity.ok(prod);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product prod) {
        try {
            Product updated = service.updateProduct(id, prod);
            if (updated != null) {
                return ResponseEntity.ok("updated");
            }
            return ResponseEntity.badRequest().body("Failed to update");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        Product prod = service.getProduct(id);
        if (prod != null) {
            service.deleteProduct(id);
            return ResponseEntity.ok("Deleted");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
    }



}
