package com.postech.challenge_01.repositories;

import com.postech.challenge_01.entities.User;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImp implements UserRepository {
    private final JdbcClient jdbcClient;

    public UserRepositoryImp(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<User> findById(Long id) { // IMPLEMENTAR
        return Optional.empty();
    }

    @Override
    public Optional<User> findByLogin(String login) {
        String sql = "SELECT id, name, email, login, password FROM users WHERE login = :login";

        return jdbcClient
                .sql(sql)
                .param("login", login)
                .query(User.class)
                .optional();
    }

    @Override
    public List<User> findAll() { // IMPLEMENTAR
        return List.of();
    }

    @Override
    public User save(User user) {
        String sql = """
            INSERT INTO users (name, email, login, password)
            VALUES (:name, :email, :login, :password)
        """;

        var keyHolder = new GeneratedKeyHolder();
        this.jdbcClient
                .sql(sql)
                .param("name", user.getName())
                .param("email", user.getEmail())
                .param("login", user.getLogin())
                .param("password", user.getPassword())
                .update(keyHolder);

        var generatedId = keyHolder.getKeyAs(Long.class);
        user.setId(generatedId);

        return user;
    }

    @Override
    public User update(User user, Long id) {
        String sql = """
            UPDATE users
            SET name = :name, email = :email, login = :login, password = :password
            WHERE id = :id
        """;

        this.jdbcClient
                .sql(sql)
                .param("name", user.getName())
                .param("email", user.getEmail())
                .param("login", user.getLogin())
                .param("password", user.getPassword())
                .param("id", id)
                .update();

        return user;
    }

    @Override
    public Integer delete(Long id) {
        String sql = "DELETE FROM users WHERE id = :id";

        return this.jdbcClient
                .sql(sql)
                .param("id", id)
                .update();
    }
}
