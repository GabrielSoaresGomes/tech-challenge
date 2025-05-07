package com.postech.challenge_01.controllers;

import com.postech.challenge_01.dtos.requests.UserRequestDTO;
import com.postech.challenge_01.dtos.responses.UserResponseDTO;
import com.postech.challenge_01.entities.User;
import com.postech.challenge_01.services.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUser(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        logger.info("GET -> /api/v1/users");
        var users = this.userService.findAllUsers(page, size);
        var httpStatus = HttpStatus.OK.value();
        return ResponseEntity.status(httpStatus).body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUserById(
            @PathVariable("id") Long id
    ) {
        logger.info("GET -> /api/v1/users");
        var users = this.userService.findPessoaById(id);
        var httpStatus = HttpStatus.OK.value();
        return ResponseEntity.status(httpStatus).body(users);
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

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @RequestBody @Valid UserRequestDTO userRequestDTO,
            @PathVariable(value = "id") Long id
    ) {
        logger.info("PUT -> /api/v1/users/{}", id);
        UserResponseDTO userResponseDTO = this.userService.updateUser(userRequestDTO, id);
        return ResponseEntity.ok(userResponseDTO);
    }
}
