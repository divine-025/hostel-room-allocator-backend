package com.exam.hostelroomallocator.controller;

import com.exam.hostelroomallocator.domain.MaintenanceRequest;
import com.exam.hostelroomallocator.service.MaintenanceRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController 
@RequestMapping("/api/maintenance-requests")
public class MaintenanceRequestController {

    private final MaintenanceRequestService service;

    public MaintenanceRequestController(MaintenanceRequestService service) {
        this.service = service;
    }

    //E1
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestParam UUID roomAllocationId, @RequestBody MaintenanceRequest maintenanceRequest) {
        String message = service.save(roomAllocationId, maintenanceRequest);
        if (message.equals("Maintenance request saved successfully")) {
            return ResponseEntity.status(HttpStatus.CREATED).body(message);
        } else if (message.equals("The room allocation with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<MaintenanceRequest>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    //E2
    @GetMapping("/student/{regNumber}")
    public ResponseEntity<List<MaintenanceRequest>> findHistory(@PathVariable String regNumber) {
        return ResponseEntity.ok(service.findHistoryByRegNumber(regNumber)); 
    }

    //E3
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        if (service.delete(id)) {
            return ResponseEntity.ok("Maintenance request deleted successfully");   
             } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The maintenance request with that id does not exist");
             }
    }
}

