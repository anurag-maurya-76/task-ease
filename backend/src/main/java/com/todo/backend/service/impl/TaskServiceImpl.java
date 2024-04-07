package com.todo.backend.service.impl;

import com.todo.backend.entity.Task;
import com.todo.backend.entity.TaskMap;
import com.todo.backend.enums.ResponseCode;
import com.todo.backend.enums.TaskStatus;
import com.todo.backend.exception.ToDoException;
import com.todo.backend.model.jwt.UserDetails;
import com.todo.backend.model.request.TaskRequest;
import com.todo.backend.model.response.TaskResponse;
import com.todo.backend.repository.TaskMapRepository;
import com.todo.backend.repository.TaskRepository;
import com.todo.backend.service.TaskService;
import com.todo.backend.util.ToDoUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapRepository taskMapRepository;

    static Specification<Task> hasTaskMapId(String taskMapId) {
        return (taskRoot, cq, cb) -> cb.equal(taskRoot.get("taskMapId"), taskMapId);
    }

    static Specification<Task> containsDate(String searchDate) {
        LocalDate localDate = LocalDate.parse(searchDate);
        return (taskRoot, cq, cb) -> cb.equal(taskRoot.get("createdAt"), localDate);
    }

    @Override
    public ResponseCode addTask(TaskRequest taskRequest) {
        UserDetails userDetails = ToDoUtils.fetchUser();
        if (userDetails == null) {
            throw new ToDoException(ResponseCode.USER_NOT_FOUND);
        }
        Optional<TaskMap> optionalTaskMap = taskMapRepository.findById(taskRequest.getTaskMapId());
        if (optionalTaskMap.isPresent()) {
            Task task = new Task(taskRequest.getTaskMapId(), taskRequest.getTaskName(), taskRequest.getTaskDescription(), TaskStatus.PENDING);
            task.setCreatedAt(LocalDate.now());
            task.setUpdatedAt(LocalDate.now());
            taskRepository.save(task);
            return ResponseCode.TASK_ADDED;
        } else {
            throw new ToDoException(ResponseCode.NO_TASK_MAP);
        }
    }

    @Override
    public ResponseCode updateTask(TaskRequest taskRequest, Integer taskId) {
        UserDetails userDetails = ToDoUtils.fetchUser();
        if (userDetails == null) {
            throw new ToDoException(ResponseCode.USER_NOT_FOUND);
        }
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            if (taskRequest.getTaskDescription() != null) {
                task.setTaskDescription(taskRequest.getTaskDescription());
            }
            if (taskRequest.getTaskName() != null) {
                task.setTaskName(taskRequest.getTaskName());
            }
            if (taskRequest.getTaskStatus() != null) {
                task.setTaskStatus(taskRequest.getTaskStatus());
            }
            task.setUpdatedAt(LocalDate.now());
            taskRepository.save(task);
            return ResponseCode.TASK_UPDATED;
        }
        throw new ToDoException(ResponseCode.TASK_NOT_FOUND);
    }

    @Override
    public List<TaskResponse> getTaskList(Integer taskMapId, String sortBy, String sortDir, String searchBy, String searchParameter) {
        UserDetails userDetails = ToDoUtils.fetchUser();
        if (userDetails == null) {
            throw new ToDoException(ResponseCode.USER_NOT_FOUND);
        }

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        List<Task> taskList;
        if (!searchParameter.isEmpty() && !searchBy.isEmpty()) {
            taskList = taskRepository.findAll(hasTaskMapId(taskMapId.toString()).and(containsDate(searchParameter)), sort);
        } else {
            taskList = taskRepository.findAll(hasTaskMapId(taskMapId.toString()), sort);
        }
        return ToDoUtils.mapTaskToTaskResponse(taskList);
    }
}
