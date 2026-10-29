package com.exam.hostelroomallocator.controller;

import com.exam.hostelroomallocator.domain.RoomAllocation;
import com.exam.hostelroomallocator.service.RoomAllocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController 
@RequestMapping("/api/room-allocations")
public class RoomAllocationController {
    
    private final RoomAllocationService service;

    public RoomAllocationController(RoomAllocationService service) {
        this.service = service;
    }
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody RoomAllocation roomAllocation) {
        service.save(roomAllocation);
        return ResponseEntity.status(HttpStatus.CREATED).body("Room allocation saved successfully");
    }
    @GetMapping("/all")
    public ResponseEntity<List<RoomAllocation>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        Optional<RoomAllocation> roomAllocation = service.findById(id);
        if (roomAllocation.isPresent()) {
            return ResponseEntity.ok(roomAllocation.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The room allocation with this id does not exist");
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody RoomAllocation roomAllocation) {
        Optional<RoomAllocation> updated = service.update(id, roomAllocation);
        if (updated.isPresent()) {
            return ResponseEntity.ok(updated.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The room allocation with that id does not exist");
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        if (service.delete(id)) {
            return ResponseEntity.ok("Room allocation deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The room allocation with this id does not exist");
        }
    }
}

