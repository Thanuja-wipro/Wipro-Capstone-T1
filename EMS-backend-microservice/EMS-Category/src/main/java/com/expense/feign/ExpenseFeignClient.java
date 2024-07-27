package com.expense.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.expense.dto.ExpenseDto;

@FeignClient(name = "expense-service", url = "http://localhost:6063")
public interface ExpenseFeignClient {
    @GetMapping("/expenses/{id}")
    ExpenseDto getExpenseById(@PathVariable("id") Long id);
    
}

