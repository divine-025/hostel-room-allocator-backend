package com.exam.hostelroomallocator.repository;

import com.exam.hostelroomallocator.domain.AllocationStatus;
import com.exam.hostelroomallocator.domain.RoomAllocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    
    //F1: one row per status with its count. Status with 0 rows never appear
    @Query("SELECT r.status, COUNT(r) FROM RoomAllocation r GROUP BY r.status")
    List<Object[]> statsByStatus();

    //F2
    @Modifying 
    @Query("UPDATE RoomAllocation r SET r.status = :declined " + "WHERE r.warden.id = :wardenId AND r.allocationDate =  :date AND r.status <> :checkedIn")
    int cancelDay(@Param("wardenId") UUID wardenId, @Param("date") LocalDate date, @Param("declined") AllocationStatus decline, @Param("checkedIn") AllocationStatus checkedIN);
}
