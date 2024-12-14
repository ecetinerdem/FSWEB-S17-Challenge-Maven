package com.workintech.spring17challenge.exceptions;

import org.springframework.http.HttpStatus;

public class CourseNotFoundException extends ApiException {

    public CourseNotFoundException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }
}