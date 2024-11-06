package com.springboot.payment_service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class JwtTokenProvider {
    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private long jwtExpirationDate;

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.get("userId", Long.class);  // Extract userId
    }

    public String[] getRolesFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        System.out.println(claims);
        for (Map.Entry<String, Object> entry : claims.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
        // Retrieve roles as a List of Strings
        List<String> rolesList = claims.get("roles", List.class);

        // Check for null and convert to String array
        if (rolesList != null) {
            String[] rolesArray = rolesList.toArray(new String[0]);
            System.out.println("Roles: " + Arrays.toString(rolesArray));
            return rolesArray;
        } else {
            // Handle the case where roles are not present in the token
            return new String[]{};
        }
    }

    private boolean isTokenExpired(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        Date expirationDate = claims.getExpiration();
        return expirationDate.before(new Date());
    }
}
