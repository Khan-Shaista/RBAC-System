package com.RBACSystem.RBAC_System.controller;

import com.RBACSystem.RBAC_System.model.Users2;
import com.RBACSystem.RBAC_System.service.adminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin")
public class adminController {

    @Autowired
    private adminService service;

    @Autowired
    AuthenticationManager authenticationManager;


    @GetMapping("/manager")


    public ResponseEntity<List<Users2>> getAllManagers() {

        return ResponseEntity.ok(service.getAllManagers());
    }


    @PostMapping("/manager")
    public ResponseEntity<?> addManager(@RequestBody Users2 user) {
        try {
            Users2 savedUser = service.addManager(user);


            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/manager/{id}")
    public ResponseEntity<String> updateManager(@PathVariable Long id, @RequestBody Users2 user) {
        try {
            Users2 updated = service.updateManager(id, user);
            if (updated != null) {
                return ResponseEntity.ok("updated");
            }
            return ResponseEntity.badRequest().body("Failed to update");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/manager/{id}")
    public ResponseEntity<String> deleteManager(@PathVariable Long id) {
        Users2 user = service.getManager(id);
        if (user != null) {
            service.deleteManager(id);
            return ResponseEntity.ok("Deleted");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
    }


}
