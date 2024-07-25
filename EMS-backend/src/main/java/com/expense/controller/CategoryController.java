package com.expense.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.entity.Category;
import com.expense.exception.ResourceNotFoundException;
import com.expense.service.CategoryService;

@CrossOrigin
@RestController
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	 @PostMapping
	    public Category createCategory(@RequestBody Category category) {
	        return categoryService.saveCategory(category);
	    }

	    @GetMapping
	    public List<Category> getAllCategories() {
	        return categoryService.getAllCategories();
	    }

	    @GetMapping("/{id}")
	    public Optional<Category> getCategoryById(@PathVariable Long id) {
	        return categoryService.getCategoryById(id);
	    }

	    @PutMapping("/{id}")
	    public Category updateCategory(@PathVariable Long id, @RequestBody Category category) throws ResourceNotFoundException{
	    	return categoryService.updateCategory(id, category);
	    }
	    
	    @DeleteMapping("/{id}")
	    public void deleteUser(@PathVariable Long id) {
	    	categoryService.deleteCategory(id);
	    }
}
