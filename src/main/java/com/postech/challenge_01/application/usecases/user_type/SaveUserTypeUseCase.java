package com.postech.challenge_01.application.usecases.user_type;

import com.postech.challenge_01.domain.UserType;
import com.postech.challenge_01.dtos.requests.UserTypeRequestDTO;
import com.postech.challenge_01.dtos.responses.UserTypeResponseDTO;
import com.postech.challenge_01.mappers.UserTypeMapper;
import com.postech.challenge_01.infrastructure.data_sources.repositories.UserTypeRepository;
import com.postech.challenge_01.application.usecases.UseCase;
import com.postech.challenge_01.application.usecases.rules.Rule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class SaveUserTypeUseCase implements UseCase<UserTypeRequestDTO, UserTypeResponseDTO> {
    private final UserTypeRepository userTypeRepository;
    private final List<Rule<UserType>> rules;

    @Override
    public UserTypeResponseDTO execute(UserTypeRequestDTO userTypeRequestDTO) {
        var entity = UserTypeMapper.userTypeRequestDTOToUserType(userTypeRequestDTO);

        rules.forEach(rule -> rule.execute(entity));

        log.info("Criando um novo tipo de usuário: {}", entity);
        var savedEntity = this.userTypeRepository.save(entity);

        log.info("Novo tipo de usuário criado: {}", savedEntity);
        return UserTypeMapper.userTypeToUserTypeResponseDTO(savedEntity);
    }
}
