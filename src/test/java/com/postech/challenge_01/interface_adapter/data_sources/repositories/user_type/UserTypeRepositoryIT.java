package com.postech.challenge_01.interface_adapter.data_sources.repositories.user_type;

import com.postech.challenge_01.domain.enums.UserTypeEnum;
import com.postech.challenge_01.dtos.transfer.user_type.NewUserTypeDTO;
import com.postech.challenge_01.interface_adapter.data_sources.repositories.UserTypeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringBootTest
public class UserTypeRepositoryIT {

    @Container
    public static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:16-alpine")
            .withDatabaseName("tech-challenge-01")
            .withUsername("root")
            .withPassword("root");

    @DynamicPropertySource
    public static void configure(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresContainer::getUsername);
        registry.add("spring.datasource.password", postgresContainer::getPassword);
    }

    @Autowired
    private UserTypeRepository repository;

    @Test
    void shoudFindByName() {
        //Arrange
        var name = "ownerType";
        var entity = new NewUserTypeDTO(name, UserTypeEnum.Owner);
        repository.save(entity);

        //Act
        var opEntityReturned = repository.findByName(name);

        //Assert
        assertTrue(opEntityReturned.isPresent());
        assertNotNull(opEntityReturned.get().id());
    }

    @Test
    void shoudNotFindByName() {
        //Arrange
        var rightName = "clientType";
        var wrongName = "clientType2";
        var entity = new NewUserTypeDTO(rightName, UserTypeEnum.Client);
        repository.save(entity);

        //Act
        var opEntityReturned = repository.findByName(wrongName);

        //Assert
        assertTrue(opEntityReturned.isEmpty());
    }
}
