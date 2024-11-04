//package com.springboot.orderservice.security;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import java.util.Collection;
//
//public class CustomUserDetails implements UserDetails {
//    private Long userId;  // Store userId
//    private String username;
//    private Collection<GrantedAuthority> authorities;
//
//    public CustomUserDetails(Long userId, String username, Collection<GrantedAuthority> authorities) {
//        this.userId = userId;
//        this.username = username;
//        this.authorities = authorities;
//    }
//
//    public Long getUserId() {
//        return userId;
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    @Override
//    public Collection<GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return null; // Password is not needed here for JWT
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
//
//
