package com.postech.challenge_01.infrastructure.data_sources.repositories.address;

import com.postech.challenge_01.dtos.transfer.address.AddressDTO;
import com.postech.challenge_01.dtos.transfer.address.NewAddressDTO;
import com.postech.challenge_01.infrastructure.entities.AddressEntity;
import com.postech.challenge_01.infrastructure.mappers.AddressEntityMapper;
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
    public Optional<AddressDTO> findById(Long id) {
        return this.jpaRepository.findById(id)
                .map(AddressEntityMapper::toAddressDTO);
    }

    @Override
    public Optional<AddressDTO> findByIdAndUserId(Long id, Long userId) {
        return this.jpaRepository.findByIdAndUserId(id, userId)
                .map(AddressEntityMapper::toAddressDTO);
    }

    @Override
    public List<AddressDTO> findAll(int size, long offset) {
        Pageable pageable = PageRequest.of((int) offset, size);
        return this.jpaRepository.findAll(pageable)
                .stream()
                .map(AddressEntityMapper::toAddressDTO)
                .toList();
    }

    @Override
    public List<AddressDTO> findAllByUserId(Long userId, int size, long offset) {
        Pageable pageable = PageRequest.of((int) offset, size);
        return this.jpaRepository.findAllByUserId(userId, pageable)
                .stream()
                .map(AddressEntityMapper::toAddressDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AddressDTO save(NewAddressDTO address) {
        AddressEntity savedEntity = jpaRepository.save(AddressEntityMapper.toAddressEntity(address));
        return AddressEntityMapper.toAddressDTO(savedEntity);
    }

    @Override
    public Optional<AddressDTO> update(AddressDTO address) {
        var entity = AddressEntityMapper.toAddressEntity(address);
        AddressEntity updated = this.jpaRepository.save(entity);
        return Optional.of(AddressEntityMapper.toAddressDTO(updated));
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
