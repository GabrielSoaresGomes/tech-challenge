package com.postech.challenge_01.repositories.user;

import com.postech.challenge_01.domains.User;
import com.postech.challenge_01.entities.UserEntity;
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
public class UserRepositoryImp implements UserRepository {
    private final JdbcClient jdbcClient;

    public UserRepositoryImp(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<User> findById(Long id) {
        String sql = "SELECT * FROM users WHERE id = :id";

        var opUserEntity = jdbcClient
                .sql(sql)
                .param("id", id)
                .query(UserEntity.class)
                .optional();

        return opUserEntity.map(UserEntity::toUser);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        String sql = "SELECT id, userTypeId, name, email, login, password, lastModifiedDateTime FROM users WHERE login = :login";

        var opUserEntity = jdbcClient
                .sql(sql)
                .param("login", login)
                .query(UserEntity.class)
                .optional();

        return opUserEntity.map(UserEntity::toUser);
    }

    @Override
    public List<User> findAll(int size, long offset) {
        String sql = "SELECT * FROM users LIMIT :size OFFSET :offset";

        var opUserEntity = jdbcClient
                .sql(sql)
                .param("size", size)
                .param("offset", offset)
                .query(UserEntity.class)
                .list();

        return opUserEntity.stream().map(UserEntity::toUser).collect(Collectors.toList());
    }

    @Override
    public User save(User user) {
        var entity = UserEntity.of(user);

        String sql = """
                    INSERT INTO users (userTypeId, name, email, login, password, lastModifiedDateTime)
                    VALUES (:userTypeId, :name, :email, :login, :password, :lastModifiedDateTime)
                """;

        var keyHolder = new GeneratedKeyHolder();
        Integer result = this.jdbcClient
                .sql(sql)
                .param("userTypeId", entity.getUserTypeId())
                .param("name", entity.getName())
                .param("email", entity.getEmail())
                .param("login", entity.getLogin())
                .param("password", entity.getPassword())
                .param("lastModifiedDateTime", entity.getLastModifiedDateTime())
                .update(keyHolder);
        if (result == 0) {
            return null;
        }
        var generatedId = this.getIdFromKeyHolder(keyHolder);

        var savedEntity = new UserEntity();
        savedEntity.setId(generatedId);
        savedEntity.setUserTypeId(user.getUserTypeId());
        savedEntity.setName(user.getName());
        savedEntity.setEmail(user.getEmail());
        savedEntity.setLogin(user.getLogin());
        savedEntity.setPassword(user.getPassword());
        savedEntity.setLastModifiedDateTime(user.getLastModifiedDateTime());

        return savedEntity.toUser();
    }

    @Override
    public Optional<User> update(User user, Long id) {
        var entity = UserEntity.of(user);

        String sql = """
                    UPDATE users
                    SET userTypeId = :userTypeId, name = :name, email = :email, login = :login, password = :password, lastModifiedDateTime = :lastModifiedDateTime
                    WHERE id = :id
                """;

        Integer result = this.jdbcClient
                .sql(sql)
                .param("userTypeId", entity.getUserTypeId())
                .param("name", entity.getName())
                .param("email", entity.getEmail())
                .param("login", entity.getLogin())
                .param("password", entity.getPassword())
                .param("lastModifiedDateTime", entity.getLastModifiedDateTime())
                .param("id", id)
                .update();
        if (result == 0) {
            return Optional.empty();
        }

        return Optional.of(entity.toUser());
    }

    @Override
    public Integer delete(Long id) {
        String sql = "DELETE FROM users WHERE id = :id";

        return this.jdbcClient
                .sql(sql)
                .param("id", id)
                .update();
    }

    @Override
    public boolean updatePassword(Long id, String password) {
        int rows = jdbcClient.sql("UPDATE users SET password = :password WHERE id = :id")
                .param("password", password)
                .param("id", id)
                .update();

        return rows > 0;
    }

    private Long getIdFromKeyHolder(GeneratedKeyHolder keyHolder) {
        Map<String, Object> keys = keyHolder.getKeys();

        if (Objects.isNull(keys) || !keys.containsKey("id")) {
            throw new IdNotReturnedException();
        }

        return ((Number) keys.get("id")).longValue();
    }
}
