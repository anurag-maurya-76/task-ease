package com.todo.backend.service;

import com.todo.backend.enums.ResponseCode;
import com.todo.backend.model.request.UserRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    ResponseCode createUser(UserRequest userRequest);

    ResponseCode updateUser(String id, UserRequest userRequest);

    ResponseCode login(Authentication authentication);
}
