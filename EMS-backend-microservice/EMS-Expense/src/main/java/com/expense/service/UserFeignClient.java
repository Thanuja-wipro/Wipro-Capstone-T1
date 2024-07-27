package com.expense.service;

//UserServiceClient.java
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.expense.dto.UserDto;

@FeignClient(name = "user-service", url = "${user-service.url}")
public interface UserFeignClient {

 @GetMapping("/users/{userId}")
 UserDto getUserById(@PathVariable("userId") Long userId);

 // Other methods for interacting with user-service
}
