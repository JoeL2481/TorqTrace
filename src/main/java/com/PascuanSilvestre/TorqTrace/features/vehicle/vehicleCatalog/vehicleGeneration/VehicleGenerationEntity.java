package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleGeneration;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name="vehicle_generation")
public class VehicleGenerationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
