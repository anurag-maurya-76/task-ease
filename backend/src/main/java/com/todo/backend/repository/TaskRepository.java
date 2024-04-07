package com.todo.backend.repository;

import com.todo.backend.entity.Task;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer>, JpaSpecificationExecutor<Task> {

    @Query("select tr from Task tr where tr.taskMapId=?1 order by tr.taskId")
    List<Task> findAllByTaskMapId1(int taskmapId);
    @Query("select tr from Task tr where tr.taskMapId=?1 order by tr.taskId desc")
    List<Task> findAllByTaskMapId(int taskmapId);
}
