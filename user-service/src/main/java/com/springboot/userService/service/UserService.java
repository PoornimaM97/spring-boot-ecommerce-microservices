package com.springboot.userService.service;

import com.springboot.userService.dto.LoginDto;
import com.springboot.userService.dto.RegisterDto;
import com.springboot.userService.dto.UserProfileDto;

public interface UserService {

    String register(RegisterDto registerDto);

    String adminRegister(RegisterDto registerDto);

    String login(LoginDto loginDto);

    UserProfileDto getUserProfile(String username);

    UserProfileDto getUserByUserId(Long userId);
}
