package com.postech.challenge_01.controllers;

import com.postech.challenge_01.dtos.requests.RestaurantRequestDTO;
import com.postech.challenge_01.dtos.requests.RestaurantUpdateRequestDTO;
import com.postech.challenge_01.dtos.responses.RestaurantResponseDTO;
import com.postech.challenge_01.usecases.restaurant.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Restaurants", description = "Endpoints para gerenciamento de restaurantes")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {
    private final SaveRestaurantUseCase saveRestaurantUseCase;
    private final FindAllRestaurantsUseCase findAllRestaurantsUseCase;
    private final FindRestaurantByIdUseCase findRestaurantByIdUseCase;
    private final UpdateRestaurantUseCase updateRestaurantUseCase;
    private final DeleteRestaurantUseCase deleteRestaurantUseCase;

    @Operation (
            summary = "Busca por todos os restaurantes",
            description = "Busca por todos os restaurantes, informe o número de restaurantes exibidos por página",
            tags = {"Restaurants"}
    )
    @GetMapping
    public List<RestaurantResponseDTO> getRestaurants(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        return this.findAllRestaurantsUseCase.execute(PageRequest.of(page, size));
    }

    @Operation(
            summary = "Busca por somente um restaurante",
            description = "Busca restaurante pelo id, informe id do restaurante",
            tags = {"Restaurants"}
    )
    @GetMapping("/{id}")
    public RestaurantResponseDTO getRestaurantById(
            @PathVariable("id") Long id
    ) {
        return this.findRestaurantByIdUseCase.execute(id);
    }

    @Operation(
            summary = "Cria um restaurante",
            description = "Cria um restaurante, informe dono, endereço, tipo, horário de abertura e fechamento",
            tags = {"Restaurants"}
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestaurantResponseDTO saveRestaurant(
            @RequestBody @Valid RestaurantRequestDTO restaurantRequest
    ) {
        return this.saveRestaurantUseCase.execute(restaurantRequest);
    }

    @Operation(
            summary = "Atualize um restaurante",
            description = "Atualize um restaurante, informe os campos atualizados",
            tags = {"Restaurants"}
    )
    @PutMapping("/{id}")
    public RestaurantResponseDTO updateRestaurant(
            @RequestBody @Valid RestaurantRequestDTO restaurantRequest,
            @PathVariable("id") Long id
            ) {
        RestaurantUpdateRequestDTO updateRequestDTO = new RestaurantUpdateRequestDTO(id, restaurantRequest);
        return this.updateRestaurantUseCase.execute(updateRequestDTO);
    }

    @Operation(
            summary = "Exclua um restaurante",
            description = "Exclua um restaurante, informe o id do restaurante",
            tags = {"Restaurants"}
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRestaurant(
            @PathVariable("id") Long id
    ) {
        this.deleteRestaurantUseCase.execute(id);
    }
}
