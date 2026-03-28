package com.setec.school_management.dtos.student_dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

public class StudentRequestDto {

    @NotBlank(message = "Frist name are required")
    private String firstName;
    @NotBlank(message = "Last name are required")
    private String lastName;
    @NotBlank(message = "Gender are required")
    private String gender;
    private LocalDate dob;
    private String address;

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
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }


    
}
