package com.exam.hostelroomallocator.repository;

import com.exam.hostelroomallocator.domain.DutyArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.UUID;

public interface DutyAreaRepository extends JpaRepository<DutyArea, UUID> {

    //C3
    @Query("SELECT d FROM DutyArea d WHERE d.wardens IS EMPTY")
    List<DutyArea> findUnused();
    
}
