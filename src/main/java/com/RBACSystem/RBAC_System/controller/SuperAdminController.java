//package com.RBACSystem.RBAC_System.controller;
//
//
//import com.RBACSystem.RBAC_System.model.Users2;
//import com.RBACSystem.RBAC_System.service.SuperAdminService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@CrossOrigin
//@RestController
//@RequestMapping("/S-admin")
//public class SuperAdminController {
//
//    @Autowired
//    private SuperAdminService service;
//
//    @Autowired
//    AuthenticationManager authenticationManager;
//
//    @GetMapping("/admin")
//    public ResponseEntity<List<Users2>> getAllAdmin(){
//        return ResponseEntity.ok(service.getAllAdmin());
//    }
//
//    @PostMapping("/admin")
//    public ResponseEntity<?> addAdmin(@RequestBody Users2 user) {
//        try {
//            Users2 savedUser = service.addAdmin(user);
//
//
//            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//
//    @PutMapping("/admin/{id}")
//    public ResponseEntity<String> updateAdmin(@PathVariable Long id, @RequestBody Users2 user) {
//        try {
//            Users2 updated = service.updateAdmin(id, user);
//            if (updated != null) {
//                return ResponseEntity.ok("updated");
//            }
//            return ResponseEntity.badRequest().body("Failed to update");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
//        }
//    }
//
//    @DeleteMapping("/admin/{id}")
//    public ResponseEntity<String> deleteAdmin(@PathVariable Long id) {
//        Users2 user = service.getAdmin(id);
//        if (user != null) {
//            service.deleteAdmin(id);
//            return ResponseEntity.ok("Deleted");
//        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
//    }
//
//}
