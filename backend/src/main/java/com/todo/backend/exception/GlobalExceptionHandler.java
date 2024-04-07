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
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException(UsernameNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ResponseCode.USER_NOT_FOUND.getCode(), ResponseCode.USER_NOT_FOUND.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {

        return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.OK);
    }
}
