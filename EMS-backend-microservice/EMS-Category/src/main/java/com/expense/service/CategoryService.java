package com.expense.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expense.dto.ExpenseDto;
import com.expense.entity.Category;
import com.expense.exception.ResourceNotFoundException;
import com.expense.feign.ExpenseFeignClient;
import com.expense.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired
	private ExpenseFeignClient expenseFeignClient;
	
	public Category saveCategory(Category category) {
		return categoryRepo.save(category);
	}
	
	public List<Category> getAllCategories() {
		return categoryRepo.findAll();
	}
	
	public Category getCategoryById(Long id) throws ResourceNotFoundException{
		return categoryRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category Not Found"));

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
	
	public ExpenseDto getExpense(Long expenseId) {
        return expenseFeignClient.getExpenseById(expenseId);
    }

}
