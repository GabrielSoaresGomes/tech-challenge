package com.postech.challenge_01.repositories.restaurant;

import com.postech.challenge_01.domains.Restaurant;
import com.postech.challenge_01.entities.RestaurantEntity;
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
public class RestaurantRepositoryImp implements RestaurantRepository {
    private final JdbcClient jdbcClient;

    public RestaurantRepositoryImp(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<Restaurant> findById(Long id) {
        String sql = "SELECT * FROM restaurants WHERE id = :id";

        var opRestaurantEntity = this.jdbcClient
                .sql(sql)
                .param("id", id)
                .query(RestaurantEntity.class)
                .optional();

        return opRestaurantEntity.map(RestaurantEntity::toRestaurant);
    }

    @Override
    public List<Restaurant> findAll(int size, long offset) {
        String sql = "SELECT * FROM restaurants LIMIT :size OFFSET :offset";

        var restaurantEntityList = this.jdbcClient
                .sql(sql)
                .param("size", size)
                .param("offset", offset)
                .query(RestaurantEntity.class)
                .list();

        return restaurantEntityList.stream()
                .map(RestaurantEntity::toRestaurant)
                .collect(Collectors.toList());
    }

    @Override
    public List<Restaurant> findAllOpen(int size, long offset) {
        String sql = """
            SELECT * FROM restaurants
            WHERE CURRENT_TIME BETWEEN startTime AND endTime
            LIMIT :size OFFSET :offset
        """;

        var restaurantEntityList = this.jdbcClient
                .sql(sql)
                .param("size", size)
                .param("offset", offset)
                .query(RestaurantEntity.class)
                .list();

        return restaurantEntityList.stream()
                .map(RestaurantEntity::toRestaurant)
                .collect(Collectors.toList());
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        var entity = RestaurantEntity.of(restaurant);

        String sql = """
            INSERT INTO restaurants (ownerId, addressId, name, type, startTime, endTime, lastModifiedDateTime)
            VALUES (:ownerId, :addressId, :name, :type, :startTime, :endTime, :lastModifiedDateTime)
        """;

        var keyHolder = new GeneratedKeyHolder();
        int result = this.jdbcClient
                .sql(sql)
                .param("ownerId", entity.getOwner().getId())
                .param("addressId", entity.getAddress().getId())
                .param("name", entity.getName())
                .param("type", entity.getType())
                .param("startTime", entity.getStartTime())
                .param("endTime", entity.getEndTime())
                .param("lastModifiedDateTime", entity.getLastModifiedDateTime())
                .update(keyHolder);

        if (result == 0) {
            return null;
        }
        var generatedId = this.getIdFromKeyHolder(keyHolder);

        var savedEntity = new RestaurantEntity(
                generatedId,
                entity.getOwner(),
                entity.getAddress(),
                entity.getName(),
                entity.getType(),
                entity.getStartTime(),
                entity.getEndTime(),
                entity.getLastModifiedDateTime()
        );

        return savedEntity.toRestaurant();
    }

    @Override
    public Optional<Restaurant> update(Restaurant restaurant, Long id) {
        var entity = RestaurantEntity.of(restaurant);

        String sql = """
            UPDATE restaurants
            SET ownerId = :ownerId, addressId = :addressId, name = :name, type = :type,
                startTime = :startTime, endTime = :endTime, lastModifiedDateTime = :lastModifiedDateTime
            WHERE id = :id
        """;

        int result = this.jdbcClient
                .sql(sql)
                .param("id", id)
                .param("ownerId", entity.getOwner().getId())
                .param("addressId", entity.getAddress().getId())
                .param("name", entity.getName())
                .param("type", entity.getType())
                .param("startTime", entity.getStartTime())
                .param("endTime", entity.getEndTime())
                .param("lastModifiedDateTime", entity.getLastModifiedDateTime())
                .update();

        if (result == 0) {
            return Optional.empty();
        }

        return Optional.of(entity.toRestaurant());
    }

    @Override
    public Integer delete(Long id) {
        String sql = "DELETE FROM restaurants WHERE id = :id";

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
