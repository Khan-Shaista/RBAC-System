package com.RBACSystem.RBAC_System.controller;

import com.RBACSystem.RBAC_System.model.Users;
import com.RBACSystem.RBAC_System.service.adminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
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
  //  @PreAuthorize("hasAuthority('MANAGER_READ')")
    public ResponseEntity<List<Users>> getAllManagers() {
        return ResponseEntity.ok(service.getAllManagers());
    }

    @PostMapping("/manager")
  //  @PreAuthorize("hasAuthority('MANAGER_WRITE')")
    public ResponseEntity<?> addManager(@RequestBody Users user) {
        try {
            Users savedUser = service.addManager(user);


            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/manager/{id}")
  //  @PreAuthorize("hasAuthority('MANAGER_UPDATE')")
    public ResponseEntity<String> updateManager(@PathVariable Long id, @RequestBody Users user) {
        try {
            Users updated = service.updateManager(id, user);
            if (updated != null) {
                return ResponseEntity.ok("updated");
            }
            return ResponseEntity.badRequest().body("Failed to update");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/manager/{id}")
//    @PreAuthorize("hasAuthority('MANAGER_DELETE')")
    public ResponseEntity<String> deleteManager(@PathVariable Long id) {
        Users user = service.getManager(id);
        if (user != null) {
            service.deleteManager(id);
            return ResponseEntity.ok("Deleted");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
    }






}
