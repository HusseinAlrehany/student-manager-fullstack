package com.studentmanager.studentmanger.exception;

import com.studentmanager.studentmanger.bindingresultmap.ErrorResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private ErrorResponseService errorResponseService;

    //FOR GENERAL EXCEPTION HANDLING
   @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exceptionHandlingGlobally(Exception ex, WebRequest request){

       return new ResponseEntity<Object>(new ErrorResponse(ex.getMessage(),HttpStatus.BAD_REQUEST, LocalDateTime.now()), HttpStatus.BAD_REQUEST);
   }

  //for NotFoundException
    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> notFoundExceptionHandling(NotFoundException ex, WebRequest request){

       return new ResponseEntity<Object>(new ErrorResponse(ex.getMessage(),HttpStatus.NOT_FOUND, LocalDateTime.now()), HttpStatus.NOT_FOUND);

    }

    //For Fields Validation
  /* @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> onMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request){
           FieldsErrorValidation fieldsErrorValidation =  new FieldsErrorValidation();
           for(FieldError fieldError: ex.getBindingResult().getFieldErrors()){
                   fieldsErrorValidation.getViolations()
                           .add(new Violation(fieldError.getField(), fieldError.getDefaultMessage()));

           }
       return ResponseEntity.badRequest().body(fieldsErrorValidation);
    }*/

    // the previous block of code can be replaced by the following
    //to be reusable for all controllers
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request){

        Map<String,String> errors = errorResponseService .generateErrorMap(ex.getBindingResult());
        return ResponseEntity.badRequest().body(errors);



    }


    //for handling requests that don't have endpoints
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return new ResponseEntity<Object>(new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }



}
