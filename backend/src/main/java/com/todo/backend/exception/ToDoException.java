package com.todo.backend.exception;

import com.todo.backend.enums.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ToDoException extends RuntimeException {
    ResponseCode responseCode;
}
