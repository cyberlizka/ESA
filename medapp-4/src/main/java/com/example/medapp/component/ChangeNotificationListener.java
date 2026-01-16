package com.example.medapp.component;

import com.example.medapp.dto.EntityChangeMessage;
import com.example.medapp.service.NotificationService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ChangeNotificationListener {

    private final NotificationService notificationService;

    public ChangeNotificationListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @JmsListener(
            destination = "entityChangeTopic",
            containerFactory = "topicListenerFactory"
    )
    public void onMessage(EntityChangeMessage message) {
        notificationService.checkAndSendNotifications(message);
    }
}
