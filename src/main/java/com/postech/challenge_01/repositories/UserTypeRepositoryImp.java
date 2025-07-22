package com.postech.challenge_01.repositories;

import com.postech.challenge_01.domains.UserType;
import com.postech.challenge_01.entities.UserTypeEntity;
import com.postech.challenge_01.exceptions.IdNotReturnedException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserTypeRepositoryImp implements UserTypeRepository {
    private final JdbcClient jdbcClient;

    public UserTypeRepositoryImp(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<UserType> findById(Long id) {
        String sql = "SELECT * FROM user_type WHERE id = :id";

        var opUserTypeEntity = jdbcClient
                .sql(sql)
                .param("id", id)
                .query(UserTypeEntity.class)
                .optional();

        return opUserTypeEntity.map(UserTypeEntity::toUserType);
    }

    @Override
    public Optional<UserType> findByName(String name) {
        String sql = "SELECT * FROM user_type WHERE name = :name";

        var opUserTypeEntity = jdbcClient
                .sql(sql)
                .param("name", name)
                .query(UserTypeEntity.class)
                .optional();

        return opUserTypeEntity.map(UserTypeEntity::toUserType);
    }


    @Override
    public List<UserType> findAll(int size, long offset) {
        String sql = "SELECT * FROM user_type LIMIT :size OFFSET :offset";

        var opUserTypeEntity = jdbcClient
                .sql(sql)
                .param("size", size)
                .param("offset", offset)
                .query(UserTypeEntity.class)
                .list();

        return opUserTypeEntity.stream().map(UserTypeEntity::toUserType).collect(Collectors.toList());
    }

    @Override
    public UserType save(UserType userType) {
        var entity = UserTypeEntity.of(userType);

        String sql = """
                    INSERT INTO user_type (name, lastModifiedDateTime)
                    VALUES (:name, :lastModifiedDateTime)
                """;

        var keyHolder = new GeneratedKeyHolder();
        Integer result = this.jdbcClient
                .sql(sql)
                .param("name", entity.getName())
                .param("lastModifiedDateTime", entity.getLastModifiedDateTime())
                .update(keyHolder);
        if (result == 0) {
            return null;
        }
        var generatedId = this.getIdFromKeyHolder(keyHolder);

        var savedEntity = new UserTypeEntity(
                generatedId,
                userType.getName(),
                userType.getLastModifiedDateTime()
        );

        return savedEntity.toUserType();
    }

    @Override
    public Optional<UserType> update(UserType userType, Long id) {
        var entity = UserTypeEntity.of(userType);

        String sql = """
                    UPDATE user_type
                    SET name = :name, lastModifiedDateTime = :lastModifiedDateTime
                    WHERE id = :id
                """;

        Integer result = this.jdbcClient
                .sql(sql)
                .param("name", entity.getName())
                .param("lastModifiedDateTime", entity.getLastModifiedDateTime())
                .param("id", id)
                .update();
        if (result == 0) {
            return Optional.empty();
        }

        return Optional.of(entity.toUserType());
    }

    @Override
    public Integer delete(Long id) {
        String sql = "DELETE FROM user_type WHERE id = :id";

        return this.jdbcClient
                .sql(sql)
                .param("id", id)
                .update();
    }

    private Long getIdFromKeyHolder(GeneratedKeyHolder keyHolder) {
        Map<String, Object> keys = keyHolder.getKeys();

        if (Objects.isNull(keys) || !keys.containsKey("id")) {
            throw new IdNotReturnedException();
        }

        return ((Number) keys.get("id")).longValue();
    }
}
