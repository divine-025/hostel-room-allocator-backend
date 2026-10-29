package com.exam.hostelroomallocator.service;

import com.exam.hostelroomallocator.domain.Student;
import com.exam.hostelroomallocator.repository.StudentRepository;
import com.exam.hostelroomallocator.repository.WardenRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service 
public class StudentService {

    private final StudentRepository repository;
    private final WardenRepository wardenRepository;

    //Constructor Injection
    public StudentService(StudentRepository repository, WardenRepository wardenRepository) {
        this.repository = repository;
        this.wardenRepository = wardenRepository;

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


//B1
public List<Student> findByLastName(String lastName) {
    return repository.findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
}

//C4 for empty optional; which means the warden does not exist
public Optional<List<Student>> findStudentsOfWarden(UUID wardenId) {
    if (!wardenRepository.existsById(wardenId)) {
        return Optional.empty();
    }
    return Optional.of(repository.findStudentsOfWarden(wardenId));
}
}

