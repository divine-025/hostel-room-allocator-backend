package com.exam.hostelroomallocator.repository;

import com.exam.hostelroomallocator.domain.MaintenanceRequest;
import com.exam.hostelroomallocator.domain.RoomAllocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.UUID;

public interface MaintenanceRequestRepository extends JpaRepository<MaintenanceRequest, UUID> {
    
    //E2: JPQL with path navigation, newest first
    @Query("SELECT m FROM MaintenanceRequest m " + "WHERE m.roomAllocation.student.regNumber = :regNumber " + "ORDER BY m.loggedDate DESC")
    List<MaintenanceRequest> findHistoryByRegNumber(@Param("regNumber") String regNumber);

    //E3: derived. does this room allocation have at least one maintenance request?
    boolean existsByRoomAllocation(RoomAllocation roomAllocation);    
}
