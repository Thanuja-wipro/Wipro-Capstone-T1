package com.expense.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expense.dto.NotificationDto;
import com.expense.entity.Expense;
import com.expense.entity.Notification;
import com.expense.entity.User;
import com.expense.repository.NotificationRepository;
import com.expense.repository.UserRepository;

@Service
public class NotificationService {
	
	@Autowired
	private NotificationRepository notificationRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	public Notification saveNotification(NotificationDto notificationDTO) {
        User user = userRepo.findById(notificationDTO.getUser()).orElseThrow(() -> new RuntimeException("User not found"));

		Notification notification = new Notification();
		notification.setUser(user);
		notification.setMessage(notificationDTO.getMessage());
		Notification.Status status = Notification.Status.valueOf(notificationDTO.getStatus().toUpperCase());
		notification.setStatus(status);
		notification.setDate(notificationDTO.getDate());

        return notificationRepo.save(notification);
	}
	
	public List<Notification> getAllNotifications(){
		return notificationRepo.findAll();
	}
	
	
	public void deleteNotification(Long id) {
		notificationRepo.deleteById(id);
	}
	
	public void deleteAllNotification() {
		notificationRepo.deleteAll();
	}
}
