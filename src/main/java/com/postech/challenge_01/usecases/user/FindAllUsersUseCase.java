package com.postech.challenge_01.usecases.user;

import com.postech.challenge_01.dtos.responses.UserResponseDTO;
import com.postech.challenge_01.mappers.UserMapper;
import com.postech.challenge_01.repositories.UserRepository;
import com.postech.challenge_01.usecases.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindAllUsersUseCase implements UseCase<Pageable, List<UserResponseDTO>> {
    private final UserRepository userRepository;

    @Override
    public List<UserResponseDTO> execute(Pageable pageable) {
        log.info("Listando usuários");
        var entityList = this.userRepository.findAll(pageable.getPageSize(), pageable.getOffset());
        return UserMapper.userToUserResponseDTOList(entityList);
    }
}
