package com.PascuanSilvestre.TorqTrace.features.auth.userProvider;
import com.PascuanSilvestre.TorqTrace.common.AuditableBase;
import com.PascuanSilvestre.TorqTrace.features.auth.authProvider.AuthProviderEntity;
import com.PascuanSilvestre.TorqTrace.features.user.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name=("user_provider"))
@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
public class UserProviderEntity extends AuditableBase {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id")
    AuthProviderEntity provider;

    @Column (name="external_id",nullable = false, length = 255)
    private String externalId;


}
