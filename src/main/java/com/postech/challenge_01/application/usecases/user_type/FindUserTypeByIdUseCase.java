package com.postech.challenge_01.application.usecases.user_type;

import com.postech.challenge_01.dtos.responses.UserTypeResponseDTO;
import com.postech.challenge_01.exceptions.ResourceNotFoundException;
import com.postech.challenge_01.infrastructure.data_sources.repositories.user_type.UserTypeRepository;
import com.postech.challenge_01.mappers.UserTypeMapper;
import com.postech.challenge_01.application.usecases.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindUserTypeByIdUseCase implements UseCase<Long, UserTypeResponseDTO> {
    private final UserTypeRepository userTypeRepository;

    @Override
    public UserTypeResponseDTO execute(Long id) {
        log.info("Buscando tipo de usuário com ID: {}", id);
        var entity = this.userTypeRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de usuário não encontrado para o id " + id));

        log.info("Tipo de usuário encontrado: {}", entity);
        return UserTypeMapper.userTypeToUserTypeResponseDTO(entity);
    }
}
