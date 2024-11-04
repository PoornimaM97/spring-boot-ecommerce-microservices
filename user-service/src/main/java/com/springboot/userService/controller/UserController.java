package com.springboot.userService.controller;

import com.springboot.userService.dto.JwtAuthResponse;
import com.springboot.userService.dto.LoginDto;
import com.springboot.userService.dto.RegisterDto;
import com.springboot.userService.dto.UserProfileDto;
import com.springboot.userService.exception.ResourceNotFoundException;
import com.springboot.userService.security.CustomUserDetails;
import com.springboot.userService.security.CustomUserDetailsService;
import com.springboot.userService.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String response = userService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/admin")
    public ResponseEntity<String> adminRegister(@RequestBody RegisterDto registerDto){
        String response = userService.adminRegister(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto){
        String token = userService.login(loginDto);
        System.out.println(loginDto.getUsernameOrEmail());
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        return ResponseEntity.ok(jwtAuthResponse);
    }

    @GetMapping("/profile")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserProfileDto> getUserByUsername(@AuthenticationPrincipal CustomUserDetails userDetails){
        System.out.println(userDetails.getUsername());
        System.out.println(userDetails.getPassword());
        System.out.println(userDetails.getAuthorities());
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        String username = userDetails.getUsername();
        System.out.println("1");
        UserProfileDto user = userService.getUserProfile(username);
        System.out.println("2");
        return ResponseEntity.ok(user);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserProfileDto> getUserByUserId(@PathVariable("id") Long userId){
        UserProfileDto user = userService.getUserByUserId(userId);
        return ResponseEntity.ok(user);
    }


}
