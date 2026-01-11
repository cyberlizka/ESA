package com.example.medapp.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EntityChangeMessage {
    private String entityName;
    private String entityId;
    private String changeType;
    private String changeDetails;
    
    public EntityChangeMessage() {
    }

    @JsonCreator
    public EntityChangeMessage(
            @JsonProperty("entityName") String entityName,
            @JsonProperty("entityId") String entityId,
            @JsonProperty("changeType") String changeType,
            @JsonProperty("changeDetails") String changeDetails) {
        this.entityName = entityName;
        this.entityId = entityId;
        this.changeType = changeType;
        this.changeDetails = changeDetails;
    }

    // Геттеры и сеттеры
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
        return "EntityChangeMessage{" +
                "entityName='" + entityName + '\'' +
                ", entityId='" + entityId + '\'' +
                ", changeType='" + changeType + '\'' +
                ", changeDetails='" + changeDetails + '\'' +
                '}';
    }
}