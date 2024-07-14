package com.studentmanager.studentmanger.successresponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class SuccessResponseWithEntity {

    public static ResponseEntity<Object> successBuilder(
            String message,
            HttpStatus httpStatus,
            Object entity
    ){

        Map<String, Object> responseBuilder = new HashMap<>();
           responseBuilder.put("message", message);
           responseBuilder.put("httpStatus", httpStatus);
           responseBuilder.put("entity", entity);

           return new ResponseEntity<>(responseBuilder, httpStatus);

    }
}
