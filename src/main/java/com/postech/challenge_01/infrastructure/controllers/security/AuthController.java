package com.postech.challenge_01.infrastructure.controllers.security;

import com.postech.challenge_01.dtos.security.AccountCredentialsDTO;
import com.postech.challenge_01.dtos.security.TokenDTO;
import com.postech.challenge_01.application.usecases.security.AuthUseCase;
import com.postech.challenge_01.infrastructure.api.AuthApi;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController implements AuthApi {
    private final AuthUseCase authUseCase;

    @Override
    @PostMapping
    public ResponseEntity<TokenDTO> signin(@RequestBody @Valid AccountCredentialsDTO accountCredentialsDTO) {
        var tokenDTO = authUseCase.execute(accountCredentialsDTO);
        return ResponseEntity.ok(tokenDTO);
    }
}
