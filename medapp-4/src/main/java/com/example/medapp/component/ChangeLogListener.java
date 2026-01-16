package com.example.medapp.component;

import com.example.medapp.dto.EntityChangeMessage;
import com.example.medapp.service.LogService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ChangeLogListener {

    private final LogService logService;

    public ChangeLogListener(LogService logService) {
        this.logService = logService;
    }

    @JmsListener(
            destination = "entityChangeTopic",
            containerFactory = "topicListenerFactory"
    )
    public void onMessage(EntityChangeMessage message) {
        logService.logChange(
                message.getEntityName(),
                message.getEntityId(),
                message.getChangeType(),
                message.getChangeDetails()
        );
    }
}
