package com.expense.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.dto.ExpenseDto;
import com.expense.entity.Category;
import com.expense.exception.ResourceNotFoundException;
import com.expense.service.CategoryService;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	 @PostMapping
	    public Category createCategory(@Valid @RequestBody Category category) {
	        return categoryService.saveCategory(category);
	    }

	    @GetMapping
	    public List<Category> getAllCategories() {
	        return categoryService.getAllCategories();
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) throws ResourceNotFoundException{
	    	Category category =  categoryService.getCategoryById(id);
	        return ResponseEntity.ok().body(category);
	    }

	    @PutMapping("/{id}")
	    public Category updateCategory(@PathVariable Long id, @RequestBody Category category) throws ResourceNotFoundException{
	    	return categoryService.updateCategory(id, category);
	    }
	    
	    @DeleteMapping("/{id}")
	    public void deleteUser(@PathVariable Long id) {
	    	categoryService.deleteCategory(id);
	    }
	    
	    @GetMapping("/expense/{id}")
	    public ResponseEntity<ExpenseDto> getExpense(@PathVariable Long id) {
	        ExpenseDto expense = categoryService.getExpense(id);
	        return ResponseEntity.ok().body(expense);
	    }
}
