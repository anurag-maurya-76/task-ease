package com.todo.backend.service.impl;

import com.todo.backend.entity.TaskMap;
import com.todo.backend.enums.ResponseCode;
import com.todo.backend.exception.ToDoException;
import com.todo.backend.model.jwt.UserDetails;
import com.todo.backend.model.request.TaskMapRequest;
import com.todo.backend.model.response.TaskMapResponse;
import com.todo.backend.repository.TaskMapRepository;
import com.todo.backend.service.TaskMapService;
import com.todo.backend.util.ToDoUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TaskMapServiceImpl implements TaskMapService {
    private final TaskMapRepository taskMapRepository;

    @Override
    public ResponseCode addTaskMap(TaskMapRequest taskMapRequest) {
        UserDetails userDetails = ToDoUtils.fetchUser();
        if (userDetails == null) {
            throw new ToDoException(ResponseCode.USER_NOT_FOUND);
        }
        try {
            TaskMap taskMap = new TaskMap(taskMapRequest.getTaskMapName(), userDetails.getUserId(), new ArrayList<>());
            taskMapRepository.save(taskMap);
            return ResponseCode.TASK_MAP_ADDED;
        } catch (Exception e) {
            throw new ToDoException(ResponseCode.ADD_TASK_MAP_FAILED);
        }
    }

    @Override
    public ResponseCode updateTaskMap(TaskMapRequest taskMapRequest, Integer taskMapId) {
        UserDetails userDetails = ToDoUtils.fetchUser();
        if (userDetails == null) {
            throw new ToDoException(ResponseCode.USER_NOT_FOUND);
        }
        Optional<TaskMap> optionalTaskMap = taskMapRepository.findById(taskMapId.toString());
        if (optionalTaskMap.isPresent()) {
            TaskMap taskMap = optionalTaskMap.get();
            taskMap.setTaskMapName(taskMapRequest.getTaskMapName());
            return ResponseCode.TASK_MAP_UPDATED;
        } else {
            throw new ToDoException(ResponseCode.NO_TASK_MAP);
        }
    }

    @Override
    public List<TaskMapResponse> getTaskMap() {
        UserDetails userDetails = ToDoUtils.fetchUser();
        if (userDetails == null) {
            throw new ToDoException(ResponseCode.USER_NOT_FOUND);
        }
        List<TaskMap> taskMapList = taskMapRepository.findByUserId(userDetails.getUserId());
        List<TaskMapResponse> taskMapResponseList = new ArrayList<>();
        for (TaskMap taskMap : taskMapList) {
            taskMapResponseList.add(new TaskMapResponse(taskMap.getTaskMapName(), taskMap.getTaskMapId()));
        }
        return taskMapResponseList;
    }
}
