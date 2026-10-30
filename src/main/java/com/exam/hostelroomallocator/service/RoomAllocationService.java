package com.exam.hostelroomallocator.service;

import com.exam.hostelroomallocator.repository.MaintenanceRequestRepository;
import com.exam.hostelroomallocator.domain.RoomAllocation;
import com.exam.hostelroomallocator.domain.AllocationStatus;
import java.time.LocalDate;
import com.exam.hostelroomallocator.repository.RoomAllocationRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service 
public class RoomAllocationService {

    private final RoomAllocationRepository repository;
    private final MaintenanceRequestRepository maintenanceRequestRepository;
    
    public RoomAllocationService(RoomAllocationRepository repository, MaintenanceRequestRepository maintenanceRequestRepository) {
        this.repository = repository;
        this.maintenanceRequestRepository = maintenanceRequestRepository;

    }

    //D1: returns a message. The controller turns the message into a status code.
    public String save (RoomAllocation roomAllocation) {
        if (roomAllocation.getAllocationDate().isBefore(LocalDate.now())) {
            return "Room allocation date cannot be in the past";
        }
        boolean busy = repository.existsByWardenIdAndAllocationDateAndStatusNot(
            roomAllocation.getWarden().getId(),
            roomAllocation.getAllocationDate(),
            AllocationStatus.DECLINED);
        if (busy) {
            return "Warden already handles an allocation on that date";
        }
        roomAllocation.setStatus(AllocationStatus.REQUESTED); // always starts as REQUESTED
        repository.save(roomAllocation);
        return "Room allocation saved successfully";               

    }
    public List<RoomAllocation> findAll() {
        return repository.findAll();
    }
    public Optional<RoomAllocation> findById(UUID id) {
        return repository.findById(id);
    }
    //D2 (part)the general PUT must no longer change the status
    //to mean we keep the status already stored.
    public Optional<RoomAllocation> update(UUID id, RoomAllocation roomAllocation) {
        Optional<RoomAllocation> existing = repository.findById(id);
        if (existing.isPresent()) {
            roomAllocation.setId(id);
            roomAllocation.setStatus(existing.get().getStatus());
            return Optional.of(repository.save(roomAllocation));
        }
        return Optional.empty();
    }public String delete(UUID id) {
        Optional<RoomAllocation> found = repository.findById(id);
        if (found.isEmpty()) {
            return "The room allocation with that id does not exist";
        }
        if (maintenanceRequestRepository.existsByRoomAllocation(found.get())) {
            return "The room allocation has maintenance requests, delete them first";
        }
        repository.deleteById(id);
        return "Room allocation deleted successfully";
    }
    //B2
    public List<RoomAllocation> findByStatus(AllocationStatus status) {
        return repository.findByStatusOrderByAllocationDateAsc(status);
    }

    //B3
    public List<RoomAllocation> findBetween(LocalDate start, LocalDate end) {
        return repository.findByAllocationDateBetweenOrderByAllocationDateAsc(start, end);
    }

    //D2: Status flow
    public String updateStatus(UUID id, AllocationStatus newStatus) {
        Optional<RoomAllocation> found = repository.findById(id);
        if (found.isEmpty()) {
            return "The room allocation woth that id does not exist";
        }
        RoomAllocation roomAllocation = found.get();
        AllocationStatus current = roomAllocation.getStatus();
        
        boolean allowed = false;
        if (current == AllocationStatus.REQUESTED) {
            allowed = (newStatus == AllocationStatus.APPROVED || newStatus == AllocationStatus.DECLINED);
        } else if (current == AllocationStatus.APPROVED) {
            allowed = (newStatus == AllocationStatus.CHECKED_IN || newStatus == AllocationStatus.DECLINED);
        }
        // CHECKED_IN and DECLINE  are final: alllowed sytays false

        if (!allowed) {
            return "Cannot change status from" + current + "to" + newStatus;
        }
        roomAllocation.setStatus(newStatus);
        repository.save(roomAllocation);
        return "Room allocation status updated successfully";
    }

    //F1
    public List<Object[]> statsByStatus() {
        return repository.statsByStatus();
    }
    //F2: Transactional is required by the service
    @Transactional 
    public String cancelDay(UUID wardenId, LocalDate date) {
        int count = repository.cancelDay(wardenId, date, AllocationStatus.DECLINED, AllocationStatus.CHECKED_IN);
        return count + " room allocation cancelled";
    }
}