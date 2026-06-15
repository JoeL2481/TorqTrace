package com.PascuanSilvestre.TorqTrace.auth.authProvider;
import com.PascuanSilvestre.TorqTrace.auth.authProvider.enums.EAuthProviders;
import com.PascuanSilvestre.TorqTrace.common.utils.AuditableBase;
import com.PascuanSilvestre.TorqTrace.auth.userProvider.UserProviderEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name="auth_provider")
@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
public class AuthProviderEntity extends AuditableBase {


    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50, unique = true)
    private EAuthProviders name;

    @Column(name = "display_name",nullable = false, length = 55)
    private String displayName;

    @OneToMany(mappedBy = "provider",fetch = FetchType.LAZY)
    List<UserProviderEntity> users;

}
