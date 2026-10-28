package com.exam.hostelroomallocator.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.List;
import java.util.UUID;

@Entity 
@Table(name = "warden")
public class Warden {

    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id; 

    private String firstName;
    private String lastName;
    private String phone;

    @ManyToOne 
    @JoinColumn(name = "hostel_block_id", nullable = true)
    private HostelBlock hostelBlock;

    @ManyToMany 
    @JoinTable(name = "warden_duty_area")
    private List<DutyArea> dutyAreas;

    //Getters and setters

    public UUID getId() { return id;}
    public void setId(UUID id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirtsName(String firstName) { this.firstName = firstName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public HostelBlock getHostelBlock() { return hostelBlock; }
    public void setHostelBlock(HostelBlock hostelBlock) { this.hostelBlock = hostelBlock; }

    public List<DutyArea> getDutyAreas() { return dutyAreas; }
    public void setDutyAreas(List<DutyArea> dutyAreas) { this.dutyAreas = dutyAreas; }
}
