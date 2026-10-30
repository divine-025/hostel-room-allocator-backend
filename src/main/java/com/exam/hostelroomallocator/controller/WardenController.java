package com.exam.hostelroomallocator.controller;

import com.exam.hostelroomallocator.domain.Warden;
import com.exam.hostelroomallocator.service.WardenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController @RequestMapping("/api/wardens")
public class WardenController {

    private final WardenService service;

    public WardenController(WardenService service) {
        this.service = service;
    }
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Warden warden) {
        service.save(warden);
        return ResponseEntity.status(HttpStatus.CREATED).body("Warden saved successfully");
    }
    @GetMapping("/all")
    public ResponseEntity<List<Warden>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        Optional<Warden> warden = service.findById(id);
        if (warden.isPresent()) {
            return ResponseEntity.ok(warden.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The warden with that id does not exist");
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody Warden warden) {
        Optional<Warden> updated = service.update(id, warden);
        if (updated.isPresent()) {
            return ResponseEntity.ok(updated.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The warden with that id does not exist");
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        if (service.delete(id)) {
            return ResponseEntity.ok("warden deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The warden with that id does not exist");
        }
    }
    //C1
    @GetMapping("/by-duty-area")
    public ResponseEntity<List<Warden>> findByDutyArea(@RequestParam String name) {
        return ResponseEntity.ok(service.findByDutyAreaName(name));
    }
    //D3
    @PutMapping("/{wardenId}/duty-areas/{dutyAreaId}")
    public ResponseEntity<?> addDutyArea(@PathVariable UUID wardenId, @PathVariable UUID dutyAreaId) {
        String message = service.addDutyArea(wardenId, dutyAreaId);
        if (message.equals("Duty area added to the warden")) {
            return ResponseEntity.ok(message);
        } else if (message.equals("The warden already has that dyty area")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    // D4
    @PatchMapping("/{wardenId}/hostel-block")
    public ResponseEntity<?> changeHostelBlock(@PathVariable UUID wardenId, @RequestParam int blockNumber) {
    String message = service.changeHostelBlock(wardenId, blockNumber);
    if (message.equals("Hostel block changed successfully")) {
        return ResponseEntity.ok(message);
    } else if (message.equals("That hostel block is taken b another warden")) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
    }
}



