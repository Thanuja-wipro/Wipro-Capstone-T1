package com.expense;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
@EnableDiscoveryClient
@SpringBootConfiguration
@SpringBootApplication
@EnableFeignClients
public class ExpenseMainApplication {

	public static void main(String[] args) {
        SpringApplication.run(ExpenseMainApplication.class, args);


	}

}
