package com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.engineTransmission;

import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.VehicleEntity;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.engine.EngineEntity;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.transmission.TransmissionEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="engine_transmission")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EngineTransmissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vehicle", nullable = false)
    private VehicleEntity vehicle;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "engine_id", nullable = false)
    private EngineEntity engine;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "transmission_id", nullable = false)
    private TransmissionEntity transmission;

}
