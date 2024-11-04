//package com.springboot.userService.mapper;
//
//import com.springboot.userService.dto.UserProfileDto;
//import com.springboot.userService.entity.Users;
//import lombok.AllArgsConstructor;
//
//@AllArgsConstructor
//public class UserMapper {
//
//    public static Users mapToUsers(UserProfileDto userProfileDto){
//        return new Users(
//                userProfileDto.getId(),
//                userProfileDto.getName(),
//                userProfileDto.getUsername(),
//                userProfileDto.getEmail(),
//                userProfileDto.getPassword()
//        );
//    }
//
//    public static UserDto mapToUserDto(Users users){
//        return new UserDto(
//                users.getId(),
//                users.getUsername(),
//                users.getEmail(),
//                users.getPassword()
//        );
//    }
//}
