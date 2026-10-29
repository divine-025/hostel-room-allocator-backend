package com.exam.hostelroomallocator.repository;

import com.exam.hostelroomallocator.domain.Warden;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface WardenRepository extends JpaRepository<Warden, UUID> {
    
}
