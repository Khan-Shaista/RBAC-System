package com.RBACSystem.RBAC_System.controller;

import com.RBACSystem.RBAC_System.model.Users2;
import com.RBACSystem.RBAC_System.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin

@RestController
@RequestMapping("/Manager")
//@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")

public class ManagerController {

        @Autowired
        private ManagerService service;

        @Autowired
        AuthenticationManager authenticationManager;



        @GetMapping("/cashier")
        public ResponseEntity<List<Users2>> getAllCashier() {

            return ResponseEntity.ok(service.getAllCashier());
        }

    @GetMapping("/cashier/{id}")
    public ResponseEntity<Users2> getcashier(@PathVariable Long id) {
        try {
            Users2 cashier = service.getcashier(id); // Make sure service method exists
            if (cashier != null) {
                return ResponseEntity.ok(cashier);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

        @PostMapping("/cashier")
        public ResponseEntity<?> addcashier(@RequestBody Users2 user) {
            try {
                Users2 savedUser = service.addcashier(user);


                return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        @PutMapping("/cashier/{id}")
        public ResponseEntity<String> updatecashier(@PathVariable Long id, @RequestBody Users2 user) {
            try {
                Users2 updated = service.updatecashier(id, user);
                if (updated != null) {
                    return ResponseEntity.ok("updated");
                }
                return ResponseEntity.badRequest().body("Failed to update");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
            }
        }

        @DeleteMapping("/cashier/{id}")
        public ResponseEntity<String> deletecashier(@PathVariable Long id) {
            Users2 user = service.getcashier(id);
            if (user != null) {
                service.deletecashier(id);
                return ResponseEntity.ok("Deleted");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        }
}
