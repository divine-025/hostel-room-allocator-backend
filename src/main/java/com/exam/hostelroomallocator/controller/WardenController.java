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
}



