package com.springboot.userService.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private Long userId;
    private String username;
    private String password;
    private Collection<GrantedAuthority> authorities;

    public CustomUserDetails(Long userId,String username, String password, Collection<GrantedAuthority> authorities) {
        this.userId= userId;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public Long getUserId() {
        return userId;
    }
}
