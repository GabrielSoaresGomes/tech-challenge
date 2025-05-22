package com.postech.challenge_01.usecases.user;

import com.postech.challenge_01.dtos.responses.UserResponseDTO;
import com.postech.challenge_01.mappers.UserMapper;
import com.postech.challenge_01.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindAllUsersUseCase {
    private final UserRepository userRepository;

    public List<UserResponseDTO> execute(int page, int size) {
        int offset = (page - 1) * size;

        log.info("Listando usuários");
        var entityList = this.userRepository.findAll(size, offset);
        return UserMapper.userToUserResponseDTOList(entityList);
    }
}
