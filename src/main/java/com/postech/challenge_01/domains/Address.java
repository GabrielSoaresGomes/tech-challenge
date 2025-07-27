package com.postech.challenge_01.domains;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
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

    @JsonCreator
    public Address(
            @JsonProperty("street") String street,
            @JsonProperty("number") String number,
            @JsonProperty("complement") String complement,
            @JsonProperty("neighborhood") String neighborhood,
            @JsonProperty("city") String city,
            @JsonProperty("state") String state,
            @JsonProperty("country") String country,
            @JsonProperty("postalCode") String postalCode
    ) {
        this.id = null;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
        this.lastModifiedDateTime = LocalDateTime.now();
    }
}
