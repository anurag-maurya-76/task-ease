package com.todo.backend.model.request;

import com.todo.backend.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskRequest {
    private String taskName;
    private String taskMapId;
    private String taskDescription;
    private TaskStatus taskStatus;
}
