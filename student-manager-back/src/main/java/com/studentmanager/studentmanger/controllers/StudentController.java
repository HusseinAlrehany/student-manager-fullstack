package com.studentmanager.studentmanger.controllers;

import com.studentmanager.studentmanger.bindingresultmap.ErrorResponseService;
import com.studentmanager.studentmanger.dtos.StudentDTO;
import com.studentmanager.studentmanger.service.StudentService;
import com.studentmanager.studentmanger.successresponse.SuccessResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {
   @Autowired
    private StudentService studentService;

   @Autowired
   private ErrorResponseService errorResponseService;

    @GetMapping("/all")
    public ResponseEntity<List<StudentDTO>> findAll(){
        List<StudentDTO> studentDTOList = studentService.findAll();

        return new ResponseEntity<>(studentDTOList, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> saveStudent(@Valid @RequestBody StudentDTO studentDTO, BindingResult bindingResult){

        /*if(bindingResult.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            for(FieldError fieldError: bindingResult.getFieldErrors()){
                String fieldName = fieldError.getField();
                String errorMessage = fieldError.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            }
            return ResponseEntity.badRequest().body(errors);
        }*/

        // the previous block of code can be replaced by the following
        //to be reusable for all controllers
        if(bindingResult.hasErrors()){
            Map<String, String> errors = errorResponseService.generateErrorMap(bindingResult);
            return ResponseEntity.badRequest().body(errors);
        }

        StudentDTO dbStudent = studentService.saveStudent(studentDTO);

        return ResponseEntity.ok(new SuccessResponse("Student Saved Successfully!"));

    }

    @PostMapping("/addAll")
    public ResponseEntity<List<StudentDTO>> saveAll(@RequestBody List<StudentDTO> studentDTOS){

         List<StudentDTO> theStudentDTOS= studentService.saveAll(studentDTOS);

         return new ResponseEntity<>(theStudentDTOS, HttpStatus.CREATED);
    }

    @GetMapping("/find/{studentId}")
    public ResponseEntity<StudentDTO> findById(@PathVariable ("studentId") Long studentId){
        StudentDTO studentDTO = studentService.findById(studentId);
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateStudent( @Valid @RequestBody StudentDTO studentDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
             Map<String, String> errors= errorResponseService.generateErrorMap(bindingResult);
             return ResponseEntity.badRequest().body(errors);
        }
        StudentDTO studentdb = studentService.saveStudent(studentDTO);

        return ResponseEntity.ok(new SuccessResponse("Student Updated Successfully"));
    }
    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity<?> deleteStudentById(@PathVariable("studentId") Long studentId){
        studentService.deleteStudentById(studentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
