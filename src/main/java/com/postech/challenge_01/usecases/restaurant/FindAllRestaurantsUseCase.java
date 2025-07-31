package com.postech.challenge_01.usecases.restaurant;

import com.postech.challenge_01.domains.Restaurant;
import com.postech.challenge_01.dtos.requests.restaurant.FindAllRestaurantsRequestDTO;
import com.postech.challenge_01.dtos.responses.RestaurantResponseDTO;
import com.postech.challenge_01.mappers.RestaurantMapper;
import com.postech.challenge_01.repositories.restaurant.RestaurantRepository;
import com.postech.challenge_01.usecases.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindAllRestaurantsUseCase implements UseCase<FindAllRestaurantsRequestDTO, List<RestaurantResponseDTO>> {
    private final RestaurantRepository restaurantRepository;

    public List<RestaurantResponseDTO> execute(FindAllRestaurantsRequestDTO findAllRestaurantsRequestDTO) {
        Pageable pageable = findAllRestaurantsRequestDTO.pageable();
        Boolean onlyOpen = findAllRestaurantsRequestDTO.onlyOpen();
        log.info("Listando restaurantes, filtrando por aberto: {}", onlyOpen);
        List<Restaurant> entityList = onlyOpen
                ? restaurantRepository.findAllOpen(pageable.getPageSize(), pageable.getOffset())
                : restaurantRepository.findAll(pageable.getPageSize(), pageable.getOffset());

        return RestaurantMapper.restaurantToRestaurantResponseDTOList(entityList);
    }
}
