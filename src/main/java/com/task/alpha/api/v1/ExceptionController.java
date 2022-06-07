package com.task.alpha.api.v1;

import com.task.alpha.exception.IncorrectCodeException;
import com.task.alpha.model.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.UnknownHostException;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(UnknownHostException.class)
    public ResponseEntity<ExceptionResponse> handleUnknownHostException(UnknownHostException e) {
        log.error(e.toString());
        ExceptionResponse response = new ExceptionResponse
                (HttpStatus.SERVICE_UNAVAILABLE, 503, "Service is not available.",
                        "Any of external services is not available. Your internet connection may have troubles as well. " +
                                "Check your network and wait for a little time, please.");
        return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(IncorrectCodeException.class)
    public ResponseEntity<ExceptionResponse> handleIncorrectCodeException(IncorrectCodeException e) {
        ExceptionResponse response = new ExceptionResponse
                (HttpStatus.BAD_REQUEST, 400, "Incorrect currency code parameter.",
                        e.getMessage() + " is not found in the list of available currency codes.");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ExceptionResponse> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        ExceptionResponse response = new ExceptionResponse
                (HttpStatus.BAD_REQUEST, 400, "Missing required request parameter.", e.getMessage() + ".");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
