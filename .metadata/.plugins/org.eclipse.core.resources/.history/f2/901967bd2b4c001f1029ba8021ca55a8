package com.expense.repository;

import com.expense.entity.Expense;
import com.expense.entity.User;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long>{
    List<Expense> findByUserAndDateBetween(User user, Date startDate, Date endDate);
    

}
