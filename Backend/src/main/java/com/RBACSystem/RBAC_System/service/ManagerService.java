package com.RBACSystem.RBAC_System.service;

import com.RBACSystem.RBAC_System.model.Role;
import com.RBACSystem.RBAC_System.model.Users2;
import com.RBACSystem.RBAC_System.repository.UsersDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UsersDetailRepo repo;

    public List<Users2> getAllCashier() {

//        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
//        Users2 creator = repo.findByUsername(currentUsername)
//                .orElseThrow(() -> new UsernameNotFoundException("Creator not found"));
//
//        return repo.findByRoleAndCreatedBy(Role.ROLE_CASHIER, creator);


            return repo.findByRole(Role.ROLE_CASHIER);


    }


    public Users2 addcashier(Users2 user) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        Users2 creator = repo.findByUsername(currentUsername)
                .orElseThrow(() -> new UsernameNotFoundException("Creator not found"));

        user.setCreatedBy(creator);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public Users2 updatecashier(Long id, Users2 updatedUser) {
        Users2 existingUser = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cashier with ID " + id + " not found"));

        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setRole(updatedUser.getRole());
        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isBlank()) {
            String encoded = passwordEncoder.encode(updatedUser.getPassword());
            existingUser.setPassword(encoded);
        }
        return repo.save(existingUser);
    }

    public void deletecashier(Long id) {
        repo.deleteById(id);
    }

    public Users2 getcashier(Long id) {
        return repo.findById(id).orElse(new Users2());
    }
}
