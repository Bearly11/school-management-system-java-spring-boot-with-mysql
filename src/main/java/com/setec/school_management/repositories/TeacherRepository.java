package com.setec.school_management.repositories;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;

import com.setec.school_management.models.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    List<Teacher> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName);
   
}
