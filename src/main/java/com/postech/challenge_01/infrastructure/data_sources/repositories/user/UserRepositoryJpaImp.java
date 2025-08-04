package com.postech.challenge_01.infrastructure.data_sources.repositories.user;

import com.postech.challenge_01.dtos.transfer.user.NewUserDTO;
import com.postech.challenge_01.dtos.transfer.user.UserDTO;
import com.postech.challenge_01.infrastructure.entities.UserEntity;
import com.postech.challenge_01.infrastructure.mappers.UserEntityMapper;
import com.postech.challenge_01.interface_adapter.data_sources.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryJpaImp implements UserRepository {
    private final UserJpaRepository jpaRepository;

    public UserRepositoryJpaImp(UserJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<UserDTO> findById(Long id) {
        return this.jpaRepository.findById(id)
                .map(UserEntityMapper::toUserDTO);
    }

    @Override
    public Optional<UserDTO> findByLogin(String login) {
        return this.jpaRepository.findByLogin(login)
                .map(UserEntityMapper::toUserDTO);
    }

    @Override
    public List<UserDTO> findByUserTypeId(Long userTypeId) {
        return this.jpaRepository.findByUserTypeId(userTypeId)
                .stream()
                .map(UserEntityMapper::toUserDTO)
                .toList();
    }

    @Override
    public List<UserDTO> findAll(int size, long offset) {
        Pageable pageable = PageRequest.of((int) offset, size);
        return this.jpaRepository.findAll(pageable)
                .stream()
                .map(UserEntityMapper::toUserDTO)
                .toList();
    }

    @Override
    public UserDTO save(NewUserDTO user) {
        UserEntity savedEntity = jpaRepository.save(UserEntityMapper.toUserEntity(user));
        return UserEntityMapper.toUserDTO(savedEntity);
    }

    @Override
    @Transactional
    public Optional<UserDTO> update(UserDTO user) {
        var entity = UserEntityMapper.toUserEntity(user);
        UserEntity updated = this.jpaRepository.save(entity);
        return Optional.of(UserEntityMapper.toUserDTO(updated));
    }

    @Override
    public Integer delete(Long id) {
        this.jpaRepository.deleteById(id);
        return 1;
    }

    @Override
    @Transactional
    public boolean updatePassword(Long id, String password) {
        return jpaRepository.updatePassword(id, password) > 0;
    }
}
