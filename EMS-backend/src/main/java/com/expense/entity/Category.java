package com.expense.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryID;

    @NotNull
    private String name;
    private String description;

    @OneToMany(mappedBy = "category")
    @JsonManagedReference("category-expenses")
    private List<Expense> expenses;

	public Category() {
		super();
	}

	public Category(Long categoryID, String name, String description, List<Expense> expenses) {
		super();
		this.categoryID = categoryID;
		this.name = name;
		this.description = description;
		this.expenses = expenses;
	}

	public Long getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Long categoryID) {
		this.categoryID = categoryID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}

    
}
