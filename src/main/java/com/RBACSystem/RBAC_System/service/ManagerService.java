package com.RBACSystem.RBAC_System.service;

import com.RBACSystem.RBAC_System.model.Users;
import com.RBACSystem.RBAC_System.repository.UsersDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UsersDetailRepo repo;

    public List<Users> getAllCashier() {
        return repo.findAll();
    }

    public Users addcashier(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public Users updatecashier(Long id, Users user) {
        repo.save(user);
        return user;
    }

    public void deletecashier(Long id) {
        repo.deleteById(id);
    }

    public Users getcashier(Long id) {
        return repo.findById(id).orElse(new Users());
    }
}
