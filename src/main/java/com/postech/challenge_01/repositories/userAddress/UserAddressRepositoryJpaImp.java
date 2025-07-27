package com.postech.challenge_01.repositories.userAddress;

import com.postech.challenge_01.domains.UserAddress;
import com.postech.challenge_01.entities.UserAddressEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserAddressRepositoryJpaImp implements  UserAddressRepository{
    private final UserAddressJpaRepository jpaRepository;

    public UserAddressRepositoryJpaImp(UserAddressJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<UserAddress> findById(Long id) {
        return jpaRepository.findById(id)
                .map(UserAddressEntity::toUserAddress);
    }

    @Override
    public List<UserAddress> findAll(int size, long offset) {
        Pageable pageable = PageRequest.of((int) offset, size);
        return this.jpaRepository.findAll(pageable)
                .stream()
                .map(UserAddressEntity::toUserAddress)
                .collect(Collectors.toList());
    }

    @Override
    public UserAddress save(UserAddress userAddress) {
        UserAddressEntity savedUserAddressEntity = jpaRepository.save(UserAddressEntity.of(userAddress));
        return savedUserAddressEntity.toUserAddress();
    }

    @Override
    public Optional<UserAddress> update(UserAddress userAddress, Long id) {
        if (!jpaRepository.existsById(id)) {
            return Optional.empty();
        }

        UserAddressEntity userAddressEntity = UserAddressEntity.of(userAddress);
        userAddressEntity.setId(id);

        UserAddressEntity updatedUserAddressEntity = jpaRepository.save(userAddressEntity);
        return Optional.of(updatedUserAddressEntity.toUserAddress());
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
