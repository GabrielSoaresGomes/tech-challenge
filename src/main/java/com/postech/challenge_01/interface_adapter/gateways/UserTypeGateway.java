package com.postech.challenge_01.interface_adapter.gateways;

import com.postech.challenge_01.application.gateways.IUserTypeGateway;
import com.postech.challenge_01.domain.UserType;
import com.postech.challenge_01.exceptions.ResourceNotFoundException;
import com.postech.challenge_01.exceptions.UserTypeNotFoundException;
import com.postech.challenge_01.interface_adapter.data_sources.repositories.UserTypeRepository;
import com.postech.challenge_01.mappers.user_type.UserTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserTypeGateway implements IUserTypeGateway {

    private final UserTypeRepository repository;

    @Override
    public UserType findById(Long id) {
        var userTypeDTO = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de usuário não encontrado para o id " + id));

        return UserTypeMapper.toUserType(userTypeDTO);
    }

    @Override
    public Optional<UserType> findByName(String name) {
        return repository.findByName(name)
                .map(UserTypeMapper::toUserType);
    }

    @Override
    public List<UserType> findAll(Pageable pageable) {
        return repository.findAll(pageable.getPageSize(), pageable.getOffset())
                .stream()
                .map(UserTypeMapper::toUserType)
                .toList();
    }

    @Override
    public UserType save(UserType entity) {
        var newUserTypeDTO = UserTypeMapper.toNewUserTypeDTO(entity);
        var savedUserTypeDTO = repository.save(newUserTypeDTO);
        return UserTypeMapper.toUserType(savedUserTypeDTO);
    }

    @Override
    public UserType update(UserType entity, Long id) {
        var userTypeDTO = UserTypeMapper.toUserTypeDTO(entity, id);
        var savedUserTypeDTO = repository.update(userTypeDTO)
                .orElseThrow(() -> new UserTypeNotFoundException(id));

        return UserTypeMapper.toUserType(savedUserTypeDTO);
    }

    @Override
    public void delete(Long id) {
        var wasDeleted = repository.delete(id);
        if (wasDeleted == 0) {
            throw new UserTypeNotFoundException(id);
        }
    }
}
