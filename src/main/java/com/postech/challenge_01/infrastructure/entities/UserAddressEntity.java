package com.postech.challenge_01.infrastructure.entities;

import com.postech.challenge_01.domain.UserAddress;
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
@Table(name = "user_address")
public class UserAddressEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressEntity address;

    public static UserAddressEntity of(UserAddress userAddress) {
        UserAddressEntity entity = new UserAddressEntity();
        entity.setId(userAddress.getId());

        UserEntity userEntity = new UserEntity();
        userEntity.setId(userAddress.getUserId());

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId(userAddress.getAddressId());

        entity.setUser(userEntity);
        entity.setAddress(addressEntity);
        return entity;
    }

    public UserAddress toUserAddress() {
        return new UserAddress(
                this.id,
                this.user.getId(),
                this.address.getId()
        );
    }
}
