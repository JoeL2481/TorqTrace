package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleGeneration;

import com.PascuanSilvestre.TorqTrace.common.AuditableBase;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="vehicle_generation")
public class VehicleGenerationEntity  extends AuditableBase {


    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "alias")
    private String alias;

    @Column(name = "year_from", nullable = false)
    private Integer yearFrom;

    @Column(name = "month_from")
    private Integer monthFrom;

    @Column(name = "year_to")
    private Integer yearTo;

    @Column(name = "month_to")
    private Integer monthTo;


}
