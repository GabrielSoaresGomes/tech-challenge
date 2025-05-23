package com.postech.challenge_01.entities;

import com.postech.challenge_01.dtos.requests.AddressRequestDTO;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Address {
    private Long id;
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
            @NonNull String street,
            @NonNull String number,
            String complement,
            @NonNull String neighborhood,
            @NonNull String city,
            @NonNull String state,
            @NonNull String country,
            @NonNull String postalCode
    ) {
        this(id, street, number, complement, neighborhood, city, state, country, postalCode, LocalDateTime.now());
    }

    public Address(
            @NonNull String street,
            @NonNull String number,
            String complement,
            @NonNull String neighborhood,
            @NonNull String city,
            @NonNull String state,
            @NonNull String country,
            @NonNull String postalCode
    ) {
        this(null, street, number, complement, neighborhood, city, state, country, postalCode, LocalDateTime.now());
    }
}
