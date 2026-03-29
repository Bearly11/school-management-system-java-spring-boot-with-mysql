package com.setec.school_management.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.setec.school_management.dtos.student_dto.StudentRequestDto;
import com.setec.school_management.dtos.student_dto.StudentResponseDto;
import com.setec.school_management.exceptions.exceptions.BadRequestException;
import com.setec.school_management.exceptions.exceptions.MyResourceNotFoundException;
import com.setec.school_management.mappers.student_mappers.StudentMapper;
import com.setec.school_management.repositories.StudentRepository;

@Service
public class StudentService {
   
    private StudentMapper _studentMapper;
    private final StudentRepository _studentRepository;
    public StudentService(StudentRepository studentRepository,StudentMapper studentMapper) {
        this._studentRepository = studentRepository;
        _studentMapper = studentMapper;
    }

    public List<StudentResponseDto> getAllStudents() {
        var students = this._studentRepository.findAll()
                    .stream().map(_studentMapper::toDto)
                    .toList();
        return students;
    }

    public StudentResponseDto createStudent(StudentRequestDto dto) {        
        var student = _studentMapper.toEntity(dto);
        var saved = _studentRepository.save(student);

        return _studentMapper.toDto(saved);

    }

    

    public StudentResponseDto updateStudent(Long id, StudentRequestDto studentDetails) {
       var student = _studentRepository.findById(id).orElseThrow(()->
                new MyResourceNotFoundException("Student not found"));
                student.setFirstname(studentDetails.getFirstname());
                student.setLastname(studentDetails.getLastname());
                student.setGender(studentDetails.getGender());
                student.setDob(studentDetails.getDob());
                student.setAddress(studentDetails.getAddress());
                

                return _studentMapper.toDto(_studentRepository.save(student));

    }

    public void deleteStudent(Long id) {
       if (!_studentRepository.existsById(id)) {
            throw new MyResourceNotFoundException("Student not found with id " + id);
        }
        _studentRepository.deleteById(id);
    
    }

    public StudentResponseDto getById(Long id) {
        var student = _studentRepository.findById(id).orElseThrow(()->
                new MyResourceNotFoundException("Student not found"));
        
        return _studentMapper.toDto(student);
        
    }

    public List<StudentResponseDto> findByName(String name) {
        var students = _studentRepository.findByFirstnameContainingIgnoreCaseOrLastnameContainingIgnoreCase(name, name)
                            .stream().map(_studentMapper::toDto).toList();
        return students;
    }

    public List<StudentResponseDto> getPaginatedStudents(int page, int size) {
        var pageble= PageRequest.of(page, size);
        var students = _studentRepository.findAll(pageble).getContent()
        .stream().map(_studentMapper::toDto).toList();
        return students;
    }

    

    // public List<Student> findByName(String name) {
    //     var students = _studentRepository.findByNameContainingIgnoreCase(name);
    //     return students;
    // }


   

}
