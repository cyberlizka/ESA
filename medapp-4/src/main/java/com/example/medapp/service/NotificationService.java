package com.example.medapp.service;

import com.example.medapp.dto.EntityChangeMessage;
import com.example.medapp.model.Notification;
import com.example.medapp.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class NotificationService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private NotificationRepository notificationRepository;

    @Transactional
    public void checkAndSendNotifications(EntityChangeMessage message) {

        String entityName = message.getEntityName();
        String changeType = message.getChangeType();

        List<String> entityVariants = List.of(entityName, "*");
        List<String> changeVariants = List.of(changeType, "*");


        Set<Notification> subscriptions = new HashSet<>();

        for (String e : entityVariants) {
            for (String c : changeVariants) {
                subscriptions.addAll(
                        notificationRepository.findByEntityNameAndChangeType(e, c)
                );
            }
        }

        if (subscriptions.isEmpty()) {
            return;
        }

        String emailText =
                "Change detected in Medical Application:\n\n" +
                        "Entity: " + message.getEntityName() + "\n" +
                        "Entity ID: " + message.getEntityId() + "\n" +
                        "Change Type: " + message.getChangeType() + "\n" +
                        "Details: " + message.getChangeDetails();

        for (Notification subscription : subscriptions) {
            sendEmail(subscription.getEmail(), emailText);
        }
    }

    private void sendEmail(String email, String text) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("dashanek007@gmail.com");
        mailMessage.setTo(email);
        mailMessage.setSubject("Medical App — Entity Change");
        mailMessage.setText(text);

        mailSender.send(mailMessage);
    }
}
