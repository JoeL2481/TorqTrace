package com.PascuanSilvestre.TorqTrace.features.workshop.stock;

import com.PascuanSilvestre.TorqTrace.features.inventory.sparePart.SparePartEntity;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.WorkshopEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table (name="stock_workshop")
public class StockEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "workshop_id")
    private WorkshopEntity workshop;
    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "spare_part_id")
    private SparePartEntity sparePart;
    @Column(name = "stock_quantity",nullable = false)
    private Integer stockQuantity;
    @Column(name="min_stock",nullable = false)
    private Integer minStock;


    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
