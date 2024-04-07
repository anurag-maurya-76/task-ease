package com.todo.backend.enums;

import lombok.Getter;

@Getter
public enum ResponseCode {

    TASK_ADDED("Task added successfully", 201),
    TASK_UPDATED("Task updated successfully", 202),
    USER_CREATED("User Created successfully", 203),
    USER_UPDATED("User updated successfully", 204),
    LOGIN_SUCCESS("Login successfully", 205),
    TASK_MAP_ADDED("Task map added successfully", 206),
    TASK_MAP_UPDATED("Task map updated successfully", 207),
    NO_TASK("No task found for given user id", 401),
    TASK_NOT_FOUND("Task not found for the provided Task Id", 402),
    USER_ALREADY_PRESENT("User already present", 403),
    USER_NOT_FOUND("User not found", 404),
    USERNAME_NULL("User name can not be null", 404),
    NO_TASK_MAP("No task map found for corresponding task map id", 405),
    ADD_TASK_MAP_FAILED("Failed to add task map", 406),
    UPDATE_TASK_MAP_FAILED("Failed to update task map", 407),
    USER_UPDATE_FAILED("Failed to update user details", 408),
    LOGIN_FAILED("Login failed", 409),
    INVALID_CREDENTIAL("Failed to validate user details", 410);
    String message;
    Integer code;

    ResponseCode(String message, Integer code) {
        this.code = code;
        this.message = message;
    }
}
