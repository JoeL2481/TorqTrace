package com.PascuanSilvestre.TorqTrace.features.inventory.sparePart;

import jakarta.persistence.*;

@Entity
public class SparePartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 255)
    private String name;
}
