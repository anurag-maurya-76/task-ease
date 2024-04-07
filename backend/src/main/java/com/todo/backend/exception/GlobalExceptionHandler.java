package com.todo.backend.exception;

import com.todo.backend.enums.ResponseCode;
import com.todo.backend.model.todo_response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ToDoException.class)
    public ResponseEntity<ErrorResponse> handleTodoException(ToDoException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getResponseCode().getCode(), ex.getResponseCode().getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialException(BadCredentialsException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ResponseCode.INVALID_CREDENTIAL.getCode(), ResponseCode.INVALID_CREDENTIAL.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(ResponseCode.SERVICE_EXCEPTION.getCode(), ResponseCode.SERVICE_EXCEPTION.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }
}
