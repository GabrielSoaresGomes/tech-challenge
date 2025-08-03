package com.postech.challenge_01.interface_adapter.data_sources.repositories;

import com.postech.challenge_01.dtos.transfer.user.NewUserDTO;
import com.postech.challenge_01.dtos.transfer.user.UserDTO;

import java.util.Optional;

public interface UserRepository extends CrudRepository<NewUserDTO, UserDTO, Long> {
    Optional<UserDTO> findByLogin(String login);
    boolean updatePassword(Long id, String password);
}