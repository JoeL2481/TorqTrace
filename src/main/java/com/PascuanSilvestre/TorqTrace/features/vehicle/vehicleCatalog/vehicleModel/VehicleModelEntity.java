package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleModel;

import com.PascuanSilvestre.TorqTrace.common.AuditableBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="vehicle_model")


public class VehicleModelEntity extends AuditableBase  {

    @Column(name = "name")
    private String name;

}
