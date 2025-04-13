package com.postech.challenge_01.services;

import com.postech.challenge_01.dtos.requests.UserRequestDTO;
import com.postech.challenge_01.dtos.responses.UserResponseDTO;
import com.postech.challenge_01.entities.User;
import com.postech.challenge_01.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDTO saveUser(UserRequestDTO userRequestDTO) {
        var userEntity = new User();
        userEntity.setName(userRequestDTO.name());
        userEntity.setEmail(userRequestDTO.email());
        userEntity.setLogin(userRequestDTO.login());
        userEntity.setPassword(passwordEncoder.encode(userRequestDTO.password()));

        var save = this.userRepository.save(userEntity);
        return new UserResponseDTO(save.getId(), save.getName(), save.getEmail(), save.getLogin());
    }
}
