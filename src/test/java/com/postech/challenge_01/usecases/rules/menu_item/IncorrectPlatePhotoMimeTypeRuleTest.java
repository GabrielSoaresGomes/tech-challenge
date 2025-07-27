package com.postech.challenge_01.usecases.rules.menu_item;

import com.postech.challenge_01.builder.menu_item.MenuItemBuilder;
import com.postech.challenge_01.exceptions.IncorrectFileTypeException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IncorrectPlatePhotoMimeTypeRuleTest {
    @InjectMocks
    private IncorrectPlatePhotoMimeTypeRule rule;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        this.closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void shouldBeAllowedMimeType() {
        // Arrange
        var menuItem = MenuItemBuilder.oneMenuItem().withPlatePhotoMimeType("image/png").build();

        // Act + Assert
        assertDoesNotThrow(() -> this.rule.execute(menuItem));
    }

    @Test
    void shouldBeIncorrectMimeTypeAndThrowError() {
        // Arrange
        var menuItem = MenuItemBuilder.oneMenuItem().withPlatePhotoMimeType("application/pdf").build();

        // Act + Assert
        assertThrows(IncorrectFileTypeException.class, () -> this.rule.execute(menuItem));
    }
}