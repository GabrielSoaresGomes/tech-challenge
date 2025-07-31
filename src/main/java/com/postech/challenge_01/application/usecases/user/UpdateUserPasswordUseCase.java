package com.postech.challenge_01.application.usecases.user;

import com.postech.challenge_01.application.gateways.IPasswordEncoderGateway;
import com.postech.challenge_01.application.gateways.IUserGateway;
import com.postech.challenge_01.application.usecases.UseCase;
import com.postech.challenge_01.dtos.requests.user.UserPasswordRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateUserPasswordUseCase implements UseCase<UserPasswordRequestDTO, Void> {
    private final IUserGateway gateway;
    private final IPasswordEncoderGateway passwordEncoderGateway;

    @Override
    public Void execute(UserPasswordRequestDTO request) {
        var id = request.id();
        var passwordEncoded = passwordEncoderGateway.encode(request.password());

        log.info("Atualizando a senha do usuário com ID {}", id);
        gateway.updatePassword(id, passwordEncoded);

        return null;
    }
}
