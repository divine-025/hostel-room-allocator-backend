package com.exam.hostelroomallocator.repository;

import com.exam.hostelroomallocator.domain.AllocationStatus;
import com.exam.hostelroomallocator.domain.RoomAllocation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface RoomAllocationRepository extends JpaRepository<RoomAllocation, UUID> {

    //B2
    List<RoomAllocation> findByStatusOrderByAllocationDateAsc(AllocationStatus status);

    //B3
    List<RoomAllocation> findByAllocationDateBetweenOrderByAllocationDateAsc(LocalDate start, LocalDate end);

    //D1: derived (existsBy + And + Not).
    boolean existsByWardenIdAndAllocationDateAndStatusNot(UUID wardenId, LocalDate allocationDate, AllocationStatus status);
    
}
