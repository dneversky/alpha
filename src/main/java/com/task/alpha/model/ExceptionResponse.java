package com.task.alpha.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ExceptionResponse {

    private HttpStatus status;
    private int code;
    private String message;
    private String description;
}
