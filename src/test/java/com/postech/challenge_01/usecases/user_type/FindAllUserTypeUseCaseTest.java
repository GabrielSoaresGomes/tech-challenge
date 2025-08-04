package com.postech.challenge_01.usecases.user_type;

import com.postech.challenge_01.application.gateways.IUserTypeGateway;
import com.postech.challenge_01.application.usecases.user_type.FindAllUserTypeUseCase;
import com.postech.challenge_01.builder.user_type.UserTypeBuilder;
import com.postech.challenge_01.domain.UserType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class FindAllUserTypeUseCaseTest {

    @Mock
    private IUserTypeGateway gateway;

    @InjectMocks
    private FindAllUserTypeUseCase useCase;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        useCase = new FindAllUserTypeUseCase(gateway);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void shouldReturnListOfUserTypes() {
        Pageable pageable = PageRequest.of(0, 10);

        UserType userType1 = UserTypeBuilder
                .oneUserType()
                .withId(1L)
                .withName("Admin")
                .build();

        UserType userType2 = UserTypeBuilder
                .oneUserType()
                .withId(2L)
                .withName("Client")
                .build();

        List<UserType> userTypes = List.of(userType1, userType2);

        when(gateway.findAll(pageable)).thenReturn(userTypes);

        List<UserType> result = useCase.execute(pageable);

        verify(gateway, times(1)).findAll(pageable);

        assertEquals(userTypes.size(), result.size());
        assertEquals(userType1.getId(), result.get(0).getId());
        assertEquals(userType1.getName(), result.get(0).getName());
        assertEquals(userType2.getId(), result.get(1).getId());
        assertEquals(userType2.getName(), result.get(1).getName());
    }

    @Test
    void shouldThrowExceptionWhenGatewayFails() {
        Pageable pageable = PageRequest.of(0, 10);

        when(gateway.findAll(pageable)).thenThrow(new RuntimeException("Erro no gateway"));

        RuntimeException ex = assertThrows(RuntimeException.class, () -> useCase.execute(pageable));

        assertEquals("Erro no gateway", ex.getMessage());

        verify(gateway, times(1)).findAll(pageable);
    }
}
