package com.PascuanSilvestre.TorqTrace.features.auth.authProvider;
import com.PascuanSilvestre.TorqTrace.common.AuditableBase;
import com.PascuanSilvestre.TorqTrace.features.auth.userProvider.UserProviderEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="auth_provider")
@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
public class AuthProviderEntity extends AuditableBase {


    @Column(nullable = false, length = 50)
    private String name;
    @Column(name = "display_name",nullable = false, length = 55)
    private String displayName;
    @OneToMany(mappedBy = "provider",fetch = FetchType.LAZY)
    List<UserProviderEntity> users;

}
