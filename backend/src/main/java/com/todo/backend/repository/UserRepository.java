package com.todo.backend.repository;

import com.todo.backend.entity.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select user from user_details user where user.email = ?1")
    User findByEmail(String id);

}
