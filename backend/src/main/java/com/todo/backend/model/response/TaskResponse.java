package com.todo.backend.model.response;

import com.todo.backend.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class TaskResponse {
    private String taskName;
    private String taskDescription;
    private TaskStatus taskStatus;
    private LocalDate createdAt;
    private Integer taskId;
}
