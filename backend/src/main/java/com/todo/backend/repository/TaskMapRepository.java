package com.todo.backend.repository;

import com.todo.backend.entity.TaskMap;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskMapRepository extends JpaRepository<TaskMap, String> {

    @Query("select * from task_map_details taskMap where taskMap.user_id = ?1")
    List<TaskMap> findByUserId(String userId);

}
