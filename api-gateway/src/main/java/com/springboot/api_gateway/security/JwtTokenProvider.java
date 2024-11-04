//package com.springboot.api_gateway.security;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.security.Key;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@Component
//public class JwtTokenProvider {
//    @Value("${app.jwt-secret}")
//    private String jwtSecret;
//
//    @Value("${app.jwt-expiration-milliseconds}")
//    private long jwtExpirationDate;
//
//    // Generate JWT token
//    public String generateToken(Authentication authentication){
//        String username = authentication.getName();
//
//        Date currentDate = new Date();
//
//        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);
//
//        List<String> roles = authentication.getAuthorities().stream()
//                .map(role -> role.getAuthority())
//                .collect(Collectors.toList());
//
//        String token = Jwts.builder()
//                .setSubject(username)
//                .claim("roles", roles)
//                .setIssuedAt(new Date())
//                .setExpiration(expireDate)
//                .signWith(key())
//                .compact();
//
//        return token;
//    }
//
//    private Key key(){
//        return Keys.hmacShaKeyFor(
//                Decoders.BASE64.decode(jwtSecret)
//        );
//    }
//
//    // Get username from JWT token
//    public String getUsername(String token){
//        Claims claims = Jwts.parserBuilder()
//                .setSigningKey(key())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//
//        String username = claims.getSubject();
//
//        return username;
//    }
//
//    // Validate JWT Token
//    public boolean validateToken(String token){
//        Jwts.parserBuilder()
//                .setSigningKey(key())
//                .build()
//                .parse(token);
//        return true;
//    }
//
//    public boolean validateToken(String token, UserDetails userDetails) {
//        final String username = getUsername(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
//
//    private boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//
//
//    public String[] getRolesFromToken(String token) {
//        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
//        System.out.println(claims);
//        for (Map.Entry<String, Object> entry : claims.entrySet()) {
//            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//        }
//        // Retrieve roles as a List of Strings
//        List<String> rolesList = claims.get("roles", List.class);
//
//        // Check for null and convert to String array
//        if (rolesList != null) {
//            String[] rolesArray = rolesList.toArray(new String[0]);
//            System.out.println("Roles: " + Arrays.toString(rolesArray));
//            return rolesArray;
//        } else {
//            // Handle the case where roles are not present in the token
//            return new String[]{};
//        }
//    }
//
//    private Date extractExpiration(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(key())
//                .build()
//                .parseClaimsJws(token)
//                .getBody()
//                .getExpiration();
//    }
//}
//
