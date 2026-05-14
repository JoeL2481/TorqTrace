package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleBrand;

import com.PascuanSilvestre.TorqTrace.common.AuditableBase;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="vehicle_brand")
public class VehicleBrandEntity extends AuditableBase {

    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "public_id", nullable = false, unique = true, updatable = false, length = 36)
    private UUID publicId;

    @Column(name = "name", length = 255)
    private String name;


}
