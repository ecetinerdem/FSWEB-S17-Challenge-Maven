package com.workintech.spring17challenge.exceptions;



import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;



@Getter
public class ApiException extends RuntimeException {
    private ApiErrorResponse error;
    // Getter for HttpStatus
    @Getter
    @Setter
    private HttpStatus httpStatus; // Add a field for HttpStatus

    // Constructor that accepts a message and HttpStatus
    public ApiException(HttpStatus httpStatus, String message) {
        super(message); // Call the superclass constructor with the message
        this.httpStatus = httpStatus; // Set the HttpStatus
        this.error = new ApiErrorResponse(httpStatus.value(), message, LocalDateTime.now());
    }

}

