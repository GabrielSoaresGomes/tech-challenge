package com.postech.challenge_01.repositories;

import com.postech.challenge_01.entities.User;
import org.springframework.jdbc.core.simple.JdbcClient;
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
    public List<User> findAll() { // IMPLEMENTAR
        return List.of();
    }

    @Override
    public User save(User user) {
        String sql = """
            INSERT INTO users (name, email, login, password)
            VALUES (:name, :email, :login, :password)
        """;

        this.jdbcClient
                .sql(sql)
                .param("name", user.getName())
                .param("email", user.getEmail())
                .param("login", user.getLogin())
                .param("password", user.getPassword())
                .update();

        Long id = this.jdbcClient.sql("SELECT id FROM users ORDER BY 1 DESC LIMIT 1") // TODO - Quando alterar o banco de dados, utilizar o RETURNING id na query acima
                .query(Long.class)
                .single();

        user.setId(id);
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
