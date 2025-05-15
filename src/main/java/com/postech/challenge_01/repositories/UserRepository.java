package com.postech.challenge_01.repositories;

import com.postech.challenge_01.dtos.responses.UserResponseDTO;
import com.postech.challenge_01.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<UserResponseDTO> findById(Long id);
    Optional<User> findByLogin(String login);
    List<UserResponseDTO> findAll(int size, int offset);
    User save(User user);
    User update(User user, Long id);
    Integer delete(Long id);
}
