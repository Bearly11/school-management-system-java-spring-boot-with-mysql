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

import com.setec.school_management.services.TeacherService;

import jakarta.validation.Valid;

import com.setec.school_management.dtos.teacher_dto.TeacherReponseDto;
import com.setec.school_management.dtos.teacher_dto.TeacherRequestDto;

@RestController
@RequestMapping("api/v1/teacher/")
public class TeacherController {

    private final TeacherService _teacherService;
    public TeacherController(TeacherService teacherService) {
        this._teacherService = teacherService;
    }

    @GetMapping("")
    public ResponseEntity<List<TeacherReponseDto>> getAllTeachers() {
       var teachers = _teacherService.getAllTeachers();
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("{id}")
    public ResponseEntity<TeacherReponseDto> getById(@PathVariable Long id) {
       var teacher = _teacherService.getById(id);
        return ResponseEntity.ok(teacher);
    }

    @PostMapping("")
    public ResponseEntity<TeacherReponseDto> createTeacher(@Valid @RequestBody TeacherRequestDto teacher) {
      
        var teachers = _teacherService.createTeacher(teacher);
       
        return ResponseEntity.status(201).body(teachers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherReponseDto> updateTeacher(@PathVariable Long id,
             @Valid @RequestBody TeacherRequestDto teacher) {
        return ResponseEntity.ok(_teacherService.updateTeacher(id, teacher));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
         _teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("search")
    public ResponseEntity<List<TeacherReponseDto>> findByName(@RequestParam String name) {
        var teachers = _teacherService.findByName(name);
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("paginated")
    public ResponseEntity<List<TeacherReponseDto>> getPaginatedTeachers(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        // Assuming you have a method in your service to fetch paginated students
        var teachers = _teacherService.getPaginatedTeachers(page, size);
        return ResponseEntity.ok(teachers);
    }
}
