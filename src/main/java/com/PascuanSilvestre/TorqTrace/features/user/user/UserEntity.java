package com.PascuanSilvestre.TorqTrace.features.user.user;

import ch.qos.logback.core.status.Status;
import com.PascuanSilvestre.TorqTrace.common.AddressInfo;
import com.PascuanSilvestre.TorqTrace.common.ContactInfo;
import com.PascuanSilvestre.TorqTrace.features.user.enums.UserStatus;
import com.PascuanSilvestre.TorqTrace.features.userVehicle.userVehicle.UserVehicleEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name="user")
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;
    @Column(name = "password_hash", length = 255)
    private String passwordHash;
    @Column(name="avatar_url", length = 255)
    private String avatarUrl;

    @Enumerated(EnumType.STRING)
    @Column(name="Status", nullable = false,length = 255)
    private UserStatus status;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "address", column = @Column(name = "user_address")),
            @AttributeOverride(name = "city", column = @Column(name = "user_city")),
            @AttributeOverride(name = "state", column = @Column(name = "user_state")),
            @AttributeOverride(name = "country", column = @Column(name = "user_country"))
    })
    private AddressInfo userAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "phoneNumber", column = @Column(name = "user_phone")),
            @AttributeOverride(name = "email", column = @Column(name = "user_email"))
    })
    private ContactInfo userContactInfo;

    @OneToMany(mappedBy = "user")
    private List<UserVehicleEntity> userVehicles;



}
