package com.expense.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.entity.User;
import com.expense.repository.UserRepository;

@CrossOrigin
@RestController
@RequestMapping("/user/auth")
public class UserController {

   @Autowired
   private AuthenticationManager authenticationManager;

   @Autowired
   private UserRepository userRepository;

   @Autowired
   private PasswordEncoder passwordEncoder;

   @PostMapping("/signin")
   public ResponseEntity<?> authenticateUser(@RequestBody User user) {
       
    	   User ex_user = userRepository.findByEmail(user.getEmail())
                   .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + user.getEmail()));
    	   System.out.println("user existed");
           Authentication authentication = authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

           SecurityContextHolder.getContext().setAuthentication(authentication);

           Map<String, Object> response = new HashMap<>();
           response.put("status", 200);
           response.put("message", "User signed-in successful");

           return ResponseEntity.ok(response);
     
   }


   @PostMapping("/signup")
   public ResponseEntity<?> registerUser(@RequestBody User user) {

       if (userRepository.existsByEmail(user.getEmail())) {
           return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
       }

       User new_user = new User();
       new_user.setName(user.getName());
       new_user.setRole(user.getRole());
       new_user.setEmail(user.getEmail());
       new_user.setPassword(passwordEncoder.encode(user.getPassword()));

       userRepository.save(new_user);

       Map<String, Object> response = new HashMap<>();
       response.put("status", 200);
       response.put("message", "User registered successfully");

       return new ResponseEntity<>(response, HttpStatus.OK);
   }
}