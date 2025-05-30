package com.postech.challenge_01.repositories;

import com.postech.challenge_01.domains.Address;
import com.postech.challenge_01.entities.AddressEntity;
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
public class AddressRepositoryImp implements AddressRepository {
    private final JdbcClient jdbcClient;

    public AddressRepositoryImp(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<Address> findById(Long id) {
        String sql = "SELECT * FROM addresses WHERE id = :id";

        var opAddressEntity = this.jdbcClient
                .sql(sql)
                .param("id", id)
                .query(AddressEntity.class)
                .optional();

        return opAddressEntity.map(AddressEntity::toAddress);
    }

    @Override
    public Optional<Address> findByIdAndUserId(Long id, Long userId) {
        String sql = "SELECT * FROM addresses WHERE id = :id and userId = :userId";

        var opAddressEntity = this.jdbcClient
                .sql(sql)
                .param("id", id)
                .param("userId", userId)
                .query(AddressEntity.class)
                .optional();

        return opAddressEntity.map(AddressEntity::toAddress);
    }

    @Override
    public List<Address> findAll(int size, int offset) {
        String sql = "SELECT * FROM addresses LIMIT :size OFFSET :offset";

        var addressEntityList = this.jdbcClient
                .sql(sql)
                .param("size", size)
                .param("offset", offset)
                .query(AddressEntity.class)
                .list();

        return addressEntityList.stream().map(AddressEntity::toAddress).collect(Collectors.toList());
    }

    @Override
    public List<Address> findAllByUserId(Long userId, int size, int offset) {
        String sql = "SELECT * FROM addresses WHERE userId = :userId LIMIT :size OFFSET :offset";

        var addressEntityList = this.jdbcClient
                .sql(sql)
                .param("userId", userId)
                .param("size", size)
                .param("offset", offset)
                .query(AddressEntity.class)
                .list();

        return addressEntityList.stream().map(AddressEntity::toAddress).collect(Collectors.toList());
    }

    @Override
    public Address save(Address address) {
        var entity = AddressEntity.of(address);

        String sql = """
            INSERT INTO addresses (userId, street, number, complement, neighborhood, city, state, country, postalCode, lastModifiedDateTime)
            VALUES (:userId, :street, :number, :complement, :neighborhood, :city, :state, :country, :postalCode, :lastModifiedDateTime)
            """;

        var keyHolder = new GeneratedKeyHolder();
        Integer result = this.jdbcClient
                .sql(sql)
                .param("userId", entity.getUserId())
                .param("street", entity.getStreet())
                .param("number", entity.getNumber())
                .param("complement", entity.getComplement())
                .param("neighborhood", entity.getNeighborhood())
                .param("city", entity.getCity())
                .param("state", entity.getState())
                .param("country", entity.getCountry())
                .param("postalCode", entity.getPostalCode())
                .param("lastModifiedDateTime", entity.getLastModifiedDateTime())
                .update(keyHolder);
        if (result == 0) {
            return null;
        }
        var generatedId = this.getIdFromKeyHolder(keyHolder);

        var savedEntity = new AddressEntity(
                generatedId,
                entity.getUserId(),
                entity.getStreet(),
                entity.getNumber(),
                entity.getComplement(),
                entity.getNeighborhood(),
                entity.getCity(),
                entity.getState(),
                entity.getCountry(),
                entity.getPostalCode(),
                entity.getLastModifiedDateTime()
        );

        return savedEntity.toAddress();
    }

    @Override
    public Address update(Address address, Long id) {
        var entity = AddressEntity.of(address);

        String sql = """
            UPDATE addresses
            SET street = :street, number = :number, complement = :complement, neighborhood = :neighborhood,
                city = :city, state = :state, country = :country, postalCode = :postalCode, lastModifiedDateTime = :lastModifiedDateTime
            WHERE id = :id
            """;

        Integer result = this.jdbcClient
                .sql(sql)
                .param("street", entity.getStreet())
                .param("number", entity.getNumber())
                .param("complement", entity.getComplement())
                .param("neighborhood", entity.getNeighborhood())
                .param("city", entity.getCity())
                .param("state", entity.getState())
                .param("country", entity.getCountry())
                .param("postalCode", entity.getPostalCode())
                .param("lastModifiedDateTime", entity.getLastModifiedDateTime())
                .param("id", id)
                .update();

        if (result == 0) {
            return null;
        }
        return entity.toAddress();
    }

    @Override
    public Integer delete(Long id) {
        String sql = "DELETE FROM addresses WHERE id = :id";

        return this.jdbcClient
                .sql(sql)
                .param("id", id)
                .update();
    }

    @Override
    public void deleteByUserId(Long userId) {
        String sql = "DELETE FROM addresses WHERE userId = :userId";

        this.jdbcClient
                .sql(sql)
                .param("userId", userId)
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
