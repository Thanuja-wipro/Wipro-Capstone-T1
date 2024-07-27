package com.expense.entity;


import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="userSer")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    private String name;
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String password;

    
//    @OneToMany(mappedBy = "user")
//    @JsonBackReference
//    private List<Expense> expenses;

	public User(Long userID, String name, String email, Role role, String password) {
		super();
		this.userID = userID;
		this.name = name;
		this.email = email;
		this.role = role;
		this.password = password;
//		this.expenses = expenses;
	}


	public User() {
		super();
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//	public List<Expense> getExpenses() {
//		return expenses;
//	}
//
//	public void setExpenses(List<Expense> expenses) {
//		this.expenses = expenses;
//	}

    
	public enum Role {
		ADMIN, USER
	}
}

