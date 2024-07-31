package com.expense.entity;

import java.util.Date;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportID;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    private String period;
    private Double totalAmount;
    private Date generatedDate;
	public Report(Long reportID, User user, String period, Double totalAmount, Date generatedDate) {
		super();
		this.reportID = reportID;
		this.user = user;
		this.period = period;
		this.totalAmount = totalAmount;
		this.generatedDate = generatedDate;
	}
	public Report() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getReportID() {
		return reportID;
	}
	public void setReportID(Long reportID) {
		this.reportID = reportID;
	}
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Date getGeneratedDate() {
		return generatedDate;
	}
	public void setGeneratedDate(Date generatedDate) {
		this.generatedDate = generatedDate;
	}
	
    
}
