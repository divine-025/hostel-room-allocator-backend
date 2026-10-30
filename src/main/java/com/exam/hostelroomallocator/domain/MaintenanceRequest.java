package com.exam.hostelroomallocator.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Entity 
@Table(name = "maintenance_request")
public class MaintenanceRequest {
    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    private String issue;
    private String description;
    private int priorityLevel;
    private LocalDate loggedDate;

    @ManyToOne 
    @JoinColumn(name = "room_allocation_id")
    private RoomAllocation roomAllocation;

    //Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getIssue() { return issue; }
    public void setIssue(String issue) { this.issue = issue; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getPriorityLevel() { return priorityLevel; }
    public void setPriorityLevel(int priorityLevel) { this.priorityLevel = priorityLevel; }

    public LocalDate getLoggedDate() { return loggedDate; }
    public void setLoggedDate(LocalDate loggedDate) { this.loggedDate = loggedDate; }

    public RoomAllocation getRoomAllocation() { return roomAllocation; }
    public void setRoomAllocation(RoomAllocation roomAllocation) { this.roomAllocation = roomAllocation; }
}
