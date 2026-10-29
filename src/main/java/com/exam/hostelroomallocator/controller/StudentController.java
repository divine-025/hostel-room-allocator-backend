package com.exam.hostelroomallocator.controller;

import com.exam.hostelroomallocator.domain.Student;
import com.exam.hostelroomallocator.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController 
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
     }

     @PostMapping("/save")
     public ResponseEntity<?> save(@RequestBody Student student) {
        service.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body("Student save successfully");
     }

     @GetMapping("/all")
     public ResponseEntity<List<Student>> findAll() {
        return ResponseEntity.ok(service.findAll());
     }

     @GetMapping("/{id}")
     public ResponseEntity<?> findById(@PathVariable UUID id) {
        Optional<Student> student = service.findById(id);
        if (student.isPresent()) {
            return ResponseEntity.ok(student.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The student with that id does not exist");
        }
     }
     @PutMapping("/update/{id}")
     public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody Student student) {
        Optional<Student> updated = service.update(id, student);
        if (updated.isPresent()) {
            return ResponseEntity.ok(updated.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The student with that id does not exist");
        }
     }

     @DeleteMapping("/delete/{id}")
     public ResponseEntity<?> delete(@PathVariable UUID id) {
        if (service.delete(id)) {
            return ResponseEntity.ok("Student delete successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The studente with that id does not exist");
        }
     }
     // B1
     @GetMapping("/by-last-name")
     public ResponseEntity<List<Student>> findByLastName(@RequestParam String lastName) {
        return ResponseEntity.ok(service.findByLastName(lastName));
     }
}


