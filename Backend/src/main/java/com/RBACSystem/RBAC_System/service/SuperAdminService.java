//package com.RBACSystem.RBAC_System.service;
//
//import com.RBACSystem.RBAC_System.model.Role;
//import com.RBACSystem.RBAC_System.model.Users2;
//import com.RBACSystem.RBAC_System.repository.UsersDetailRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class SuperAdminService {
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    UsersDetailRepo repo;
//
//    public List<Users2> getAllAdmin() {
//        return repo.findByRole(Role.ROLE_ADMIN);
//    }
//
//    public Users2 addAdmin(Users2 user) {
//        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
//
//        Users2 creator = repo.findByUsername(currentUsername)
//                .orElseThrow(() -> new UsernameNotFoundException("Creator not found"));
//
//        user.setCreatedBy(creator);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        return repo.save(user);
//    }
//
//    public Users2 updateAdmin(Long id,Users2 updatedUser) {
//        Users2 existingUser = repo.findById(id)
//                .orElseThrow(() -> new RuntimeException("Admin with ID " + id + " not found"));
//
//        existingUser.setUsername(updatedUser.getUsername());
//        existingUser.setPassword(updatedUser.getPassword()); // ideally encode password if changed
//        existingUser.setRole(updatedUser.getRole());
//
//        return repo.save(existingUser);
//    }
//
//    public Users2 getAdmin(Long id) {
//        return repo.findById(id).orElse(new Users2());
//    }
//
//    public void deleteAdmin(Long id) {
//        repo.deleteById(id);
//    }
//}
