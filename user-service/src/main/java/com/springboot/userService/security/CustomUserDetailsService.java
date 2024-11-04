package com.springboot.userService.security;

import com.springboot.userService.entity.Users;
import com.springboot.userService.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Users user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(()->new UsernameNotFoundException("User does not exits with Username or Email"));
        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map((role)-> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
        return new CustomUserDetails(
                user.getId(),
                usernameOrEmail,
                user.getPassword(),
                authorities
        );
    }
}
