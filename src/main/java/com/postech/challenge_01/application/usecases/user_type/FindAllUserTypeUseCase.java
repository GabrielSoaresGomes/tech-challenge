package com.postech.challenge_01.application.usecases.user_type;

import com.postech.challenge_01.application.gateways.IUserTypeGateway;
import com.postech.challenge_01.application.usecases.UseCase;
import com.postech.challenge_01.domain.UserType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindAllUserTypeUseCase implements UseCase<Pageable, List<UserType>> {
    private final IUserTypeGateway gateway;

    @Override
    public List<UserType> execute(Pageable pageable) {
        log.info("Listando usuários");
        return gateway.findAll(pageable);
    }
}
