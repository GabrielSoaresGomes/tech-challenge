package com.postech.challenge_01.interface_adapter.gateways;

import com.postech.challenge_01.application.gateways.IUserGateway;
import com.postech.challenge_01.domain.User;
import com.postech.challenge_01.exceptions.ResourceNotFoundException;
import com.postech.challenge_01.exceptions.UserNotFoundException;
import com.postech.challenge_01.interface_adapter.data_sources.repositories.UserRepository;
import com.postech.challenge_01.application.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserGateway implements IUserGateway {

    private final UserRepository repository;

    @Override
    public User findById(Long id) {
        var userDTO = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado para o id " + id));

        return UserMapper.toUser(userDTO);
    }

    @Override
    public List<User> findAll(Pageable pageable) {
        return repository.findAll(pageable.getPageSize(), pageable.getOffset())
                .stream()
                .map(UserMapper::toUser)
                .toList();
    }

    @Override
    public User save(User entity) {
        var newUserDTO = UserMapper.toNewUserDTO(entity);
        var savedUserDTO = repository.save(newUserDTO);
        return UserMapper.toUser(savedUserDTO);
    }

    @Override
    public User update(User entity, Long id) {
        var userDTO = UserMapper.toUserDTO(entity, id);
        var savedUserDTO = repository.update(userDTO)
                .orElseThrow(() -> new UserNotFoundException(id));

        return UserMapper.toUser(savedUserDTO);
    }

    @Override
    public void delete(Long id) {
        var wasDeleted = repository.delete(id);
        if (wasDeleted == 0) {
            throw new UserNotFoundException(id);
        }
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return repository.findByLogin(login)
                .map(UserMapper::toUser);
    }

    @Override
    public List<User> findByUserTypeId(Long userTypeId) {
        return repository.findByUserTypeId(userTypeId)
                .stream()
                .map(UserMapper::toUser)
                .toList();
    }

    @Override
    public User requireByLogin(String login) {
        return repository.findByLogin(login)
                .map(UserMapper::toUser)
                .orElseThrow(() -> new UsernameNotFoundException("Login "+ login +" não encontrado!"));
    }

    @Override
    public void updatePassword(Long id, String password) {
        var hasUpdated = repository.updatePassword(id, password);

        if(!hasUpdated) {
            throw new UserNotFoundException(id);
        }
    }
}
