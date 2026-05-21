package com.setec.school_management.mappers.subject_mappers;

import com.setec.school_management.dtos.subject_dto.SubjectResponseDto;
import org.mapstruct.Mapper;

import com.setec.school_management.dtos.subject_dto.SubjectRequestDto;
import com.setec.school_management.models.Subject;

@Mapper(componentModel = "spring")
public interface SubjectMapper {
    SubjectResponseDto toDto(Subject subject);
    Subject toEntity(SubjectRequestDto dto);
    
}
