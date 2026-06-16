package com.PascuanSilvestre.TorqTrace.features.workshop.workshopStock;

import com.PascuanSilvestre.TorqTrace.common.utils.AuditableBase;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePart.SparePartEntity;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.WorkShopEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table (name="stock_workshop")
@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
public class WorkshopStockEntity extends AuditableBase {


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "workshop_id", nullable = false)
    private WorkShopEntity workshop;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "spare_part_id", nullable = false)
    private SparePartEntity sparePart;

    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity;

    @Column(name = "min_stock_alert", nullable = false)
    private Integer minStockAlert;



}
