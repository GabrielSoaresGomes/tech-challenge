package com.postech.challenge_01.infrastructure.controllers.security;

import com.postech.challenge_01.dtos.security.AccountCredentialsDTO;
import com.postech.challenge_01.dtos.security.TokenDTO;
import com.postech.challenge_01.application.usecases.security.AuthUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth", description = "Endpoint para realizar o login")
@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController {
    private final AuthUseCase authUseCase;

    @Operation(
            summary = "Realiza login",
            description = "Realiza o login, informe o login e senha",
            tags = {"Auth"}
    )
    @PostMapping
    public ResponseEntity<TokenDTO> signin(@RequestBody @Valid AccountCredentialsDTO accountCredentialsDTO) {
        var tokenDTO = authUseCase.execute(accountCredentialsDTO);
        return ResponseEntity.ok(tokenDTO);
    }
}
