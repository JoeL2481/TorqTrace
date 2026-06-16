package com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCategory;

import com.PascuanSilvestre.TorqTrace.common.aspects.AuditableBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="spare_part_category")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SparePartCategoryEntity extends AuditableBase {
    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "description", length = 255)
    private String description;
}
