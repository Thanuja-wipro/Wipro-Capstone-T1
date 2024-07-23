package com.expense.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expense.entity.Category;
import com.expense.exception.ResourceNotFoundException;
import com.expense.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	public Category saveCategory(Category category) {
		return categoryRepo.save(category);
	}
	
	public List<Category> getAllCategories() {
		return categoryRepo.findAll();
	}
	
	public Optional<Category> getCategoryById(Long id) {
		return categoryRepo.findById(id);
	}
	
	public Category updateCategory(Long id, Category category) throws ResourceNotFoundException{
		Category existingCategory = categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category Not Found"));
		existingCategory.setName(category.getName());
		existingCategory.setDescription(category.getDescription());
        
        return categoryRepo.save(existingCategory);
	}
	
	public void deleteCategory(Long id) {
		categoryRepo.deleteById(id);
	}

}
