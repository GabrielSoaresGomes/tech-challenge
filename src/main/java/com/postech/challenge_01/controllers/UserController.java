package com.postech.challenge_01.controllers;

import com.postech.challenge_01.dtos.requests.UserRequestDTO;
import com.postech.challenge_01.dtos.responses.UserResponseDTO;
import com.postech.challenge_01.services.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> saveUser(
            @RequestBody @Valid UserRequestDTO userRequestDTO
    ) {
        logger.info("POST -> /api/v1/users");
        UserResponseDTO userResponseDTO = this.userService.saveUser(userRequestDTO);
        var httpStatus = HttpStatus.CREATED.value();
        return ResponseEntity.status(httpStatus).body(userResponseDTO);
    }
}
