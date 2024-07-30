package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.expense.entity.User;
import feign.TestFeignClient;


@RestController
@RequestMapping("/api")
public class TestController {
	
	@Autowired
	TestFeignClient testFeign;
	
	@GetMapping("/hello")
	String hello() {
		return "Hello World, Spring Boot.... Welcome to You!";
	}
	
	 @GetMapping
	   public List<User> getAllUsers() {
	       return testFeign.findAll();
	   }

   
}