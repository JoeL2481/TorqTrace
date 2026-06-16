package com.PascuanSilvestre.TorqTrace.features.user.user;

import com.PascuanSilvestre.TorqTrace.auth.userProvider.UserProviderEntity;
import com.PascuanSilvestre.TorqTrace.common.utils.AddressInfo;
import com.PascuanSilvestre.TorqTrace.common.aspects.AuditableBase;
import com.PascuanSilvestre.TorqTrace.common.utils.ContactInfo;
import com.PascuanSilvestre.TorqTrace.features.user.enums.UserStatus;
import com.PascuanSilvestre.TorqTrace.features.userVehicle.userVehicle.UserVehicleEntity;
import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;


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

    @Column(name="avatar_url", length = 255)
    private String avatarUrl;

    @Enumerated(EnumType.STRING)
    @Column(name="Status", nullable = false,length = 255)
    private UserStatus status;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "user_street")),
            @AttributeOverride(name = "city", column = @Column(name = "user_city")),
            @AttributeOverride(name = "state", column = @Column(name = "user_state")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "user_zip_code")),
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

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserProviderEntity> providers;

}
