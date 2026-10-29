package com.exam.hostelroomallocator.repository;

import com.exam.hostelroomallocator.domain.DutyArea;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface DutyAreaRepository extends JpaRepository<DutyArea, UUID> {
    
}
