package com.workintech.spring17challenge.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidCreditException extends ApiException {

    public InvalidCreditException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }
}
