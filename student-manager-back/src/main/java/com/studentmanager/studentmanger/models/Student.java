package com.studentmanager.studentmanger.models;

import jakarta.persistence.*;

import java.io.Serializable;
@Entity
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( nullable = false, updatable = false)
    private Long id;


    private String firstName;

    private String lastName;

    private String phone;

    private String course;

    private String imageUrl;
    @Column(nullable = false, updatable = false)
    private String studentCode;

    public Student(){}

    public Student(String firstName, String lastName, String phone, String course, String imageUrl, String studentCode) {
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

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", course='" + course + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", studentCode='" + studentCode + '\'' +
                '}';
    }
}
