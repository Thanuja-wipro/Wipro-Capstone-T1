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
import com.expense.entity.User;

import feign.Headers;

@Headers("Content-Type: application/json")
@FeignClient(name = "test-userseservice", url = "${USER_SERVICE:http://localhost:9091}")
public interface TestFeignClient {


	List<User> findAll();
}
