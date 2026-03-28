package com.setec.school_management.repositories;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.setec.school_management.models.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findStudentById(Long id);


    List<Student> findByFirstnameContainingIgnoreCaseOrLastnameContainingIgnoreCase(String firstname, String lastname);

    

    

    // List<Student> findByNameContainingIgnoreCase(String firsname);

    // List<Student> findbyNameContainingIgnoreCase(String name); 

}
