package com.exam.hostelroomallocator.repository;

import com.exam.hostelroomallocator.domain.RoomAllocation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface RoomAllocationRepository extends JpaRepository<RoomAllocation, UUID> {
    
}
