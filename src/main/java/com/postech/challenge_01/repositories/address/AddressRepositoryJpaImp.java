package com.postech.challenge_01.repositories.address;

import com.postech.challenge_01.domains.Address;
import com.postech.challenge_01.entities.AddressEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class AddressRepositoryJpaImp implements AddressRepository {
    private final AddressJpaRepository jpaRepository;

    public AddressRepositoryJpaImp(AddressJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Address> findById(Long id) {
        return this.jpaRepository.findById(id)
                .map(AddressEntity::toAddress);
    }

    @Override
    public Optional<Address> findByIdAndUserId(Long id, Long userId) {
        return this.jpaRepository.findByIdAndUserId(id, userId)
                .map(AddressEntity::toAddress);
    }

    @Override
    public List<Address> findAll(int size, long offset) {
        Pageable pageable = PageRequest.of((int) offset, size);
        return this.jpaRepository.findAll(pageable)
                .stream()
                .map(AddressEntity::toAddress)
                .toList();
    }

    @Override
    public List<Address> findAllByUserId(Long userId, int size, long offset) {
        Pageable pageable = PageRequest.of((int) offset, size);
        return this.jpaRepository.findAllByUserId(userId, pageable)
                .stream()
                .map(AddressEntity::toAddress)
                .collect(Collectors.toList());
    }

    @Override
    public Address save(Address address) {
        AddressEntity savedEntity = jpaRepository.save(AddressEntity.of(address));
        return savedEntity.toAddress();
    }

    @Override
    public Optional<Address> update(Address address, Long id) {
        var entity = AddressEntity.of(address);
        entity.setId(id);
        AddressEntity updated = this.jpaRepository.save(entity);
        return Optional.of(updated.toAddress());
    }

    @Override
    public Integer delete(Long id) {
        this.jpaRepository.deleteById(id);
        return 1;
    }

    @Override
    public void deleteByUserId(Long userId) {
        this.jpaRepository.deleteByUserId(userId);
    }
}
