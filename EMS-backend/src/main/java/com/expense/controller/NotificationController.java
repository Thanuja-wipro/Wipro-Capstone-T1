package com.expense.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.dto.NotificationDto;
import com.expense.entity.Notification;
import com.expense.service.NotificationService;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

	@Autowired
	private NotificationService notificationService;
	
	@PostMapping
    public Notification createNotification(@RequestBody NotificationDto notificationDTO) {
        return notificationService.saveNotification(notificationDTO);
    }

    @GetMapping
    public List<Notification> getAllNotifications() {
        return notificationService.getAllNotifications();
    }


    @DeleteMapping("/{id}")
    public void deleteNotification(@PathVariable Long id) {
    	notificationService.deleteNotification(id);
    }
    
    @DeleteMapping
    public void deleteAllNotification() {
    	notificationService.deleteAllNotification();
    }
    
}
