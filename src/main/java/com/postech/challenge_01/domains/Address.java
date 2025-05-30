package com.postech.challenge_01.domains;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
@ToString
public class Address {
    private Long id;
    private Long userId;
    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private LocalDateTime lastModifiedDateTime;

    public Address(
            Long id,
            @NonNull Long userId,
            @NonNull String street,
            @NonNull String number,
            String complement,
            @NonNull String neighborhood,
            @NonNull String city,
            @NonNull String state,
            @NonNull String country,
            @NonNull String postalCode,
            @NonNull LocalDateTime lastModifiedDateTime
    ) {
        this.id = id;
        this.userId = userId;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    public Address(
            Long id,
            @NonNull Long userId,
            @NonNull String street,
            @NonNull String number,
            String complement,
            @NonNull String neighborhood,
            @NonNull String city,
            @NonNull String state,
            @NonNull String country,
            @NonNull String postalCode
    ) {
        this(id, userId, street, number, complement, neighborhood, city, state, country, postalCode, LocalDateTime.now());
    }

    public Address(
            @NonNull Long userId,
            @NonNull String street,
            @NonNull String number,
            String complement,
            @NonNull String neighborhood,
            @NonNull String city,
            @NonNull String state,
            @NonNull String country,
            @NonNull String postalCode
    ) {
        this(null, userId, street, number, complement, neighborhood, city, state, country, postalCode, LocalDateTime.now());
    }
}
