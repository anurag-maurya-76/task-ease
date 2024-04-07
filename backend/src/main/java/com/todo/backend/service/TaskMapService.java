package com.todo.backend.service;

import com.todo.backend.enums.ResponseCode;
import com.todo.backend.model.request.TaskMapRequest;
import com.todo.backend.model.response.TaskMapResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskMapService {
    ResponseCode addTaskMap(TaskMapRequest taskMapRequest);

    ResponseCode updateTaskMap(TaskMapRequest taskMapRequest, Integer taskMapId);

    List<TaskMapResponse> getTaskMap();

}
