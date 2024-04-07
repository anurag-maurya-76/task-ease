package com.todo.backend.service.impl;

import com.todo.backend.entity.User;
import com.todo.backend.enums.ResponseCode;
import com.todo.backend.exception.ToDoException;
import com.todo.backend.model.jwt.UserDetails;
import com.todo.backend.model.request.UserRequest;
import com.todo.backend.repository.UserRepository;
import com.todo.backend.service.UserService;
import com.google.gson.Gson;
import com.todo.backend.util.ToDoUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseCode createUser(UserRequest userRequest) throws ToDoException {

        User user = userRepository.findByEmail(userRequest.getEmail());
        if (user != null) {
            throw new ToDoException(ResponseCode.USER_ALREADY_PRESENT);
        }
        String hashPassword = passwordEncoder.encode(userRequest.getPassword());
        User requestedUser = new User(userRequest.getEmail(), hashPassword);
        userRepository.save(requestedUser);
        return ResponseCode.USER_CREATED;
    }

    @Override
    public ResponseCode updateUser(String email, UserRequest userRequest) {
        UserDetails userDetails = ToDoUtils.fetchUser();
        if (userDetails == null) {
            throw new ToDoException(ResponseCode.USER_NOT_FOUND);
        }
        String hashPassword = passwordEncoder.encode(userRequest.getPassword());
        User requestedUser = new User(userDetails.getUserId(), userRequest.getEmail(), hashPassword);
        try {
            userRepository.save(requestedUser);
            return ResponseCode.USER_UPDATED;
        } catch (Exception e) {
            throw new ToDoException(ResponseCode.USER_UPDATE_FAILED);
        }
    }

    @Override
    public ResponseCode login(Authentication authentication) {
        String userJson = authentication.getPrincipal().toString();
        Gson gson = new Gson();
        UserDetails userDetails = gson.fromJson(userJson, UserDetails.class);
        User user = userRepository.findByEmail(userDetails.getEmail());
        if (user == null) {
            return ResponseCode.USER_NOT_FOUND;
        } else {
            return ResponseCode.LOGIN_SUCCESS;
        }
    }
}
