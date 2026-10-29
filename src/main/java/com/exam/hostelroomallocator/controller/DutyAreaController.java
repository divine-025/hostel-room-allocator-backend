package com.exam.hostelroomallocator.controller;

import com.exam.hostelroomallocator.domain.DutyArea;
import com.exam.hostelroomallocator.service.DutyAreaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController 
@RequestMapping("/api/duty-areas")
public class DutyAreaController {
    private final DutyAreaService service;

    public DutyAreaController(DutyAreaService service) {
        this.service = service;
    }
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody DutyArea dutyArea) {
        service.save(dutyArea);
        return ResponseEntity.status(HttpStatus.CREATED).body("Duty area saved successfully");
    }
    @GetMapping("/all")
    public ResponseEntity<List<DutyArea>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        Optional<DutyArea> dutyArea = service.findById(id);
        if (dutyArea.isPresent()) {
            return ResponseEntity.ok(dutyArea.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The duty area with that id does not exist");
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody DutyArea dutyArea) {
        Optional<DutyArea> updated = service.update(id, dutyArea); 
        if (updated.isPresent()) {
            return ResponseEntity.ok(updated.get());        
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The dusty area with that id does not exist");
        }
    }
    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<?> delete(@PathVariable UUID id) {
        if (service.delete(id)) {
            return ResponseEntity.ok("Duty are deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("the duty area with that id does not exist");
        }
    }
  
}
