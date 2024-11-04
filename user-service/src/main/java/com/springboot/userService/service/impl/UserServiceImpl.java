package com.springboot.userService.service.impl;

import com.springboot.userService.dto.LoginDto;
import com.springboot.userService.dto.RegisterDto;
import com.springboot.userService.dto.UserProfileDto;
import com.springboot.userService.entity.Roles;
import com.springboot.userService.entity.Users;
import com.springboot.userService.exception.ResourceNotFoundException;
import com.springboot.userService.exception.UserAPIException;
import com.springboot.userService.repository.RoleRepository;
import com.springboot.userService.repository.UserRepository;
import com.springboot.userService.security.JwtTokenProvider;
import com.springboot.userService.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;


    @Override
    public String register(RegisterDto registerDto) {

        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw new UserAPIException(HttpStatus.BAD_REQUEST, "The Username already exists");
        }

        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new UserAPIException(HttpStatus.BAD_REQUEST, "The email already exists");
        }

        Users users = new Users();
        users.setName(registerDto.getName());
        users.setUsername(registerDto.getUsername());
        users.setEmail(registerDto.getEmail());
        users.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        Set<Roles> roles = new HashSet<>();
        Roles userRoles = roleRepository.findByName("ROLE_USER");
        roles.add(userRoles);
        users.setRoles(roles);
        userRepository.save(users);
        return "The user registered successfully";
    }

    @Override
    public String adminRegister(RegisterDto registerDto) {
        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw new UserAPIException(HttpStatus.BAD_REQUEST, "The Username already exists");
        }

        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new UserAPIException(HttpStatus.BAD_REQUEST, "The email already exists");
        }

        Users users = new Users();
        users.setName(registerDto.getName());
        users.setUsername(registerDto.getUsername());
        users.setEmail(registerDto.getEmail());
        users.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        Set<Roles> roles = new HashSet<>();
        Roles userRoles = roleRepository.findByName("ROLE_ADMIN");
        roles.add(userRoles);
        users.setRoles(roles);
        userRepository.save(users);
        return "The admin user registered successfully";
    }

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),
                loginDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }

    @Override
    public UserProfileDto getUserProfile(String username) {
        System.out.println("3");
        System.out.println(username);
        String name = username.substring(0, username.indexOf("@"));
        Users user =userRepository.findByUsername(name).orElseThrow(
                ()-> new ResourceNotFoundException("User does not exists with username: "+ username));
        System.out.println("4");
        return new UserProfileDto(
                user.getUsername(),
                user.getEmail(),
                user.getRoles()
        );
    }

    @Override
    public UserProfileDto getUserByUserId(Long userId) {
        Users user = userRepository.findById(userId).orElseThrow(()->
                new ResourceNotFoundException("User does not exists with id: "+ userId));
        return new UserProfileDto(
                user.getUsername(),
                user.getEmail(),
                user.getRoles()
        );

    }
}
