package com.PascuanSilvestre.TorqTrace.features.user.user;

import com.PascuanSilvestre.TorqTrace.common.AddressInfo;
import com.PascuanSilvestre.TorqTrace.common.AuditableBase;
import com.PascuanSilvestre.TorqTrace.common.ContactInfo;
import com.PascuanSilvestre.TorqTrace.features.auth.userProvider.UserProviderEntity;
import com.PascuanSilvestre.TorqTrace.features.user.enums.UserStatus;
import com.PascuanSilvestre.TorqTrace.features.userVehicle.userVehicle.UserVehicleEntity;
import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="user")
@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
public class UserEntity  extends AuditableBase {


    @Column(name = "public_id", nullable = false, length = 100, unique = true)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID publicId;

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

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private List<UserVehicleEntity> userVehicles;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    List<UserProviderEntity> providers;




}
