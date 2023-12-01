package com.petsociety.backend.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petsociety.backend.entity.NotificationEntity;
import com.petsociety.backend.entity.UserEntity;
import com.petsociety.backend.repository.NotificationRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public void createNotification(UserEntity user, String message,LocalDateTime timestamp) {
        NotificationEntity notification = new NotificationEntity();
        notification.setUser(user);
        notification.setMessage(message);
        notification.setTimestamp(timestamp);
        notification.setStatus(false); // Assuming false for unread
        notificationRepository.save(notification);
    }

    public List<NotificationEntity> getNotifications(UserEntity user) {
        List<NotificationEntity> notifications = notificationRepository.findByUserOrderByTimestampDesc(user);

        // Mark notifications as read
        for (NotificationEntity notification : notifications) {
            notification.setStatus(true);
        }
        saveNotifications(notifications);

        return notifications;
    }

    public void saveNotifications(List<NotificationEntity> notifications) {
        notificationRepository.saveAll(notifications);
    }
}
