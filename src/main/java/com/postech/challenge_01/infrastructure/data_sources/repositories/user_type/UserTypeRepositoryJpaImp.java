package com.postech.challenge_01.infrastructure.data_sources.repositories.user_type;

import com.postech.challenge_01.dtos.transfer.user_type.NewUserTypeDTO;
import com.postech.challenge_01.dtos.transfer.user_type.UserTypeDTO;
import com.postech.challenge_01.infrastructure.entities.UserTypeEntity;
import com.postech.challenge_01.infrastructure.mappers.UserTypeEntityMapper;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import com.postech.challenge_01.interface_adapter.data_sources.repositories.UserTypeRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserTypeRepositoryJpaImp implements UserTypeRepository {
    private final UserTypeJpaRepository jpaRepository;

    public UserTypeRepositoryJpaImp(UserTypeJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<UserTypeDTO> findById(Long id) {
        return this.jpaRepository.findById(id)
                .map(UserTypeEntityMapper::toUserTypeDTO);
    }

    @Override
    public Optional<UserTypeDTO> findByName(String name) {
        return this.jpaRepository.findByName(name)
                .map(UserTypeEntityMapper::toUserTypeDTO);
    }


    @Override
    public List<UserTypeDTO> findAll(int size, long offset) {
        return this.jpaRepository.findAll(PageRequest.of((int) offset, size))
                .stream()
                .map(UserTypeEntityMapper::toUserTypeDTO)
                .toList();
    }

    @Override
    public UserTypeDTO save(NewUserTypeDTO userType) {
        UserTypeEntity savedEntity = jpaRepository.save(UserTypeEntityMapper.toUserTypeEntity(userType));
        return UserTypeEntityMapper.toUserTypeDTO(savedEntity);
    }

    @Override
    @Transactional
    public Optional<UserTypeDTO> update(UserTypeDTO userType) {
        var entity = UserTypeEntityMapper.toUserTypeEntity(userType);
        UserTypeEntity updated = this.jpaRepository.save(entity);
        return Optional.of(UserTypeEntityMapper.toUserTypeDTO(updated));
    }

    @Override
    public Integer delete(Long id) {
        this.jpaRepository.deleteById(id);
        return 1;
    }
}
