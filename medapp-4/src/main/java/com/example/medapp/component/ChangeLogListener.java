package com.example.medapp.component;

import com.example.medapp.dto.EntityChangeMessage;
import com.example.medapp.service.LogService;
import com.example.medapp.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ChangeLogListener {

    @Autowired
    private LogService logService;

    @Autowired
    private NotificationService notificationService;

    @JmsListener(destination = "entityChangeQueue")
    public void onMessage(EntityChangeMessage message) {

        logService.logChange(
                message.getEntityName(),
                message.getEntityId(),
                message.getChangeType(),
                message.getChangeDetails()
        );

        notificationService.checkAndSendNotifications(message);
    }
}
