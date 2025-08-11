package com.RBACSystem.RBAC_System.service;

import com.RBACSystem.RBAC_System.model.Role;
import com.RBACSystem.RBAC_System.model.Users2;
import com.RBACSystem.RBAC_System.repository.UsersDetailRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminUserInitializer {

    // Remove it once the user registration API is implemented.
    @Bean
    public CommandLineRunner loadAdmin(UsersDetailRepo userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                Users2 admin = new Users2();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole(Role.ROLE_ADMIN);
                userRepository.save(admin);
                System.out.println("Admin user created");
            } else System.out.println(" Admin user already exists");
        };
    }
}