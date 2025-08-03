package com.postech.challenge_01.interface_adapter.data_sources.repositories.user_type;

import com.postech.challenge_01.domain.enums.UserTypeEnum;
import com.postech.challenge_01.dtos.transfer.user_type.NewUserTypeDTO;
import com.postech.challenge_01.interface_adapter.data_sources.repositories.UserTypeRepository;
import com.postech.challenge_01.interface_adapter.data_sources.repositories.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserTypeRepositoryIT extends BaseIntegrationTest {

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
