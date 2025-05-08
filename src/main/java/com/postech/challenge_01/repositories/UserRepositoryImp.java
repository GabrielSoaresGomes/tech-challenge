package com.postech.challenge_01.repositories;

import com.postech.challenge_01.dtos.responses.UserResponseDTO;
import com.postech.challenge_01.entities.User;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class UserRepositoryImp implements UserRepository {
    private final JdbcClient jdbcClient;

    public UserRepositoryImp(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<UserResponseDTO> findById(Long id) {
        String sql = "SELECT * FROM users WHERE id = :id";

        return jdbcClient
                .sql(sql)
                .param("id", id)
                .query(UserResponseDTO.class)
                .optional();
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
    public List<UserResponseDTO> findAll(int size, int offset) {
        String sql = "SELECT * FROM users LIMIT :size OFFSET :offset";

        return jdbcClient
                .sql(sql)
                .param("size", size)
                .param("offset", offset)
                .query(UserResponseDTO.class)
                .list();
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

        var generatedId = (Long) Objects.requireNonNull(keyHolder.getKeys()).get("id");
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
    public User delete(Long id) { // IMPLEMENTAR
        return null;
    }
}
