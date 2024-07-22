package com.expense.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expense.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Object findByEmail(String email);

}
