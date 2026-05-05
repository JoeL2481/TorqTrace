package com.PascuanSilvestre.TorqTrace.features.workshop.stock;

import com.PascuanSilvestre.TorqTrace.features.inventory.sparePart.SparePartEntity;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.WorkshopEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table (name="stock_workshop")
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class StockEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "workshop_id", nullable = false)
    private WorkshopEntity workshop;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "spare_part_id", nullable = false)
    private SparePartEntity sparePart;

    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity;

    @Column(name = "min_stock_alert", nullable = false)
    private Integer minStockAlert;


    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
