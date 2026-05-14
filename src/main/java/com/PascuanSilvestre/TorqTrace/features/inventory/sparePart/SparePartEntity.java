package com.PascuanSilvestre.TorqTrace.features.inventory.sparePart;

import com.PascuanSilvestre.TorqTrace.common.AuditableBase;
import com.PascuanSilvestre.TorqTrace.features.inventory.enums.SparePartCategory;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCompatibility.SparePartCompatibilityEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="spare_part")
@Getter
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
public class SparePartEntity extends AuditableBase {



    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false, length = 30)
    private SparePartCategory category;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "sparePart")
    private List<SparePartCompatibilityEntity> compatibilities;


}
