package com.PascuanSilvestre.TorqTrace.features.inventory.sparePart;

import com.PascuanSilvestre.TorqTrace.features.inventory.enums.SparePartCategory;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCompatibility.SparePartCompatibilityEntity;
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
@Table(name="spare_part")
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class SparePartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false, length = 30)
    private SparePartCategory category;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "sparePart")
    private List<SparePartCompatibilityEntity> compatibilities;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
