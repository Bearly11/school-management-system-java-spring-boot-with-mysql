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

import com.setec.school_management.models.Subject;
import com.setec.school_management.services.SubjectService;



@RestController
@RequestMapping("api/v1/subject/")
public class SubjectController {
    private final SubjectService _subjectService;
    public SubjectController(SubjectService subjectService) {
        this._subjectService = subjectService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAllSubjects() {
        var subjects = _subjectService.getAllSubjects();
        return ResponseEntity.ok(subjects);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        var subject = _subjectService.getById(id);
        return ResponseEntity.ok(subject);
    }


    @PostMapping("")
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject) {
        var subjects = _subjectService.createSubject(subject);
        return ResponseEntity.ok(subjects);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateSubject(@PathVariable Long id,
            @RequestBody Subject subject) {
        return ResponseEntity.ok(_subjectService.updateSubject(id, subject));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long id) {
         _subjectService.deleteSubject(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("search")
    public ResponseEntity<List<Subject>> findByName(@RequestParam String subjectname) {
        var subjects = _subjectService.findByName(subjectname);
        return ResponseEntity.ok(subjects);
    }

}
