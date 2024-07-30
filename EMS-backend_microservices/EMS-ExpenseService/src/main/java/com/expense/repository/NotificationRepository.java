package com.expense.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expense.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
