package com.setec.school_management.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.setec.school_management.models.Subject;
import com.setec.school_management.repositories.SubjectRepository;

@Service
public class SubjectService {
    @Autowired
    private final SubjectRepository _subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this._subjectRepository = subjectRepository;
    }

    public List<Subject> getAllSubjects() {
        return _subjectRepository.findAll();
    }

    public Subject getById(Long id) {
        var subject = _subjectRepository.findById(id).orElse(null);
        if (subject == null) {
            throw new RuntimeException("Subject not found with id " + id);
        }
        return subject;
    }

    @Transactional
    public Subject createSubject(Subject subject) {
        var existingSubject = _subjectRepository.findAll().stream()
                .filter(s -> s.getSubjectName().equalsIgnoreCase(subject.getSubjectName()))
                .findFirst()
                .orElse(null);
        if (existingSubject != null) {
            throw new RuntimeException("Subject with name " + subject.getSubjectName() + " already exists");
        }
        return _subjectRepository.save(subject);

    }

    public Subject updateSubject(Long id, Subject subject) {
        return _subjectRepository.findById(id).map(Subject -> {
            Subject.setSubjectName(subject.getSubjectName());
            var existingSubject = _subjectRepository.findAll().stream()
                    .filter(s -> s.getSubjectName().equalsIgnoreCase(subject.getSubjectName()))
                    .findFirst()
                    .orElse(null);
            if (existingSubject != null) {
                throw new RuntimeException("Subject with name " + subject.getSubjectName() + " already exists");
            }
            return _subjectRepository.save(Subject);
        }).orElseThrow(() -> new RuntimeException("Subject not found with id " + id));
    }

    public void deleteSubject(Long id) {
        if (!_subjectRepository.existsById(id)) {
            throw new RuntimeException("Subject not found with id " + id);
        }
        _subjectRepository.deleteById(id); 
         
    }

    public List<Subject> findByName(String subjectname) {
        return _subjectRepository.findBySubjectNameContainingIgnoreCase(subjectname);
    }

    

}
