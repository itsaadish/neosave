package com.example.neosave.exceptions;

import com.example.neosave.models.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PinCodeNotFound.class)
    public ResponseEntity<Object> handlePinCodeNotFoundException(PinCodeNotFound e) {
        ApiErrors error = new ApiErrors(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFound e) {
        ApiErrors error = new ApiErrors(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DuplicateEmailFound.class)
    public ResponseEntity<Object> handleDuplicateEmailFoundException(DuplicateEmailFound e) {
        ApiErrors error = new ApiErrors(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(InvalidEmail.class)
    public ResponseEntity<Object> handleInvalidEmailFoundException(InvalidEmail e) {
        ApiErrors error = new ApiErrors(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}