package com.PascuanSilvestre.TorqTrace.features.workshop.workshopClient;

import com.PascuanSilvestre.TorqTrace.features.user.user.UserEntity;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.WorkShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkshopClientRepository
        extends JpaRepository<WorkshopClientEntity, Long>, JpaSpecificationExecutor<WorkshopClientEntity> {

    // Buscar relación cliente-taller por user
    List<WorkshopClientEntity> findByUser(UserEntity user);

    // Buscar todos los clientes de un taller
    List<WorkshopClientEntity> findByWorkshop(Long workShopId);

    // Buscar relación específica user + workshop
    Optional<WorkshopClientEntity> findByUserAndWorkshop(UserEntity user, WorkShopEntity workshop);

    // Variante por IDs
    Optional<WorkshopClientEntity> findByUserId(Long userId);

    List<WorkshopClientEntity> findByWorkshopId(Long workshopId);

    boolean existsByUserIdAndWorkshopId(Long userId, Long workshopId);
}
