package com.expense.entity;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name="expenseSer")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expenseID;

    @ManyToOne
    @JoinColumn(name = "userID")
    @JsonManagedReference
    private Long userID;

    private Double amount;
    private Date date;

    
    @ManyToOne
    @JoinColumn(name = "categoryID")
    @JsonManagedReference
    private Long categoryID;

    private String description;

	public Expense(Long expenseID, Long userID, Double amount, Date date, Long categoryID, String description) {
		super();
		this.expenseID = expenseID;
		this.userID = userID;
		this.amount = amount;
		this.date = date;
		this.categoryID = categoryID;
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

	

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public Long getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Long categoryID) {
		this.categoryID = categoryID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

    
}
