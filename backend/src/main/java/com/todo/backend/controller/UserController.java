package com.todo.backend.controller;

import com.todo.backend.enums.ResponseCode;
import com.todo.backend.exception.ToDoException;
import com.todo.backend.model.request.UserRequest;
import com.todo.backend.model.todo_response.ErrorResponse;
import com.todo.backend.model.todo_response.SuccessResponse;
import com.todo.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<SuccessResponse<String>> createUser(@RequestBody UserRequest userRequest) {
        ResponseCode responseCode = userService.createUser(userRequest);
        SuccessResponse successResponse = new SuccessResponse(responseCode.getCode(),responseCode.getMessage());
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    @PutMapping("/updateUser/{userId}")
    public ResponseEntity<SuccessResponse<String>> updateUser(@RequestBody UserRequest userRequest, @PathVariable String userId) {
        ResponseCode responseCode = userService.updateUser(userId, userRequest);
        SuccessResponse successResponse = new SuccessResponse(responseCode.getCode(),responseCode.getMessage());
        return new ResponseEntity<>(successResponse,HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<SuccessResponse<String>> login(Authentication authentication) {
        ResponseCode responseCode = userService.login(authentication);
        SuccessResponse successResponse = new SuccessResponse(responseCode.getCode(),responseCode.getMessage());
        return new ResponseEntity<>(successResponse,HttpStatus.OK);
    }

}
