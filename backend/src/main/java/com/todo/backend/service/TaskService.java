package com.todo.backend.service;

import com.todo.backend.enums.ResponseCode;
import com.todo.backend.model.request.TaskRequest;
import com.todo.backend.model.response.TaskResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {

    ResponseCode addTask(TaskRequest taskRequest);

    ResponseCode updateTask(TaskRequest taskRequest, Integer taskId);

    List<TaskResponse> getTaskList(Integer taskMapId, String sortBy, String sortDir, String searchBy, String searchParameter);

}
