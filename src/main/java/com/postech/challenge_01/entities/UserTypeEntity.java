package com.postech.challenge_01.entities;

import com.postech.challenge_01.domains.UserType;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "user_type")
public class UserTypeEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, name = "lastmodifieddatetime")
    private LocalDateTime lastModifiedDateTime;

    @OneToMany(mappedBy = "userType")
    private Set<UserEntity> users;

    @PrePersist
    @PreUpdate
    public void updateLastModifiedDateTime() {
        this.lastModifiedDateTime = LocalDateTime.now();
    }

    public UserTypeEntity(Long id, String name, LocalDateTime lastModifiedDateTime) {
        this(id, name, lastModifiedDateTime, null);
    }

    public static UserTypeEntity of(final UserType userType) {
        return new UserTypeEntity(
                userType.getId(),
                userType.getName(),
                userType.getLastModifiedDateTime(),
                null
        );
    }

    public UserType toUserType() {
        return new UserType(
                this.getId(),
                this.getName(),
                this.getLastModifiedDateTime()
        );
    }
}
