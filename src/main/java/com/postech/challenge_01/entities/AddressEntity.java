package com.postech.challenge_01.entities;

import com.postech.challenge_01.domains.Address;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AddressEntity {
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

    public static AddressEntity of(final Address address) {
        return new AddressEntity(
                address.getId(),
                address.getUserId(),
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

    public Address toAddress() {
        return new Address(
                this.getId(),
                this.getUserId(),
                this.getStreet(),
                this.getNumber(),
                this.getComplement(),
                this.getNeighborhood(),
                this.getCity(),
                this.getState(),
                this.getCountry(),
                this.getPostalCode(),
                this.getLastModifiedDateTime()
        );
    }
}
