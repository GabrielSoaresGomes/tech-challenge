package com.postech.challenge_01.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@Table(name = "usersaddresses")
public class UserAddressEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "userid", referencedColumnName = "id")
    private UserEntity user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "addressid", referencedColumnName = "id")
    private AddressEntity address;
}
