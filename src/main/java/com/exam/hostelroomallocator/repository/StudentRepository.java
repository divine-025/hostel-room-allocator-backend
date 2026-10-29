package com.exam.hostelroomallocator.repository;

import com.exam.hostelroomallocator.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.UUID;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, UUID> {

    //B1
    List<Student> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);
    
    //C4
    @Query("SELECT DISTINCT r.student FROM RoomAllocation r WHERE r.warden.id = :wardenId")
    List<Student> findStudentsOfWarden(@Param("wardenId") UUID wardenId);
}
