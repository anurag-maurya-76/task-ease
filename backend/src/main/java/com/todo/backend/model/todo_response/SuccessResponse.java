package com.todo.backend.model.todo_response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SuccessResponse<T> {
    private int statusCode;
    private T responseBody;
}
