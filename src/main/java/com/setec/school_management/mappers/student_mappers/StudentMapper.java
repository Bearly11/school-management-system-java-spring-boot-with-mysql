package com.setec.school_management.mappers.student_mappers;

import org.mapstruct.Mapper;
import com.setec.school_management.models.Student;
import com.setec.school_management.dtos.student_dto.StudentRequestDto;
import com.setec.school_management.dtos.student_dto.StudentResponseDto;

@Mapper(componentModel="Spring")
public interface StudentMapper {
    StudentResponseDto toDto(Student student);

    Student toEntity(StudentRequestDto dto);


    
} 
    

