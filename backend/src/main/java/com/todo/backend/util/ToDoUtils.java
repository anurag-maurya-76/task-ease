package com.todo.backend.util;

import com.todo.backend.entity.Task;
import com.todo.backend.model.jwt.UserDetails;
import com.todo.backend.model.response.TaskResponse;
import com.google.gson.Gson;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

public class ToDoUtils {
    public static List<TaskResponse> mapTaskToTaskResponse(List<Task> tasks) {
        List<TaskResponse> taskResponses = new ArrayList<>();
        for (Task task : tasks) {
            taskResponses.add(new TaskResponse(task.getTaskName(), task.getTaskDescription(), task.getTaskStatus(), task.getCreatedAt(), task.getTaskId()));
        }
        return taskResponses;
    }

    public static UserDetails fetchUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userJson = authentication.getPrincipal().toString();
        Gson gson = new Gson();
        UserDetails userDetails = gson.fromJson(userJson, UserDetails.class);
        return userDetails;
    }

}
