package com.exam.hostelroomallocator.controller;

import com.exam.hostelroomallocator.domain.RoomAllocation;
import com.exam.hostelroomallocator.service.RoomAllocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.exam.hostelroomallocator.domain.AllocationStatus;
import java.time.LocalDate;
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
        String message = service.save(roomAllocation);
        if (message.equals("Room alllocation saved successfully")) {
            return ResponseEntity.status(HttpStatus.CREATED).body(message);
        } else if (message.equals("Room allocation date cannot be in the past")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
        }
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
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The room allocation with that id does not exist");
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
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The room allocation with that id does not exist");
        }
    }

    //B2
    @GetMapping("/by-status")
    public ResponseEntity<List<RoomAllocation>> findByStatus(@RequestParam AllocationStatus status) {
        return ResponseEntity.ok(service.findByStatus(status));
    }

    //B3
    @GetMapping("/between")
    public ResponseEntity<List<RoomAllocation>> findBetween(@RequestParam String start, @RequestParam String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return ResponseEntity.ok(service.findBetween(startDate, endDate));
    }
    // D2
    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable UUID id, @RequestParam AllocationStatus status) {
        String message = service.updateStatus(id, status);
        if (message.equals("Room allocation status updated successfully")) {
            return ResponseEntity.ok(message);
        } else if (message.startsWith("Cannot change status")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }
}


