package com.exam.hostelroomallocator.service;

import com.exam.hostelroomallocator.domain.Student;
import com.exam.hostelroomallocator.repository.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service 
public class StudentService {

    private final StudentRepository repository;

    //Constructor Injection
    public StudentService(StudentRepository repository) {
        this.repository = repository;

    }
    public Student save(Student student) { 
        return repository.save(student);

    }
    public List<Student> findAll() {
        return repository.findAll();
    }
    public Optional<Student> findById(UUID id) {
        return repository.findById(id);
    }
    public Optional<Student> update(UUID id, Student student) {
        if (repository.existsById(id)) {
            student.setId(id); // this ensures that the ID matches the one inURL
            return Optional.of(repository.save(student));
        }
        return Optional.empty(); // this will return empty if the ID does not exist
    }

    public boolean delete(UUID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

}


