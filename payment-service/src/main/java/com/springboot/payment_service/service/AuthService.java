package com.springboot.payment_service.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class AuthService {

    private String jwtToken;
}
