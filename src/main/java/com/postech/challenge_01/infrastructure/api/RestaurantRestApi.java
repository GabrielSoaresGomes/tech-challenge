package com.postech.challenge_01.infrastructure.api;

import com.postech.challenge_01.dtos.requests.restaurant.RestaurantRequestDTO;
import com.postech.challenge_01.dtos.requests.restaurant.RestaurantUpdateDataDTO;
import com.postech.challenge_01.dtos.responses.RestaurantResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Restaurants", description = "Endpoints para gerenciamento de restaurantes")
public interface RestaurantRestApi {
    @Operation(
            summary = "Busca por todos os restaurantes",
            description = "Busca por todos os restaurantes, informe o número de restaurantes exibidos por página",
            tags = {"Restaurants"}
    )
    public List<RestaurantResponseDTO> getRestaurants(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam(required = false, defaultValue = "false", name = "onlyOpen") Boolean onlyOpen
    );

    @Operation(
            summary = "Busca por somente um restaurante",
            description = "Busca restaurante pelo id, informe id do restaurante",
            tags = {"Restaurants"}
    )
    public RestaurantResponseDTO getRestaurantById(
            @PathVariable("id") Long id
    );

    @Operation(
            summary = "Cria um restaurante",
            description = "Cria um restaurante, informe dono, endereço, tipo, horário de abertura e fechamento",
            tags = {"Restaurants"}
    )
    public RestaurantResponseDTO saveRestaurant(
            @RequestBody @Valid RestaurantRequestDTO restaurantRequest
    );

    @Operation(
            summary = "Atualize um restaurante",
            description = "Atualize um restaurante, informe os campos atualizados",
            tags = {"Restaurants"}
    )
    public RestaurantResponseDTO updateRestaurant(
            @RequestBody @Valid RestaurantUpdateDataDTO restaurantRequest,
            @PathVariable("id") Long id
    );

    @Operation(
            summary = "Exclua um restaurante",
            description = "Exclua um restaurante, informe o id do restaurante",
            tags = {"Restaurants"}
    )
    public void deleteRestaurant(
            @PathVariable("id") Long id
    );
}
