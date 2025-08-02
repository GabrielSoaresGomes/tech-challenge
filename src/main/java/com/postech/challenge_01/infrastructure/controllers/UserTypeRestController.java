package com.postech.challenge_01.infrastructure.controllers;

import com.postech.challenge_01.dtos.requests.user_type.UserTypeRequestDTO;
import com.postech.challenge_01.dtos.requests.user_type.UserTypeUpdateDataDTO;
import com.postech.challenge_01.dtos.requests.user_type.UserTypeUpdateRequestDTO;
import com.postech.challenge_01.dtos.responses.user_type.UserTypeResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.postech.challenge_01.interface_adapter.controllers.UserTypeController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user-types")
public class UserTypeRestController {
    private final UserTypeController controller;

    @GetMapping
    public List<UserTypeResponseDTO> getUserTypes(
        @RequestParam("page") int page,
        @RequestParam("size") int size
    ) {
        var request = PageRequest.of(page, size);

        return this.controller.getUserTypes(request);
    }

    @GetMapping("/{id}")
    public UserTypeResponseDTO getUserTypeById(
            @PathVariable("id") Long id
    ) {
        return this.controller.getUserTypeById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserTypeResponseDTO saveUserType(
            @RequestBody UserTypeRequestDTO userTypeRequestDTO
    ) {
        return this.controller.saveUserType(userTypeRequestDTO);
    }

    @PutMapping("/{id}")
    public UserTypeResponseDTO updateUserType(
            @RequestBody @Valid UserTypeUpdateDataDTO userTypeRequest,
            @PathVariable(value = "id") Long id
    ) {
        var updateRequest = new UserTypeUpdateRequestDTO(id, userTypeRequest);
        return this.controller.updateUserType(updateRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserType(
            @PathVariable("id") Long id
    ) {
        this.controller.deleteUserType(id);
    }
}
