package com.expense.entity;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

@Entity
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expenseID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID")
    @JsonBackReference("user-expenses")
    private User user;
    
    @Transient
    @JsonProperty("userID")
    private Long userID;


    private Double amount;
    private Date date;

    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryID")
    @JsonBackReference("category-expenses")
    private Category category;

    @Transient
    @JsonProperty("categoryID")
    private Long categoryID;
    
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

	@PostLoad
    public void populateTransientFields() {
        if (this.user != null) {
            this.userID = this.user.getUserID();
        }
        if (this.category != null) {
            this.categoryID = this.category.getCategoryID();
        }
    }
}
