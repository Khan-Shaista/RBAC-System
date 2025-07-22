package com.RBACSystem.RBAC_System.service;

import com.RBACSystem.RBAC_System.model.Users;
import com.RBACSystem.RBAC_System.repository.UsersDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class adminService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UsersDetailRepo repo;


    public List<Users> getAllManagers(){
        return repo.findAll();
    }



    public Users getManager(Long id) {

        return repo.findById(id).orElse(new Users());

    }

    public Users addManager(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repo.save(user);

    }



//    public Users updateManager(Long id, Users user) {
//        repo.save(user);
//        return user;
//    }


//    Checks if the manager (user) exists before updating.
    public Users updateManager(Long id, Users updatedUser) {
        Users existingUser = repo.findById(id)
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