package com.PascuanSilvestre.TorqTrace.features.inventory.sparePart;

import com.PascuanSilvestre.TorqTrace.common.aspects.AuditableBase;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCategory.SparePartCategoryEntity;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCompatibility.SparePartCompatibilityEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name="spare_part")
@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
public class SparePartEntity extends AuditableBase {



    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private SparePartCategoryEntity category;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "sparePart")
    private List<SparePartCompatibilityEntity> compatibilities;
}
