package com.postech.challenge_01.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "usersAdresses")
public class UserAddressEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private UserEntity user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "addressId", referencedColumnName = "id")
    private AddressEntity address;
}
