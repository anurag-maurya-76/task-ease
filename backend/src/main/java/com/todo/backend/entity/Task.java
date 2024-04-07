package com.todo.backend.entity;

import com.todo.backend.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Table(name = "task_details")
@Data
@Entity
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer taskId;

    @Column(name = "task_map_id", length = 50, nullable = false, unique = false)
    private String taskMapId;

    @Column(name = "task_name", length = 50, nullable = false, unique = false)
    private String taskName;

    @Column(name = "task_description", length = 250, nullable = false, unique = false)
    private String taskDescription;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDate createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    public Task(String taskMapId, String taskName, String taskDescription, TaskStatus taskStatus) {
        this.taskMapId = taskMapId;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskStatus = taskStatus;
    }
}
