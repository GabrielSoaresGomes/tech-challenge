package com.postech.challenge_01.repositories.menu;

import com.postech.challenge_01.domains.Menu;
import com.postech.challenge_01.entities.MenuEntity;
import com.postech.challenge_01.exceptions.IdNotReturnedException;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
class MenuRepositoryImpl implements MenuRepository {
    private final JdbcClient jdbcClient;

    @Override
    public Optional<Menu> findById(Long id) {
        var sql = "SELECT id, restaurantId, lastModifiedDateTime FROM menus WHERE id = :id";

        return this.jdbcClient
                .sql(sql)
                .param("id", id)
                .query(MenuEntity.class)
                .optional()
                .map(MenuEntity::toMenu);
    }

    @Override
    public List<Menu> findAll(int size, long offset) {
        var sql = """
                SELECT
                    id,
                    restaurantId,
                    lastModifiedDateTime
                FROM menus
                LIMIT :size
                OFFSET :offset
                """;

        var entityList = this.jdbcClient
                .sql(sql)
                .param("size", size)
                .param("offset", offset)
                .query(MenuEntity.class)
                .list();

        return entityList
                .stream()
                .map(MenuEntity::toMenu)
                .toList();
    }

    @Override
    public Menu save(Menu menu) {
        var entity = MenuEntity.of(menu);

        var sql = """
                INSERT INTO menus (restaurantId, lastModifiedDateTime)
                VALUES (:restaurantId, :lastModifiedDateTime)
                """;
        var keyHolder = new GeneratedKeyHolder();
        var result = this.jdbcClient
                .sql(sql)
                .param("restaurantId", entity.getRestaurantId())
                .param("lastModifiedDateTime", entity.getLastModifiedDateTime())
                .update(keyHolder);

        if (result == 0) {
            return null;
        }

        var generatedId = this.getIdFromKeyHolder(keyHolder);
        var savedEntity = new MenuEntity(
                generatedId,
                entity.getRestaurantId(),
                entity.getLastModifiedDateTime()
        );

        return savedEntity.toMenu();
    }

    @Override
    public Optional<Menu> update(Menu menu, Long id) {
        throw new UnsupportedOperationException("A entidade MenuEntity não pode ser alterada.");
    }

    @Override
    public Integer delete(Long id) {
        var sql = "DELETE FROM menus WHERE id = :id";

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
