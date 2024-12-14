package com.workintech.spring17challenge.exceptions;

import org.springframework.http.HttpStatus;

public class CourseAlreadyExistsException extends ApiException {
    public CourseAlreadyExistsException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }
}