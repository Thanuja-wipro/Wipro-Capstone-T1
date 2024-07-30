package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.entity.Expense;
import com.expense.entity.User;
import com.expense.exception.ResourceNotFoundException;

import feign.Test_Feign_Client;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping
public class TestController {
	
	@Autowired
	Test_Feign_Client testFeign;
	
	@GetMapping("/hello")
	String hello() {
		return "Hello World, Spring Boot.... Welcome to You!";
	}
	


	 @GetMapping
	   public List<User> getAllUsers() {
	       return testFeign.findAll();
	   }
}