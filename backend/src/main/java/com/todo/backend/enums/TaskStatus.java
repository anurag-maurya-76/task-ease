package com.todo.backend.enums;

public enum TaskStatus {
    PENDING("Pending"), INPROGRESS("In Progress"), COMPLETED("Completed"), BLOCKED("Blocked");

    private String value;

    TaskStatus(String value) {
        this.value = value;
    }
}
