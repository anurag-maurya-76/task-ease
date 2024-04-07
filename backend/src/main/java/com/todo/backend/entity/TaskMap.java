package com.todo.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Table(name = "task_map_details")
@Data
@Entity
@RequiredArgsConstructor
public class TaskMap {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer taskMapId;
    @Column(name = "task_map_name", nullable = false)
    private String taskMapName;
    @Column(name = "user_id", nullable = false, unique = false)
    private String userId;
    @OneToMany(mappedBy = "taskMapId")
    private List<Task> tasks;

    public TaskMap(String taskMapName, String userId, List<Task> tasks) {
        this.taskMapName = taskMapName;
        this.userId = userId;
        this.tasks = tasks;
    }
}
