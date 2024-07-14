package com.studentmanager.studentmanger.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

public class StudentDTO {

    private Long id;
    @NotBlank(message = "firstName can not be blank")
    private String firstName;
    private String lastName;
    @NotBlank(message = "phone is required")
    private String phone;

    @NotBlank(message = "Enter a course plaese")
    private String course;
    @NotBlank(message = "upload your photo please")
    private String imageUrl;
    private String studentCode;

    public StudentDTO(){}

    public StudentDTO(String firstName, String lastName, String phone, String course, String imageUrl, String studentCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.course = course;
        this.imageUrl = imageUrl;
        this.studentCode = studentCode;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }
}
