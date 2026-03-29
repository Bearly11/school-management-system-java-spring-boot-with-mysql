package com.setec.school_management.mappers.teacher_mappers;

import org.mapstruct.Mapper;
import com.setec.school_management.dtos.teacher_dto.TeacherReponseDto;
import com.setec.school_management.dtos.teacher_dto.TeacherRequestDto;
import org.mapstruct.Mapping;

import com.setec.school_management.models.Teacher;

@Mapper (componentModel = "spring")
public interface TeacherMapper {

    
    TeacherReponseDto toDto(Teacher teacher);
    
    @Mapping(source = "firstName", target = "firstName")
    Teacher toEntity(TeacherRequestDto dto);

    
}
