package com.expense.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.expense.dto.ExpenseDto;
import com.expense.entity.Category;
import com.expense.exception.ResourceNotFoundException;
import com.expense.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CategoryControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    public void testCreateCategory() throws Exception {
        Category category = new Category(1L, "Food", "Expenses on food");

        when(categoryService.saveCategory(any(Category.class))).thenReturn(category);

        mockMvc.perform(post("/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(category)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Food"));
    }

    @Test
    public void testGetAllCategories() throws Exception {
        Category category1 = new Category(1L, "Food", "Expenses on food");
        Category category2 = new Category(2L, "Transport", "Expenses on transport");

        List<Category> categories = Arrays.asList(category1, category2);

        when(categoryService.getAllCategories()).thenReturn(categories);

        mockMvc.perform(get("/categories")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Food"))
                .andExpect(jsonPath("$[1].name").value("Transport"));
    }

    @Test
    public void testGetCategoryById() throws Exception {
        Category category = new Category(1L, "Food", "Expenses on food");

        when(categoryService.getCategoryById(1L)).thenReturn(category);

        mockMvc.perform(get("/categories/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Food"));
    }

    @Test
    public void testGetCategoryById_NotFound() throws Exception {
        when(categoryService.getCategoryById(1L)).thenThrow(new ResourceNotFoundException("Category Not Found"));

        mockMvc.perform(get("/categories/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateCategory() throws Exception {
        Category category = new Category(1L, "Food", "Expenses on food");
        Category updatedCategory = new Category(1L, "Transport", "Expenses on transport");

        when(categoryService.updateCategory(eq(1L), any(Category.class))).thenReturn(updatedCategory);

        mockMvc.perform(put("/categories/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(updatedCategory)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Transport"));
    }

    @Test
    public void testDeleteCategory() throws Exception {
        doNothing().when(categoryService).deleteCategory(1L);

        mockMvc.perform(delete("/categories/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(categoryService, times(1)).deleteCategory(1L);
    }

    @Test
    public void testGetExpense() throws Exception {
        ExpenseDto expenseDto = new ExpenseDto();
        expenseDto.setDate(new Date());
        expenseDto.setUser(1L);
        expenseDto.setDescription("Groceries");
        expenseDto.setCategory(1L);
        expenseDto.setAmount(50.0);

        when(categoryService.getExpense(1L)).thenReturn(expenseDto);

        mockMvc.perform(get("/categories/expense/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("Groceries"))
                .andExpect(jsonPath("$.amount").value(50.0));
    }
}
