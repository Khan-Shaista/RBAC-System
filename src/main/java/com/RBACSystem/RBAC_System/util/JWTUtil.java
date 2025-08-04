package com.RBACSystem.RBAC_System.util;

import com.RBACSystem.RBAC_System.model.Users2;
import com.RBACSystem.RBAC_System.repository.UsersDetailRepo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;

@Component
public class JWTUtil {

    @Autowired
    private UsersDetailRepo repo;

    private static final String SECRET = "my-super-secret-key-that-is-long-enough-1234567890!@#";
    private static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET.getBytes());
    private static final long EXPIRATION_TIME = 1000 * 60 * 60;

    public String generateToken(String username) {
        Users2 user = repo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // ✅ convert enum to string like "ROLE_ADMIN"
        String roleName = user.getRole().name();

        return Jwts.builder()
                .setSubject(username)
                .claim("authorities", List.of(roleName)) // ✅ now a clean string array
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String username, UserDetails userDetails, String token) {
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }
}
