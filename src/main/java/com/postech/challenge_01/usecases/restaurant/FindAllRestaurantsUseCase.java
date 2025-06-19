package com.postech.challenge_01.usecases.restaurant;

import com.postech.challenge_01.dtos.responses.RestaurantResponseDTO;
import com.postech.challenge_01.mappers.RestaurantMapper;
import com.postech.challenge_01.repositories.RestaurantRepository;
import com.postech.challenge_01.usecases.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindAllRestaurantsUseCase implements UseCase<Pageable, List<RestaurantResponseDTO>> {
    private final RestaurantRepository restaurantRepository;

    public List<RestaurantResponseDTO> execute(Pageable pageable) {
        log.info("Listando restaurantes");
        var entityList = this.restaurantRepository.findAll(pageable.getPageSize(), pageable.getOffset());
        return RestaurantMapper.restaurantToRestaurantResponseDTOList(entityList);
    }
}
