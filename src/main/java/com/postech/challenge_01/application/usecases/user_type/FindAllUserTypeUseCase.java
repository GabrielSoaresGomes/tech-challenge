package com.postech.challenge_01.application.usecases.user_type;

import com.postech.challenge_01.dtos.responses.UserTypeResponseDTO;
import com.postech.challenge_01.mappers.UserTypeMapper;
import com.postech.challenge_01.infrastructure.data_sources.repositories.UserTypeRepository;
import com.postech.challenge_01.application.usecases.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindAllUserTypeUseCase implements UseCase<Pageable, List<UserTypeResponseDTO>> {
    private final UserTypeRepository userTypeRepository;

    @Override
    public List<UserTypeResponseDTO> execute(Pageable pageable) {
        log.info("Listando tipos de usuários");
        var entityList = this.userTypeRepository.findAll(pageable.getPageSize(), pageable.getOffset());
        return UserTypeMapper.userTypeToUserTypeResponseDTOList(entityList);
    }

}
