package com.springboot.userService.utils;

import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Base64;

public class JwtSecretKeyGenerator {

    public static void main(String[] args) {
        // Generate a secret key for HS256 algorithm (use HS384/HS512 as needed)
        Key key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);

        // Convert the secret key to Base64-encoded string
        String base64SecretKey = Base64.getEncoder().encodeToString(key.getEncoded());

        // Print the Base64-encoded secret key
        System.out.println("Base64 Encoded Secret Key: " + base64SecretKey);
    }
}
