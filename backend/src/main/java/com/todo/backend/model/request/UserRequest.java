package com.todo.backend.model.request;

import lombok.Data;

@Data
public class UserRequest {
    private String email;
    private String password;
}
