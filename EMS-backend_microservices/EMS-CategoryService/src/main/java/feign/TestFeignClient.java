package feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.expense.entity.Expense;
import feign.Headers;

@Headers("Content-Type: application/json")
@FeignClient(name = "test-expenseservice", url = "${EXPENSE_SERVICE:http://localhost:9092}")
public interface TestFeignClient {

    @PostMapping("/expenses")
    Expense saveExpense(@RequestBody Expense expenseDTO);

    @GetMapping("/expenses")
    List<Expense> getAllExpenses();

    @GetMapping("/expenses/{id}")
    Expense getExpenseById(@PathVariable("id") Long id);

    @PutMapping("/expenses/{id}")
    Expense updateExpense(@PathVariable("id") Long id, @RequestBody Expense expenseDTO);

    @DeleteMapping("/expenses/{id}")
    void deleteExpense(@PathVariable("id") Long id);

	<ExpenseDto> Expense saveExpense(ExpenseDto expenseDTO);

	<ExpenseDto> Expense updateExpense(Long id, ExpenseDto expenseDTO);
}
