package feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import com.expense.entity.Expense;
import com.expense.entity.User;

import feign.Headers;

@Headers("Content-Type: application/json")

@FeignClient (name = "test-userservice", url = "${USER_SERVICE:http://localhost:9091}")
public interface Test_Feign_Client {

	List<User> findAll();
	
}