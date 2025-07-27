package com.postech.challenge_01.repositories.user;

import com.postech.challenge_01.domains.User;
import com.postech.challenge_01.entities.UserEntity;
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
    public Optional<User> findById(Long id) {
        return this.jpaRepository.findById(id)
                .map(UserEntity::toUser);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return this.jpaRepository.findByLogin(login)
                .map(UserEntity::toUser);
    }

    @Override
    public List<User> findAll(int size, long offset) {
        Pageable pageable = PageRequest.of((int) offset, size);
        return this.jpaRepository.findAll(pageable)
                .stream()
                .map(UserEntity::toUser)
                .toList();
    }

    @Override
    public User save(User user) {
        UserEntity savedEntity = jpaRepository.save(UserEntity.of(user));
        return savedEntity.toUser();
    }

    @Override
    @Transactional
    public Optional<User> update(User user, Long id) {
        var entity = UserEntity.of(user);
        entity.setId(id);
        UserEntity updated = this.jpaRepository.save(entity);
        return Optional.of(updated.toUser());
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
