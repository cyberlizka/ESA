package com.example.medapp.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "log")
public class Log implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "entity_name", nullable = false)
    private String entityName;

    @Column(name = "entity_id", nullable = false)
    private String entityId; // String вместо Long, т.к. у вас Integer ID

    @Column(name = "change_type")
    private String changeType; // INSERT, UPDATE, DELETE

    @Column(name = "change_details", columnDefinition = "TEXT")
    private String changeDetails;

    public Log() {}

    public Log(String entityName, String entityId, String changeType, String changeDetails) {
        this.entityName = entityName;
        this.entityId = entityId;
        this.changeType = changeType;
        this.changeDetails = changeDetails;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEntityName() { return entityName; }
    public void setEntityName(String entityName) { this.entityName = entityName; }

    public String getEntityId() { return entityId; }
    public void setEntityId(String entityId) { this.entityId = entityId; }

    public String getChangeType() { return changeType; }
    public void setChangeType(String changeType) { this.changeType = changeType; }

    public String getChangeDetails() { return changeDetails; }
    public void setChangeDetails(String changeDetails) { this.changeDetails = changeDetails; }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", entityName='" + entityName + '\'' +
                ", entityId='" + entityId + '\'' +
                ", changeType='" + changeType + '\'' +
                ", changeDetails='" + changeDetails + '\'' +
                '}';
    }
}