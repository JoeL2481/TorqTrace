package com.PascuanSilvestre.TorqTrace.features.auth.userProvider;
import com.PascuanSilvestre.TorqTrace.features.auth.authProvider.AuthProviderEntity;
import com.PascuanSilvestre.TorqTrace.features.user.user.UserEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name=("user_provider"))
public class UserProviderEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false)
    private AuthProviderEntity provider;
    @Column (name="external_id",nullable = false, length = 255)
    private String external_id;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
