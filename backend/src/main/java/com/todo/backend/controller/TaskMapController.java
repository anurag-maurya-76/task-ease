package com.todo.backend.controller;

import com.todo.backend.enums.ResponseCode;
import com.todo.backend.exception.ToDoException;
import com.todo.backend.model.request.TaskMapRequest;
import com.todo.backend.model.response.TaskMapResponse;
import com.todo.backend.model.todo_response.ErrorResponse;
import com.todo.backend.model.todo_response.SuccessResponse;
import com.todo.backend.service.TaskMapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class TaskMapController {

    private final TaskMapService taskMapService;

    @GetMapping("/getTaskMap")
    public ResponseEntity<SuccessResponse<List<TaskMapResponse>>> getTaskMap() {
        SuccessResponse successResponse = new SuccessResponse(200,taskMapService.getTaskMap());
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    @PostMapping("/addTaskMap")
    public ResponseEntity<SuccessResponse<String>> addTask(@RequestBody TaskMapRequest taskMapRequest) {
        ResponseCode responseCode = taskMapService.addTaskMap(taskMapRequest);
        SuccessResponse successResponse = new SuccessResponse(responseCode.getCode(),responseCode.getMessage());
        return new ResponseEntity<>(successResponse,HttpStatus.OK);
    }

    @PutMapping("/updateTaskMap/{taskMapId}")
    public ResponseEntity<SuccessResponse<String>> updateTask(@RequestBody TaskMapRequest taskMapRequest, @PathVariable Integer taskMapId) {
        ResponseCode responseCode = taskMapService.updateTaskMap(taskMapRequest, taskMapId);
        SuccessResponse successResponse = new SuccessResponse(responseCode.getCode(),responseCode.getMessage());
        return new ResponseEntity<>(successResponse,HttpStatus.OK);
    }

}
