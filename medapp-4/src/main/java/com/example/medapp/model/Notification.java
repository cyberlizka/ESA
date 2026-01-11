package com.example.medapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;


    @Column(name = "entity_name", nullable = false)
    private String entityName;


    @Column(name = "change_type", nullable = false)
    private String changeType;

    public Notification() {}

    public Notification(String email, String entityName, String changeType) {
        this.email = email;
        this.entityName = entityName;
        this.changeType = changeType;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getEntityName() {
        return entityName;
    }

    public String getChangeType() {
        return changeType;
    }
}
