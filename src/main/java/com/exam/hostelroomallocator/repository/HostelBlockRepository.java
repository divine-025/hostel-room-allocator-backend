package com.exam.hostelroomallocator.repository;

import com.exam.hostelroomallocator.domain.HostelBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface HostelBlockRepository extends JpaRepository<HostelBlock, UUID> {

    //C2
    @Query("SELECT h FROM HostelBlock h WHERE NOT EXISTS " + "(SELECT w FROM Warden w WHERE w.hostelBlock = h) ORDER BY h.blockNumber")
    List<HostelBlock> findFreeBlocks();
    
    //D4: derived
    Optional<HostelBlock> findByBlockNumber(int blockNumber);
}
