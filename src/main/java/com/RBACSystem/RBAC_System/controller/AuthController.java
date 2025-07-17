package com.RBACSystem.RBAC_System.controller;

import com.RBACSystem.RBAC_System.model.AuthRequest;
import com.RBACSystem.RBAC_System.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTUtil jwtUtil;

    @PostMapping({"/authenticate"})
    public String generateToken(@RequestBody AuthRequest authRequest){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword())
            );
            return jwtUtil.generateToken(authRequest.getUsername());

        }catch(Exception e){
            throw e;
        }
    }
}
