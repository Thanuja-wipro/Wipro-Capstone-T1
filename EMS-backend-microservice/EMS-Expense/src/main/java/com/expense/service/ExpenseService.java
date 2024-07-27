package com.expense.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.expense.entity.Expense;
import com.expense.dto.ExpenseDto;
import com.expense.exception.ResourceNotFoundException;
import com.expense.repository.ExpenseRepository;

@Service
public class ExpenseService {
	
	@Autowired
	private ExpenseRepository expenseRepo;
	
	
	public Expense saveExpense(ExpenseDto expenseDTO) {
        
        Expense expense = new Expense();
//        expense.setUser(user);
        expense.setDescription(expenseDTO.getDescription());
//        expense.setCategory(category);
        expense.setAmount(expenseDTO.getAmount());
        expense.setDate(expenseDTO.getDate());

        return expenseRepo.save(expense);
    }

    public List<Expense> getAllExpenses() {
        return expenseRepo.findAll();
    }

    public Optional<Expense> getExpenseById(Long id){
        return expenseRepo.findById(id);
    }
    
    public Expense updateExpense(Long id, ExpenseDto expenseDTO) throws ResourceNotFoundException{
    	Expense existingExpense= expenseRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Expense Not Found"));
    	
//        existingExpense.setUser(user);
        existingExpense.setDescription(expenseDTO.getDescription());
//        existingExpense.setCategory(category);
        existingExpense.setAmount(expenseDTO.getAmount());
        existingExpense.setDate(expenseDTO.getDate());

        return expenseRepo.save(existingExpense);
	}

    public void deleteExpense(Long id) {
    	expenseRepo.deleteById(id);
    }

}
