package com.postech.challenge_01.services;

import com.postech.challenge_01.services.exceptions.UserNotFoundException;
import com.postech.challenge_01.dtos.requests.UserRequestDTO;
import com.postech.challenge_01.dtos.responses.UserResponseDTO;
import com.postech.challenge_01.entities.User;
import com.postech.challenge_01.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


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
        LocalDateTime now = LocalDateTime.now();

        userEntity.setName(userRequestDTO.name());
        userEntity.setEmail(userRequestDTO.email());
        userEntity.setLogin(userRequestDTO.login());
        userEntity.setAddress(userRequestDTO.address());
        userEntity.setLastModifiedDateTime(now);
        userEntity.setPassword(passwordEncoder.encode(userRequestDTO.password()));

        var save = this.userRepository.save(userEntity);

        if (save == null) {
            throw new UserNotFoundException("Usuário não foi encontrado");
        }

        return new UserResponseDTO(save.getId(), save.getName(), save.getEmail(), save.getLogin(), save.getAddress(), now);
    }

    public UserResponseDTO updateUser(UserRequestDTO userRequestDTO, Long id) {
        var userEntity = new User();
        LocalDateTime now = LocalDateTime.now();

        userEntity.setName(userRequestDTO.name());
        userEntity.setEmail(userRequestDTO.email());
        userEntity.setLogin(userRequestDTO.login());
        userEntity.setAddress(userRequestDTO.address());
        userEntity.setLastModifiedDateTime(now);
        userEntity.setPassword(passwordEncoder.encode(userRequestDTO.password()));
        userEntity.setId(id);

        var update = this.userRepository.update(userEntity, id);
        if (update == null) {
            throw new UserNotFoundException("Usuário com ID " + id + " não foi encontrado");
        }
        return new UserResponseDTO(update.getId(), update.getName(), update.getEmail(), update.getLogin(), update.getAddress(), now);
    }

    public void deleteUser(Long id) {
        var delete = this.userRepository.delete(id);
        if (delete == 0) {
            throw new UserNotFoundException("Usuário com ID " + id + " não foi encontrado");
        }
    }
  
    public Optional<UserResponseDTO> findUserById(Long id) {
        return this.userRepository.findById(id);
    }

    public List<UserResponseDTO> findAllUsers(int page, int size) {
        int offset = (page - 1) * size;
        return this.userRepository.findAll(size, offset);
    }
}
