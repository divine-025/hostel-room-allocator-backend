package com.exam.hostelroomallocator.repository;

import com.exam.hostelroomallocator.domain.HostelBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface HostelBlockRepository extends JpaRepository<HostelBlock, UUID> {
    
}
