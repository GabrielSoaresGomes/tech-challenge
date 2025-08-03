package com.postech.challenge_01.infrastructure.controllers;

import com.postech.challenge_01.dtos.requests.restaurant.RestaurantRequestDTO;
import com.postech.challenge_01.dtos.requests.restaurant.RestaurantUpdateDataDTO;
import com.postech.challenge_01.dtos.requests.restaurant.RestaurantUpdateRequestDTO;
import com.postech.challenge_01.dtos.responses.RestaurantResponseDTO;
import com.postech.challenge_01.infrastructure.api.RestaurantRestApi;
import com.postech.challenge_01.interface_adapter.controllers.RestaurantController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/restaurants")
public class RestaurantRestController implements RestaurantRestApi {
    private final RestaurantController restaurantController;

    @Override
    @GetMapping
    public List<RestaurantResponseDTO> getRestaurants(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam(required = false, defaultValue = "false", name = "onlyOpen") Boolean onlyOpen
    ) {
        return this.restaurantController.getRestaurants(PageRequest.of(page, size), onlyOpen);
    }

    @Override
    @GetMapping("/{id}")
    public RestaurantResponseDTO getRestaurantById(
            @PathVariable("id") Long id
    ) {
        return this.restaurantController.getRestaurantById(id);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestaurantResponseDTO saveRestaurant(
            @RequestBody @Valid RestaurantRequestDTO restaurantRequest
    ) {
        return this.restaurantController.saveRestaurant(restaurantRequest);
    }

    @Override
    @PutMapping("/{id}")
    public RestaurantResponseDTO updateRestaurant(
            @RequestBody @Valid RestaurantUpdateDataDTO restaurantRequest,
            @PathVariable("id") Long id
            ) {
        var updateRequestDTO = new RestaurantUpdateRequestDTO(id, restaurantRequest);
        return this.restaurantController.updateRestaurant(updateRequestDTO);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRestaurant(
            @PathVariable("id") Long id
    ) {
        this.restaurantController.deleteRestaurant(id);
    }
}
