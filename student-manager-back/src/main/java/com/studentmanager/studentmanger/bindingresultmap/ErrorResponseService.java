package com.studentmanager.studentmanger.bindingresultmap;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

//to be reusable for all controllers binding result
//to avoid redundancy
@Service
public class ErrorResponseService {

   public Map<String, String> generateErrorMap(BindingResult result){
       Map<String, String> errors = new HashMap<>();
       for(FieldError fieldError: result.getFieldErrors()){
           errors.put(fieldError.getField(),  fieldError.getDefaultMessage());
       }

       return errors;

   }

}
