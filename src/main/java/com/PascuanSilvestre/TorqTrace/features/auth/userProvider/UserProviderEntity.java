package com.PascuanSilvestre.TorqTrace.features.auth.userProvider;
import com.PascuanSilvestre.TorqTrace.features.auth.authProvider.AuthProviderEntity;
import com.PascuanSilvestre.TorqTrace.features.user.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name=("user_provider"))
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserProviderEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    UserEntity user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id")
    AuthProviderEntity provider;

    @Column (name="external_id",nullable = false, length = 255)
    private String external_id;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
