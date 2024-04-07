package com.todo.backend.controller;

import com.todo.backend.enums.ResponseCode;
import com.todo.backend.exception.ToDoException;
import com.todo.backend.model.response.TaskMapResponse;
import com.todo.backend.model.todo_response.ErrorResponse;
import com.todo.backend.model.todo_response.SuccessResponse;
import com.todo.backend.model.request.TaskRequest;
import com.todo.backend.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/getTaskList/{taskMapId}")
    public ResponseEntity<SuccessResponse<List<TaskMapResponse>>> getTaskMap(@PathVariable Integer taskMapId, @RequestParam(defaultValue = "ASC", required = false) String sortDir,
                                                             @RequestParam(defaultValue = "taskName") String sortBy, @RequestParam(defaultValue = "", required = false) String searchParameter,
                                                             @RequestParam(defaultValue = "") String searchBy) {
        SuccessResponse successResponse = new SuccessResponse(200, taskService.getTaskList(taskMapId, sortBy, sortDir, searchBy, searchParameter));
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    @PostMapping("/addTask")
    public ResponseEntity<SuccessResponse<String>> addTask(@RequestBody TaskRequest taskRequest) {
        ResponseCode responseCode = taskService.addTask(taskRequest);
        SuccessResponse successResponse = new SuccessResponse((responseCode.getCode()),responseCode.getMessage());
        return new ResponseEntity<>(successResponse,HttpStatus.OK);
    }

    @PostMapping("/updateTask/{taskId}")
    public ResponseEntity<SuccessResponse<String>> updateTask(@RequestBody TaskRequest taskRequest, @PathVariable Integer taskId) {
        ResponseCode responseCode = taskService.updateTask(taskRequest, taskId);
        SuccessResponse successResponse = new SuccessResponse(responseCode.getCode(),responseCode.getMessage());
        return new ResponseEntity<>(successResponse,HttpStatus.OK);
    }

}
