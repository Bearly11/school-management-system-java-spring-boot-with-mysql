package com.setec.school_management.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.setec.school_management.models.Teacher;
import com.setec.school_management.repositories.TeacherRepository;

@Service
public class TeacherService {
    @Autowired
    private final TeacherRepository _teacherRepository;
    public TeacherService(TeacherRepository teacherRepository) {
        this._teacherRepository = teacherRepository;
    }
    public List<Teacher> getAllTeachers() {
        var teachers = this._teacherRepository.findAll();
        return teachers;
    }
    
    public Teacher getById(Long id) {
       var teacher = this._teacherRepository.findById(id).orElse(null);
       if (teacher == null) {
            throw new RuntimeException("Teacher not found with id " + id);
        }
        return teacher;
    }
    public Teacher createTeacher(Teacher teacher) {
        return this._teacherRepository.save(teacher);
    }
    public Teacher updateTeacher(Long id, Teacher teacher) {
         return _teacherRepository.findById(id).map(t -> {
                t.setFirstName(teacher.getFirstName());
                t.setLastName(teacher.getLastName());
                t.setGender(teacher.getGender());
                t.setDob(teacher.getDob());
                t.setAddress(teacher.getAddress());
                t.setSalary(teacher.getSalary());
                return _teacherRepository.save(t);
          }).orElseThrow(() -> new RuntimeException("Teacher not found with id " + id));
    }
    public void deleteTeacher(Long id) {
         if (!_teacherRepository.existsById(id)) {
                throw new RuntimeException("Teacher not found with id " + id);
          }
          _teacherRepository.deleteById(id);
    }
    public List<Teacher> findByName(String name) {
        var teachers = _teacherRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name);
        return teachers;
    }
    public List<Teacher> getPaginatedStudents(int page, int size) {
        var teachers = _teacherRepository.findAll(); // Replace with actual pagination logic
        return teachers;
        
    }

}
