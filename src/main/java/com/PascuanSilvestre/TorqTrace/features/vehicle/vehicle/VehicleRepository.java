package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle;

import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.enums.VehicleBodyType;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.enums.VehicleCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {
    Optional<VehicleEntity> findByPublicId(String publicId);

    boolean existsByPublicId(String publicId);
//query larga para validar que pueda o no venir null sin obligacion pero que no este repetido en caso de que si exista toda la config en particular
    @Query("""
            select count(v) > 0
            from VehicleEntity v
            where v.vehicleBrand.id = :brandId
              and v.vehicleModel.id = :modelId
              and ((:generationId is null and v.vehicleGeneration is null) or v.vehicleGeneration.id = :generationId)
              and ((:variantId is null and v.vehicleVariant is null) or v.vehicleVariant.id = :variantId)
              and ((:equipmentLevelId is null and v.vehicleEquipmentLevel is null) or v.vehicleEquipmentLevel.id = :equipmentLevelId)
              and ((:engineId is null and v.engine is null) or v.engine.id = :engineId)
              and ((:transmissionId is null and v.transmission is null) or v.transmission.id = :transmissionId)
              and v.vehicleBodyType = :vehicleBodyType
              and v.vehicleCategory = :vehicleCategory
            """)
    boolean existsDuplicateConfiguration(
            @Param("brandId") Long brandId,
            @Param("modelId") Long modelId,
            @Param("generationId") Long generationId,
            @Param("variantId") Long variantId,
            @Param("equipmentLevelId") Long equipmentLevelId,
            @Param("engineId") Long engineId,
            @Param("transmissionId") Long transmissionId,
            @Param("vehicleBodyType") VehicleBodyType vehicleBodyType,
            @Param("vehicleCategory") VehicleCategory vehicleCategory
    );
}
