package com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCategory;

import jakarta.persistence.*;

@Entity
@Table(name="spare_part_category")
public class SparePartCategoryEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
}
