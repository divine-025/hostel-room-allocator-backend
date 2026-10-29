package com.exam.hostelroomallocator.repository;

import com.exam.hostelroomallocator.domain.Warden;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface WardenRepository extends JpaRepository<Warden, UUID> {

    //C1
    @Query("SELECT DISTINCT w FROM Warden w JOIN w.dutyAreas d WHERE LOWER(d.name) = LOWER(:name)")
    List<Warden> searchByDutyAreaName(@Param("name") String name);
}
