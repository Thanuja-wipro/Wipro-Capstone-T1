package com.expense.entity;


import java.util.Date;

import jakarta.persistence.*;

@Entity
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expenseID;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    private Double amount;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "categoryID")
    private Category category;

    private String description;

	public Expense(Long expenseID, User user, Double amount, Date date, Category category, String description) {
		super();
		this.expenseID = expenseID;
		this.user = user;
		this.amount = amount;
		this.date = date;
		this.category = category;
		this.description = description;
	}

	public Expense() {
		super();
	}

	public Long getExpenseID() {
		return expenseID;
	}

	public void setExpenseID(Long expenseID) {
		this.expenseID = expenseID;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

    
}
