package com.postech.challenge_01.repositories;

import com.postech.challenge_01.entities.Address;
import com.postech.challenge_01.entities.User;
import com.postech.challenge_01.exceptions.IdNotReturnedException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Repository
public class AddressRepositoryImp implements AddressRepository {
    private final JdbcClient jdbcClient;

    public AddressRepositoryImp(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<Address> findById(Long id) {
        String sql = "SELECT * FROM addresses WHERE id = :id";

        return this.jdbcClient
                .sql(sql)
                .param("id", id)
                .query(Address.class)
                .optional();
    }

    @Override
    public List<Address> findAll(int size, int offset) {
        String sql = "SELECT * FROM addresses LIMIT :size OFFSET :offset";

        return this.jdbcClient
                .sql(sql)
                .param("size", size)
                .param("offset", offset)
                .query(Address.class)
                .list();
    }

    @Override
    public Address save(Address address) {
        String sql = """
            INSERT INTO addresses (street, number, complement, neighborhood, city, state, country, postalCode, lastModifiedDateTime)
            VALUES (:street, :number, :complement, :neighborhood, :city, :state, :country, :postalCode, :lastModifiedDateTime)
            """;

        var keyHolder = new GeneratedKeyHolder();
        Integer result = this.jdbcClient
                .sql(sql)
                .param("street", address.getStreet())
                .param("number", address.getNumber())
                .param("complement", address.getComplement())
                .param("neighborhood", address.getNeighborhood())
                .param("city", address.getCity())
                .param("state", address.getState())
                .param("country", address.getCountry())
                .param("postalCode", address.getPostalCode())
                .param("lastModifiedDateTime", address.getLastModifiedDateTime())
                .update(keyHolder);
        if (result == 0) {
            return null;
        }
        var generatedId = this.getIdFromKeyHolder(keyHolder);

        return new Address(
                generatedId,
                address.getStreet(),
                address.getNumber(),
                address.getComplement(),
                address.getNeighborhood(),
                address.getCity(),
                address.getState(),
                address.getCountry(),
                address.getPostalCode(),
                address.getLastModifiedDateTime()
        );
    }

    @Override
    public Address update(Address address, Long id) {
        String sql = """
            UPDATE addresses
            SET street = :street, number = :number, complement = :complement, neighborhood = :neighborhood,
                city = :city, state = :state, country = :country, postalCode = :postalCode, lastModifiedDateTime = :lastModifiedDateTime
            WHERE id = :id
            """;

        Integer result = this.jdbcClient
                .sql(sql)
                .param("street", address.getStreet())
                .param("number", address.getNumber())
                .param("complement", address.getComplement())
                .param("neighborhood", address.getNeighborhood())
                .param("city", address.getCity())
                .param("state", address.getState())
                .param("country", address.getCountry())
                .param("postalCode", address.getPostalCode())
                .param("lastModifiedDateTime", address.getLastModifiedDateTime())
                .param("id", id)
                .update();

        if (result == 0) {
            return null;
        }
        return address;
    }

    @Override
    public Integer delete(Long id) {
        String sql = "DELETE FROM addresses WHERE id = :id";

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
