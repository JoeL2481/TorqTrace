package com.PascuanSilvestre.TorqTrace.features.auth.authProvider;
import com.PascuanSilvestre.TorqTrace.features.auth.userProvider.UserProviderEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="auth_provider")
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AuthProviderEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, length = 55)
    private String display_name;
    @OneToMany(mappedBy = "provider")
    List<UserProviderEntity> users;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

}
