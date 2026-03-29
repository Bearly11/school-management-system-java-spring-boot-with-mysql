package com.setec.school_management.dtos.subject_dto;

import jakarta.validation.constraints.NotBlank;


public class SubjectRequestDto {

    @NotBlank(message = "Subject name is required")
    private String subjectName;
    public String getSubjectName() {
        return subjectName;
    }
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
    
}
