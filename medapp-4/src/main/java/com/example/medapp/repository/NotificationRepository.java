package com.example.medapp.repository;

import com.example.medapp.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByEntityNameAndChangeType(String entityName, String changeType);

    List<Notification> findByEntityNameAndChangeTypeIn(String entityName, List<String> changeTypes);

    List<Notification> findByEntityNameInAndChangeType(List<String> entityNames, String changeType);
}
