package com.expense.entity;


import jakarta.persistence.*;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryID;

    private String name;
    private String description;

    @OneToMany(mappedBy = "category")
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
