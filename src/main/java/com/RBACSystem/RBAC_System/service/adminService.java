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
public class adminService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UsersDetailRepo repo;


    public List<Users2> getAllManagers() {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        Users2 creator = repo.findByUsername(currentUsername)
                .orElseThrow(() -> new UsernameNotFoundException("Creator not found"));

        return repo.findByRoleAndCreatedBy(Role.ROLE_MANAGER, creator);
    }





    public Users2 getManager(Long id) {

        return repo.findById(id).orElse(new Users2());

    }

    public Users2 addManager(Users2 user) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        Users2 creator = repo.findByUsername(currentUsername)
                .orElseThrow(() -> new UsernameNotFoundException("Creator not found"));

        user.setCreatedBy(creator);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repo.save(user);

    }



//    public Users updateManager(Long id, Users user) {
//        repo.save(user);
//        return user;
//    }


//    Checks if the manager (user) exists before updating.
    public Users2 updateManager(Long id, Users2 updatedUser) {
        Users2 existingUser = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Manager with ID " + id + " not found"));

        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setPassword(updatedUser.getPassword()); // ideally encode password if changed
        existingUser.setRole(updatedUser.getRole());

        return repo.save(existingUser);
    }



    public void deleteManager(Long id) {
        repo.deleteById(id);
    }


}