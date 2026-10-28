package com.exam.hostelroomallocator.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity 
@Table(name = "hostel_block")
public class HostelBlock {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    private int blockNumber;
    private String name;

    //Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public int getBlockNumber() { return blockNumber; }
    public void setBlockNumber(int blockNumber) { this.blockNumber = blockNumber; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
 
}
