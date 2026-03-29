package com.setec.school_management.services;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.setec.school_management.dtos.teacher_dto.TeacherReponseDto;
import com.setec.school_management.dtos.teacher_dto.TeacherRequestDto;
import com.setec.school_management.mappers.teacher_mappers.TeacherMapper;
import com.setec.school_management.exceptions.exceptions.MyResourceNotFoundException;
import com.setec.school_management.models.Teacher;
import com.setec.school_management.repositories.TeacherRepository;

@Service
public class TeacherService {
    private final TeacherMapper _teacherMapper;
   
    private final TeacherRepository _teacherRepository;
    public TeacherService(TeacherRepository teacherRepository, TeacherMapper teacherMapper) {
        this._teacherRepository = teacherRepository;
        this._teacherMapper = teacherMapper;
    }
    public List<TeacherReponseDto> getAllTeachers() {
        var teachers = this._teacherRepository.findAll()
                .stream()
                .map(_teacherMapper::toDto)
                .toList();
        return teachers;
    }
    
    public TeacherReponseDto getById(Long id) {
       var teacher = _teacherRepository.findById(id)
                .orElseThrow(()-> new MyResourceNotFoundException("Teacher not found with id " + id));
        
        return _teacherMapper.toDto(teacher);
    }
    public TeacherReponseDto createTeacher(TeacherRequestDto dto) {
       
        var teacherEntity = _teacherMapper.toEntity(dto);         
        var savedTeacher = _teacherRepository.save(teacherEntity);
        return _teacherMapper.toDto(savedTeacher);
    }
    public TeacherReponseDto updateTeacher(Long id, TeacherRequestDto teacher) {
        var existingTeacher = _teacherRepository.findById(id)
                .orElseThrow(() -> new MyResourceNotFoundException("Teacher not found with id " + id));
        existingTeacher.setFirstName(teacher.getFirstName());
        existingTeacher.setLastName(teacher.getLastName());
        existingTeacher.setGender(teacher.getGender());
        existingTeacher.setDob(teacher.getDob());
        existingTeacher.setAddress(teacher.getAddress());
        var updatedTeacher = _teacherRepository.save(existingTeacher);
        return _teacherMapper.toDto(updatedTeacher);
    }
    public void deleteTeacher(Long id) {
         if (!_teacherRepository.existsById(id)) {
                throw new MyResourceNotFoundException("Teacher not found with id " + id);
          }
          _teacherRepository.deleteById(id);
    }
    public List<TeacherReponseDto> findByName(String name) {
        var teachers = _teacherRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name)
                .stream()
                .map(_teacherMapper::toDto)
                .toList();
        return teachers;
    }
    public List<TeacherReponseDto> getPaginatedTeachers(int page, int size) {
        var pageAble = PageRequest.of(page, size);
        var teachers = _teacherRepository.findAll().stream().map(_teacherMapper::toDto).toList(); 
        return teachers;
        
    }

}
