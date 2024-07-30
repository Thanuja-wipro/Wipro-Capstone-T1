package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.expense.entity.Expense;
import com.expense.exception.ResourceNotFoundException;

import feign.TestFeignClient;

@RestController
@RequestMapping("/api")
public class TestController<ExpenseDto> {
	
	@Autowired
    TestFeignClient testFeign;
	
	@GetMapping("/hello")
	String hello() {
		return "Hello World, Spring Boot.... Welcome to You!";
	}
	
	@PostMapping
    public Expense createUser(@RequestBody ExpenseDto expenseDTO) {
        return testFeign.saveExpense(expenseDTO);
    }

    @GetMapping
    public List<Expense> getAllExpenses() {
        return testFeign.getAllExpenses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id) throws ResourceNotFoundException{
    	Expense expense = testFeign.getExpenseById(id);
        return ResponseEntity.ok().body(expense);
    }
    
    @PutMapping("/{id}")
    public Expense updateExpense(@PathVariable Long id, @RequestBody ExpenseDto expenseDTO) throws ResourceNotFoundException{
    	return testFeign.updateExpense(id, expenseDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id) {
    	testFeign.deleteExpense(id);
    }

    
}