package com.springboot.orderservice.service;

import com.springboot.orderservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE")
public interface UserAPIClient {

    @GetMapping("api/user/{id}")
    UserDto getUserByUserId(@PathVariable("id") Long userId);

}
