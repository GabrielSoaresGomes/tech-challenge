package com.postech.challenge_01.entities;

import com.postech.challenge_01.domains.Address;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString(exclude = "restaurant")
@Entity
public class AddressEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String number;

    @Column()
    private String complement;

    @Column(nullable = false)
    private String neighborhood;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String postalCode;

    @Column(nullable = false)
    private LocalDateTime lastModifiedDateTime;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "address")
    private RestaurantEntity restaurant;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserAddressEntity> userAddresses = new HashSet<>();;

    @PrePersist
    @PreUpdate
    public void updateLastModifiedDateTime() {
        this.lastModifiedDateTime = LocalDateTime.now();
    }

    public AddressEntity(Long id, String street, String number, String complement,
                         String neighborhood, String city, String state,
                         String country, String postalCode,
                         LocalDateTime lastModifiedDateTime) {
        this(id, street, number, complement, neighborhood, city, state, country, postalCode, lastModifiedDateTime, null, null);
    }

    public static AddressEntity of(final Address address) {
        return new AddressEntity(
                address.getId(),
                address.getStreet(),
                address.getNumber(),
                address.getComplement(),
                address.getNeighborhood(),
                address.getCity(),
                address.getState(),
                address.getCountry(),
                address.getPostalCode(),
                address.getLastModifiedDateTime(),
                null,
                null
        );
    }

    public Address toAddress() {
        return new Address(
                this.getId(),
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
