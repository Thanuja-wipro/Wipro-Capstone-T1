package com.expense.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expense.entity.Notification;
import com.expense.repository.NotificationRepository;

@Service
public class NotificationService {
	
	@Autowired
	private NotificationRepository notificationRepo;
	
	public Notification saveNotification(Notification notification) {
		return notificationRepo.save(notification);
	}
	
	public List<Notification> getAllNotifications(){
		return notificationRepo.findAll();
	}
	
	public Optional<Notification> getNotificationById(Long id) {
		return notificationRepo.findById(id);
	}
	
	public void deleteNotification(Long id) {
		notificationRepo.deleteById(id);
	}
}
