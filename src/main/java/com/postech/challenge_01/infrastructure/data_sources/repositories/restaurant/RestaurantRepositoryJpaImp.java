package com.postech.challenge_01.infrastructure.data_sources.repositories.restaurant;

import com.postech.challenge_01.dtos.transfer.restaurant.NewRestaurantDTO;
import com.postech.challenge_01.dtos.transfer.restaurant.RestaurantDTO;
import com.postech.challenge_01.infrastructure.entities.RestaurantEntity;
import com.postech.challenge_01.infrastructure.entities.UserEntity;
import com.postech.challenge_01.infrastructure.mappers.RestaurantEntityMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import com.postech.challenge_01.interface_adapter.data_sources.repositories.RestaurantRepository;


import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public class RestaurantRepositoryJpaImp implements RestaurantRepository{
    private final RestaurantJpaRepository jpaRepository;

    public RestaurantRepositoryJpaImp(RestaurantJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<RestaurantDTO> findById(Long id) {
        return this.jpaRepository.findById(id)
                .map(RestaurantEntityMapper::toRestaurantDTO);
    }

    @Override
    public List<RestaurantDTO> findAll(int size, long offset) {
        Pageable pageable = PageRequest.of((int) offset, size);
        return this.jpaRepository.findAll(pageable)
                .stream()
                .map(RestaurantEntityMapper::toRestaurantDTO)
                .toList();
    }

    @Override
    public List<RestaurantDTO> findAllOpen(int size, long offset) {
        Pageable pageable = PageRequest.of((int) offset, size);
        LocalTime now = LocalTime.now();

        return this.jpaRepository.findAllOpen(now, pageable)
                .stream()
                .map(RestaurantEntityMapper::toRestaurantDTO)
                .toList();
    }

    @Override
    public RestaurantDTO save(NewRestaurantDTO restaurant) {
        RestaurantEntity savedEntity = jpaRepository.save(RestaurantEntityMapper.toRestaurantEntity(restaurant));
        return RestaurantEntityMapper.toRestaurantDTO(savedEntity);
    }

    @Override
    public Optional<RestaurantDTO> update(RestaurantDTO dto) {
        return jpaRepository.findById(dto.id())
                .map(entity -> {
                    entity.setName(dto.name());
                    entity.setType(dto.type());
                    entity.setStartTime(dto.startTime());
                    entity.setEndTime(dto.endTime());

                    if (dto.ownerId() != null) {
                        UserEntity newOwner = new UserEntity();
                        newOwner.setId(dto.ownerId());
                        entity.setOwner(newOwner);
                    }

                    RestaurantEntity saved = jpaRepository.save(entity);

                    return RestaurantEntityMapper.toRestaurantDTO(saved);
                });
    }

    @Override
    public Integer delete(Long id) {
        jpaRepository.deleteById(id);
        return 1;
    }
}
