package com.springboot.payment_service.security;

import com.springboot.payment_service.service.AuthService;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class JwtRequestInterceptor implements RequestInterceptor {

    private AuthService authService;

    @Override
    public void apply(RequestTemplate template) {
        String token = authService.getJwtToken(); // Retrieve the stored token
        if (token != null) {
            template.header("Authorization", "Bearer " + token);
            System.out.println("Authorization Header: " + "Bearer " + token); // Log for debugging
        }
    }
}
