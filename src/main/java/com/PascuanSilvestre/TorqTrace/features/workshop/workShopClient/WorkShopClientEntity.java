package com.PascuanSilvestre.TorqTrace.features.workshop.workShopClient;
import com.PascuanSilvestre.TorqTrace.features.user.user.UserEntity;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.WorkShopEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="client_workshop")
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class WorkShopClientEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="workshop_id", nullable = false)

    private WorkShopEntity workshop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private UserEntity user;

    @Column(name="description", columnDefinition = "TEXT" )
    private String description;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
