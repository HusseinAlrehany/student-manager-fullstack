package com.studentmanager.studentmanger.service;
import com.studentmanager.studentmanger.dtos.StudentDTO;
import com.studentmanager.studentmanger.models.Student;
import com.studentmanager.studentmanger.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class StudentServiceImpl implements StudentService{


    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<StudentDTO> findAll() {
         List<Student> students= studentRepository.findAll();
        if(students.isEmpty()){
            throw new RuntimeException("No Student Found");
        }

        return modelMapper.map(students, new TypeToken<List<StudentDTO>>(){}.getType());
    }

    @Override
    public StudentDTO findById(Long studentId) {
        Student theStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student Not Found"));

        return modelMapper.map(theStudent, StudentDTO.class);
    }

    @Override
    public StudentDTO saveStudent(StudentDTO studentDTO) {
        Student theStudent = modelMapper.map(studentDTO, Student.class);
         theStudent.setStudentCode(UUID.randomUUID().toString());
         studentRepository.save(theStudent);

        return modelMapper.map(theStudent, StudentDTO.class);

    }

    @Override
    public StudentDTO updateStudent(StudentDTO studentDTO) {
        Student theStudent = modelMapper.map(studentDTO, Student.class);


        return modelMapper.map(studentRepository.save(theStudent), StudentDTO.class);
    }

    @Override
    public void deleteStudentById(Long studentId) {
          studentRepository.deleteById(studentId);
    }

    @Override
    public List<StudentDTO> saveAll(List<StudentDTO> studentDTOS) {
        List<Student> students = modelMapper.map(
                studentDTOS, new TypeToken<List<Student>>(){}.getType()
        );

        for(Student temp: students){
            temp.setStudentCode(UUID.randomUUID().toString());
        }

       students = studentRepository.saveAll(students);

        return modelMapper.map(students, new TypeToken<List<StudentDTO>>(){}.getType());
    }
}
