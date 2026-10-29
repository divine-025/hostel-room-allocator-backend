package com.exam.hostelroomallocator.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Entity 
@Table(name = "room_allocation")
public class RoomAllocation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDate allocationDate;

    @Enumerated(EnumType.STRING)
    private AllocationStatus status;

    private String roomCode;
    private int bedNumber;

    @ManyToOne 
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne 
    @JoinColumn(name = "warden_id")
    private Warden warden;

    //Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public LocalDate getAllocationDate() { return allocationDate; }
    public void setAllocationDate(LocalDate allocationDate) { this.allocationDate = allocationDate; }

    public AllocationStatus getStatus() { return status; }
    public void setStatus(AllocationStatus status) { this.status = status; }

    public String getRoomCode() { return roomCode; }
    public void setRoomCode(String roomCode) { this.roomCode = roomCode; }

    public int getBedNumber() { return bedNumber; }
    public void setBedNumber(int bedNumber) { this.bedNumber= bedNumber; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public Warden getWarden() { return warden; }
    public void setWarden(Warden warden) { this.warden = warden; }
}


