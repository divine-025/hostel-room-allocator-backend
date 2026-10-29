package com.exam.hostelroomallocator.controller;

import com.exam.hostelroomallocator.domain.HostelBlock;
import com.exam.hostelroomallocator.service.HostelBlockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController 
@RequestMapping("/api/hostel-blocks")
public class HostelBlockController {

    private final HostelBlockService service;

    public HostelBlockController(HostelBlockService service) {
        this.service = service;
    }
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody HostelBlock  hostelBlock) {
        service.save(hostelBlock);
        return ResponseEntity.status(HttpStatus.CREATED).body("Hostel block saved successfully");
    }
    @GetMapping("/all")
    public ResponseEntity<List<HostelBlock>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
    @GetMapping("/{id}") 
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        Optional<HostelBlock> hostelBlock = service.findById(id);
        if (hostelBlock.isPresent()) {
            return ResponseEntity.ok(hostelBlock.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("the hostel block with that id does not exist");
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody HostelBlock hostelBlock) {
        Optional<HostelBlock> updated = service.update(id, hostelBlock);
        if (updated.isPresent()) {
            return ResponseEntity.ok(updated.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The hostel block with that id does not exist");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        if (service.delete(id)) {
            return ResponseEntity.ok("Hostel block deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The hostel block with that id does not exist");
        }
    }
    //C2
    @GetMapping("/free")
    public ResponseEntity<List<HostelBlock>> findFree() {
        return ResponseEntity.ok(service.findFreeBlocks());
    }
}
