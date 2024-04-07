package com.todo.backend.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class TaskMapResponse {
    private String taskMapName;
    private Integer taskMapId;
}
