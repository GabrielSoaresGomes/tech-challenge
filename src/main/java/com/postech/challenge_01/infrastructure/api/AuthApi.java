package com.postech.challenge_01.infrastructure.api;

import com.postech.challenge_01.dtos.security.AccountCredentialsDTO;
import com.postech.challenge_01.dtos.security.TokenDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Auth", description = "Endpoint para realizar o login")
public interface AuthApi {
    @Operation(
            summary = "Realiza login",
            description = "Realiza o login, informe o login e senha",
            tags = {"Auth"}
    )
    public ResponseEntity<TokenDTO> signin(@RequestBody @Valid AccountCredentialsDTO accountCredentialsDTO);
}
