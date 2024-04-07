package com.todo.backend.model.todo_response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private Integer errorCode;
    private String errorMessage;
}
