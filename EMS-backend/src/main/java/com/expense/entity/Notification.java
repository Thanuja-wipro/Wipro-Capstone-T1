package com.expense.entity;


import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationID;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    private String message;
    private Date date;

    @Enumerated(EnumType.STRING)
    private Status status;

	public Notification(Long notificationID, User user, String message, Date date, Status status) {
		super();
		this.notificationID = notificationID;
		this.user = user;
		this.message = message;
		this.date = date;
		this.status = status;
	}

	public Notification() {
		super();
	}

	public Long getNotificationID() {
		return notificationID;
	}

	public void setNotificationID(Long notificationID) {
		this.notificationID = notificationID;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

    
	public enum Status {
		READ, UNREAD
	}
}

