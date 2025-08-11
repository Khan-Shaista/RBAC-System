//package com.RBACSystem.RBAC_System.service;
//
//import com.RBACSystem.RBAC_System.repository.UsersDetailRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//@Component
//public  class CustomUserDetailService implements UserDetailsService {
//
//    @Autowired
//    UsersDetailRepo userDetailsRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userDetailsRepository.findByUsername(username)
//                .orElseThrow(()->new UsernameNotFoundException("Username not found."));
//    }
//}

package com.RBACSystem.RBAC_System.service;

import com.RBACSystem.RBAC_System.repository.UsersDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UsersDetailRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
}
