package com.setec.school_management.dtos.student_dto;

import java.time.LocalDate;

public class StudentResponseDto {

    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate dob;

    public String getLastname() {
        return lastName;
    }
    public void setLastname(String lastName) {
        this.lastName = lastName;
    }
    public String getFirstname() {
        return firstName;
    }
    public void setFirstname(String firstName) {
        this.firstName = firstName;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public LocalDate getDob() {
        return dob;
    }
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
    
}
