package com.namyxc.locations;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.net.URI;
import java.util.UUID;

@ControllerAdvice
public class LocationsExceptionhandler {


    @ExceptionHandler(LocationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ProblemDetail handleLocationNotFoundException(LocationNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
                e.getMessage());
        problemDetail.setType(URI.create("locations/not-found"));
        problemDetail.setTitle("Location not found");
        problemDetail.setProperty("id", UUID.randomUUID().toString());
        return problemDetail;
    }
}
