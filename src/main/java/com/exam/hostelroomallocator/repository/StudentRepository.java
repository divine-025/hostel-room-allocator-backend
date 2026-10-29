package com.exam.hostelroomallocator.repository;

import com.exam.hostelroomallocator.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {
    
}
