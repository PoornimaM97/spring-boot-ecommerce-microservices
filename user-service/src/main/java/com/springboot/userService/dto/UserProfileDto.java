package com.springboot.userService.dto;

import com.springboot.userService.entity.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDto {

    private String username;
    private String email;
    private Set<Roles> roles;

}
