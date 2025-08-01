package com.postech.challenge_01.application.usecases.user_type;

import com.postech.challenge_01.domain.UserType;
import com.postech.challenge_01.dtos.requests.user_type.UserTypeUpdateRequestDTO;
import com.postech.challenge_01.dtos.responses.UserTypeResponseDTO;
import com.postech.challenge_01.exceptions.UserTypeNotFoundException;
import com.postech.challenge_01.infrastructure.data_sources.repositories.user_type.UserTypeRepository;
import com.postech.challenge_01.mappers.UserTypeMapper;
import com.postech.challenge_01.application.usecases.UseCase;
import com.postech.challenge_01.application.usecases.rules.Rule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateUserTypeUseCase implements UseCase<UserTypeUpdateRequestDTO, UserTypeResponseDTO> {
    private final UserTypeRepository userTypeRepository;
    private final List<Rule<UserType>> rules;

    @Override
    public UserTypeResponseDTO execute(UserTypeUpdateRequestDTO request) {
        var id = request.id();
        var data = request.data();

        var entity = UserTypeMapper.userTypeRequestDTOToUserType(id, data);

        rules.forEach(rule -> rule.execute(entity));

        log.info("Atualizando tipo do usuário com ID {}: {}", id, entity);
        var updatedEntity = this.userTypeRepository.update(entity, id)
                .orElseThrow(() -> new UserTypeNotFoundException(id));

        return UserTypeMapper.userTypeToUserTypeResponseDTO(updatedEntity);
    }
}
