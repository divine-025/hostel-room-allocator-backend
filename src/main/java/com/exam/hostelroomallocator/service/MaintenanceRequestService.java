package com.exam.hostelroomallocator.service;

import com.exam.hostelroomallocator.domain.AllocationStatus;
import com.exam.hostelroomallocator.domain.MaintenanceRequest;
import com.exam.hostelroomallocator.domain.RoomAllocation;
import com.exam.hostelroomallocator.repository.MaintenanceRequestRepository;
import com.exam.hostelroomallocator.repository.RoomAllocationRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service 
public class MaintenanceRequestService {

     private final MaintenanceRequestRepository repository;
     private final RoomAllocationRepository roomAllocationRepository;

     public MaintenanceRequestService(MaintenanceRequestRepository repository, RoomAllocationRepository roomAllocationRepository) {
        this.repository = repository;
        this.roomAllocationRepository = roomAllocationRepository;
     }

     //E1
     public String save(UUID roomAllocationId, MaintenanceRequest maintenanceRequest) {
        Optional<RoomAllocation> found = roomAllocationRepository.findById(roomAllocationId);
        if (found.isEmpty()) {
            return "The room allocation with that id does not exist";
        }
        RoomAllocation roomAllocation = found.get();
        if (roomAllocation.getStatus() != AllocationStatus.CHECKED_IN) {
            return "Maintenance requests are only for CHECKED_IN room allocation";
        }
        maintenanceRequest.setLoggedDate(LocalDate.now());
        maintenanceRequest.setRoomAllocation(roomAllocation);
        repository.save(maintenanceRequest);
        return "Maintenance request save successfully";
     }

     //helper so you can see the ids of the requests you created
     public List<MaintenanceRequest> findAll() {
        return repository.findAll();
     }

     //E2
     public List<MaintenanceRequest> findHistoryByRegNumber(String regNumber) {
        return repository.findHistoryByRegNumber(regNumber);
     }

     //E3
     public boolean delete(UUID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
     }
}

