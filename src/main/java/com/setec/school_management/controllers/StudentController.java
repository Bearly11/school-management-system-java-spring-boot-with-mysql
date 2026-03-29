package com.setec.school_management.controllers;

import java.util.List;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.setec.school_management.dtos.student_dto.StudentRequestDto;
import com.setec.school_management.dtos.student_dto.StudentResponseDto;

import com.setec.school_management.services.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/student/")
public class StudentController {

    private final StudentService _studentService;

    public StudentController(StudentService studentService) {
        this._studentService = studentService;
    }

    @GetMapping("")
    public ResponseEntity<List<StudentResponseDto>> getAllStudents() {
        var students = _studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("{id}")
    public ResponseEntity<StudentResponseDto> getById(@PathVariable Long id) {
        var student = _studentService.getById(id);
        return ResponseEntity.ok(student);
    }

    @PostMapping("")
    public ResponseEntity<StudentResponseDto> createStudent(@Valid @RequestBody StudentRequestDto student) {
        var students = _studentService.createStudent(student);
        return ResponseEntity.status(201).body(students);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDto> updateStudent(@PathVariable Long id,
            @Valid @RequestBody StudentRequestDto studentDetails) {
        return ResponseEntity.ok(_studentService.updateStudent(id, studentDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        _studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    // @GetMapping("search")
    // public ResponseEntity<?> findByName(@RequestParam String name) {
    // var students = _studentService.findByName(name);
    // return ResponseEntity.ok(students);
    // }

    @GetMapping("search")
    public ResponseEntity<List<StudentResponseDto>> search(@RequestParam String name) {
        return ResponseEntity.ok(_studentService.findByName(name));
    }

    @GetMapping("paginated")
    public ResponseEntity<List<StudentResponseDto>> getPaginatedStudents(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        // Assuming you have a method in your service to fetch paginated students
        var students = _studentService.getPaginatedStudents(page, size);
        return ResponseEntity.ok(students);
    }

}
