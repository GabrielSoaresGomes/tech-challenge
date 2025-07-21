package com.postech.challenge_01.repositories.menu_item;

import com.postech.challenge_01.domains.menu_item.MenuItem;
import com.postech.challenge_01.entities.menu_item.MenuItemEntity;
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
class MenuItemRepositoryImpl implements MenuItemRepository {
    private final JdbcClient jdbcClient;

    @Override
    public Optional<MenuItem> findById(Long id) {
        var sql = """
                SELECT
                    id,
                    menuId,
                    name,
                    description,
                    dineInOnly,
                    platePhoto,
                    lastModifiedDateTime
                FROM menuItem
                WHERE id = :id
                """;

        return this.jdbcClient
                .sql(sql)
                .param("id", id)
                .query(MenuItemEntity.class)
                .optional()
                .map(MenuItemEntity::toMenuItem);
    }

    @Override
    public List<MenuItem> findAll(int size, long offset) {
        var sql = """
                SELECT
                    id,
                    menuId,
                    name,
                    description,
                    dineInOnly,
                    platePhoto,
                    lastModifiedDateTime
                FROM menuItem
                LIMIT :size
                OFFSET :offset
                """;

        var entityList = this.jdbcClient
                .sql(sql)
                .param("size", size)
                .param("offset", offset)
                .query(MenuItemEntity.class)
                .list();

        return entityList
                .stream()
                .map(MenuItemEntity::toMenuItem)
                .toList();
    }

    @Override
    public MenuItem save(MenuItem menuItem) {
        var entity = MenuItemEntity.of(menuItem);

        var sql = """
                INSERT INTO menuItem (menuId, name, description, dineInOnly, platePhoto, lastModifiedDateTime)
                VALUES (:menuId, :name, :description, :dineInOnly, :platePhoto, :lastModifiedDateTime)
                """;

        var keyHolder = new GeneratedKeyHolder();
        var result = this.jdbcClient
                .sql(sql)
                .param("menuId", entity.getMenuId())
                .param("name", entity.getName())
                .param("description", entity.getDescription())
                .param("dineInOnly", entity.getDineInOnly())
                .param("platePhoto", entity.getPlatePhoto())
                .param("lastModifiedDateTime", entity.getLastModifiedDateTime())
                .update(keyHolder);

        if (result == 0) {
            return null;
        }

        var generatedId = this.getIdFromKeyHolder(keyHolder);
        var savedEntity = new MenuItemEntity(
                generatedId,
                entity.getMenuId(),
                entity.getName(),
                entity.getDescription(),
                entity.getDineInOnly(),
                entity.getPlatePhoto(),
                entity.getLastModifiedDateTime()
        );

        return savedEntity.toMenuItem();
    }

    @Override
    public Optional<MenuItem> update(MenuItem menuItem, Long id) {
        var entity = MenuItemEntity.of(menuItem);

        var sql = """
                UPDATE menuItem
                SET name = :name,
                    description = :description,
                    dineInOnly = :dineInOnly,
                    platePhoto = :platePhoto,
                    lastModifiedDateTime = :lastModifiedDateTime
                WHERE id = :d
                """;

        var keyHolder = new GeneratedKeyHolder();
        var result = this.jdbcClient
                .sql(sql)
                .param("name", entity.getName())
                .param("description", entity.getDescription())
                .param("dineInOnly", entity.getDineInOnly())
                .param("platePhoto", entity.getPlatePhoto())
                .param("lastModifiedDateTime", entity.getLastModifiedDateTime())
                .param("id", entity.getId())
                .update(keyHolder);

        if (result == 0) {
            return Optional.empty();
        }

        return Optional.of(entity.toMenuItem());
    }

    @Override
    public Integer delete(Long id) {
        String sql = "DELETE FROM menuItem WHERE id = :id";

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
