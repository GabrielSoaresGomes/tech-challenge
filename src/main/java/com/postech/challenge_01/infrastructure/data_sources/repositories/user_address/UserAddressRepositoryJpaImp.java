package com.postech.challenge_01.infrastructure.data_sources.repositories.user_address;

import com.postech.challenge_01.dtos.transfer.user_address.NewUserAddressDTO;
import com.postech.challenge_01.dtos.transfer.user_address.UserAddressDTO;
import com.postech.challenge_01.infrastructure.entities.UserAddressEntity;
import com.postech.challenge_01.infrastructure.mappers.UserAddressEntityMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserAddressRepositoryJpaImp implements  UserAddressRepository {
    private final UserAddressJpaRepository jpaRepository;

    public UserAddressRepositoryJpaImp(UserAddressJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<UserAddressDTO> findById(Long id) {
        return jpaRepository.findById(id)
                .map(UserAddressEntityMapper::toUserAddressDTO);
    }

    @Override
    public List<UserAddressDTO> findAll(int size, long offset) {
        Pageable pageable = PageRequest.of((int) offset, size);
        return this.jpaRepository.findAll(pageable)
                .stream()
                .map(UserAddressEntityMapper::toUserAddressDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserAddressDTO save(NewUserAddressDTO dto) {
        var entity = UserAddressEntityMapper.toUserAddressEntity(dto);
        var savedEntity = jpaRepository.save(entity);
        return UserAddressEntityMapper.toUserAddressDTO(savedEntity);
    }

    @Override
    public Optional<UserAddressDTO> update(UserAddressDTO userAddress) {
        if (!jpaRepository.existsById(userAddress.id())) {
            return Optional.empty();
        }

        UserAddressEntity userAddressEntity = UserAddressEntityMapper.toUserAddressEntity(userAddress);

        UserAddressEntity updatedUserAddressEntity = jpaRepository.save(userAddressEntity);
        return Optional.of(UserAddressEntityMapper.toUserAddressDTO(updatedUserAddressEntity));
    }

    @Override
    public Integer delete(Long id) {
        if (!jpaRepository.existsById(id)) {
            return 0;
        }
        jpaRepository.deleteById(id);
        return 1;
    }
}
