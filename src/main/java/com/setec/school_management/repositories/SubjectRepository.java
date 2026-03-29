package com.setec.school_management.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.setec.school_management.models.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    List<Subject> findBySubjectNameContainingIgnoreCase(String subjectname);
    boolean existsBySubjectName(String subjectName);
    boolean existsBySubjectNameAndIdNot(String subjectName, Long id);
   
}
