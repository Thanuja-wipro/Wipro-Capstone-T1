package com.expense.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expense.entity.Expense;
import com.expense.repository.ExpenseRepository;

@Service
public class ExpenseService {
	
	@Autowired
	private ExpenseRepository expenseRepo;
	
	public Expense saveExpense(Expense expense) {
        return expenseRepo.save(expense);
    }

    public List<Expense> getAllExpenses() {
        return expenseRepo.findAll();
    }

    public Optional<Expense> getExpenseById(Long id) {
        return expenseRepo.findById(id);
    }

    public void deleteExpense(Long id) {
    	expenseRepo.deleteById(id);
    }

}
