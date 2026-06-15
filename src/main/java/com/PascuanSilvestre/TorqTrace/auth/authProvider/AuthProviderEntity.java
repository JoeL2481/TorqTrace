package com.PascuanSilvestre.TorqTrace.auth.authProvider;

import com.PascuanSilvestre.TorqTrace.auth.authProvider.enums.EAuthProviders;
import com.PascuanSilvestre.TorqTrace.auth.userProvider.UserProviderEntity;
import com.PascuanSilvestre.TorqTrace.common.utils.AuditableBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "auth_provider")
@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
public class AuthProviderEntity extends AuditableBase {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50, unique = true)
    private EAuthProviders name;

    @Column(name = "display_name", nullable = false, length = 55)
    private String displayName;

    @OneToMany(mappedBy = "provider", fetch = FetchType.LAZY)
    private List<UserProviderEntity> users;
}
