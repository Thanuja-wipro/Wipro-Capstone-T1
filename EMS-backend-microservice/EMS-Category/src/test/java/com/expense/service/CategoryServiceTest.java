package com.expense.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.expense.dto.ExpenseDto;
import com.expense.entity.Category;
import com.expense.exception.ResourceNotFoundException;
import com.expense.feign.ExpenseFeignClient;
import com.expense.repository.CategoryRepository;

public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepo;

    @Mock
    private ExpenseFeignClient expenseFeignClient;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveCategory() {
        Category category = new Category(1L, "Food", "Expenses on food");
        when(categoryRepo.save(any(Category.class))).thenReturn(category);

        Category savedCategory = categoryService.saveCategory(category);

        assertNotNull(savedCategory);
        assertEquals("Food", savedCategory.getName());
    }

    @Test
    public void testGetAllCategories() {
        Category category1 = new Category(1L, "Food", "Expenses on food");
        Category category2 = new Category(2L, "Transport", "Expenses on transport");

        when(categoryRepo.findAll()).thenReturn(Arrays.asList(category1, category2));

        List<Category> categories = categoryService.getAllCategories();

        assertNotNull(categories);
        assertEquals(2, categories.size());
    }

    @Test
    public void testGetCategoryById() throws ResourceNotFoundException {
        Category category = new Category(1L, "Food", "Expenses on food");
        when(categoryRepo.findById(1L)).thenReturn(Optional.of(category));

        Category foundCategory = categoryService.getCategoryById(1L);

        assertNotNull(foundCategory);
        assertEquals("Food", foundCategory.getName());
    }

    @Test
    public void testGetCategoryById_NotFound() {
        when(categoryRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> categoryService.getCategoryById(1L));
    }

    @Test
    public void testUpdateCategory() throws ResourceNotFoundException {
        Category existingCategory = new Category(1L, "Food", "Expenses on food");
        Category updatedCategory = new Category(1L, "Transport", "Expenses on transport");

        when(categoryRepo.findById(1L)).thenReturn(Optional.of(existingCategory));
        when(categoryRepo.save(any(Category.class))).thenReturn(updatedCategory);

        Category result = categoryService.updateCategory(1L, updatedCategory);

        assertNotNull(result);
        assertEquals("Transport", result.getName());
    }

    @Test
    public void testUpdateCategory_NotFound() {
        Category updatedCategory = new Category(1L, "Transport", "Expenses on transport");

        when(categoryRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> categoryService.updateCategory(1L, updatedCategory));
    }

    @Test
    public void testDeleteCategory() {
        doNothing().when(categoryRepo).deleteById(1L);

        categoryService.deleteCategory(1L);

        verify(categoryRepo, times(1)).deleteById(1L);
    }

    @Test
    public void testGetExpense() {
        ExpenseDto expenseDto = new ExpenseDto();
        expenseDto.setDate(new Date());
        expenseDto.setUser(1L);
        expenseDto.setDescription("Groceries");
        expenseDto.setCategory(1L);
        expenseDto.setAmount(50.0);

        when(expenseFeignClient.getExpenseById(1L)).thenReturn(expenseDto);

        ExpenseDto result = categoryService.getExpense(1L);

        assertNotNull(result);
        assertEquals("Groceries", result.getDescription());
        assertEquals(50.0, result.getAmount());
    }
}
