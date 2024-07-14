package com.studentmanager.studentmanger.service;

import com.studentmanager.studentmanger.dtos.StudentDTO;
import com.studentmanager.studentmanger.models.Student;

import java.util.List;

public interface StudentService {

    List<StudentDTO> findAll();

    StudentDTO findById(Long studentId);

    StudentDTO saveStudent(StudentDTO studentDTO);

    StudentDTO updateStudent(StudentDTO studentDTO);

    void deleteStudentById(Long studentId);


    List<StudentDTO> saveAll(List<StudentDTO> studentDTOS);
}
