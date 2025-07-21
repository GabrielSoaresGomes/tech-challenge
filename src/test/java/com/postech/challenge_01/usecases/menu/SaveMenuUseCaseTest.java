package com.postech.challenge_01.usecases.menu;

import com.postech.challenge_01.domains.menu.Menu;
import com.postech.challenge_01.dtos.requests.menu.MenuRequestDTO;
import com.postech.challenge_01.exceptions.RestaurantNotFoundException;
import com.postech.challenge_01.mappers.menu.MenuMapper;
import com.postech.challenge_01.repositories.menu.MenuRepository;
import com.postech.challenge_01.usecases.rules.menu.ExistsRestaurantRule;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Slf4j
class SaveMenuUseCaseTest {
    @Mock
    private MenuRepository menuRepository;

    @Mock
    private ExistsRestaurantRule existsRestaurantRule;

    @InjectMocks
    private SaveMenuUseCase useCase;

    private MenuRequestDTO requestDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(this.useCase, "rules", List.of(this.existsRestaurantRule));

        var restaurantId = 1L;
        this.requestDTO = new MenuRequestDTO(restaurantId);
    }

    @Test
    void shouldCreateAndSaveSuccessfully() {
        // Arrange
        var savedMenu = MenuMapper.menuRequestDTOToMenu(this.requestDTO);

        doNothing().when(this.existsRestaurantRule).execute(any(Menu.class));
        when(this.menuRepository.save(any(Menu.class))).thenReturn(savedMenu);

        // Act
        var response = this.useCase.execute(this.requestDTO);

        // Assert
        verify(this.existsRestaurantRule).execute(any(Menu.class));
        verify(this.menuRepository).save(any(Menu.class));

        assertNotNull(response);
        assertEquals(this.requestDTO.restaurantId(), response.restaurantId());
    }

    @Test
    void shouldCreateAndFailRuleRestaurantExists() {
        // Arrange
        doThrow(RestaurantNotFoundException.class).when(this.existsRestaurantRule).execute(any(Menu.class));
        when(this.menuRepository.save(any(Menu.class))).thenReturn(null);

        // Assert
        assertThrows(RestaurantNotFoundException.class, () -> this.useCase.execute(this.requestDTO));

        verify(this.existsRestaurantRule).execute(any(Menu.class));
        verify(this.menuRepository, never()).save(any(Menu.class));
    }
}