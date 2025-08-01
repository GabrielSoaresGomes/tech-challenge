package com.postech.challenge_01.repositories.user_type;

import com.postech.challenge_01.domains.UserType;
import com.postech.challenge_01.entities.UserTypeEntity;
import org.springframework.data.domain.PageRequest;
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
    public Optional<UserType> findById(Long id) {
        return this.jpaRepository.findById(id)
                .map(UserTypeEntity::toUserType);
    }

    @Override
    public Optional<UserType> findByName(String name) {
        return this.jpaRepository.findByName(name)
                .map(UserTypeEntity::toUserType);
    }


    @Override
    public List<UserType> findAll(int size, long offset) {
        return this.jpaRepository.findAll(PageRequest.of((int) offset, size))
                .stream()
                .map(UserTypeEntity::toUserType)
                .toList();
    }

    @Override
    public UserType save(UserType userType) {
        UserTypeEntity savedEntity = jpaRepository.save(UserTypeEntity.of(userType));
        return savedEntity.toUserType();
    }

    @Override
    public Optional<UserType> update(UserType userType, Long id) {
        return jpaRepository.findById(id)
                .map(existingEntity -> {
                    existingEntity.setName(userType.getName());
                    UserTypeEntity updatedEntity = jpaRepository.save(existingEntity);
                    return updatedEntity.toUserType();
                });
    }

    @Override
    public Integer delete(Long id) {
        return jpaRepository.findById(id)
                .map(entity -> {
                    jpaRepository.delete(entity);
                    return 1;
                })
                .orElse(0);
    }
}
