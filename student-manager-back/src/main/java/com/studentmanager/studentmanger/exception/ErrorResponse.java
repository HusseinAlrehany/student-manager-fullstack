package com.studentmanager.studentmanger.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorResponse {

    private String message;
    private HttpStatus http;
    private LocalDateTime timeStamp;

    public ErrorResponse(){}

    public ErrorResponse(String message, HttpStatus http, LocalDateTime timeStamp) {
        this.message = message;
        this.http = http;
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttp() {
        return http;
    }

    public void setHttp(HttpStatus http) {
        this.http = http;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
