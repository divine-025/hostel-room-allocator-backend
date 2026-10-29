package com.exam.hostelroomallocator.service;

import com.exam.hostelroomallocator.domain.RoomAllocation;
import com.exam.hostelroomallocator.repository.RoomAllocationRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service 
public class RoomAllocationService {

    private final RoomAllocationRepository repository;

    public RoomAllocationService(RoomAllocationRepository repository) {
        this.repository = repository;

    }

    public RoomAllocation save(RoomAllocation roomAllocation) {
        return repository.save(roomAllocation);
    }

    public List<RoomAllocation> findAll() {
        return repository.findAll();
    }

    public Optional<RoomAllocation> findById(UUID id) {
        return repository.findById(id);
    }
    public Optional<RoomAllocation> update(UUID id, RoomAllocation roomAllocation) {
        if (repository.existsById(id)) {
            roomAllocation.setId(id);
            return Optional.of(repository.save(roomAllocation));
        }
        return Optional.empty();
    }
    public boolean delete(UUID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}