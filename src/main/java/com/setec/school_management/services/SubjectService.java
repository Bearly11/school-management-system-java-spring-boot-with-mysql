package com.setec.school_management.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.setec.school_management.dtos.subject_dto.SubjectReponseDto;
import com.setec.school_management.dtos.subject_dto.SubjectRequestDto;
import com.setec.school_management.exceptions.exceptions.BadRequestException;
import com.setec.school_management.exceptions.exceptions.MyResourceNotFoundException;
import com.setec.school_management.mappers.subject_mappers.SubjectMapper;
import com.setec.school_management.models.Subject;
import com.setec.school_management.repositories.SubjectRepository;


@Service
public class SubjectService {
   
    private final SubjectRepository _subjectRepository;
    private final SubjectMapper _subjectMapper;

    public SubjectService(SubjectRepository subjectRepository, SubjectMapper subjectMapper) {
        this._subjectRepository = subjectRepository;
        this._subjectMapper = subjectMapper;
    }

    public List<SubjectReponseDto> getAllSubjects() {
        return _subjectRepository.findAll().stream()
                .map(_subjectMapper::toDto)
                .toList();
    }

    public SubjectReponseDto getById(Long id) {
        var subject = _subjectRepository.findById(id).
                orElseThrow(() -> new MyResourceNotFoundException("Subject not found with id " + id));
        return _subjectMapper.toDto(subject);
        
    }

    
    public SubjectReponseDto createSubject(SubjectRequestDto dto) {
        if(_subjectRepository.existsBySubjectName(dto.getSubjectName())) {
            throw new BadRequestException("Subject name already exists");
        }
        var subject = _subjectMapper.toEntity(dto);
        return _subjectMapper.toDto(_subjectRepository.save(subject));

    }

    public SubjectReponseDto updateSubject(Long id,SubjectRequestDto dto) {
        var subject = _subjectRepository.findById(id)
                .orElseThrow(() -> new MyResourceNotFoundException("Subject not found with id " + id));
        if(_subjectRepository.existsBySubjectNameAndIdNot(dto.getSubjectName(), id)) {
            throw new BadRequestException("Subject name already exists");
        }
        subject.setSubjectName(dto.getSubjectName());
        return _subjectMapper.toDto(_subjectRepository.save(subject));
       
    }

    public void deleteSubject(Long id) {
        if (!_subjectRepository.existsById(id)) {
            throw new MyResourceNotFoundException("Subject not found with id " + id);
        }
        _subjectRepository.deleteById(id); 
         
    }

    public List<SubjectReponseDto> findByName(String subjectname) {
        return _subjectRepository.findBySubjectNameContainingIgnoreCase(subjectname).stream()
                .map(_subjectMapper::toDto)
                .toList();
    }

    

}
